package spring.jpa.web.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import spring.jpa.web.dto.MemberDTO;
import spring.jpa.web.service.MemberService;

@Controller
@RequestMapping("member/*")
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@GetMapping("home")
	public String home() {
		
		return "member/main";
	}
	
	// 회원 가입 FORM
	@GetMapping("inputForm")
	public String inputForm() {
		
		return "member/inputForm";
	}
	
	@PostMapping("inputPro")
	public String inputPro(MemberDTO dto, Model model) {
		int result = service.memberAdd(dto);
		model.addAttribute("result", result);
		return "member/inputPro";
	}
	
	@GetMapping("loginForm")
	public String loginForm() {
		
		return "member/loginForm";
	}
	
	@PostMapping("loginPro")
	public String loginPro(@RequestParam("id") String id, 
					@RequestParam("passwd") String passwd,
					HttpSession session, Model model) {
		// public MemberEntity findByIdAndPasswd(String id, String passwd); 로그인 처리
//		boolean result = service.memberLogin(id, passwd);
//		if(result) {
//			session.setAttribute("memId", id);
//		}
//		model.addAttribute("result", result);
		
		// public int countByIdAndPasswd(String id, String passwd); 로그인 처리
		int result = service.memberLogin(id, passwd);
		
		if(result > 0) {
			session.setAttribute("memId", id);
		}
		model.addAttribute("result", result);
		return "member/loginPro";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "member/loginForm";
	}
	
	@GetMapping("memberList")
	public String memberList(Model model) {
		
		List<MemberDTO> memList = service.memberList();
		
		model.addAttribute("memList",  memList);
		return "member/memberList";
	}
	
	@RequestMapping("view")
	public String view(@RequestParam("num") int num, Model model) {
		MemberDTO dto = service.memberDetail(num);
		
		model.addAttribute("dto", dto);
		return "member/viewDetail";
	}
	
	@RequestMapping("updateForm")
	public String updateForm(@RequestParam("id") String id, Model model) {
		
		MemberDTO dto = service.memberUpdateRead(id);
		model.addAttribute("dto",  dto);
		
		return "member/updateForm";
	}
	
	@RequestMapping("updatePro")
	public String updatePro(MemberDTO dto, Model model) {
		
		int result = service.memberUpdate(dto);
		model.addAttribute("result",  result);
		
		return "member/updatePro";
	}
	
	@RequestMapping("myPage")
	public String myPage(HttpSession session, Model model) {
		String id = (String)session.getAttribute("memId");
		
		MemberDTO dto = service.memberUpdateRead(id);
		model.addAttribute("dto",  dto);
		
		return "member/myPage";
	}
	
	@RequestMapping("myUpdateForm")
	public String myUpdateForm(HttpSession session, Model model) {
		String id = (String)session.getAttribute("memId");
		
		MemberDTO dto = service.memberUpdateRead(id);
		model.addAttribute("dto",  dto);
		
		return "member/myUpdateForm";
	}
	
	@RequestMapping("myUpdatePro")
	public String myUpdatePro(MemberDTO dto, Model model) {
		
		int result = service.memberUpdate(dto);
		model.addAttribute("result",  result);
		return "member/myUpdatePro";
	}
	
	@RequestMapping("deleteForm")
	public String deleteForm(@RequestParam("id") String id, Model model) {
		model.addAttribute("id",  id);
		return "member/deleteForm";
	}
	
	@RequestMapping("deletePro")
	public String deletePro(@RequestParam("id") String id, 
							@RequestParam("passwd") String passwd,
							Model model, HttpSession session) {
		
		int result = service.memberDelete(id, passwd);
		
		if(result > 0) {			// 회원 탈퇴시
			session.invalidate();	// 세션 삭제
		}
		
		model.addAttribute("result",  result);
		
		return "member/deletePro";
	}
	
	@RequestMapping("myDeleteForm")
	public String myDeleteForm(HttpSession session, Model model) {

		String id = (String)session.getAttribute("memId");
		model.addAttribute("id", id);
		
		return "member/myDeleteForm";
	}
	
	@RequestMapping("checkId")
	@ResponseBody
	public String checkId(@RequestParam("id") String id) {
		boolean exists = service.checkIdDuplicate(id);
		// exists가 true면 사용중인 아이디입니다. 가 출력
		// exists가 false면 사용 가능한 아이디입니다. 가 출력
		return exists ? "사용중인 아이디입니다." : "사용 가능한 아이디입니다.";
	}
	
}



















