package kr.co.song.spring.mvc.parameter;

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


        public BoardParameter(String title, String contents) {
                this.title = title;
                this.contents = contents;
        }
}
