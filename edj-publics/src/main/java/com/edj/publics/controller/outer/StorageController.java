package com.edj.publics.controller.outer;

import com.edj.common.domain.dto.StorageUploadResDTO;
import com.edj.common.expcetions.BadRequestException;
import com.edj.common.utils.StringUtils;
import com.edj.thirdparty.core.storage.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * 储存相关接口
 *
 * @author A.E.
 * @date 2024/10/13
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/storage")
@Tag(name = "存储相关接口")
public class StorageController {

    private final StorageService storageService;

    @Operation(summary = "文件上传")
    @PostMapping("/upload")
    public StorageUploadResDTO upload(@RequestPart("file") MultipartFile file) {
        //获得文件扩展名
        String filename = file.getOriginalFilename();
        if (StringUtils.isBlank(filename)) {
            log.debug("文件名为空");
            throw new BadRequestException("文件名为空");
        }
        log.debug("获取文件名 filename:{}", filename);
        String extension = filename.substring(filename.lastIndexOf("."));
        String url = null;
        try {
            url = storageService.upload(extension, new ByteArrayInputStream(file.getBytes()));
        } catch (IOException e) {
            log.error("文件 filename: {} 上传失败,原因：", filename, e);
        }
        return new StorageUploadResDTO(url);
    }
}
