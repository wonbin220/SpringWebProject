package companionanimal;

import java.sql.Date;

public class CpanimalVO {

	private int cpboard_num;
	private int cpanimal_num;
	private String member_nickname;
	private String cpboard_content;
	private int cpboard_sweat;
	private int cpboard_sour;
	private int cpboard_body;
	private int cpboard_spark;
	private int cpboard_popular;
	private Date cpboard_date;
	
	public int getCpboard_num() {
		return cpboard_num;
	}
	public void setCpboard_num(int cpboard_num) {
		this.cpboard_num = cpboard_num;
	}
	public int getCpanimal_num() {
		return cpanimal_num;
	}
	public void setCpanimal_num(int cpanimal_num) {
		this.cpanimal_num = cpanimal_num;
	}
	public String getMember_nickname() {
		return member_nickname;
	}
	public void setMember_nickname(String member_nickname) {
		this.member_nickname = member_nickname;
	}
	public String getCpboard_content() {
		return cpboard_content;
	}
	public void setCpboard_content(String cpboard_content) {
		this.cpboard_content = cpboard_content;
	}
	public int getCpboard_sweat() {
		return cpboard_sweat;
	}
	public void setCpboard_sweat(int cpboard_sweat) {
		this.cpboard_sweat = cpboard_sweat;
	}
	public int getCpboard_sour() {
		return cpboard_sour;
	}
	public void setCpboard_sour(int cpboard_sour) {
		this.cpboard_sour = cpboard_sour;
	}
	public int getCpboard_body() {
		return cpboard_body;
	}
	public void setCpboard_body(int cpboard_body) {
		this.cpboard_body = cpboard_body;
	}
	public int getCpboard_spark() {
		return cpboard_spark;
	}
	public void setCpboard_spark(int cpboard_spark) {
		this.cpboard_spark = cpboard_spark;
	}
	public int getCpboard_popular() {
		return cpboard_popular;
	}
	public void setCpboard_popular(int cpboard_popular) {
		this.cpboard_popular = cpboard_popular;
	}
	public Date getCpboard_date() {
		return cpboard_date;
	}
	public void setCpboard_date(Date cpboard_date) {
		this.cpboard_date = cpboard_date;
	}
	@Override
	public String toString() {
		return "CpanimalVO [cpboard_num=" + cpboard_num + ", cpanimal_num=" + cpanimal_num + ", member_nickname="
				+ member_nickname + ", cpboard_content=" + cpboard_content + ", cpboard_sweat=" + cpboard_sweat
				+ ", cpboard_sour=" + cpboard_sour + ", cpboard_body=" + cpboard_body + ", cpboard_spark="
				+ cpboard_spark + ", cpboard_popular=" + cpboard_popular + ", cpboard_date=" + cpboard_date + "]";
	}
	
	
}
