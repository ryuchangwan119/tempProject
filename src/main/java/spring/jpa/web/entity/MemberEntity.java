package spring.jpa.web.entity;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.jpa.web.dto.MemberDTO;

@Getter @Setter
@Entity
@Table(name="member_table")
@NoArgsConstructor	// 기본 생성자 생성
public class MemberEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="member_gen")
	@SequenceGenerator(name="member_gen", sequenceName = "member_seq", 
	initialValue=1, allocationSize=1)
	private int num;	// PK
	
	// VARCHAR2(50) , 중복 허용 X
	@Column(length=50, unique=true)
	private String id;
	
	@Column(length=50)
	private String passwd;
	
	@Column(length=20)
	private String name;
	
	@Column(length=50)
	private String email;
	
	@Column(updatable=false, columnDefinition="DATE DEFAULT SYSDATE")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date regdate;
	
	// MemberDTO를 매개변수로 받아 Entity로 변환하는 정적 메서드(객체없이 클래스로 호출 가능)
	public static MemberEntity toMemberEntity(MemberDTO dto) {
		MemberEntity entity = new MemberEntity();
		entity.setId(dto.getId());
		entity.setPasswd(dto.getPasswd());
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		return entity;
	}
	
	public static MemberDTO toMemberDTO(MemberEntity entity) {
		MemberDTO dto = new MemberDTO();
		dto.setNum(entity.getNum());
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setEmail(entity.getEmail());
		dto.setRegdate(entity.getRegdate());
		return dto;
	}
}















