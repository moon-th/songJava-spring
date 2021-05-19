package kr.co.song.spring.configuration.exception;

import kr.co.song.spring.configuration.http.BaseResponseCode;

/**
 * 예외처리 사항을 전달해 줄 클래스
 */
public class BaseException extends AbstractBaseException {

    private static final long serialVersionUID = 8342235231880246631L;

    public BaseException() {
    }

    public BaseException(BaseResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public BaseException(BaseResponseCode responseCode, String[] args) {
        this.responseCode = responseCode;
        this.args = args;
    }

}