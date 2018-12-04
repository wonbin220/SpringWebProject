package com.springtest.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springtest.domain.BoardVO;
import com.springtest.domain.Criteria;
import com.springtest.domain.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Autowired
	private SqlSession session;
	
	@Override
	public void create(BoardVO vo) throws Exception {
		session.insert("board.create", vo);
		
	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		return session.selectOne("board.read", bno);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		session.update("board.update", vo);
	}

	@Override
	public void delete(Integer bno) throws Exception {
		session.delete("board.delete", bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return session.selectList("board.listAll");
	}

	@Override
	public List<BoardVO> listPage(int page) throws Exception {
		if (page <= 0 ) {
			page = 1;
		}
		page = (page - 1) * 10;
		return session.selectList("board.listPage", page);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return session.selectList("board.listCriteria", cri);
	}

	@Override //totalCount 반환
	public int countPaging(Criteria cri) throws Exception {
		return session.selectOne("board.countPaging", cri);
	}

	@Override //검색과 페이징처리
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		
		return session.selectList("board.listSearch", cri);
	}

	@Override //검색과 페이징처리
	public int listSearchCount(SearchCriteria cri) throws Exception {
		
		return session.selectOne("board.listSearchCount", cri);
	}

	@Override
	public void updateReplyCnt(Integer bno, int amount) throws Exception {
	
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("bno", bno);
		paramMap.put("amount", amount);
		
		session.update("board.updateReplyCnt", paramMap);
	}

	@Override
	public void updateViewCnt(Integer bno) throws Exception {

		session.update("board.updateViewCnt", bno);
		
	}
	
	@Override
	public void addAttach(String fullName) throws Exception {
		
		session.insert("board.addAttach", fullName);

	}



	@Override
	public List<String> getAttach(Integer bno) throws Exception {
		
		return session.selectList("board.getAttach", bno);
	}

	@Override
	public void deleteAttach(Integer bno) throws Exception {
		
		session.delete("board.deleteAttach", bno);
		
	}

	@Override
	public void replaceAttach(String fullName, Integer bno) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("bno", bno);
		paramMap.put("fullName", fullName);
		
		session.insert("board.replaceAttach", paramMap);
	}


}
