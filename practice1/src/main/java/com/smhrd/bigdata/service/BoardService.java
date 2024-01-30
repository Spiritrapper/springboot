package com.smhrd.bigdata.service;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.bigdata.entity.Board;
import com.smhrd.bigdata.repository.BoardRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	// 글 작성 후 저장 처리
	public void write(Board board, MultipartFile file) throws Exception{
		
		  // 저장할 경로 지정
	    String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

	    if (file != null) {
	        // 파일이름에 붙일 랜덤 이름 설정
	        UUID uuid = UUID.randomUUID();
	        // 파일이름 생성
	        String fileName = uuid + "_" + file.getOriginalFilename();
	        // 파일 경로에 새로 저장
	        File saveFile = new File(projectPath, fileName);
	        file.transferTo(saveFile);
	        // board에 filename에 새로 저장
	        board.setFilename(fileName);
	        board.setFilepath("/files/" + fileName);
	    } else {
	        // Handle the case where file is null (optional)
	        // For example, you may choose to log a message or perform an alternative action.
	        // In this example, we are just logging a message.
	        System.out.println("File is null. No file uploaded.");
	    }

	    // Save the board entity to the repository
	    boardRepository.save(board);
	}
	
	
	// 글 리스트 db에서 가져오기 처리
	// Pageable매개변수 넣을시 List대신 Page객체 형태로 가져온다.
	//public List<Board> boardList(Pageable pageable) {
	// JPA findAll이라는 메소드를 사용할때 정보boardSearchList를 다가지고오면 pageable이라는 클래스를 사용하여 page별로 담아서 보여줄수 있다. 
	// 
	public Page<Board> boardList(Pageable pageable) {	
		return boardRepository.findAll(pageable);
	}
	
	public Page<Board> boardSearchList(String searchKeyword, Pageable pageable){
		
		return boardRepository.findByTitleContaining(searchKeyword, pageable);
	}
	
	// 특정 게시글 불러오기
	public Board boardview(Long id) {
		
		// findById(id)로 그냥 가져오면 option값으로 get()
		return boardRepository.findById(id).get();
	}
	
	//@Transactional
    public void increaseViewCount(Long boardId) {
        Board board = boardRepository.findById(boardId).orElse(null);
        if (board != null) {
            board.setViewCount(board.getViewCount() + 1);
            boardRepository.save(board);
        }
    }
	
	// 특정 게시글 삭제
	public void boarddelete(Long id) {
		
		boardRepository.deleteById(id);
	}
	

}
