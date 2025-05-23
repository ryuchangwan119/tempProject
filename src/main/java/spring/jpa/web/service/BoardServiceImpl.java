package spring.jpa.web.service;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import spring.jpa.web.dto.BoardDTO;
import spring.jpa.web.dto.BoardFileDTO;
import spring.jpa.web.entity.BoardEntity;
import spring.jpa.web.entity.BoardFileEntity;
import spring.jpa.web.repository.BoardFileRepository;
import spring.jpa.web.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardRepository repository;	// 글작성
	
	@Autowired
	BoardFileRepository bfRepository;	// 파일 업로드

	// 단일 파일 업로드
//	@Override
//	public int write(BoardDTO dto) {
//		int result = 0;
//		// 글 저장
//		BoardEntity entity = BoardEntity.toSaveEntity(dto);
//		BoardEntity saveEntity = repository.save(entity);
//		
//		if(saveEntity != null) {
//			result = 1;
//		}
//		
//		// 파일 관련 설정
//		if(dto.getIsFile() == 1) {
//			MultipartFile boardFile = dto.getBoardFile();
//			String originalFileName = boardFile.getOriginalFilename();
//			
//			// UUID.randomUUID() : 랜덤한 문자열을 생성
//			String uuid = UUID.randomUUID().toString().replace("-", "");
//			
//			// 업로드할 파일명은 랜덤 문자열 + 원본 파일명
//			String uploadFileName = uuid + "_" +originalFileName;
//			
//			// 파일을 저장할 경로
//			String path = "c:/upload/";
//			
//			// 파일을 저장할 경로 + 파일명을 사용하여 파일 객체 생성
//			File file = new File(path + uploadFileName);
//			
//			try {
//				boardFile.transferTo(file);
//				System.out.println("저장 경로 : " + path);
//				System.out.println("업로드 파일명 : " + uploadFileName);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			// 파일 이름을 DB 저장
//			BoardFileEntity boardFileEntity = new BoardFileEntity();
//			
//			boardFileEntity.setFileName(originalFileName);
//			boardFileEntity.setUploadFileName(uploadFileName);
//			boardFileEntity.setCreateTime(new Date());
//			boardFileEntity.setBoardEntity(saveEntity);
//			bfRepository.save(boardFileEntity);
//		}
//		return result;
//	}
	
	// 다중 파일 업로드
	@Override
	public int write(BoardDTO dto) {
		int result = 0;
		// 글 저장
		BoardEntity entity = BoardEntity.toSaveEntity(dto);
		BoardEntity saveEntity = repository.save(entity);
		
		if(saveEntity != null) {
			result = 1;
		}
		
		if(dto.getIsFile() > 0 && dto.getBoardFile() != null && !dto.getBoardFile().isEmpty()) {
			for(MultipartFile file : dto.getBoardFile()) {
				if(file != null && !file.isEmpty()) {
					// 파일 업로드 관련
					String originalFileName = file.getOriginalFilename();
					// 파일명 분리
					String baseName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
					// 확장자 분리
					String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
					// 업로드할 파일명 생성
					String uploadFileName = dto.getWriter() + "_" + baseName + ext;
					// 파일을 저장할 경로 입력
					String path = "C:/upload/";
					
					File destFile = new File(path + uploadFileName);
					
					int count = 1;	// 파일명 중복시 숫자를 사용하여 파일명 수정
					while(destFile.exists()) {
						// 파일명 중복시 파일명 수정
						uploadFileName = dto.getWriter() + "_" + baseName + "_" + count + ext;
						destFile = new File(path + uploadFileName);
						count++;
					}	// test_cat.jpg  --->  test_cat_1.jpg  --->  test_cat_2.jpg  --->  test_cat_3.jpg
					try {
						file.transferTo(destFile);
					} catch(Exception e) {
						e.printStackTrace();
					}
					
					// DB등록
					BoardFileEntity boardFileEntity = new BoardFileEntity();
					boardFileEntity.setFileName(originalFileName); 		// 원본 파일명
					boardFileEntity.setUploadFileName(uploadFileName);	// 업로드 파일명
					boardFileEntity.setCreateTime(new Date());
					boardFileEntity.setBoardEntity(saveEntity);
					bfRepository.save(boardFileEntity);
				}
			}
		}
		return result;
	}
	

	@Override
	public Page<BoardDTO> writeList(Pageable pageable) {
		// 전체 글을 조회한 값을 List<BoardEntity>로 리턴
		Page<BoardEntity> boardEntityList = repository.findAll(pageable);
		
		// Controller로 리턴은 List<BoardDTO> 타입
		List<BoardDTO> boardDTOList = new ArrayList<>();
		
		for(BoardEntity entity : boardEntityList) {
			boardDTOList.add(BoardEntity.toBoardDTO(entity));
		}
		return new PageImpl<>(boardDTOList, pageable, boardEntityList.getTotalElements());
	}

	@Override
	public BoardDTO writeDetail(int id) {
		Optional<BoardEntity> OptionaleEntity = repository.findById(id);
		BoardDTO dto = new BoardDTO();
		
		if(OptionaleEntity.isPresent()) {
			BoardEntity entity = OptionaleEntity.get();
			
//			entity.setReadCount(entity.getReadCount() + 1);
//			repository.save(entity);	// ID(PK)값이 있기때문에 insert가 아니라 update가 된다.
			
			dto = BoardEntity.toBoardDTO(entity);
			
//			List<BoardFileEntity> fileList = entity.getBoardFileEntity();
//			if(fileList != null) {
//				BoardFileEntity boardFile = fileList.get(0);
//				dto.setOriginalFileName(boardFile.getFileName());
//				dto.setUploadFileName(boardFile.getUploadFileName());
//			}
			
			
		}
		
		return dto;
	}

	@Override
	@Transactional
	public void readCount(int id) {
		repository.updateReadCount(id);
	}

	@Override
	public void readCountUpdate(int id) {
		Optional<BoardEntity> OptionalEntity =  repository.findById(id);
		
		// isPresent() : Entity가 있으면 true를 리턴 없으면 false를 리턴
		if(OptionalEntity.isPresent()) {
			BoardEntity entity = OptionalEntity.get();
			entity.setReadCount(entity.getReadCount() + 1);
			repository.save(entity);
		}
	}

	@Override
	public BoardDTO boardUpdate(int id) {
		Optional<BoardEntity> OptionalEntity = repository.findById(id);
		
		BoardDTO dto = null;
		if(OptionalEntity.isPresent()) {
			BoardEntity entity = OptionalEntity.get();
			dto = BoardEntity.toBoardDTO(entity);
		}
		return dto;
	}

	@Override
	public int boardUpdatePro(BoardDTO dto) {
		int result = 0;
		// 업데이트는 Entity 객체를 만들게되면 모든 값을 넣어야한다.
		// 비밀번호는 비교할수 없고 비밀번호 입력시 오타가 발생하게되면 비밀번호가 수정된다.
//		BoardEntity entity = new BoardEntity();
//		entity.setId(dto.getId());
//		entity.setTitle(dto.getTitle());
//		entity.setContent(dto.getContent());
//		entity.setPasswd(dto.getPasswd());
//		entity.setWriter(dto.getWriter());
//		BoardEntity saveEntity = repository.save(entity);
//		
//		if(saveEntity != null) {
//			result = 1;
//		}
		
		Optional<BoardEntity> OriginalEntity = repository.findById(dto.getId());
		
		
		// 변경하지 않은 값은 원본 데이터 그대로 저장된다.
		if(OriginalEntity.isPresent()) {
			BoardEntity entity = OriginalEntity.get();
			
			if(dto.getWriter() != null && !dto.getWriter().trim().isEmpty()) {
				entity.setWriter(dto.getWriter());
			}
			
			if(dto.getTitle() != null && !dto.getTitle().trim().isEmpty()) {
				entity.setTitle(dto.getTitle());
			}
			
			if(dto.getContent() != null && !dto.getContent().trim().isEmpty()) {
				entity.setContent(dto.getContent());
			}
			repository.save(entity);
			result = 1;
		}
		
		// 비밀번호 확인을 ServiceImpl에서 설정한 경우
//		if(OriginalEntity.isPresent()) {
//			BoardEntity entity = OriginalEntity.get();
//			if(entity.getPasswd().equals(dto.getPasswd())) {
//				if(dto.getWriter() != null && !dto.getWriter().trim().isEmpty()) {
//					entity.setWriter(dto.getWriter());
//				}
//				
//				if(dto.getTitle() != null && !dto.getTitle().trim().isEmpty()) {
//					entity.setTitle(dto.getTitle());
//				}
//				
//				if(dto.getContent() != null && !dto.getContent().trim().isEmpty()) {
//					entity.setContent(dto.getContent());
//				}
//				repository.save(entity);
//				result = 1;
//			}
//		}
		
		return result;
	}

	@Override
	public void delete(int id) {
		repository.deleteById(id);
	}

	@Override
	public List<BoardFileDTO> getFiles(int boardId) {
		List<BoardFileEntity> fileList = bfRepository.findByBoardEntity_id(boardId);
		List<BoardFileDTO> fileDTO = new ArrayList<>();
		
		for(BoardFileEntity entity : fileList) {
			fileDTO.add(BoardFileEntity.toBoardFileDTO(entity));
		}
		return fileDTO;
	}
}



























