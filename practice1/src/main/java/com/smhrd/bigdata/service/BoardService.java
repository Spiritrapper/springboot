package com.smhrd.bigdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.bigdata.entity.Board;
import com.smhrd.bigdata.repository.BoardRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	// 글 작성 후 저장 처리
	public void write(Board board) {
		
		boardRepository.save(board);
		
	}
	
	
	// 글 리스트 db에서 가져오기 처리
	public List<Board> boardList() {
		
		return boardRepository.findAll();
	}
	
	// 특정 게시글 불러오기
	public Board boardview(Long id) {
		
		// findById(id)로 그냥 가져오면 option값으로 get()
		return boardRepository.findById(id).get();
	}
	
	// 특정 게시글 삭제
	public void boarddelete(Long id) {
		
		boardRepository.deleteById(id);
	}
	

}
