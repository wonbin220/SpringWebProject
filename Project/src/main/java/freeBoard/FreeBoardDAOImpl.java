package freeBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class FreeBoardDAOImpl implements FreeBoardDAO {

	@Autowired
	private SqlSession session;
	
	@Override
	public List<FreeBoardVO> getMainBoardList(int page, int limit, String topic, String keyword) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();

		int startrow = (page-1)*limit+1;
		int endrow = startrow+limit-1;
		paramMap.put("startrow", startrow);
		paramMap.put("endrow", endrow);
		paramMap.put("topic", topic);
		paramMap.put("keyword", keyword);
		System.out.println(startrow);
		System.out.println(endrow);
		
		return session.selectList("freeboard.getReplyBoardList", paramMap);
	}

	@Override
	public int getCountMainBoard(String topic, String keyword) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("topic", topic);
		paramMap.put("keyword", keyword);
		int res = session.selectOne("freeboard.getCountMainBoard", paramMap);
		return res;
	}

	@Override
	public List<HashMap<String, Object>> getReplyBoardList(int free_num) {

		List<HashMap<String, Object>> articleList =   new ArrayList<HashMap<String,Object>>();
		System.out.println("DAO ¸Þ¼Òµå : " +  articleList.size());
		return session.selectOne("freeboard.getReplyBoardList", articleList);
	}

	@Override
	public FreeBoardVO getBoard(int free_num) {
		session.update("freeboard.upReadCountBoard", free_num);
		return session.selectOne("freeboard.getBoard", free_num);
	}
		
	@Override
	public int insertBoard(FreeBoardVO vo) {
		int num = session.selectOne("freeboard.getMaxNumBoard", vo);
		num+=1;
		vo.setFree_num(num);
		
		if(vo.getFree_ref() != 0) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("free_ref", vo.getFree_ref());
			map.put("free_ref_seq", vo.getFree_ref_seq());
			int res = session.update("freeboard.insertReply", map);
			System.out.println("insertReply : " + res);
			int free_ref_seq = vo.getFree_ref_seq()+1;
			int free_ref_level = vo.getFree_ref_level()+1;
			vo.setFree_ref_seq(free_ref_seq);
			vo.setFree_ref_level(free_ref_level);
			int res2 = session.insert("freeboard.insertBoard", vo);
			return res2;
		}
		vo.setFree_ref(num);
		int res1 = session.insert("freeboard.insertBoard", vo);
		return res1;
	}

	@Override
	public int updateBoard(FreeBoardVO vo) {
		int res = session.update("freeboard.updateBoard", vo);
		return res;
	}

	@Override
	public int deleteArticle(FreeBoardVO vo) {
		int res = session.delete("freeboard.deleteArticle", vo);
		return res;
	}

	@Override
	public int deleteComment(FreeBoardVO vo) {
		int res = session.delete("freeboard.deleteComment", vo);
		return res;
	}

	@Override
	public int updateComment(FreeBoardVO vo) {
		int res = session.update("freeboard.updateComment",vo);
		return res;
	}

}
