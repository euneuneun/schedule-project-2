package com.schedule_project_2.common.interceptor;

import com.schedule_project_2.common.SessionConst;
import com.schedule_project_2.common.exception.BusinessException;
import com.schedule_project_2.common.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute(SessionConst.LOGIN_USER) == null){
            throw new BusinessException(ErrorCode.LOGIN_REQUIRED);
        }
        return true;
    }
}
