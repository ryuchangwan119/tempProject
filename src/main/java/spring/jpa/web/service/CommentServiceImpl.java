package spring.jpa.web.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.jpa.web.dto.CommentDTO;
import spring.jpa.web.entity.BoardEntity;
import spring.jpa.web.entity.CommentEntity;
import spring.jpa.web.repository.BoardRepository;
import spring.jpa.web.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	BoardRepository bRepository;
	
	@Autowired
	CommentRepository cRepository;

	@Override
	public int commentWrite(CommentDTO dto) {
		int result = 0;

		// 해당글이 있는지 확인
		Optional<BoardEntity> OptionalBoardEntity = bRepository.findById(dto.getBoardId());
		
		CommentEntity commentEntty = new CommentEntity();
		if(OptionalBoardEntity.isPresent()) {	// 해당 글이 있으면
			// OptionalBoardEntity에서 Entity를 BoardEntity로 대입
			BoardEntity boardEntity = OptionalBoardEntity.get();
			
			// DTO를 Entity로 변환
			commentEntty.setWriter(dto.getWriter());
			commentEntty.setContents(dto.getContents());
			commentEntty.setCommentSaveTime(new Date());
			commentEntty.setBoardEntity(boardEntity);
			cRepository.save(commentEntty);
			result = 1;
		}
		return result;
	}

	@Override
	public List<CommentDTO> commentAllLoad(int id) {
		
		Optional<BoardEntity> boardEntity = bRepository.findById(id);
		BoardEntity entity = boardEntity.get();
		
		List<CommentEntity> commentListEntity = cRepository.findAllByBoardEntityOrderByIdDesc(entity);
		List<CommentDTO> commentListDTO = new ArrayList<>();
		
		for(CommentEntity commentEntity : commentListEntity) {
			CommentDTO dto = new CommentDTO();
			dto.setId(commentEntity.getId());
			dto.setWriter(commentEntity.getWriter());
			dto.setContents(commentEntity.getContents());
			dto.setCommentSaveTime(commentEntity.getCommentSaveTime());
			dto.setBoardId(commentEntity.getBoardEntity().getId());
			commentListDTO.add(dto);
		}
		return commentListDTO;
	}
}

















