package com.springtest.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.springtest.domain.UserVO;
import com.springtest.dto.LoginDTO;
import com.springtest.persistence.UserDAO;

public class UserServicImpl implements UserService{
	
	@Autowired
	private UserDAO dao;
	
	@Override
	public UserVO login(LoginDTO dto) throws Exception {
	
		return dao.login(dto);
	}

	@Override
	public void keepLogin(String uid, String sessionId, Date next) throws Exception {
	
		dao.keepLogin(uid, sessionId, next);
	}

	@Override
	public UserVO checkLoginBefore(String value) {
		
		return dao.checkUserWithSessionKey(value);
	}

}
