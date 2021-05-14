package kr.co.song.spring.configuration.servlet.handler;


import kr.co.song.spring.configuration.exception.BaseException;
import kr.co.song.spring.configuration.http.BaseResponseCode;
import kr.co.song.spring.framwork.web.bind.annotation.RequestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 스프링 에서 제공 하는 HandlerInterceptorAdapter 상속 받아 사용
 */
public class BaseHandlerInterceptor extends HandlerInterceptorAdapter {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("preHandler requestURI : {}" , request.getRequestURI());
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            logger.info("handlerMethod : {}", handlerMethod);
            RequestConfig requestConfig = handlerMethod.getMethodAnnotation(RequestConfig.class);
            //로그인 체크가 필수인 경우
            if(requestConfig != null){
                throw new BaseException(BaseResponseCode.LOGIN_EXCEPTION);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("postHandler requestURI : {}", request.getRequestURI());
    }
}
