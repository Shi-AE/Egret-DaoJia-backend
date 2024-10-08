package com.edj.thirdparty.core.storage;

import java.io.InputStream;

/**
 * @author A.E.
 * @date 2024/9/23
 */
public interface StorageService {
    /**
     * 文件上传
     *
     * @param extension   文件拓展名
     * @param inputStream 文件流
     * @return 文件访问路径
     */
    String upload(String extension, InputStream inputStream);
}
