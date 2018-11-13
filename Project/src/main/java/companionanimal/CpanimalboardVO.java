package companionanimal;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class CpanimalboardVO {
	
	private int cpboard_num;  // 게시판번호
	private int cpanimal_num; // 동물번호
	private String cpboard;
	private String mboard_content;
	private int cpboard_sweat;
	private int cpboard_sour;
	private int mboard_body;
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
	public String getCpboard() {
		return cpboard;
	}
	public void setCpboard(String cpboard) {
		this.cpboard = cpboard;
	}
	public String getMboard_content() {
		return mboard_content;
	}
	public void setMboard_content(String mboard_content) {
		this.mboard_content = mboard_content;
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
	public int getMboard_body() {
		return mboard_body;
	}
	public void setMboard_body(int mboard_body) {
		this.mboard_body = mboard_body;
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
		return "MakguliboardVO [cpboard_num=" + cpboard_num + ", cpanimal_num=" + cpanimal_num + ", cpboard=" + cpboard
				+ ", mboard_content=" + mboard_content + ", cpboard_sweat=" + cpboard_sweat + ", cpboard_sour="
				+ cpboard_sour + ", mboard_body=" + mboard_body + ", cpboard_spark=" + cpboard_spark
				+ ", cpboard_popular=" + cpboard_popular + ", cpboard_date=" + cpboard_date + "]";
	}
	
 
}
