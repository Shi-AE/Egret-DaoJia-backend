package com.edj.mvc.config;

import com.edj.mvc.filter.PackResultFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author A.E.
 * @date 2024/9/20
 */
@Configuration
@Import(PackResultFilter.class)
public class FilterConfiguration {
}
