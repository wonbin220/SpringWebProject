package org.zerock.web;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.MemberVO;
import org.zerock.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = {"classpath:spring/root-context.xml"})

public class MemberDAOTest {
	@Autowired
	private MemberDAO dao;
	
	@Test
	public void testTime() throws Exception{
		System.out.println(dao.getTime());
	}
	
	@Test @Ignore
	public void testInsertMember() throws Exception{
		
		MemberVO vo = new MemberVO("user10","user10","USER10","user10@aaa.com");
		
		dao.insertMember(vo);
	}
	
	// 책에 없는 내용, readMember, readWithPW 테스트
	@Test
	public void testReadMember() throws Exception {
		MemberVO vo = dao.readMember("user10");
		System.out.println(vo.toString());
	}
	
	@Test
	public void testReadWithPW() throws Exception {
		MemberVO vo = dao.readWithPW("user10", "user10");
		System.out.println(vo.toString());
	}
}