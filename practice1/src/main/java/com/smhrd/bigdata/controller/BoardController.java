package com.smhrd.bigdata.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	public String boardWritePro(Board board, Model model, @RequestParam(name="file")MultipartFile file) throws Exception {
		
		// 엔터티 @Data 어노테이션을 하면 getTitle을 받을수있다. 
		//System.out.println(board.getTitle());
		
		boardService.write(board, file);
		
//		if(return == 1) {
			
	//	} else {
			
		//}
		model.addAttribute("message", "글작성이 완료되었습니다.");
		//model.addAttribute("message", "글작성이 실패하였습니다.");
		model.addAttribute("searchUrl", "/board/list");

		return "message";
	}
	
	@GetMapping("/board/list") // localhost:8089/board/list?page=1&size=10
	// PageableDefault라는 어노테이션을 사용하여 페이지의 설정을 정할수있다.
	public String boardList(Model model,
			@PageableDefault(page = 0, sort = "id", size=20, direction = Sort.Direction.DESC) Pageable pageable,
			String searchKeyword) {
		
		Page<Board> list = null;
		System.out.printf("나와: ",list);
		if(searchKeyword == null) {
			list = boardService.boardList(pageable);

		}else {
			list = boardService.boardSearchList(searchKeyword, pageable);

		}
		
	    
		// Pageable이 갖고있는 페이지는 0부터 시작하기때문에
		int nowPage = list.getPageable().getPageNumber()+1;
		// 두값을 비교해 큰것 1이하일땐 startPage 1로
		int startPage = Math.max(nowPage-4, 1);
		int endPage = Math.min(nowPage +5, list.getTotalPages()); 
		
		//model.addAttribute("List", boardService.boardList(pageable));
		
		model.addAttribute("List", list);	
		model.addAttribute("nowPage", nowPage);		
		model.addAttribute("startPage", startPage);		
		model.addAttribute("endPage", endPage);		

		
		return "boardlist";
	}
	
	@GetMapping("/board/view") // localhost:8089/board/view?id=1
	public String boardView(Model model, long id) {
		
		boardService.increaseViewCount(id);
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
	public String boardModify(@PathVariable("id")Long id, Model model) {
		
		model.addAttribute("board", boardService.boardview(id));
		return "boardmodify";
	}
	// pathvariable 이란 경로내 {id}가 매개변수로 인식되어 Long id 로 들어간다.
	@PostMapping("/board/update/{id}")
	public String boardUpdate(@PathVariable("id") Long id, Board board, MultipartFile file) throws Exception {
		  
		
		// pathvariable사용시엔 sql문을 사용하지 않고 사용한다.
		// 기존 객체에 id를 통해 수정한 객체를 덥어 씌워서
		Board boardTemp = boardService.boardview(id); // 기존 내용 가져오기
		boardTemp.setTitle(board.getTitle()); // 기존내용에 덮어씌우기
		boardTemp.setContent(board.getContent());
		
		boardService.write(boardTemp, file);
		return "redirect:/board/list";
	}
	
	/*
	 * @GetMapping("/") public String index() { return "hello"; }
	 */
	
}
