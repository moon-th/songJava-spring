package kr.co.song.spring.mvc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    private int boardSeq;
    private BoardType boardType;
    private String title;
    private String contents;
    private Date regDate;


}
