package org.zerock.web;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})  // root.xml에 등록되어있는 데이터소스를 밑에서 di~해줌

public class DataSourceTest {
	
	@Inject
	private DataSource ds;
	
	@Test
	public void testConnection() throws Exception {
		try (Connection con = ds.getConnection()){
			System.out.println(con);;
		}
		
		catch (Exception e){
			e.printStackTrace();
			
		}
		
	}

}
