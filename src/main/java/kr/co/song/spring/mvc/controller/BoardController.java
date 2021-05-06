package kr.co.song.spring.mvc.controller;

import io.swagger.annotations.*;
import kr.co.song.spring.configuration.exception.BaseException;
import kr.co.song.spring.configuration.http.BaseResponse;
import kr.co.song.spring.configuration.http.BaseResponseCode;
import kr.co.song.spring.mvc.domain.Board;
import kr.co.song.spring.mvc.parameter.BoardParameter;
import kr.co.song.spring.mvc.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@Api(tags = "게시판 API") //Swagger 문서 태그
public class BoardController {

    private final BoardService boardService;

    /**
     * 목록
     * @return
     */
    @GetMapping
    @ApiOperation(value = "목록 조회", notes="게시물 전체 목록 조회.")
    public BaseResponse<List<Board>>  getList(){
        return new BaseResponse(boardService.getList());
    }

    /**
     * 상세정보
     * @param boardSeq
     * @return
     */
    @GetMapping("/{boardSeq}")
    @ApiOperation(value = "상세 조회", notes="게시물 번호에 해당하는 상세 정보를 조회할 수 있습니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardSeq",value = "게시물 번호",example = "1")
    })
    public BaseResponse<Board> get(@PathVariable("boardSeq")  int boardSeq){
        Board board = boardService.get(boardSeq);
        //null 처리
        if(board == null){
            throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] {"게시물"});
        }
        return new BaseResponse(boardService.get(boardSeq));
    };

    /**
     * 저장 및 수정
     * @param parameter
     */
    @PutMapping("/save")
    @ApiOperation(value = "등록 / 수정 처리", notes="신규 게시물 저장 및 기존 게시물 수정할 수 있습니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardSeq",value = "게시물 번호",example = "1"),
            @ApiImplicitParam(name = "title",value = "제목",example = "spring"),
            @ApiImplicitParam(name = "contents",value = "내용",example = "spring 강의")
    })
    public BaseResponse<Integer> save(BoardParameter parameter){
        // 제목 필수 체크
        if(StringUtils.isEmpty(parameter.getTitle())){
            throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] {"title","제목"});
        }
        // 내용 필수 체크
        if(StringUtils.isEmpty(parameter.getContents())){
            throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] {"contents","내용"});
        }

        boardService.save(parameter);
        return new BaseResponse(parameter.getBoardSeq());
    };


    /**
     * 삭제
     * @param boardSeq
     */
    @DeleteMapping("/{boardSeq}")
    @ApiOperation(value = "삭제 처리", notes="게시물 번호에 해당하는 정보를 삭제 합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardSeq",value = "게시물 번호",example = "1")
    })
    public BaseResponse<Boolean> delete(@PathVariable("boardSeq") int boardSeq){
        Board board = boardService.get(boardSeq);
        if (board == null) {
            return new BaseResponse(false);
        }
        boardService.delete(boardSeq);
        return new BaseResponse(true);
    };
}
