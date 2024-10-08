package com.edj.mvc.handler;

import com.edj.common.domain.CurrentUserInfo;
import com.edj.common.handler.UserInfoHandler;
import com.edj.mvc.utils.UserContext;
import org.springframework.stereotype.Component;

@Component
public class UserInfoHandlerImpl implements UserInfoHandler {
    @Override
    public CurrentUserInfo currentUserInfo() {
        return UserContext.currentUser();
    }
}
