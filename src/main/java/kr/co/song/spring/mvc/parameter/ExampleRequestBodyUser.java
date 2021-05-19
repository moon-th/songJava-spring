package kr.co.song.spring.mvc.parameter;

import kr.co.song.spring.mvc.domain.ExampleUser;
import lombok.Data;

@Data
public class ExampleRequestBodyUser {

    private ExampleUser user;

}
