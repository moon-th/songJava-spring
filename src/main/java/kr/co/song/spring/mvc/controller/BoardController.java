package kr.co.song.spring.mvc.controller;

import io.swagger.annotations.*;
import kr.co.song.spring.configuration.exception.BaseException;
import kr.co.song.spring.configuration.http.BaseResponse;
import kr.co.song.spring.configuration.http.BaseResponseCode;
import kr.co.song.spring.framwork.web.bind.annotation.RequestConfig;
import kr.co.song.spring.mvc.domain.Board;
import kr.co.song.spring.mvc.domain.MySQLPageRequest;
import kr.co.song.spring.mvc.domain.PageRequestParameter;
import kr.co.song.spring.mvc.parameter.BoardParameter;
import kr.co.song.spring.mvc.parameter.BoardSearchParameter;
import kr.co.song.spring.mvc.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@Api(tags = "게시판 API") //Swagger 문서 태그
public class BoardController {

    private final BoardService boardService;

    Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 목록
     * @return
     */
    @GetMapping
    @ApiOperation(value = "목록 조회", notes="게시물 전체 목록 조회.")
    public BaseResponse<List<Board>>  getList(
            @ApiParam BoardSearchParameter parameter,
            @ApiParam MySQLPageRequest pageRequest
            ){
        logger.info("pageRequest: {}",pageRequest);
        PageRequestParameter<BoardSearchParameter> pageRequestParameter = new PageRequestParameter<>(pageRequest, parameter);
        return new BaseResponse(boardService.getList(pageRequestParameter));
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
    @RequestConfig // 해당 어노테이션을 사용하여 로그인 필수 체크 실행
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
     * 대용량 처리 등록 1 ( 반복문 사용 )
     * @return
     */
    @ApiOperation(value="대용량 등록처리1",notes = "대용량 등록처리1")
    @PutMapping("/saveList1")
    public BaseResponse<Boolean> saveList1() {
        int count = 0;
        //테스트를 위한 랜덤 1000건 데이터를 생성
        List<BoardParameter> list = new ArrayList<BoardParameter>();
        while(true){
            count++;
            String title = RandomStringUtils.randomAlphabetic(10);
            String contents = RandomStringUtils.randomAlphabetic(10);
            list.add(new BoardParameter(title, contents));
            if(count >= 1000){
                break;
            }
        }
        long start = System.currentTimeMillis();
        boardService.saveList1(list);
        long end = System.currentTimeMillis();
        logger.info("실행시간 : {}", (end - start) / 1000.0);
        return new BaseResponse<Boolean>(true);
    }

    /**
     * 대용량 처리 등록 2 ( Mybatis froEach 문 사용  )
     * @return
     */
    @ApiOperation(value="대용량 등록처리2",notes = "대용량 등록처리2")
    @PutMapping("/saveList2")
    public BaseResponse<Boolean> saveList2() {
        int count = 0;
        //테스트를 위한 랜덤 1000건 데이터를 생성
        List<BoardParameter> list = new ArrayList<BoardParameter>();
        while(true){
            count++;
            String title = RandomStringUtils.randomAlphabetic(10);
            String contents = RandomStringUtils.randomAlphabetic(10);
            list.add(new BoardParameter(title, contents));
            if(count >= 1000){
                break;
            }
        }
        long start = System.currentTimeMillis();
        boardService.saveList2(list);
        long end = System.currentTimeMillis();
        logger.info("실행시간 : {}", (end - start) / 1000.0);
        return new BaseResponse<Boolean>(true);
    }


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
