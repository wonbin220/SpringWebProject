package com.springtest.persistence;

import java.util.List;

import com.springtest.domain.BoardVO;
import com.springtest.domain.Criteria;
import com.springtest.domain.SearchCriteria;

public interface BoardDAO {

	public void create(BoardVO vo)throws Exception;
	public BoardVO read(Integer bno)throws Exception;
	public void update(BoardVO vo)throws Exception;
	public void delete(Integer bno)throws Exception;
	public List<BoardVO> listAll()throws Exception;
	public List<BoardVO> listPage(int page)throws Exception;
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	public int countPaging(Criteria cri)throws Exception; //totalCount 반환
	public List<BoardVO> listSearch(SearchCriteria cri)throws Exception; //검색과 페이징처리
	public int listSearchCount(SearchCriteria cri)throws Exception; //검색과 페이징처리
	public void updateReplyCnt(Integer bno, int amount) throws Exception; // 댓글의 숫자 를 변경
	public void updateViewCnt(Integer bno) throws Exception;//조회수를 1씩 증가
}
