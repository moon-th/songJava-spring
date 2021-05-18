package kr.co.song.spring.mvc.parameter;

import kr.co.song.spring.mvc.domain.BoardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardParameter {

        private int boardSeq;
        private String title;
        private String contents;
        private BoardType boardType;
        private boolean delYn;


        public BoardParameter(String title, String contents) {
                this.title = title;
                this.contents = contents;
        }
}
