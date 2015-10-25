/**
 * 
 *//**
 * 
 */

$(document).ready(function() {
    	//var panel = $('#left_menu').accordion('getSelected');
    	//panel.panel('collapse');
	$('#mb1').bind('click', function(){   
    	var isExist = $('#tt').tabs('exists','变更信息录入');
		if(isExist){
			$('#tt').tabs('close','变更信息录入');
		}
		$('#tt').tabs('add',{   
		    title:'变更信息录入',   
		    href:'alterinsert.html', 
		    closable:true, 
		});
    });   

$('#mb2').bind('click', function(){   
	var isExist = $('#tt').tabs('exists','变更信息按日期查询');
	if(isExist){
		$('#tt').tabs('close','变更信息按日期查询');
	}
	$('#tt').tabs('add',{   
	    title:'变更信息按日期查询',   
	    href:'findbyday.html',   
	    closable:true,
	});
}); 
$('#mb3').bind('click', function(){   
	var isExist = $('#tt').tabs('exists','变更信息按时间段查询');
	if(isExist){
		$('#tt').tabs('close','变更信息按时间段查询');
	}
	$('#tt').tabs('add',{   
	    title:'变更信息按时间段查询',   
	    href:'findbyscope.html', 
	    closable:true, 
	});
}); 

$('#mb4').bind('click', function(){   
	var isExist = $('#tt').tabs('exists','事件信息录入');
	if(isExist){
		$('#tt').tabs('close','事件信息录入');
	}
	$('#tt').tabs('add',{   
	    title:'事件信息录入',   
	    href:'caseinsert.html',   
	    closable:true,
	});
}); 
$('#mb5').bind('click', function(){   
	var isExist = $('#tt').tabs('exists','事件信息按日期查询');
	if(isExist){
		$('#tt').tabs('close','事件信息按日期查询');
	}
	$('#tt').tabs('add',{   
	    title:'事件信息按日期查询',   
	    href:'caseinday.html',   
	    closable:true,
	});
 }); 
 $('#mb6').bind('click', function(){   
	var isExist = $('#tt').tabs('exists','事件信息批量导入');
	if(isExist){
		$('#tt').tabs('close','事件信息批量导入');
	}
	$('#tt').tabs('add',{   
	    title:'事件信息批量导入',   
	    href:'uploadfile.jsp',   
	    closable:true,
	});
 }); 
});    
 