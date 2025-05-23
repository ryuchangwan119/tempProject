package spring.jpa.web.service;
import java.util.List;
import org.springframework.stereotype.Service;
import spring.jpa.web.dto.MemberDTO;

@Service
public interface MemberService {
	
	public int memberAdd(MemberDTO dto);
	
//	public boolean memberLogin(String id, String passwd);
	public int memberLogin(String id, String passwd);
	public List<MemberDTO> memberList();
	public MemberDTO memberDetail(int num);
	public MemberDTO memberUpdateRead(String id);
	public int memberUpdate(MemberDTO dto);
	public int memberDelete(String id, String passwd);
	public boolean checkIdDuplicate(String id);
}
