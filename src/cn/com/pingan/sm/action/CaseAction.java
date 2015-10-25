package cn.com.pingan.sm.action;

import java.sql.Timestamp;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import cn.com.pingan.sm.daoimpl.CaseDaoImpl;
import cn.com.pingan.sm.pojo.CaseInfo;
import cn.com.pingan.sm.service.CaseAdder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CaseAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSONArray row = new JSONArray();
	private JSONObject result = new JSONObject();
	private String id;
	private String relevance ;
	private String description;
    private String starttime;
    private String endtime;
    private String scope;
    private String solution;
    private String reason;
    public String remark;
    public String date;
    private String sdate;
    private String edate;
    private String mins;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRelevance() {
		return relevance;
	}
	public void setRelevance(String relevance) {
		this.relevance = relevance;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	public String  add(){

		CaseInfo ci = new CaseInfo();
		ci.setRelevance(relevance);
		//System.out.println(datepoint);
		Timestamp sd,ed;
		sd = Timestamp.valueOf(starttime);
		ci.setStarttime(sd);
		ed = Timestamp.valueOf(endtime);
		ci.setEndtime(ed);
		ci.setDescription(description);
		ci.setScope(scope);
	    ci.setReason(reason);
	    ci.setSolution(solution);
	    ci.setRemark(remark);
	    ci.setMins(Float.parseFloat(mins));
	    String status;
		status = CaseAdder.insertCase(ci); 
		result.put("status",status);
		System.out.println("事件插入结果："+status);
		return SUCCESS;
	}
	
	
	
	public String findByDate(){
		
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//AlterInfo ai = new AlterInfo();
		CaseDaoImpl cdi = new CaseDaoImpl();
		//System.out.println(datepoint);
		//Date d;
		
			
			//System.out.println(d);
		List<CaseInfo> list_case = cdi.findByDate(date,false);
		
		for(CaseInfo ci :list_case){
				JSONObject jo = new JSONObject();
				jo.put("id", ci.getId());
				jo.put("relevance", ci.getRelevance());
				jo.put("description", ci.getDescription());
				jo.put("starttime", ci.getStarttime().toString());
				jo.put("endtime", ci.getEndtime().toString());
				jo.put("scope", ci.getScope());
				jo.put("solution", ci.getSolution());
				jo.put("reason", ci.getReason());
				jo.put("remark", ci.getRemark());
				jo.put("mins", ci.getMins());
				//jo.put("", value)
				row.add(jo);
			}
			//rows=JSONArray.fromObject(list_alter);
	
		//Timestamp ts = Timestamp.valueOf(datepoint);
		
		return "success";
		
	}
	
	public String findByScope(){
		CaseDaoImpl cdi = new CaseDaoImpl();
		//System.out.println(sdate);
		//System.out.println(edate);
		List<CaseInfo> list_case = cdi.findByDate(sdate,edate,false);
		Collections.sort(list_case, new Comparator<CaseInfo>(){
			
			public int compare(CaseInfo c1,CaseInfo c2){
				
				return c1.getStarttime().compareTo(c2.getStarttime());
			}
		});
		for(CaseInfo ci :list_case){
			JSONObject jo = new JSONObject();
			jo.put("id", ci.getId());
			jo.put("relevance", ci.getRelevance());
			jo.put("description", ci.getDescription());
			jo.put("starttime", ci.getStarttime().toString());
			jo.put("endtime", ci.getEndtime().toString());
			jo.put("scope", ci.getScope());
			jo.put("solution", ci.getSolution());
			jo.put("reason", ci.getReason());
			jo.put("remark", ci.getRemark());
			jo.put("mins", ci.getMins());
			//jo.put("", value)
			row.add(jo);
		}
		//System.out.println(row.size());
		//System.out.println(list_case.size());
		return "success";
	}
	
	
	public JSONArray getRow() {
		return row;
	}
	public void setRow(JSONArray row) {
		this.row = row;
	}
	public JSONObject getResult() {
		return result;
	}
	public void setResult(JSONObject result) {
		this.result = result;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
	public String getMins() {
		return mins;
	}
	public void setMins(String mins) {
		this.mins = mins;
	}
	

}
