package com.edj.thirdparty.ali;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.edj.common.utils.DateUtils;
import com.edj.common.utils.IdUtils;
import com.edj.common.utils.ObjectUtils;
import com.edj.thirdparty.ali.properties.AliOssProperties;
import com.edj.thirdparty.core.storage.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDate;

import static com.edj.common.utils.DateUtils.DEFAULT_DAY_FORMAT_SLASH;

/**
 * ali oss
 *
 * @author A.E.
 * @date 2024/9/23
 */
@Slf4j
@Service
@ConditionalOnBean(AliOssProperties.class)
@RequiredArgsConstructor
public class AliOssStorageServiceImpl implements StorageService {

    private final AliOssProperties aliOssProperties;

    /**
     * 文件上传
     *
     * @param extension   文件拓展名
     * @param inputStream 文件流
     * @return 文件访问路径
     */
    @Override
    public String upload(String extension, InputStream inputStream) {
        String endpoint = aliOssProperties.getEndpoint();
        String accessKeyId = aliOssProperties.getAccessKeyId();
        String accessKeySecret = aliOssProperties.getAccessKeySecret();
        String bucketName = aliOssProperties.getBucketName();
        String basePath = aliOssProperties.getBasePath();

        String datePath = DateUtils.format(LocalDate.now(), DEFAULT_DAY_FORMAT_SLASH);
        String fileName = String.format("%s/%s/%s%s", basePath, datePath, IdUtils.randomUUID(), extension);

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        StringBuilder URI = new StringBuilder("https://");

        try {
            // 创建PutObject请求。
            ossClient.putObject(bucketName, fileName, inputStream);

            // 文件访问路径规则 https://BucketName.Endpoint/ObjectName
            URI.append(bucketName)
                    .append(".")
                    .append(endpoint)
                    .append("/")
                    .append(fileName);
        } catch (OSSException oe) {
            log.error("阿里OSS异常，Error Message:{}", oe.getErrorMessage());
        } catch (ClientException ce) {
            log.error("阿里OSS Client异常，Error Message:{}", ce.getMessage());
        } finally {
            if (ObjectUtils.isNotEmpty(ossClient)) {
                ossClient.shutdown();
            }
        }

        return URI.toString();
    }
}
