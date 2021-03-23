package board.service;

import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.common.FileUtil;
//import board.controller.Slf4j;
import board.dto.BoardDto;
import board.dto.BoardFileDto;
import board.mapper.BoardMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
//	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private FileUtil fileUtil;
	
	@Override
	public List<BoardDto> selectBoardList() throws Exception {
		return boardMapper.selectBoardList();
	}
	
	@Override
	public BoardDto selectBoardDetail(int boardIdx) throws Exception {
//		게시글 상세보기 클릭 시 View Count 올림
		boardMapper.updateHitCount(boardIdx);
		
//		지정한 게시물의 상세 정보 가져오기
//		게시물의 상세 정보를 가져오는 시점에는 첨부된 파일에 대한 정보가 없음
//		fileList 멤버 변수의 값은 null 임
		BoardDto board = boardMapper.selectBoardDetail(boardIdx);
		
//		지정한 게시물에 첨부된 파일 리스트 가져오기
		List<BoardFileDto> fileList = boardMapper.selectBoardFileList(boardIdx);
//		가져온 첨부 파일 리스트를 기존의 게시물 정보에 추가하기
		board.setFileList(fileList); 
		
		return board;
	}
	
//	실행만 시키면 된다. 
	@Override
	public void insertBoard(BoardDto board, MultipartHttpServletRequest uploadFiles) throws Exception {
		log.debug("----- BoardService의 insertBoard 실행 이전 ------");
		log.debug("출력값 : " + Integer.toString(board.getBoardIdx()));
		
//		기존의 게시물 등록
		boardMapper.insertBoard(board);
		log.debug("----- BoardService의 insertBoard 실행 이후 ------");
		log.debug("출력값 : " + Integer.toString(board.getBoardIdx()));
//		FileUtil 클래스를 통해서 생성한 파일 정보 받아오기(서버에 파일 저장 기능 포함)
		List<BoardFileDto> fileList = fileUtil.parseFileInfo(board.getBoardIdx(), uploadFiles);
		
//		데이터 베이스에 업로드된 파일 정보 저장
//		CollectionUtils 클래스는 스프링 프레임워크에서 지원하는 유틸 중 하나임, 여기서는 fileList.isEmpty() 를 사용해도 상관없음.
		if (CollectionUtils.isEmpty(fileList) == false) {
			boardMapper.insertBoardFileList(fileList);
		}
	}
		
		
		
		
		
////		업로드한 파일이 존재하는지 여부 확인
//		if (ObjectUtils.isEmpty(files) == false) {
////			Iterator 타입으로 받으면 <> 안에 들어오는 형식으로 만들어서 배열로 받아옴. 
////			업로드된 파일의 이름 목록을 받아옴
//			Iterator<String> iterator = files.getFileNames();
//			String fileName;
//			
////			hasNext는 받아온 이름 목록 중에서 다음 것이 있는지 확인
//			while (iterator.hasNext()) {
//				fileName = iterator.next(); // 실제 가져옴
//				
////				실제 파일을 가져와서 List 타입에 저장
//				List<MultipartFile> fileList = files.getFiles(fileName);
//				
////				list에 저장된 파일을 하나씩 꺼내어 정보 출력
//				for (MultipartFile file : fileList) {
//					log.debug("===== start file information ======");
//					log.debug("file name : " + file.getOriginalFilename());
//					log.debug("file size : " + file.getSize());
//					log.debug("===== end file information. ===== \n\n");
//				}
//			}
//		}
	
	
	@Override
	public void updateBoard(BoardDto board) throws Exception {
//		맵퍼 사용해서 sql문 실행시켜야한다.
		boardMapper.updateBoard(board);
	}
	
	@Override
	public void deleteBoard(int boardIdx) throws Exception {
		boardMapper.deleteBoard(boardIdx);
	}

//	파일 다운로드
	
//	마이바티스는 매개변수로 사용할 수 있는 데이터 타입이 1개 밖에 없음,, 한 번쓸 때 하나밖에 못 씀. 데이터 타입이
//	마이바티스는 map을 파라미터(매개변수)로 사용하는 기능을 지원함
//	쿼리의 파라미터가 2 ~ 3개 정도인 경우 이를 위해 DTO 를 새로 생성하기 애매한 경우가 존재함
//	이럴 때 @Param 어노테이션을 사용하면 해당 파라미터들을 Map 에 지정하여 DTO 를 생성하지 않고 여러 개의 파라미터를 지정할 수 있음
//	sql에서 parameterType="map"로 지정하여 사용할 수 있음
//	5 개 정도되면 dto 를 만들어서 사용하는 것이 편함
	@Override
	public BoardFileDto selectBoardFileInformation(int idx, int boardIdx) throws Exception {
		return boardMapper.selectBoardFileInformation(idx, boardIdx);
	}
}


























