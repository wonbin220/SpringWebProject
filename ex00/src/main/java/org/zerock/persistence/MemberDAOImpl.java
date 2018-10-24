package org.zerock.persistence;

import java.util.ArrayList;

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
	

}
