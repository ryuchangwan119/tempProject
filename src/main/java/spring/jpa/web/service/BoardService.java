package spring.jpa.web.service;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import spring.jpa.web.dto.BoardDTO;
import spring.jpa.web.dto.BoardFileDTO;

@Service
public interface BoardService {
	
	public int write(BoardDTO dto);			// 글 쓰기
	public Page<BoardDTO> writeList(Pageable pageable);	// 전체 글 조회
	public BoardDTO writeDetail(int id);	// 상세 글보기
	public void readCount(int id);			// 조회수 증가(새로 고침시 조회수 증가)
	public void readCountUpdate(int id);	// 쿠키 방식을 사용하여 새로 고침시에도 조회수 증가 X
	public BoardDTO boardUpdate(int id);
	public int boardUpdatePro(BoardDTO dto);
	public void delete(int id);
	public List<BoardFileDTO> getFiles(int boardId);
}
