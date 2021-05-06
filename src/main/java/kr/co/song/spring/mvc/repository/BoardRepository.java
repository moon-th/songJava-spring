package kr.co.song.spring.mvc.repository;

import kr.co.song.spring.mvc.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository {

    List<Board> getList();

    Board get(int boardSeq);

    void save(Board board);

    void update(Board board);

    void delete(int boardSeq);
}
