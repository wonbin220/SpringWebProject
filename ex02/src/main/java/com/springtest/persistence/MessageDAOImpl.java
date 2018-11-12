package com.springtest.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springtest.domain.MessageVO;

@Repository
public class MessageDAOImpl implements MessageDAO{
	
	@Autowired
	private SqlSession session;
	
	@Override
	public void create(MessageVO vo) throws Exception {
		session.insert("message.create", vo);
		
	}

	@Override
	public MessageVO readMessage(Integer mid) throws Exception {
		
		return session.selectOne("message.readMessage", mid);
	}

	@Override
	public void updateState(Integer mid) throws Exception {
		session.update("message.updateState", mid);
		
	}

}
