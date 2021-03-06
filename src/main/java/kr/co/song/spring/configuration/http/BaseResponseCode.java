package kr.co.song.spring.configuration.http;


public enum BaseResponseCode {
    SUCCESS, // 성공
    ERROR, // 실패
    DATA_IS_NULL, //null 처리
    VALIDATE_REQUIRED, // 필수값 체크
    LOGIN_EXCEPTION, // 로그인 체크
    UPLOAD_FILE_IS_NULL //파일 체크
    ;
}
