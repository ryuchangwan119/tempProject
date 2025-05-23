package spring.jpa.web.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.jpa.web.dto.MemberDTO;
import spring.jpa.web.entity.MemberEntity;
import spring.jpa.web.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberRepository repository;

	@Override
	public int memberAdd(MemberDTO dto) {
		int result = 0;
		
		try {
			// MemberEntity 클래스에서 만든 MemberDTO를 MemberEntity로 변환하는 메서드 호출
			MemberEntity entity = MemberEntity.toMemberEntity(dto);
			repository.save(entity);
			result = 1;
		} catch(Exception e) {
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

//	@Override
//	public boolean memberLogin(String id, String passwd) {
//		boolean result = false;
//		
//		MemberEntity entity = repository.findByIdAndPasswd(id, passwd);
//		
//		if(entity != null) {
//			result = true;
//		}
//		
//		return result;
//	}
	

	@Override
	public int memberLogin(String id, String passwd) {
		int result = 0;
		// 아이디와 비밀번호가 일치하면 1을 리턴, 없으면 0을 리턴
		result = repository.countByIdAndPasswd(id, passwd);
		
		return result;
	}

	@Override
	public List<MemberDTO> memberList() {
		List<MemberEntity> memberList = repository.findAll();
		List<MemberDTO> dtoList = new ArrayList<>();
		
		for(MemberEntity entity : memberList) {
			MemberDTO dto = new MemberDTO();
			dto.setNum(entity.getNum());
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setEmail(entity.getEmail());
			dto.setPasswd(entity.getPasswd());
			dto.setRegdate(entity.getRegdate());
			dtoList.add(dto);
		}
		
		return dtoList;
	}

	@Override
	public MemberDTO memberDetail(int num) {
		// 회원번호를 사용하여 MemberEntity를 리턴
		MemberEntity entity = repository.findByNum(num);
		
		// 회원번호를 사용하여 검색한 MemberEntity를 MemberDTO로 변환
		MemberDTO dto = MemberEntity.toMemberDTO(entity);
		
		return dto;
	}
	
	@Override
	public MemberDTO memberUpdateRead(String id) {
		MemberEntity entity = repository.findById(id);
		MemberDTO dto = MemberEntity.toMemberDTO(entity);
		return dto;
	}

	@Override
	public int memberUpdate(MemberDTO dto) {
		int result = 0;
		MemberEntity entity = repository.findByNum(dto.getNum());

		MemberEntity SaveEntity = null;
		if(entity != null) {
			entity.setNum(dto.getNum());
			
			// 이메일 값이 null이 아니고 값이 ""이 아닐때만 변경
			if(dto.getEmail() != null && !dto.getEmail().trim().isEmpty()) {
				System.out.println("이메일 호출");
				entity.setEmail(dto.getEmail());
			}
			
			// 이름 값이 null이 아니고 값이 ""이 아닐때만 변경
			if(dto.getName() != null && !dto.getName().trim().isEmpty()) {
				System.out.println("이름 호출");
				entity.setName(dto.getName());
			}
			
			SaveEntity = repository.save(entity);
			if(SaveEntity != null) {
				result = 1;
			}
		}
		return result;
	}

	@Override
	public int memberDelete(String id, String passwd) {
		int result = 0;
		
		MemberEntity entity = repository.findById(id);
		
		// entity가 null이 아니고 DB의 password와 입력받은 password가 동일하면
		if(entity != null && entity.getPasswd().equals(passwd)) {
			repository.delete(entity);	// 해당 Entity를 DB에서 삭제
			result = 1;
		}
		
		return result;
	}

	@Override
	public boolean checkIdDuplicate(String id) {
		boolean result = repository.existsById(id);
		return result;	// 매개변수로 받은 ID가 있으면 true 없으면 false를 리턴
	}
}















