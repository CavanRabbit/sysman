

$(document).ready(function(){
	
	
	var date = new Date();
	
	var yes_mill = date.getTime()-1000*60*60*24;
	
	var yes = new Date();
	
	yes.setTime(yes_mill);
	
	var year = yes.getYear();
	var month = yes.getMonth()+1;
	var day = yes.getDate();
	var d = year+'-'+month+'-'+day;
	
	$('#case_dg').datagrid({    
	
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
	toolbar: [{
		iconCls: 'icon-add',
		text:'批量导入',
		handler: function(){
		 importExcel();
		}
	}
	],
		
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
        {field:'remark',title:'备注',width:100} ,
    ]]    ,
	
}); 
		 
		

		$('#condition_submit').bind('click', function(){  
		   
			//alert($('#day').datebox('getValue'));
			d=$('#day').datebox('getValue');
			$("#case_dg").datagrid({title:d}); 
			 $('#case_dg').datagrid('load',{
				 date:$('#day').datebox('getValue'),
			 });
			 
		 });
	
});

function importExcel(){
	
	$('#importExcel').dialog({
		title:'文件导入',
		width:400,
		height:300,
		closable:true,
		modal:true,
		buttons:[{
			text:'导入',
			handler: function(){
				
					$('#ff').form({
					    url:'uploadfile.action',
					    success:function(data){
					    	var d = eval('(' + data + ')'); // change the JSON string to javascript object
							if (d.status=="success"){
								alert("导入成功");
							}else{
								alert("导入失败");
							}

					    }
					});
					// submit the form
					$('#ff').submit();
				
			}
		}]
	});
}
