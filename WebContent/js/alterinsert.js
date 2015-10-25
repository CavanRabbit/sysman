
$(document).ready(function(){
	

$('#sbmt').bind('click',function(){
	
	//alert($('#date').val());
	//alert("start");
	var date = $('#date').datebox('getValue');
	//alert(date);
/*	var year = d.getYear();
	var month = d.getMonth()+1;
	var day = d.getDate();
	var date = year+'-'+month+'-'+day;*/
	//alert("dsadasdas");
	var sysname = $('#sysname').val();
	var content = $('#content').val();
	var id = $('#id').val();
/*	if(id.length<8){
		id = '00'+id;
	}*/
	var len = id.length;

	if(len<8){
	
		for(var i=0;i<8-len;i++)
			id = '0'+id;
	}
	
	var risk = $('#risk').val();
	var isagree = $('#isagree').val();
	var execute = $('#execute').val();
	var executer = $('#executer').val();
	var recheck = $('#recheck').val();
	var rechecker = $('#rechecker').val();
	var platform = $('#platform').val();
	
	
     
	if(date=="" || sysname=="" || content=="" || id==""||
			risk==""||isagree==""|| executer==""||rechecker==""||platform==""){
		
		
		alert("请将必填字段填写完整！");
		return;
	}
	
	$.ajax({
		
		url:'alter_add.action', 
		timeout:10000,
		data:{
		 
			  // datepoint:$('#date').val(),
			  datepoint:date,
			   sysname:$('#sysname').val(),
			   content:$('#content').val(),
			  // id:$('#id').val(),
			  id:id,
			  risk:$('#risk').val(),
			  isagree:$('#isagree').val(),
			  execute:$('#execute').val(),
			  executer:$('#executer').val(),
			  recheck:$('#recheck').val(),
			  rechecker:$('#rechecker').val(),
			  platform:$('#platform').val()
		},
		dataType:'json',
	error:function(re,status,errThrown){
		
		if(status=="timeout"){
			alert("请求超时,请重新提交");
		}
	},
	success:function(data){
		
		
		if(data.status=="success"){
			alert("录入成功！");
			
			$('#date').attr("value","");
			$('#sysname').attr("value","");
			$('#content').attr("value","");
			$('#id').attr("value","");
			$('#risk').attr("value","");
			$('#isagree').attr("value","");
			$('#execute').attr("value","");
			$('#executer').attr("value","");
			$('#recheck').attr("value","");
			$('#rechecker').attr("value","");
		}else if(data.status=="exist"){
			
			alert("对不起！本次录入的申请编号已存在于系统中！");
		}
			
		else
			alert("录入失败，请重新提交");
	}
	});
});
   
	 
});