


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
	
	dg=$('#dg').datagrid({    
		url:'case_find_date.action',   
		title:d,
		fitColumns:true,
		//resizeHandle:'both',
		rownumbers:true,
		height:450,
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
	{field:'id',title:'事件编号',width:100},    
	{field:'relevance',title:'问题关联',width:100},    
	{field:'description',title:'事件描述',width:150},
	{field:'starttime',title:'开始时间',width:100} ,
	{field:'endtime',title:'结束时间',width:100} ,
	{field:'mins',title:'持续时长(分钟)',width:100} ,
	{field:'scope',title:'影响范围',width:100} ,
	{field:'solution',title:'解决措施',width:150} ,
	{field:'reason',title:'事件原因',width:150} ,
	{field:'remark',title:'备注',width:100}   
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

		dg.datagrid({title:csd+'--'+ced,
			url:'case_find_scope.action',
			queryParams: {
					sdate:csd,
					edate:ced
				}
			//sortName:'alterdate',
					
	}); 

		 
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
























