package com.example.sbre.service;


import org.springframework.stereotype.Service;

import com.example.sbre.domain.Board;
import com.example.sbre.domain.BoardDTO;
import com.example.sbre.domain.Member;
import com.example.sbre.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	
	public void insertBoard(Board board) {
		
		boardRepository.save(board);
		
	}
	
}



