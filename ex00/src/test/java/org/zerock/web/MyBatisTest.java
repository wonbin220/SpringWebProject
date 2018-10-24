package org.zerock.web;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = {"classpath:/root-context.xml"})

public class MyBatisTest {
	
	@Inject
	private SqlSessionFactory sqlFactory;
	
	@Test @Ignore
	public void testFactory() {
		System.out.println(sqlFactory);
	}

		
	@Test @Ignore
	public void testSession() throws Exception{
		try(SqlSession session = sqlFactory.openSession()) {
			System.out.println(session);
		}
		catch(Exception  e) {
			e.printStackTrace();
		}

	}
}
