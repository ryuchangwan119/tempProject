package spring.jpa.web.entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import spring.jpa.web.dto.BoardDTO;

@Entity
@Setter
@Getter
@Table(name="board_table")
public class BoardEntity {
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_gen")
	@SequenceGenerator(name="board_gen", sequenceName="board_seq", allocationSize=1, initialValue=1)
	private int id;			// 글번호(PK, sequence로 자동 증가)
	
	@Column(nullable = false, length = 50)
	private String writer;	// 작성자
	
	@Column(nullable = false, length = 50)
	private String passwd;	// 글 비밀번호
	
	@Column(nullable = false, length = 50)
	private String title;	// 글 제목
	
	@Column(nullable = false, length = 4000)
	private String content;	// 본문
	
	@Column(columnDefinition = "number(10) default 0")	// 조회수 기본값 0
	private int readCount;	// 조회수
	
	@Column(updatable = false)	// 작성시간 변경 불가
	@CreationTimestamp			// 현재시간 기준으로 작성시간 설정
	private Date boardWriteTime;	// 글 작성 시간
	
	@Column(columnDefinition = "number(10) default 0")
	private int isFile;
	
	@OneToMany(mappedBy = "boardEntity", orphanRemoval = true, cascade = CascadeType.REMOVE, fetch=FetchType.LAZY)
	private List<BoardFileEntity> boardFileEntity = new ArrayList<>();
	
	@OneToMany(mappedBy = "boardEntity", orphanRemoval = true, cascade = CascadeType.REMOVE, fetch=FetchType.LAZY)
	private List<CommentEntity> commentEntityList = new ArrayList<>();
	
	public static BoardEntity toSaveEntity(BoardDTO dto) {
		BoardEntity entity = new BoardEntity();
		entity.setPasswd(dto.getPasswd());
		entity.setWriter(dto.getWriter());
		entity.setTitle(dto.getTitle());
		entity.setContent(dto.getContent());
		entity.setIsFile(dto.getIsFile());
		return entity;
	}
	// select * from board_file_table;
	public static BoardDTO toBoardDTO(BoardEntity entity) {
		BoardDTO dto = new BoardDTO();
		dto.setId(entity.getId());
		dto.setPasswd(entity.getPasswd());
		dto.setWriter(entity.getWriter());
		dto.setTitle(entity.getTitle());
		dto.setContent(entity.getContent());
		dto.setReadCount(entity.getReadCount());
		dto.setBoardWriteTime(entity.getBoardWriteTime());
		dto.setIsFile(entity.getIsFile());
		return dto;
	}
}


















