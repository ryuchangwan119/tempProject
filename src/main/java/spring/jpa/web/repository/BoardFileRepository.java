package spring.jpa.web.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.jpa.web.entity.BoardFileEntity;

@Repository
public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Integer> {
	public List<BoardFileEntity> findByBoardEntity_id(int boardId);	// 해당 글에 대한 파일정보를 가져오기위한 메서드
}
