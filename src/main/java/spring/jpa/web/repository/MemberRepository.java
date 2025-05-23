package spring.jpa.web.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import spring.jpa.web.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
	
	// 로그인 처리 (리턴 타입 MemberEntity) 쿼리 메소드
	public MemberEntity findByIdAndPasswd(String id, String passwd);
	
	// 로그인 처리 (리턴 타입 MemberEntity) JPQL
	@Query("select m from MemberEntity m where m.id = :id and m.passwd = :passwd")
	public MemberEntity findByLogin(@Param("id") String id, @Param("passwd") String passwd);
	
	// 로그인 처리 (리턴 타입 int) 쿼리 메소드
	// select count(*) from member_table where id=? and passwd=?
	public int countByIdAndPasswd(String id, String passwd);
	
	// 로그인 처리 (리턴 타입 int) JPQL
	@Query("select count(m) from MemberEntity m where m.id = :id and m.passwd = :passwd")
	public MemberEntity countByLogin(@Param("id") String id, @Param("passwd") String passwd);
	
	// select * from member_table where num=?	
	// num은 PK이므로 1개 또는 0개만 검색 (리턴타입 : MemberEntity)
	public MemberEntity findByNum(int num);
	
	// select * from member_table where name=?
	// name은 중복을 허용하기때문에 0개 또는 다수가 검색 가능 (리턴타입 : List<MemberEntity>)
	public List<MemberEntity> findByName(String name);
	
	// findById(Integer id) = findById() 메서드의 기본 매개변수는 Integer (PK)
	public MemberEntity findById(String id);
	
	// 매개변수로 받은 ID가 있으면 true 없으면 false를 리턴
	public boolean existsById(String id);
	
}





