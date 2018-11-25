package com.springtest.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springtest.domain.UserVO;
import com.springtest.dto.LoginDTO;

@Repository
public class UserDAOImpl implements UserDAO{
	
	@Autowired
	private SqlSession session;

	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		
		return session.selectOne("UserMapper.login", dto);
	}

	@Override
	public void keepLogin(String uid, String sessionId, Date next) {
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("uid", uid);
		paraMap.put("sessionId", sessionId);
		paraMap.put("next", next);
		
		session.update("UserMapper.keepLogin", paraMap);
	}

	@Override
	public UserVO checkUserWithSessionKey(String value) {
		
		return session.selectOne("UserMapper.checkUserWithSessionKey", value);
	}

}
