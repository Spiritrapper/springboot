package com.smhrd.bigdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smhrd.bigdata.entity.Board;
import com.smhrd.bigdata.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board/write") // localhost:8089/board/write
	public String boardWriteForm() {
		return "boardwrite";
	}
	
	@PostMapping("/board/writepro")
	// entity 형식 그대로 받을수 있기에 Board로 바꿔서 매개변수를 사용
	public String boardWritePro(Board board) {
		
		// 엔터티 @Data 어노테이션을 하면 getTitle을 받을수있다. 
		System.out.println(board.getTitle());
		
		boardService.write(board);
		return "";
	}
	
	@GetMapping("/board/list")
	public String boardList(Model model) {
		
		model.addAttribute("List", boardService.boardList());
		
		return "boardlist";
	}
	
	@GetMapping("/board/view") // localhost:8089/board/view?id=1
	public String boardView(Model model, long id) {
		
		// 가져오기만 하는거지 출력은 thymleaf에서
		// id를 통해 글을 불러오기
		model.addAttribute("board", boardService.boardview(id) );
		
		return "boardview";
	}
	
	
	@GetMapping("/board/delete")
	public String boardDelete(long id) {
		
		boardService.boarddelete(id);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/board/modify/{id}")
	// pathvariable 이란 경로내 {id}가 매개변수로 인식되어 Long id 로 들어간다.
	public String boardModify(@PathVariable("id")Long id,Model model) {
		
		model.addAttribute("board", boardService.boardview(id));
		return "boardmodify";
	}
	
	@PostMapping("/board/update/{id}")
	// pathvariable 이란 경로내 {id}가 매개변수로 인식되어 Long id 로 들어간다.
	public String boardUpdate(@PathVariable("id")Long id,Board board) {
		
		Board boardTemp = boardService.boardview(id); // 기존 내용 가져오기
		boardTemp.setTitle(board.getTitle()); // 기존내용에 덮어씌우기
		boardTemp.setContent(board.getContent());
		
		boardService.write(boardTemp);
		return "redirect:/board/list";
	}
	
	
}
