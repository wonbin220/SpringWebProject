package freeBoard;

import java.util.HashMap;
import java.util.List;

public interface FreeBoardService {
	
	List<FreeBoardVO> getMainBoardList(int page, int limit, String topic, String keyword);
	int getCountMainBoard(String topic, String keyword);
	List<HashMap<String, Object>> getReplyBoardList(int _num);
	FreeBoardVO getBoard(int _num);
	int insertBoard(FreeBoardVO vo);
	int updateBoard(FreeBoardVO vo);
	int deleteArticle(FreeBoardVO vo);
	int deleteComment(FreeBoardVO vo);
	int updateComment(FreeBoardVO vo);
}
