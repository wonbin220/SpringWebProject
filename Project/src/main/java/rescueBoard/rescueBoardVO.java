package rescueBoard;

import java.util.Date;

public class rescueBoardVO {
	
	private int rescue_num;
	private String member_nickname;
	private String rescue_subject;
	private String rescue_content;
	private int rescue_readcount;
	private int rescue_ref;
	private int rescue_ref_seq;
	private int rescue_ref_level;
	private Date rescue_date;
	
	public int getRescue_num() {
		return rescue_num;
	}
	public void setRescue_num(int rescue_num) {
		this.rescue_num = rescue_num;
	}
	public String getMember_nickname() {
		return member_nickname;
	}
	public void setMember_nickname(String member_nickname) {
		this.member_nickname = member_nickname;
	}
	public String getRescue_subject() {
		return rescue_subject;
	}
	public void setRescue_subject(String rescue_subject) {
		this.rescue_subject = rescue_subject;
	}
	public String getRescue_content() {
		return rescue_content;
	}
	public void setRescue_content(String rescue_content) {
		this.rescue_content = rescue_content;
	}
	public int getRescue_readcount() {
		return rescue_readcount;
	}
	public void setRescue_readcount(int rescue_readcount) {
		this.rescue_readcount = rescue_readcount;
	}
	public int getRescue_ref() {
		return rescue_ref;
	}
	public void setRescue_ref(int rescue_ref) {
		this.rescue_ref = rescue_ref;
	}
	public int getRescue_ref_seq() {
		return rescue_ref_seq;
	}
	public void setRescue_ref_seq(int rescue_ref_seq) {
		this.rescue_ref_seq = rescue_ref_seq;
	}
	public int getRescue_ref_level() {
		return rescue_ref_level;
	}
	public void setRescue_ref_level(int rescue_ref_level) {
		this.rescue_ref_level = rescue_ref_level;
	}
	public Date getRescue_date() {
		return rescue_date;
	}
	public void setRescue_date(Date rescue_date) {
		this.rescue_date = rescue_date;
	}
	@Override
	public String toString() {
		return "rescueBoardVO [rescue_num=" + rescue_num + ", member_nickname=" + member_nickname + ", rescue_subject="
				+ rescue_subject + ", rescue_content=" + rescue_content + ", rescue_readcount=" + rescue_readcount
				+ ", rescue_ref=" + rescue_ref + ", rescue_ref_seq=" + rescue_ref_seq + ", rescue_ref_level="
				+ rescue_ref_level + ", rescue_date=" + rescue_date + "]";
	}
	
	
}