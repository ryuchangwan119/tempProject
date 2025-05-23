package spring.jpa.web.entity;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import spring.jpa.web.dto.BoardFileDTO;

@Entity
@Getter
@Setter
@Table(name="board_file_table")
public class BoardFileEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_file_gen")
	@SequenceGenerator(name="board_file_gen", sequenceName="board_file_seq", allocationSize=1, initialValue=1)
	private int id;
	
	@Column(length = 255, name="file_name")
	private String fileName;
	
	@Column(length = 255, name="upload_file_name")
	private String uploadFileName;
	
	@Column(updatable = false)	// 작성시간 변경 불가
	@CreationTimestamp			// 현재시간 기준으로 작성시간 설정
	private Date createTime;
	//   작성 글           업로드 파일
	// BoardEntity : BoardFileEntity     1  : N
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="board_id")
	private BoardEntity boardEntity;
	
	public static BoardFileDTO toBoardFileDTO(BoardFileEntity entity) {
		BoardFileDTO dto = new BoardFileDTO();
		dto.setId(entity.getId());
		dto.setFileName(entity.getFileName());
		dto.setUploadFileName(entity.getUploadFileName());
		dto.setCreateTime(entity.getCreateTime());
		return dto;
	}
}









