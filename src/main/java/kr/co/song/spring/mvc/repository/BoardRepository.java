package kr.co.song.spring.mvc.repository;

import kr.co.song.spring.mvc.domain.Board;
import kr.co.song.spring.mvc.parameter.BoardParameter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository {

    List<Board> getList();

    Board get(int boardSeq);

    void save(BoardParameter board);

    void update(BoardParameter board);

    void delete(int boardSeq);
}
