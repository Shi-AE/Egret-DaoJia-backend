package com.edj.user.service.impl;


import com.edj.user.domain.entity.EdjAuthority;
import com.edj.user.mapper.EdjAuthorityMapper;
import com.edj.user.service.EdjAuthorityService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_authority(权限信息表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/10/5
 */
@Service
public class EdjAuthorityServiceImpl extends MPJBaseServiceImpl<EdjAuthorityMapper, EdjAuthority> implements EdjAuthorityService {
}




