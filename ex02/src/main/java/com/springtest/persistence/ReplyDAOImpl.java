package com.springtest.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	
}
