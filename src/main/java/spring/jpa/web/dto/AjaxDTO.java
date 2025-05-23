package spring.jpa.web.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AjaxDTO {
	private String msg;
	private String extra;
	
	@Override
	public String toString() {
		return "AjaxDTO[msg : " + msg + ", extra : " + extra + "]";
	}
	
	
}
