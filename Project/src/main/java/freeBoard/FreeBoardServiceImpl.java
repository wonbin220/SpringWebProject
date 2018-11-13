package freeBoard;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class FreeBoardServiceImpl implements FreeBoardService{
	
	@Autowired
	private FreeBoardDAO dao;
	
	@Override
	public List<FreeBoardVO> getMainBoardList(int page, int limit, String topic, String keyword) {

		return dao.getMainBoardList(page, limit, topic, keyword);
	}

	@Override
	public int getCountMainBoard(String topic, String keyword) {
		
		return dao.getCountMainBoard(topic, keyword);
	}

	@Override
	public List<HashMap<String, Object>> getReplyBoardList(int free_num) {
		 
		return dao.getReplyBoardList(free_num);
	}

	@Override
	public FreeBoardVO getBoard(int free_num) {
 
		return dao.getBoard(free_num);
	}

	@Override
	public int insertBoard(FreeBoardVO vo) {
 
		return dao.insertBoard(vo);
	}

	@Override
	public int updateBoard(FreeBoardVO vo) {
 
		return dao.updateBoard(vo);
	}

	@Override
	public int deleteArticle(FreeBoardVO vo) {
 
		return dao.deleteArticle(vo);
	}

	@Override
	public int deleteComment(FreeBoardVO vo) {
 
		return dao.deleteComment(vo);
	}

	@Override
	public int updateComment(FreeBoardVO vo) {
 
		return dao.updateComment(vo);
	}

}
