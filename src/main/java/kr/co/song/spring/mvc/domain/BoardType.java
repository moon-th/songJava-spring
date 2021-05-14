package kr.co.song.spring.mvc.domain;

public enum BoardType implements BaseCodeLabelEnum{

    NOTICE("공지사항"),
    FAQ("자주묻는질문"),
    INQUIRY("1:1문의")
    ;
    private String code;
    private String label;

    BoardType(String label) {
        this.code = name();  // 기본 적으로 enum 에서 제공하는 name() 클래스 사용 시 선언한 프로퍼티 들을 사용할 수 있다.
        this.label = label;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String label() {
        return label;
    }
}
