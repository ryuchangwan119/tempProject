package spring.jpa.web.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.jpa.web.entity.BoardEntity;
import spring.jpa.web.entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
	
	public List<CommentEntity> findAllByBoardEntityOrderByIdDesc(BoardEntity entity);
}
