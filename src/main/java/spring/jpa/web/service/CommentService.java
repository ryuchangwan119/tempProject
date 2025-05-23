package spring.jpa.web.service;
import java.util.List;

import spring.jpa.web.dto.CommentDTO;

public interface CommentService {
	
	public int commentWrite(CommentDTO dto);		// 댓글 작성 메서드
	public List<CommentDTO> commentAllLoad(int id);	// 해당 글에 대한 모든 댓글을 리턴
}
