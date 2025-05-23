package spring.jpa.web.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.jpa.web.dto.AjaxDTO;

@Controller
public class AjaxController {
	
	@GetMapping("ajaxTest")
	public String ajaxTest() {
		
		return "ajax/ajaxTest";
	}
	
	@GetMapping("/ajax/getTest")
	@ResponseBody
	public String getTest(@RequestParam("message") String message) {
		
		return "Get수신-" + message;
	}
	
	@PostMapping("/ajax/postTest")
	@ResponseBody
	public String postTest(@RequestParam("message") String message) {
		
		return "Post수신-" + message;
	}
	
	@GetMapping("/ajax/ajaxDTOTest")
	public String ajaxDTOtest(AjaxDTO dto) {
		return "ajax/ajaxDTOTest";
	}
	
	@GetMapping("/ajax/getDTOTest")
	@ResponseBody
	public String getDTOTest(AjaxDTO dto) {
		return "msg : " + dto.getMsg() + ", extra : " + dto.getExtra();
	}
	
	@PostMapping("/ajax/postDTOTest")
	@ResponseBody
	public String postDTOTest(AjaxDTO dto) {
		return "msg : " + dto.getMsg() + ", extra : " + dto.getExtra();
	}
	
	@GetMapping("/ajax/ajaxDTOList")
	public String ajaxDTOList() {
		return "ajax/ajaxDTOList";
	}
	
	@GetMapping("/ajax/getDTOList")
	@ResponseBody
	public List<AjaxDTO> getDTOList() {
		
		List<AjaxDTO> list = new ArrayList<>();
		
		AjaxDTO dto1 = new AjaxDTO();
		dto1.setMsg("dto1_msg1");
		dto1.setExtra("dto1_extra1");
		
		AjaxDTO dto2 = new AjaxDTO();
		dto2.setMsg("dto2_msg2");
		dto2.setExtra("dto2_extra2");
		
		AjaxDTO dto3 = new AjaxDTO();
		dto3.setMsg("dto3_msg3");
		dto3.setExtra("dto3_extra3");
		
		list.add(dto1);
		list.add(dto2);
		list.add(dto3);
		
		return list;
	}
	
	@PostMapping("/ajax/postDTOList")
	@ResponseBody
	public List<AjaxDTO> postDTOList() {
		
		List<AjaxDTO> list = new ArrayList<>();
		
		AjaxDTO dto1 = new AjaxDTO();
		dto1.setMsg("POST_dto1_msg1");
		dto1.setExtra("POST_dto1_extra1");
		
		AjaxDTO dto2 = new AjaxDTO();
		dto2.setMsg("POST_dto2_msg2");
		dto2.setExtra("POST_dto2_extra2");
		
		AjaxDTO dto3 = new AjaxDTO();
		dto3.setMsg("POST_dto3_msg3");
		dto3.setExtra("POST_dto3_extra3");
		
		list.add(dto1);
		list.add(dto2);
		list.add(dto3);
		
		return list;
	}
}














