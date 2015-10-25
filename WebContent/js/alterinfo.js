/**
 * 
 */
var dg;

$(document).ready(function(){
	


	var date = new Date();
	
	var yes_mill = date.getTime()-1000*60*60*24;
	
	var yes = new Date();
	
	yes.setTime(yes_mill);
	
	var year = yes.getYear();
	var month = yes.getMonth()+1;
	var day = yes.getDate();
	var d = year+'-'+month+'-'+day;
	
	dg = $('#datagrid').datagrid({    
		
	    url:'alter_find_date.action', 
		title:d,
		fitColumns:true,
		//resizeHandle:'both',
		rownumbers:true,
		height:450,
		width:1150,
		//pagination:true,
		autoRowHeight:true,
		//fit:true,
		singleSelect:true,
		//fixed:true,
		nowrap:false,
		resizable:true,
		idField : 'id',
		loadMsg:'正在加载数据，请稍等......',			
	    columns:[[    
  
	              {field:'alterdate',title:'日期',width:100},    
	              {field:'sysname',title:'系统名称',width:100},    
	              {field:'content',title:'变更内容',width:200},
	              {field:'id',title:'申请编号',width:100} ,
	              {field:'platform',title:'所属平台',width:100} ,
	              {field:'risk',title:'风险评估',width:100} ,
	              {field:'isagree',title:'是否完成审批',width:100} ,
	              {field:'execute',title:'实施情况',width:200} ,
	              {field:'recheck',title:'复核情况',width:200} ,
	              {field:'executer',title:'实施人',width:100} ,
	              {field:'rechecker',title:'复核人',width:100} ,
	    ]]   
	    
	   
	});
	
	
	
		$('#condition_submit').bind('click', function(){  
		   
			var csdate = $('#startday').calendar('options').current;
			var csyear = csdate.getYear();
			var csmonth = csdate.getMonth()+1;
			var csday = csdate.getDate();
			var csd = csyear+'-'+csmonth+'-'+csday;
			
			
			var cedate = $('#endday').calendar('options').current;
			var ceyear = cedate.getYear();
			var cemonth = cedate.getMonth()+1;
			var ceday = cedate.getDate();
			var ced = ceyear+'-'+cemonth+'-'+ceday;
			
			if(compare(csdate,cedate)==-1){
				alert("开始日期应小于截止日期!");
				return false;
			}
				
				
			
/*			$("#dg").datagrid({
				url:'alter_find_scope.action',
				title:csd+'--'+ced,
				
			});*/
			dg.datagrid({title:csd+'--'+ced,
				url:'alter_find_scope.action',
				queryParams: {
					sdate:csd,
					edate:ced
				}
					
			}); 
/*			dg.datagrid(
					
					'load',
					
					{sdate:csd,
					 edate:ced}
			); */
			
			 
		 });
		
	
});


function compare(d1,d2){
	
	if(d1.getYear()<=d2.getYear()){
		if(d1.getMonth()+1<=d2.getMonth()+1){
			if(d1.getDate()<=d2.getDate())
				return 1;
			else
				return -1;
		}else
			return -1;
	}else
		return -1;
}

