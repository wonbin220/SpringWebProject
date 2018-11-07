package com.springtest.service;

import java.util.List;

import com.springtest.domain.ReplyVO;

public interface ReplyService {
	
	public List<ReplyVO> listReply(Integer bno) throws Exception;
	public void addReply(ReplyVO vo) throws Exception;
	public void modifyReply(ReplyVO vo) throws Exception;
	public void removeReply(Integer rno) throws Exception;
}
