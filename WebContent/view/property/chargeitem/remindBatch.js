var remind = new Remind();
$(function(){
	initBatch();//初始化页面信息
})
function initBatch(){
	var data={
			projeuctId:$("#projeuctId").val(),
			unitid:$("#unitid").val()
	};
	dataList = remind.initData(data);
	drawTable(dataList);
}
function drawTable(dataList){
	var str='';
	for(var i=0;i<dataList.length;i++){
		var data = dataList[i];
		//parseFloat(data.newnumber)-parseFloat(data.lastnumber)
		var lastnumber = '';
		if(data.lastnumber !=null && data.lastnumber!=undefined && data.lastnumber !='undefined'){
			lastnumber = data.lastnumber;
		}else{
			lastnumber = '0';
		}
		var newnumber = '';
		if(data.newnumber !=null && data.newnumber!=undefined && data.newnumber !='undefined'){
			newnumber = data.newnumber;
		}else{
			newnumber = '0';
		}
		str += '<tr>';
		str +=		'<td>'+data.unitcode+'</td>';//单元编号
		str +=		'<td>'+data.itemcode+'</td>';//费用名称
		str +=		'<td>'+lastnumber+"~"+newnumber+'</td>';//起~止
		str +=		'<td>200</td>';//数量
		str +=		'<td>'+data.watch_price+'</td>';//单价
		str +=		'<td>'+'50'+'</td>';//先写死 待确定//金额
		str +=		'<td>'+data.chargeremark+'</td>';//备注
		str +=		'<td><a href="javascript:del(\''+data.id+'\')">删除</a>&nbsp;<a href="javascript:edit(\''+data.id+'\')">编辑</a></td>';//操作
		str += '</tr>';
		
	}

	$("#dataTableTbody").append(str);
}
function del(id){
	if(confirm("确定要删除吗？")){
		var data={
				chargeitemId:id
		};
		dataList = remind.deleteRemind(data);
	}
}
function edit(id){
	alert(id);
}

