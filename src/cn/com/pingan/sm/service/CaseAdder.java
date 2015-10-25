package cn.com.pingan.sm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.pingan.sm.daoimpl.CaseDaoImpl;
import cn.com.pingan.sm.pojo.CaseInfo;

public class CaseAdder {
	
	
	private static String generateCaseId(String date,int count){
		String id = "E";
		String[] validarray= date.split("-");
		String valid = validarray[0]+validarray[1]+validarray[2];
		id = id + valid;
		if(count<=8){
			id = id + "0" + (count+1) ;
			
		}else{      //插入多条case信息
			
			id = id + (count+1);
		}
		return id;
	}
	
	public synchronized static String insertCase(CaseInfo ca){
		String id ;
		CaseDaoImpl cdi = new CaseDaoImpl();
		List<CaseInfo> existCaseList = null;

		String[] time = ca.getStarttime().toString().split(" ");
		
		existCaseList = cdi.findByDate(time[0],true);
		id = generateCaseId(time[0],existCaseList.size());
		ca.setId(id);
		return cdi.insert(ca,false);
	}
	
	public synchronized static String insertCase(List<CaseInfo> caseList){
		String id ;
		CaseDaoImpl cdi = new CaseDaoImpl();
			
		Map<String,Integer> dateCountMap = new HashMap<String,Integer>();
		//有几个日期
		for(CaseInfo ca : caseList){
		    String[] time = ca.getStarttime().toString().split(" ");
			/*String[] validarray= time[0].split("-");	
			String valid = validarray[0]+validarray[1]+validarray[2];*/
			dateCountMap.put(time[0], 0);			
		}
			
		//每个日期有几条记录
		for(String key : dateCountMap.keySet()){
			int count = cdi.findByDate(key,true).size();
			dateCountMap.put(key, count);
		}
		//为列表中的元素加入ID值
		for(int i=0;i<caseList.size();i++){
				
			String[] time = caseList.get(i).getStarttime().toString().split(" ");
			id = generateCaseId(time[0],dateCountMap.get(time[0]));
			caseList.get(i).setId(id);
			dateCountMap.put(time[0], dateCountMap.get(time[0])+1);
		}
			
		return cdi.insert(caseList, false);


	}

}
