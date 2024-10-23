package com.edj.foundations.service.impl;

import com.edj.foundations.domain.dto.ServeAddDTO;
import com.edj.foundations.domain.entity.EdjServe;
import com.edj.foundations.mapper.EdjServeMapper;
import com.edj.foundations.service.EdjServeService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 针对表【edj_serve(服务表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/10/23
 */
@Service
public class EdjServeServiceImpl extends MPJBaseServiceImpl<EdjServeMapper, EdjServe> implements EdjServeService {

    @Override
    @Transactional
    public void add(List<ServeAddDTO> serveAddDTOList) {
    }
}