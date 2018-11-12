package com.springtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtest.domain.MessageVO;
import com.springtest.persistence.MessageDAO;
import com.springtest.persistence.PointDAO;

@Service
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private MessageDAO messageDAO;
	
	@Autowired
	private PointDAO pointDAO;
	
	@Override
	public void addMessage(MessageVO vo) throws Exception {
		
		messageDAO.create(vo);
		pointDAO.updatePoint(vo.getSender(), 10);
		
	}

	@Override
	public MessageVO readMessage(String uid, Integer mid) throws Exception {
		
		messageDAO.updateState(mid);
		pointDAO.updatePoint(uid, 5);
		
		return messageDAO.readMessage(mid);
	}
}
