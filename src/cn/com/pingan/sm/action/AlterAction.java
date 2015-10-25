package cn.com.pingan.sm.action;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.com.pingan.sm.daoimpl.AlterDaoImpl;
import cn.com.pingan.sm.pojo.AlterInfo;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import com.opensymphony.xwork2.ActionSupport;

public class AlterAction extends ActionSupport {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//DaoFactory df = new DaoFactory();
	//AlterDao ad = df.getAlterDao();
	private JSONArray rows = new JSONArray();
	private JSONObject result = new JSONObject();
	
	
	private String date;
	
	private String sysname;
	
	private String content;
	
	private String id;
	
	private String risk;
	
	private String  isagree;
	
	private String execute;
	
	private String rechecker;
	
	private String executer;
	
	private String recheck;
	
	private String datepoint;
	
	private String sdate;
	
	private String edate;
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

	public JSONArray getRows() {
		return rows;
	}

	public void setRows(JSONArray rows) {
		this.rows = rows;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public String getIsagree() {
		return isagree;
	}

	public void setIsagree(String isagree) {
		this.isagree = isagree;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDatepoint() {
		return datepoint;
	}

	public void setDatepoint(String datepoint) {
		this.datepoint = datepoint;
	}
	
	
	
	public String  add(){
		AlterDaoImpl adi = new AlterDaoImpl();
		AlterInfo ai = new AlterInfo();
		ai.setId(id);
		ai.setContent(content);
		//System.out.println(datepoint);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try {
			//System.out.println("转换之前"+datepoint);
			d = new Date(sdf.parse(datepoint).getTime());
			//System.out.println("转换之中"+d);

			ai.setAlterdate(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ai.setExecute(execute);
		ai.setExecuter(executer);
		ai.setIsagree(isagree);
		ai.setRecheck(recheck);
		ai.setRechecker(rechecker);
		ai.setRisk(risk);
		ai.setSysname(sysname);
		ai.setPlatform(platform);
		String status = adi.insert(ai,false);
		result.put("status",status);
		return SUCCESS;
	}
	
	

	public String delete(){
		
		//AlterInfo ai = new AlterInfo();
		//ai.setId(id);
		AlterDaoImpl adi = new AlterDaoImpl();
		
		//System.out.println(id);
		adi.delete(id,false);
		
		result.put("status", "success");
		return  "success";
	}
	
	public String update(){
		
	/*	System.out.println("已提交更新申请");
		System.out.println(id);
		System.out.println(execute);
		System.out.println(recheck);*/
		AlterInfo ai = new AlterInfo();
		ai.setId(id);
		ai.setExecute(execute);
		ai.setRecheck(recheck);
		AlterDaoImpl adi = new AlterDaoImpl();
		adi.update(ai,false);
		//System.out.println("更新成功");
		result.put("status", "success");
		return  "success";
		
	}
	
	public String findByDate(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//AlterInfo ai = new AlterInfo();
		AlterDaoImpl adi = new AlterDaoImpl();
		//System.out.println(datepoint);
		Date d;
		try {
			//System.out.println(datepoint);
			d=null;
			if(datepoint==null){
	
			//datepoint = sdf.format(new java.util.Date());
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new java.util.Date());
			cal.add(Calendar.DAY_OF_MONTH, -1);
			datepoint = sdf.format(cal.getTime());
			d = new Date(sdf.parse(datepoint).getTime());
			}else{
				
			  d = new Date(sdf.parse(datepoint).getTime());
			}
			
			//System.out.println(d);
			List<AlterInfo> list_alter = adi.findByDate(d,false);
			//System.out.println(list_alter.size());
		for(AlterInfo ai :list_alter){
				JSONObject jo = new JSONObject();
				jo.put("id", ai.getId());
				jo.put("sysname", ai.getSysname());
				jo.put("content", ai.getContent());
				jo.put("risk", ai.getRisk());
				jo.put("execute", ai.getExecute());
				jo.put("executer", ai.getExecuter());
				jo.put("recheck", ai.getRecheck());
				jo.put("rechecker", ai.getRechecker());
				jo.put("alterdate", ai.getAlterdate().toString());
				jo.put("isagree", ai.getIsagree());
				jo.put("platform", ai.getPlatform());
				//jo.put("", value)
				rows.add(jo);
			}
			//rows=JSONArray.fromObject(list_alter);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("转换异常");
		}
		//Timestamp ts = Timestamp.valueOf(datepoint);
		
		return "success";
		
	}
	
	public String findByScope(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//AlterInfo ai = new AlterInfo();
		AlterDaoImpl adi = new AlterDaoImpl();

		Date sd = null;
		Date ed = null;
		try {
			//System.out.println("sdate="+sdate);
			//System.out.println("edate="+edate);
			sd = new Date(sdf.parse(sdate).getTime());
			ed = new Date(sdf.parse(edate).getTime());
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("findByScope in AlterAction:日期转换失败");
		}
		List<AlterInfo> list_alter = adi.findByDate(sd,ed,false);
		
		Collections.sort(list_alter,new Comparator<AlterInfo>(){
			
			public int compare(AlterInfo a1,AlterInfo a2){
				return a1.getAlterdate().compareTo(a2.getAlterdate());
			}
		});
		for(AlterInfo ai :list_alter){
			JSONObject jo = new JSONObject();
			jo.put("id", ai.getId());
			jo.put("sysname", ai.getSysname());
			jo.put("content", ai.getContent());
			jo.put("risk", ai.getRisk());
			jo.put("execute", ai.getExecute());
			jo.put("executer", ai.getExecuter());
			jo.put("recheck", ai.getRecheck());
			jo.put("rechecker", ai.getRechecker());
			jo.put("alterdate", ai.getAlterdate().toString());
			jo.put("isagree", ai.getIsagree());
			jo.put("platform", ai.getPlatform());
			//jo.put("", value)
			rows.add(jo);
		}
		return "success";
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	


}
