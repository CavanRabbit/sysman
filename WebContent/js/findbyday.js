var editRow = undefined; //记录选中的行
var alter_dg;
$(document).ready(function(){
	
	
	$('#update').dialog({
	
		closed:true
	});
	var date = new Date();
	
	var yes_mill = date.getTime()-1000*60*60*24;
	
	var yes = new Date();
	
	yes.setTime(yes_mill);
	
	var year = yes.getYear();
	var month = yes.getMonth()+1;
	var day = yes.getDate();
	var d = year+'-'+month+'-'+day;
	
	alter_dg = $('#alter_dg').datagrid({    
	
    url:'alter_find_date.action', 
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
			iconCls: 'icon-edit',
			text:'编辑',
			handler: function(){
			update();
			}
		},'-',{
			iconCls: 'icon-remove',
			text:'删除',
			handler: function(){del();}
		}
		],
		
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
		   
			//alert($('#day').datebox('getValue'));
			d=$('#alter_day').datebox('getValue');
			//alert($('#alter_day').datebox('getValue'));
			$("#alter_dg").datagrid({title:d}); 
			 $('#alter_dg').datagrid('load',{
				 datepoint:$('#alter_day').datebox('getValue'),
			 });
			 
		 });
		
});



/*
 * 删除一行行记录
 */
function del() {
	
	//alert("delete");
	if (editRow != undefined) {
		alter_dg.datagrid('endEdit', editRow);
		return;
	}
	var rows = alter_dg.datagrid('getSelections');
	//var ids = [];
	var id;
	if (rows.length > 0) {
		$.messager.confirm('请确认', '您要删除当前所选项目？', function(r) {
			if (r) {
		/*		for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i].id);
				}*/
				id=rows[0].id;
				$.ajax({
					url : 'alter_del.action',
					type:'POST',
					data : {
						id:id
					},
					dataType : 'json',
					success : function(response) {
						alter_dg.datagrid('load');
						alter_dg.datagrid('unselectAll');
						$.messager.alert('成功','删除成功','info');
					}
				});
			}
		});
	} else {
		$.messager.alert('提示', '请选择要删除的记录！', 'error');
	}
}

/*
 * 编辑一行记录
 */



function update(){
	
	var rows = alter_dg.datagrid('getSelections');
	if(rows.length==1){
		
		
		$("#mo_id").attr("value",rows[0].id);
		$("#mo_id").attr("disabled","true");
		
		var mo_date = rows[0].date;
		
		$('#update').dialog({
			title:'更新信息',
			width:400,
			height:400,
			closable:true,
			modal:true,
			buttons:[{
				text:'提交',
				handler: function(){
				
		/*			var ex = $('#mo_execute').val();
					alert(ex);
					var rec = $('#mo_recheck').val()
					alert(rec);*/
					  $.ajax({type:'POST',
					      url:'alter_update.action',
					      data:{
					    	  	 id:$('#mo_id').val(),
					    	  	 execute:$('#mo_execute').val(),
					    	  	 recheck:$('#mo_recheck').val()
					    	  	},
					    	 cache:true,
					    	 dataType:'json',
					    	 success:function(data){
					    	  	 var status = data.status;
					    	  	 if(status=="fail")
					    	  		alert("更新失败，请重新更新");
					    	  	 else
					    	  	 {
					    	  	 		$('#update').dialog('close');
					    				 $('#alter_dg').datagrid('load',{
					    					 datepoint:mo_date,
					    				 });
					    					$("#mo_id").attr("value","");
					    					$("#mo_execute").attr("value","");
					    					$("#mo_recheck").attr("value","");
					    	  	 		$.messager.show({
					    	  	 			title:'更新成功',
					    	  	 			msg:'已成功更新信息',
					    	  	 			timeout:10000,
					    	  	 			showType:'slide'
					    	  	 		});
					    	  	 }
					    	  	 }
					    	  });
				}
			}]
		});//end dialog

		$('#update').dialog('open');
		
	}else{
		$.messager.alert('错误','请选择要编辑的行!','error');
	}
}

