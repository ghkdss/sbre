package com.example.sbre.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sbre.domain.Board;
import com.example.sbre.domain.BoardDTO;
import com.example.sbre.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;

	@PostMapping("/board")
	public ResponseEntity<?> insertBoard(@RequestBody Board board) {

		boardService.insertBoard(board);
		
		return new ResponseEntity<>("게시글 등록 성공", HttpStatus.OK);
	}
	
	
	@GetMapping("/board")
	public ResponseEntity<?> getBoardList() {
		List<Board> boardList = boardService.getBoardList();
		
		return new ResponseEntity<>(boardList, HttpStatus.OK);
	}
	
	
	@GetMapping("/board/{id}")
	public ResponseEntity<?> getBoard(@PathVariable Integer id) {
		Board board = boardService.getBoard(id);
		
		return new ResponseEntity<>(board, HttpStatus.OK);
	}
	
	@DeleteMapping("/board")
	public ResponseEntity<?> deleteBoard(@RequestParam Integer id) {

		boardService.deleteBoard(id);
		
		return new ResponseEntity<>(id + "번 게시글 삭제 완료", HttpStatus.OK);
	}
	
	
}




