package kr.co.song.spring.configuration.web.bind.annotation;

import kr.co.song.spring.configuration.exception.BaseException;
import kr.co.song.spring.configuration.http.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

/**
 * @Controller 즉, 전역에서 발생할 수 있는 예외를 잡아 처리해주는 annotation 이다.
 *
 */
@ControllerAdvice
public class BaseControllerAdvice {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = {BaseException.class})
        @ResponseStatus(HttpStatus.OK)
        @ResponseBody
    private BaseResponse<?> handleBaseException(BaseException e, WebRequest request){
        return new BaseResponse<String>(e.getResponseCode(), messageSource.getMessage(e.getResponseCode().name(),e.getArgs(),null));
    }


}
