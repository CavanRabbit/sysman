package cn.com.pingan.sm.pojo;

import java.sql.Date;

public class AlterInfo {

	
	private Date alterdate;
	
	private String sysname;
	
	private String content;
	
	private String id;
	
	private String risk;
	
	private String  isagree;
	
	private String execute;
	
	private String rechecker;
	
	private String executer;
	
	private String recheck;
	
	private String platform;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public String getSysname() {
		return sysname;
	}

	public void setSysname(String sysname) {
		this.sysname = sysname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}



	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}



	public String getExecute() {
		return execute;
	}

	public void setExecute(String execute) {
		this.execute = execute;
	}

	public String getRechecker() {
		return rechecker;
	}

	public void setRechecker(String rechecker) {
		this.rechecker = rechecker;
	}

	public String getExecuter() {
		return executer;
	}

	public void setExecuter(String executer) {
		this.executer = executer;
	}

	public String getRecheck() {
		return recheck;
	}

	public void setRecheck(String recheck) {
		this.recheck = recheck;
	}

	public Date getAlterdate() {
		return alterdate;
	}

	public void setAlterdate(Date alterdate) {
		this.alterdate = alterdate;
	}

	public void setIsagree(String isagree) {
		this.isagree = isagree;
	}

	public String getIsagree() {
		return isagree;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	
}
