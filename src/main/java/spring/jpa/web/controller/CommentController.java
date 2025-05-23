package spring.jpa.web.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;
import spring.jpa.web.dto.CommentDTO;
import spring.jpa.web.service.CommentService;

@Controller
@RequestMapping("comment/*")
public class CommentController {
	
	@Autowired
	CommentService service;
	
	@RequestMapping("save")
	public @ResponseBody List<CommentDTO> save(@Valid @ModelAttribute CommentDTO dto,
										BindingResult bindingResult) {
		
		// 작성자, 댓글이 null이거나 값이 없을경우 댓을을 로딩후 메서드가 종료(댓글 작성 메서드가 호출되지 않는다.)
		if(bindingResult.hasErrors()) {
			System.out.println("에러");
			return service.commentAllLoad(dto.getBoardId());
		}
		
		List<CommentDTO> commentLsit = new ArrayList<>();
		
		int result = service.commentWrite(dto);	// 글 작성
		
		if(result > 0) {
			commentLsit = service.commentAllLoad(dto.getBoardId());
		}
		return commentLsit;			// BoardEntity : CommentEntity = 1:N
	}
}
