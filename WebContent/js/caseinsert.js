
$(document).ready(function(){
	

$('#test').bind('click',function(){
	
	//alert("dsadas");
	var date = new Date(2015,7,1);
	//alert("001");
	var yes_mill = date.getTime()-1000*60*60*24;
	//alert("002");
	var yes = new Date();
	//alert("005");
	yes.setTime(yes_mill);
	//alert("004");
	var year = yes.getYear();
	var month = yes.getMonth();
	var day = yes.getDate();
	var d = year+'-'+month+'-'+day;
	//alert(d);
});

$('#sbmt').bind('click',function(){
	
	//alert($('#date').val());
	
	  // var id = $('#id').val();
	   var relevance = $('#relevance').val();
	   var description=$('#description').val();
	  // var starttime = $('#start').val();
	   //var endtime = $('#end').val();
	   var starttime = $('#start').datetimebox('getValue');
	   var endtime = $('#end').datetimebox('getValue');
	  // mins:$('#mins').val(),
	   var scope = $('#scope').val();
	   var solution = $('#solution').val();
	   var reason = $('#reason').val();
	  var remark = $('#remark').val();
	  var mins = $('#mins').val();
	  
	  if( relevance=="" || description=="" || starttime=="" || endtime=="" || scope==""
		  || solution=="" || reason=="" ||mins==""){
		  
		  alert("请将必填字段填写完整");
		  return;
	  }
	$.ajax({
		
		url:'case_add.action', 

		timeout:10000,
		data:{
		 
			   id:$('#id').val(),
			   relevance:$('#relevance').val(),
			   description:$('#description').val(),
			   starttime:$('#start').datetimebox('getValue'),
			   endtime:$('#end').datetimebox('getValue'),
			  // mins:$('#mins').val(),
			   scope:$('#scope').val(),
			   solution:$('#solution').val(),
			   reason:$('#reason').val(),
			   remark:$('#remark').val(),
			   mins:$('#mins').val()
		},
		dataType:'json',
		error:function(re,status,errThrown){
			
			if(status=="timeout"){
				alert("请求超时,请重新提交");
			}
		},
		success:function(data){
		//alert(data.status);
		if(data.status=="success"){
			alert("录入成功！");
			//$('#id').attr("value","");
			$('#relevance').attr("value","");
			$('#descripton').attr("value","");
			$('#start').attr("value","");
			$('#end').attr("value","");
			$('#scope').attr("value","");
			$('#solution').attr("value","");
			$('#reason').attr("value","");
			$('#remark').attr("value","");
			
		}else
		{
			alert("录入失败，请重新提交");
		}
			
	}
	
	});
});
   
	 
});