package rescueBoard;

import java.util.HashMap;
import java.util.List;

public interface rescueBoardService {
	
	List<rescueBoardVO> getMainBoardList(int page, int limit, String topic, String keyword);
	int getCountMainBoard(String topic, String keyword);
	List<HashMap<String, Object>> getReplyBoardList(int rescue_num);
	rescueBoardVO getBoard(int rescue_num);
	int insertBoard(rescueBoardVO vo);
	int updateBoard(rescueBoardVO vo);
	int deleteArticle(rescueBoardVO vo);
	int deleteComment(rescueBoardVO vo);
	int updateComment(rescueBoardVO vo);
}
