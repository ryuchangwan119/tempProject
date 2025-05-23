package spring.jpa.web.dto;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import spring.jpa.web.entity.BoardEntity;

@Getter
@Setter
public class BoardFileDTO {
	private int id;
	private String fileName;
	private String uploadFileName;
	private Date createTime;
	private BoardEntity boardEntity;
}
