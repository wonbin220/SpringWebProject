package freeBoard;

import java.util.HashMap;
import java.util.List;

public interface FreeBoardDAO {

	List<FreeBoardVO> getMainBoardList(int page, int limit, String topic, String keyword);
	int getCountMainBoard(String topic, String keyword);
	List<HashMap<String, Object>> getReplyBoardList(int free_num);
	FreeBoardVO getBoard(int free_num);
	int insertBoard(FreeBoardVO vo);
	int updateBoard(FreeBoardVO vo);
	int deleteArticle(FreeBoardVO vo);
	int deleteComment(FreeBoardVO vo);
	int updateComment(FreeBoardVO vo);
}
