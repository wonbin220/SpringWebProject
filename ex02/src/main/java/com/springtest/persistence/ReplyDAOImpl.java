package com.springtest.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springtest.domain.Criteria;
import com.springtest.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO{
	
	@Autowired
	private SqlSession session;
	
	@Override
	public List<ReplyVO> list(Integer bno) throws Exception {
	
		return session.selectList("reply.list", bno);
	}

	@Override
	public void create(ReplyVO vo) throws Exception {
		session.insert("reply.create", vo);
		
	}

	@Override
	public void update(ReplyVO vo) throws Exception {
		session.update("reply.update", vo);
	}

	@Override
	public void delete(Integer rno) throws Exception {
		session.delete("reply.delete", rno);
	}

	@Override
	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("bno", bno);
		paramMap.put("cri", cri);		
		return session.selectList("reply.listPage", paramMap);
	}

	@Override
	public int count(Integer bno) throws Exception {

		return session.selectOne("reply.count", bno);
	}

	@Override
	public int getBno(Integer rno) throws Exception {
		
		return session.selectOne("reply.getBno", rno);
	}
	
}
