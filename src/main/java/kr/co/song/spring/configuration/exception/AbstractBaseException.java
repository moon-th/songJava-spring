package kr.co.song.spring.configuration.exception;

import kr.co.song.spring.configuration.http.BaseResponseCode;

/**
 * 예외처리를 하기 위해 RuntimeException 을 상속받아 줍니다.
 * 예외처리에 사용 하기 위해 RuntimeException 상속 받아 준다.
 */
public abstract class AbstractBaseException extends RuntimeException {

    private static final long serialVersionUID = 8342235231880246631L;

    //만들어둔 예외 코드사용을 위해 선언
    protected BaseResponseCode responseCode;

    //message 에 사용할 매개변수를 담을 배열 선언
    protected Object[] args;

    public AbstractBaseException() {
    }

    public AbstractBaseException(BaseResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public BaseResponseCode getResponseCode() {
        return responseCode;
    }

    public Object[] getArgs() {
        return args;
    }


}
