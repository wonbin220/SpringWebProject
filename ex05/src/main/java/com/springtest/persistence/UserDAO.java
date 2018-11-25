package com.springtest.persistence;

import java.util.Date;

import com.springtest.domain.UserVO;
import com.springtest.dto.LoginDTO;

public interface UserDAO {

	public UserVO login(LoginDTO dto)throws Exception;
	
	public void keepLogin(String uid, String sessionId, Date next);
	
	public UserVO checkUserWithSessionKey(String value);
}
