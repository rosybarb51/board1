package board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import board.dto.BoardDto;
import board.dto.BoardFileDto;

// DAO 로 동작함
@Mapper
public interface BoardMapper {
//	게시물 목록 불러오기
	List<BoardDto> selectBoardList() throws Exception;
//	지정한 게시물 전체내용 불러오기
	BoardDto selectBoardDetail(int boardIdx) throws Exception;
//	지정한 게시물의 hit Count 올리기
	void updateHitCount(int boardIdx) throws Exception;
	
//	게시글 작성하기
	void insertBoard(BoardDto board) throws Exception;
	
//	게시글에 포함된 파일 정보 작성하기
	void insertBoardFileList(List<BoardFileDto> fileList) throws Exception;
	
//	게시글에 포함된 파일 정보 조회하기
	List<BoardFileDto> selectBoardFileList(int boardIdx) throws Exception;
	
//	게시글 수정하기
	void updateBoard(BoardDto board) throws Exception;
	
//	게시글 삭제하기
	void deleteBoard(int boardIdx) throws Exception;
	
<<<<<<< HEAD
//	파일 정보 가져오기
//	@Param 이라고 각각에 하나씩 붙여줘야 인식이 된다, @Param 괄호 안의 값은 밖에서 보이는 키임. 그 다음 나오는 int idx 가 이 안에서 사용하는 것.
	BoardFileDto selectBoardFileInformation(@Param("idx") int idx, @Param("boardIdx") int boardIdx);
=======
>>>>>>> branch 'master' of https://github.com/rosybarb51/board1.git
}
































