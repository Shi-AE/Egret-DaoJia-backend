package com.edj.mvc.handler;

import cn.hutool.core.util.IdUtil;
import com.edj.common.constants.HeaderConstants;
import com.edj.common.domain.CurrentUserInfo;
import com.edj.common.handler.RequestIdHandler;
import com.edj.common.utils.StringUtils;
import com.edj.mvc.utils.RequestUtils;
import com.edj.mvc.utils.UserContext;
import org.springframework.stereotype.Component;

/**
 * @author A.E.
 * @date 2024/9/20
 */
@Component
public class RequestIdHandlerImpl implements RequestIdHandler {
    @Override
    public String getRequestId() {
        // 从请求header头中获取请求id,获取不到id，生成新的请求id
        CurrentUserInfo currentUserInfo = UserContext.currentUser();
        if (currentUserInfo == null) {
            return null;
        }
        String requestId = RequestUtils.getValueFromHeader(HeaderConstants.REQUEST_ID);
        if (StringUtils.isEmpty(requestId)) {
            return IdUtil.getSnowflakeNextIdStr();
        } else {
            return requestId;
        }
    }
}
