package com.springtest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtest.domain.Criteria;
import com.springtest.domain.ReplyVO;
import com.springtest.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDAO dao;
	
	@Override
	public List<ReplyVO> listReply(Integer bno) throws Exception {
	
		return dao.list(bno);
	}

	@Override
	public void addReply(ReplyVO vo) throws Exception {
		dao.create(vo);
		
	}

	@Override
	public void modifyReply(ReplyVO vo) throws Exception {
		dao.update(vo);
		
	}

	@Override
	public void removeReply(Integer rno) throws Exception {
		dao.delete(rno);
		
	}

	@Override
	public List<ReplyVO> listReplyPage(Integer bno, Criteria cri) throws Exception {

		return dao.listPage(bno, cri);
	}

	@Override
	public int count(Integer bno) throws Exception {
	
		return dao.count(bno);
	}

}
