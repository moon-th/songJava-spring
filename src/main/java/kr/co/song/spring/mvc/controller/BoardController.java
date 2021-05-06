package kr.co.song.spring.mvc.controller;

import kr.co.song.spring.mvc.domain.Board;
import kr.co.song.spring.mvc.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    /**
     * 목록
     * @return
     */
    @GetMapping
    public List<Board> getList(){
        return boardService.getList();
    }

    /**
     * 상세정보
     * @param boardSeq
     * @return
     */
    @GetMapping("/{boardSeq}")
    public Board get(@PathVariable  int boardSeq){
        return boardService.get(boardSeq);
    };

    /**
     * 저장 및 수정
     * @param parameter
     */
    @GetMapping("/save")
    public int save(Board parameter){
        boardService.save(parameter);
        return parameter.getBoardSeq();
    };


    /**
     * 삭제
     * @param boardSeq
     */
    @GetMapping("/delete/{boardSeq}")
    public boolean delete(@PathVariable int boardSeq){
        Board board = boardService.get(boardSeq);
        if (board == null) {
            return false;
        }
        boardService.delete(boardSeq);
        return true;
    };
}
