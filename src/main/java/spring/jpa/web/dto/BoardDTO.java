package spring.jpa.web.dto;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
	private int id;			// 글번호(sequence로 자동 증가)
	private String writer;	// 작성자
	private String passwd;	// 글 비밀번호
	private String title;	// 글 제목
	private String content;	// 본문
	private int readCount;	// 조회수
	private Date boardWriteTime;	// 글 작성 시간
	private List<MultipartFile> boardFile;	// 다중 업로드(List)
	private String originalFileName;	// 원본 파일명
	private String uploadFileName;		// 업로드 파일명
	private int isFile;					// 파일 유/무
}
