package com.springtest.service;

import java.util.List;

import com.springtest.domain.BoardVO;

public interface BoardService {
	
	public void regist(BoardVO borad)throws Exception;
	
	public BoardVO read(Integer bno)throws Exception;
	
	public void modify(BoardVO board)throws Exception;
	
	public void remove(Integer bno)throws Exception;
	
	public List<BoardVO> listAll() throws Exception;
}
