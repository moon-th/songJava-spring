package kr.co.song.spring.mvc.parameter;

import kr.co.song.spring.mvc.domain.BoardType;

import java.util.Date;
import java.util.List;

/**
 * 게시물 검색
 */
public class BoardSearchParameter {

    private String keyword;
    private List<BoardType> boardTypes;

    public BoardSearchParameter() {

    }
}
