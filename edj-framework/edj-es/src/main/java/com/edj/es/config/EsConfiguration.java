package com.edj.es.config;

import cn.hutool.core.date.DatePattern;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.edj.es.core.impl.ElasticSearchTemplateImpl;
import com.edj.es.core.operations.impl.DefaultDocumentOperations;
import com.edj.es.core.operations.impl.DefaultIndexOperations;
import com.edj.es.properties.EsProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * es 配置类
 *
 * @author A.E.
 * @date 2024/12/8
 */
@Configuration
@Import({ElasticSearchTemplateImpl.class, DefaultDocumentOperations.class, DefaultIndexOperations.class})
@EnableConfigurationProperties(EsProperties.class)
public class EsConfiguration {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN);
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
        MAPPER.registerModule(javaTimeModule);
    }

    @Bean
    public ElasticsearchClient elasticsearchClient(EsProperties esProperties) {
        // 设置身份信息
        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                AuthScope.ANY,
                new UsernamePasswordCredentials(
                        esProperties.getUsername(),
                        esProperties.getPassword()
                )
        );

        RestClient restClient = RestClient
                .builder(new HttpHost(esProperties.getHost(), esProperties.getPort()))
                .setHttpClientConfigCallback(httpClientBuilder ->
                        httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider))
                .build();
        // Create the transport with a Jackson mapper
        // 使用自定义json序列化
        JacksonJsonpMapper jacksonJsonpMapper = new JacksonJsonpMapper(MAPPER);

        ElasticsearchTransport transport = new RestClientTransport(restClient, jacksonJsonpMapper);
        // And create the API client
        return new ElasticsearchClient(transport);
    }
}
