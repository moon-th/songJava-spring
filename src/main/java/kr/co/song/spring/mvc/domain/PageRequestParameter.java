package kr.co.song.spring.mvc.domain;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class PageRequestParameter<T> {

    private MySQLPageRequest pageRequest;
    private T parameter;

    public PageRequestParameter(MySQLPageRequest pageRequest, T parameter) {
        this.pageRequest = pageRequest;
        this.parameter = parameter;
    }
}
