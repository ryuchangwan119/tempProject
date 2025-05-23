package spring.jpa.web.dto;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentDTO {
	private int id;					// 댓글 순서 번호
	
	@NotBlank(message = "작성자 입력")	// null 또는 "" 값이 없을경우 에러로 처리
	private String writer;			// 작성자

	@NotBlank(message = "댓글 입력")	// null 또는 "" 값이 없을경우 에러로 처리
	private String contents;		// 댓글 내용

	private int boardId;			// 글 번호
	private Date commentSaveTime;	// 댓글 작성 시간
}
