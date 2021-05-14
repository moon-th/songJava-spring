package kr.co.song.spring.framwork.data.web;


import kr.co.song.spring.mvc.domain.MySQLPageRequest;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class MySQLPageRequestHandleMethodArgumentResolver implements HandlerMethodArgumentResolver{

    final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String DEFAULT_PARAMETER_PAGE = "page";
    private static final String DEFAULT_PARAMETER_SIZE = "size";
    private static final int DEFAULT_SIZE = 20;


    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return MySQLPageRequest.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
        //현재 페이지
        int page = NumberUtils.toInt(request.getParameter(DEFAULT_PARAMETER_PAGE),1);
        //리스트 갯수
        int offset = NumberUtils.toInt(request.getParameter(DEFAULT_PARAMETER_SIZE),DEFAULT_SIZE);
        //시작지점
        int limit = (offset * page) - offset;
        logger.info("page : {}", page);
        logger.info("limit : {} , offset : {}",limit,offset);
        return new MySQLPageRequest(page, offset, limit, offset);
    }
}