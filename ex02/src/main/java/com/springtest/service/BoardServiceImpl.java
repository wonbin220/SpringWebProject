package com.springtest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.springtest.domain.BoardVO;
import com.springtest.domain.Criteria;
import com.springtest.domain.SearchCriteria;
import com.springtest.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO dao;

	@Override
	public void regist(BoardVO borad) throws Exception {
		dao.create(borad);
		
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)//트랜잭션 격리 , 대부분의 데이터베이스가 
	@Override									// 기본으로 사용하는 수준으로 다른 연결이 커밋하지 않은 데이터는 보지 못함
	public BoardVO read(Integer bno) throws Exception {
	
		dao.updateViewCnt(bno);
		return dao.read(bno);
	}

	@Override
	public void modify(BoardVO board) throws Exception {
		dao.update(board);	
	}

	@Override
	public void remove(Integer bno) throws Exception {
		dao.delete(bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return dao.listCriteria(cri);
	}

	@Override //전체 게시물의 숫자를 계산
	public int listCountCriteria(Criteria cri) throws Exception {
		return dao.countPaging(cri);
	}

	@Override
	public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception {

		return dao.listSearch(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		
		return dao.listSearchCount(cri);
	}
	

}
