package kr.co.song.spring.mvc.service;

import kr.co.song.spring.mvc.domain.Board;
import kr.co.song.spring.mvc.parameter.BoardParameter;
import kr.co.song.spring.mvc.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository repository;


    /**
     * 목록
     * @return
     */
    public List<Board> getList(){
        return repository.getList();
    }

    /**
     * 상세정보
     * @param boardSeq
     * @return
     */
    public Board get(int boardSeq){
        return repository.get(boardSeq);
    };

    /**
     * 저장
     * @param parameter
     */
    public void save(BoardParameter parameter){
        // 조회하여 리턴된 정보
        Board board = repository.get(parameter.getBoardSeq());
        if(board == null){
            repository.save(parameter);
        }else{
            repository.update(parameter);
        }
    };

    /**
     * 삭제
     * @param boardSeq
     */
    public void delete(int boardSeq){
        repository.delete(boardSeq);
    };

    /**
     * 단순 반복문을 이용한 등록 처리.
     * @param list
     */
    public void saveList1(List<BoardParameter> list){
        for (BoardParameter boardParameter : list) {
            repository.save(boardParameter);
        }
    }

    /**
     * 100개씩 배열에 담아서 일괄 등록 처리.
     * @param list
     */
    public void saveList2(List<BoardParameter> list){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("boardList", list);
        repository.saveList(paramMap);
    }


}
