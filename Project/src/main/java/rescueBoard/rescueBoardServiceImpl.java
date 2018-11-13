package rescueBoard;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class rescueBoardServiceImpl implements rescueBoardService{
	
	@Autowired
	private rescueboardDAO dao;
	
	@Override
	public List<rescueBoardVO> getMainBoardList(int page, int limit, String topic, String keyword) {

		return dao.getMainBoardList(page, limit, topic, keyword);
	}

	@Override
	public int getCountMainBoard(String topic, String keyword) {
		
		return dao.getCountMainBoard(topic, keyword);
	}

	@Override
	public List<HashMap<String, Object>> getReplyBoardList(int rescue_num) {
		 
		return dao.getReplyBoardList(rescue_num);
	}

	@Override
	public rescueBoardVO getBoard(int rescue_num) {
 
		return dao.getBoard(rescue_num);
	}

	@Override
	public int insertBoard(rescueBoardVO vo) {
 
		return dao.insertBoard(vo);
	}

	@Override
	public int updateBoard(rescueBoardVO vo) {
 
		return dao.updateBoard(vo);
	}

	@Override
	public int deleteArticle(rescueBoardVO vo) {
 
		return dao.deleteArticle(vo);
	}

	@Override
	public int deleteComment(rescueBoardVO vo) {
 
		return dao.deleteComment(vo);
	}

	@Override
	public int updateComment(rescueBoardVO vo) {
 
		return dao.updateComment(vo);
	}

}
