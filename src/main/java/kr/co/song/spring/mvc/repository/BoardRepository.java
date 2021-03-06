package kr.co.song.spring.mvc.repository;

import kr.co.song.spring.mvc.domain.Board;
import kr.co.song.spring.mvc.domain.PageRequestParameter;
import kr.co.song.spring.mvc.parameter.BoardParameter;
import kr.co.song.spring.mvc.parameter.BoardSearchParameter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BoardRepository {

    List<Board> getList(PageRequestParameter pageRequestParameter);

    Board get(int boardSeq);

    void save(BoardParameter board);
    void saveList(Map<String, Object> paramMap);

    void update(BoardParameter board);

    void delete(int boardSeq);
}
