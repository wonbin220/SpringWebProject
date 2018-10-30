package org.zerock.domain;

import java.util.Date;

public class MemberVO {
	
	private String userid;
	private String userpw;
	private String username;
	private String email;
	private Date regdate;
	private Date updatedate;
	
	
	//MyBaits의 #문법 141p
	// 밑에껄 작성하면 JUnit테스트할때 MemberVO가 안받아지니 생성자를 만들어줘야한다
	
	public MemberVO() {}
	
	//Constructor using Fields 로 만들어줌 -> MemberDAOTest에서 쓰기위해
	public MemberVO(String userid, String userpw, String username, String email) {
		super();
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
		this.email = email;
		this.regdate = regdate;
		this.updatedate = updatedate;
	}
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	@Override
	public String toString() {
		return "MemberVO [userid=" + userid + ", userpw=" + userpw + ", username=" + username + ", email=" + email
				+ ", regdate=" + regdate + ", updatedate=" + updatedate + "]";
	}
}
