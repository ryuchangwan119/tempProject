package spring.jpa.web.dto;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberDTO {

	private int num;
	private String id;
	private String passwd;
	private String name;
	private String email;
	private Date regdate;
}
