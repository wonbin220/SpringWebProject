package com.springtest.test;



import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.springtest.domain.BoardVO;
import com.springtest.domain.Criteria;
import com.springtest.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/root-context.xml"})
public class BoardDAOTest {

	@Autowired
	private BoardDAO dao;
	
	private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
	
	@Test  @Ignore
	public void testCreate() throws Exception{
		
		BoardVO board = new BoardVO();
		board.setTitle("새로운 글을 넣습니다.");
		board.setContent("새로운 글을 넣습니다.");
		board.setWriter("user00");
		dao.create(board);
	}
	
	@Test @Ignore
	public void testRead() throws Exception {
		logger.info(dao.read(5).toString());
	}
	
	@Test @Ignore
	public void testUpdate() throws Exception{
		
		BoardVO board = new BoardVO();
		board.setBno(3);
		board.setTitle("수정된 글입니다.");
		board.setContent("수정테스트.");
		board.setWriter("user00");
		dao.update(board);
	}
	
	@Test @Ignore
	public void testDelete() throws Exception{
		
		dao.delete(3);
	}
	
	@Test @Ignore
	public void testListPage()throws Exception{
		
		int page = 3;
		
		List<BoardVO> list = dao.listPage(page);
		
		for(BoardVO boardVO : list) {
			logger.info(boardVO.getBno() + ":" + boardVO.getTitle());
		}
	}
	
	@Test @Ignore
	public void testListCriteria()throws Exception{
		Criteria cri = new Criteria();
		cri.setPage(2);
		cri.setPerPageNum(20);
		
		List<BoardVO> list = dao.listCriteria(cri);
		
		for(BoardVO boardVO : list) {
			logger.info(boardVO.getBno() + ":" + boardVO.getTitle());
		}
	}
	
	@Test
	public void testURI()throws Exception{
		
		UriComponents uirComponents = UriComponentsBuilder.newInstance()
				.path("/{module}/{page}")
				.path("/board/read")
				.queryParam("bno", 12)
				.queryParam("perPageNum", 20)
				.build()
				.expand("board","read")
				.encode();
		
//				.path("/board/read")
//				.queryParam("bno", 12)
//				.queryParam("perPageNum", 20)
//				.build()
		logger.info("/board/read?bno=12&perPageNum=20");
		logger.info(uirComponents.toString());
	}
}
