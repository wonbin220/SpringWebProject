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
	public void addAttach(String fullName)throws Exception;//파일첨부. tbl_board 테이블에 내용이 추가되는것과 동시에 tbl_attach테이블에도 게시물 번호를 같이 저장
	public List<String> getAttach(Integer bno)throws Exception;//첨부파일 조회
	public void deleteAttach(Integer bno)throws Exception;//특정게시물 번호에 속하는 모든 첨부파일을 삭제
	public void replaceAttach(String fullName, Integer bno)throws Exception;//수정된 상태의 파일 이름과, 이미 등록되어 있는 게시물의 번호
	
}
