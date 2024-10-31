package com.edj.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.edj.common.expcetions.BadRequestException;
import com.edj.common.utils.EnumUtils;
import com.edj.user.domain.entity.EdjUser;
import com.edj.user.enums.EdjUserStatus;
import com.edj.user.mapper.EdjUserMapper;
import com.edj.user.service.EdjUserService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * edj_user
 *
 * @author A.E.
 * @date 2024/10/5
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EdjUserServiceImpl extends MPJBaseServiceImpl<EdjUserMapper, EdjUser> implements EdjUserService {

    public EdjUser selectByUsername(String username) {

        LambdaQueryWrapper<EdjUser> queryWrapper = new LambdaQueryWrapper<EdjUser>()
                .eq(EdjUser::getUsername, username);

        EdjUser user = baseMapper.selectOne(queryWrapper);

        if (user == null) {
            throw new BadRequestException("用户名未找到");
        }

        if (EnumUtils.ne(EdjUserStatus.NORMAL, user.getStatus())) {
            log.debug("用户被冻结，冻结原因：{}", user.getRemark());
            throw new BadRequestException("用户被冻结");
        }

        return user;
    }

    public Runnable RecordLoginInfo(Long id, String loginIp) {
        return () -> {
            // 更新用户登录ip | 登录时间
            LambdaUpdateWrapper<EdjUser> updateWrapper = new LambdaUpdateWrapper<EdjUser>()
                    .eq(EdjUser::getId, id)
                    .set(EdjUser::getLoginIp, loginIp)
                    .set(EdjUser::getLoginTime, LocalDateTime.now());


            baseMapper.update(new EdjUser(), updateWrapper);
        };
    }

    @Override
    public String selectUsernameByOpenId(String openId) {

        LambdaQueryWrapper<EdjUser> wrapper = new LambdaQueryWrapper<EdjUser>()
                .select(EdjUser::getUsername, EdjUser::getStatus)
                .eq(EdjUser::getOpenId, openId);

        EdjUser user = baseMapper.selectOne(wrapper);

        // 如果未找到用户，返回注册
        if (user == null) {
            return null;
        }

        if (EnumUtils.ne(EdjUserStatus.NORMAL, user.getStatus())) {
            log.debug("微信用户被冻结，冻结原因：{}", user.getRemark());
            throw new BadRequestException("用户被冻结");
        }

        return user.getUsername();
    }

    @Override
    public String selectUsernameByPhone(String phone) {
        LambdaQueryWrapper<EdjUser> wrapper = new LambdaQueryWrapper<EdjUser>()
                .select(EdjUser::getUsername, EdjUser::getStatus)
                .eq(EdjUser::getPhoneNumber, phone);

        EdjUser user = baseMapper.selectOne(wrapper);

        // 如果未找到用户，返回注册
        if (user == null) {
            return null;
        }

        if (EnumUtils.ne(EdjUserStatus.NORMAL, user.getStatus())) {
            log.debug("手机号用户被冻结，冻结原因：{}", user.getRemark());
            throw new BadRequestException("用户被冻结");
        }

        return user.getUsername();
    }
}




