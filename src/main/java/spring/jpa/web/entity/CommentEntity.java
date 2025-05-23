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

@Entity
@Getter@Setter
@Table(name="comment_table")
public class CommentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="comment_gen")
	@SequenceGenerator(name="comment_gen", sequenceName = "comment_seq", initialValue=1, allocationSize=1)
	private int id;					// 댓글 순서 번호
	
	@Column
	private String writer;			// 작성자
	
	@Column
	private String contents;		// 댓글 내용
	
	@Column
	private int boardId;			// 글 번호
	
	@Column(updatable = false)
	@CreationTimestamp
	private Date commentSaveTime;	// 댓글 작성 시간
	
	@ManyToOne(fetch = FetchType.LAZY)	// N:1 (하나의 글에 여러개의 댓글)
	@JoinColumn(name="cBoard_id")
	private BoardEntity boardEntity;
}







