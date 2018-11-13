package rescueBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class rescueBoardDAOImpl implements rescueboardDAO {

	@Autowired
	private SqlSession session;
	
	@Override
	public List<rescueBoardVO> getMainBoardList(int page, int limit, String topic, String keyword) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();

		int startrow = (page-1)*limit+1;
		int endrow = startrow+limit-1;
		paramMap.put("startrow", startrow);
		paramMap.put("endrow", endrow);
		paramMap.put("topic", topic);
		paramMap.put("keyword", keyword);
		System.out.println(startrow);
		System.out.println(endrow);
		
		return session.selectList("rescueboard.getReplyBoardList", paramMap);
	}

	@Override
	public int getCountMainBoard(String topic, String keyword) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("topic", topic);
		paramMap.put("keyword", keyword);
		int res = session.selectOne("rescueboard.getCountMainBoard", paramMap);
		return res;
	}

	@Override
	public List<HashMap<String, Object>> getReplyBoardList(int free_num) {

		List<HashMap<String, Object>> articleList =   new ArrayList<HashMap<String,Object>>();
		System.out.println("DAO ¸Þ¼Òµå : " +  articleList.size());
		return session.selectOne("rescueboard.getReplyBoardList", articleList);
	}

	@Override
	public rescueBoardVO getBoard(int free_num) {
		session.update("rescueboard.upReadCountBoard", free_num);
		return session.selectOne("rescueboard.getBoard", free_num);
	}
		
	@Override
	public int insertBoard(rescueBoardVO vo) {
		int num = session.selectOne("rescueboard.getMaxNumBoard", vo);
		num+=1;
		vo.setRescue_num(num);
		
		if(vo.getRescue_ref() != 0) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("Rescue_ref", vo.getRescue_ref());
			map.put("Rescue_ref_seq", vo.getRescue_ref_seq());
			int res = session.update("rescueboard.insertReply", map);
			System.out.println("insertReply : " + res);
			int rescue_ref_seq = vo.getRescue_ref_seq()+1;
			int rescue_ref_level = vo.getRescue_ref_level()+1;
			vo.setRescue_ref_seq(rescue_ref_seq);
			vo.setRescue_ref_level(rescue_ref_level);
			int res2 = session.insert("rescueboard.insertBoard", vo);
			return res2;
		}
		vo.setRescue_ref(num);
		int res1 = session.insert("rescueboard.insertBoard", vo);
		return res1;
	}

	@Override
	public int updateBoard(rescueBoardVO vo) {
		int res = session.update("rescueboard.updateBoard", vo);
		return res;
	}

	@Override
	public int deleteArticle(rescueBoardVO vo) {
		int res = session.delete("rescueboard.deleteArticle", vo);
		return res;
	}

	@Override
	public int deleteComment(rescueBoardVO vo) {
		int res = session.delete("rescueboard.deleteComment", vo);
		return res;
	}

	@Override
	public int updateComment(rescueBoardVO vo) {
		int res = session.update("rescueboard.updateComment",vo);
		return res;
	}

}
