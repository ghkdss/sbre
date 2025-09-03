package com.example.sbre.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sbre.domain.Board;
import com.example.sbre.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	
	public void insertBoard(Board board) {
		
		boardRepository.save(board);
		
	}
	
	public List<Board> getBoardList() {
		
		return boardRepository.findAllByOrderByIdDesc();
	}
	
	public Board getBoard(Integer id) {
		
		return boardRepository.findById(id).get();
	}
	
	public void deleteBoard(Integer id) {
		boardRepository.deleteById(id);
	}
	
}



