package org.zerock.persistence;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zerock.domain.MemberVO;



@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession sqlSession;
	

	@Override
	public String getTime() {
		
		return sqlSession.selectOne("member.getTime");
	}

	@Override
	public void insertMember(MemberVO vo) {
		sqlSession.insert("member.insertMember", vo);
		
	}
	//	@Override
//	public ArrayList<MemberVO> getMembers(){
//		ArrayList<MemberVO> memberList =  new ArrayList<MemberVO>();
//		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
//		//getMembers()의 메소드명과 mapper.xml의 id는 동일해야한다.
//		memberList= memberMapper.getMembers();//memberMapper.xml에가서  id가 getMembers인것을 찾는다.
//		System.out.println(memberMapper.getCount());
//		//memberList =  memberMapper.getMembers("tab_mybatis");
//		
//		return memberList;
//	} 강사님이 알려주신 getMapper인데 이렇게쓰면 코드가 더길어지니 위에처럼 간단히 쓰는방법을 사용하는것이 좋음
	
	
	@Override
	public MemberVO readMember(String userid) throws Exception {
		//return (MemberVO)	sqlSession.selectOne("member.selectMember", userid);
		MemberVO vo = sqlSession.selectOne("member.selectMember", userid);
		return vo;
	}
	
	@Override
	public MemberVO readWithPW(String userid, String userpw)throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
//  	Map<String, String> paramMap = new HashMap<String, String>(); , object자리에 String이나 int값이 올수잇다
		
		paramMap.put("userid", userid); //"userid" 소문자가 오는 이유는 memberMapper에 #{userid} 이값이 소문자이기때문.
		paramMap.put("userpw", userpw); // 위와 마찬가지
		
		return sqlSession.selectOne("member.readWithPW", paramMap);
	}

}
