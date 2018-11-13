package companionanimal;

import java.util.ArrayList;
import java.util.HashMap;

public interface MakguliDAO {
		
	ArrayList<CpanimalVO> getCpanimalList(int page, int limit, String makguli_area, String keyword);
	int getCountCpanimal(String makguli_area);
	CpanimalVO getCpanmail(int cpanimal_num);
	ArrayList<HashMap<String, Object>> getCommentList(int page, int limt, int cpanimal_num);
	int getCountComment(int cpanimal_num);
	int insertCpanimalComment(CpanimalboardVO vo);
	String userLikeCheck(String member_email, int cpanimal_num);
	HashMap<String, Object> userLikeUpdate(String member_email, int cpanimal_num, String userLike);
	int updateCpanimalComment(CpanimalboardVO vo);
	int deleteCpanimalComment(CpanimalboardVO vo);
}
