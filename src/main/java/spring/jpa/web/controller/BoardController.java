package spring.jpa.web.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import spring.jpa.web.dto.BoardDTO;
import spring.jpa.web.dto.BoardFileDTO;
import spring.jpa.web.service.BoardService;

@Controller
@RequestMapping("board/*")
public class BoardController {
	
	@Autowired
	BoardService service;
	
	@RequestMapping("main")
	public String main() {
		
		return "board/main";
	}
	// 글 작성 Form
	@RequestMapping("writeForm")
	public String writeForm(Model model) {
		
		model.addAttribute("boardDTO", new BoardDTO());
		return "board/writeForm";
	}
	
	@RequestMapping("writePro")
	public String writePro(BoardDTO dto, Model model) {
		
		int fileCount = 0;
		
		// boardFile이 null이 아니고 빈값("")이 아니면 isFile값을 1로 나머지는 0으로 처리
//		if(dto.getBoardFile() != null && !dto.getBoardFile().isEmpty()) {
//			dto.setIsFile(1);
//		} else {
//			dto.setIsFile(0);
//		}
		
		// 파일 개수 확인
		if(dto.getBoardFile() != null) {
			for(MultipartFile file : dto.getBoardFile()) {
				if(file != null && !file.isEmpty()) {
					fileCount++;
				}
			}
		}
		dto.setIsFile(fileCount);
		
	    int result = service.write(dto);
		model.addAttribute("result", result);
		
		return "board/writePro";
	}
	
	@RequestMapping("list")
	public String list(BoardDTO dto, Model model,
			// page : 현재 페이지 정보
			@RequestParam(name = "page", defaultValue="0") int page,
			// size : 한 페이지에 보여줄 글 개수
			@RequestParam(name = "size", defaultValue="5") int size ) {
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
		
		Page<BoardDTO> boardList = service.writeList(pageable);
		
		int currentPage = boardList.getNumber();	// 시작 페이지(0)
		
		// 글수 87, 10 = 9
		int totalPage = boardList.getTotalPages();
		
		int blockSize = 10;
		
		// currentPage = 7 , (7/10) * 10 = 0
		// currentPage = 17 , (17/10) * 10 = 10
		// currentPage = 27 , (27/10) * 10 = 20
		int startPage = (currentPage / blockSize) * 10;
		int endPage = Math.min(startPage + blockSize - 1, totalPage - 1);
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totalPage", totalPage);
		
		model.addAttribute("boardList", boardList);
		return "board/list";
	}
	
	@RequestMapping("listDetail")
	public String listDetail(@RequestParam("id") int id, @RequestParam("page") int page, Model model,
							HttpServletRequest request, HttpServletResponse response) {
		System.out.println("id123 : " + id);
		boolean isViewed = false;
		
		Cookie[] cookies = request.getCookies();	// 사용자의 쿠키 정보를 Cookie 배열로 리턴
		
		if(cookies != null) {	// 쿠키값 유/무 확인
			for(Cookie cookie : cookies) {	// 쿠키 배열을 반복문을 사용하여 검색
				if(cookie.getName().equals("viewed_" + id)) {	// 글번호의 쿠키가 있는지 확인
					isViewed = true;
					break;
				}
			}
		}
		
		if(!isViewed) {	// isViewed가 false이면 (글을 조회한적이 없다면)
			service.readCountUpdate(id);	// 조회수 증가
			Cookie newCookie = new Cookie("viewed_" + id, "true");	// 해당 글번호의 쿠키 생성
			newCookie.setMaxAge(60 * 60 * 6);
			newCookie.setPath("/");
			response.addCookie(newCookie);
		}
		
		BoardDTO dto = service.writeDetail(id);	// 상세 글정보
		
		// 파일이 있을때만 파일이름을 리턴받고 view로 전송
		if(dto.getIsFile() > 0) {
			List<BoardFileDTO> fileList = service.getFiles(id);
			System.out.println("파일수 : " + fileList.size());
			model.addAttribute("fileList", fileList);
		}
		
		model.addAttribute("dto", dto);
		model.addAttribute("page", page);
		return "board/listDetail";
	}
	
	@RequestMapping("update")
	public String update(@RequestParam("id") int id, @RequestParam("page") int page, Model model) {
		
		BoardDTO dto = service.boardUpdate(id);
		
		model.addAttribute("dto", dto);
		model.addAttribute("id", id);
		model.addAttribute("page", page);
		return "board/updateForm";
	}
	
	@RequestMapping("updatePro")
	public String update(BoardDTO dto, Model model,
			@RequestParam("page") int page) {
		
		int result = service.boardUpdatePro(dto);
		
		model.addAttribute("page", page);
		model.addAttribute("result", result);
		model.addAttribute("dto", dto);
		return "board/updatePro";
	}
	
	@RequestMapping("delete")
	public String delete(@RequestParam("id") int id) {
		service.delete(id);
		return "redirect:/board/list";
	}
}














