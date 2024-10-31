package com.edj.publics.service.impl;

import com.edj.common.expcetions.BadRequestException;
import com.edj.common.utils.ObjectUtils;
import com.edj.common.utils.RandomUtils;
import com.edj.common.utils.StringUtils;
import com.edj.common.utils.ValidUtils;
import com.edj.publics.service.SmsCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import static com.edj.common.constants.SmsConstants.RedisKey.PHONE_CODE_VERIFY_KEY;
import static com.edj.common.constants.SmsConstants.Timeout.PHONE_CODE_VERIFY_TIMEOUT;

/**
 * 验证码服务实现
 *
 * @author A.E.
 * @date 2024/10/31
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SmsCodeServiceImpl implements SmsCodeService {

    private final static int codeLength = 6;

    private final StringRedisTemplate redisTemplate;

    @Override
    public void smsCodeSend(String phoneNumber) {
        // 检验手机号
        if (StringUtils.isBlank(phoneNumber) || !ValidUtils.isMobile(phoneNumber)) {
            throw new BadRequestException("手机号格式不正确");
        }

        // 生成key
        String redisKey = String.format(PHONE_CODE_VERIFY_KEY, phoneNumber);
        log.debug("redisKey:{}", redisKey);

        // 生成验证码
        String code = RandomUtils.randomNumbers(codeLength);
        log.debug("code:{}", code);

        // todo 发送短信

        redisTemplate.opsForValue().set(redisKey, code, PHONE_CODE_VERIFY_TIMEOUT);
    }

    @Override
    public boolean verify(String phone, String verifyCode) {
        // 获取
        String redisKey = String.format(PHONE_CODE_VERIFY_KEY, phone);
        String code = redisTemplate.opsForValue().get(redisKey);

        // 验证
        boolean equal = ObjectUtils.equal(code, verifyCode);

        // 删除验证码
        if (equal) {
            redisTemplate.delete(redisKey);
        }
        return equal;
    }
}
