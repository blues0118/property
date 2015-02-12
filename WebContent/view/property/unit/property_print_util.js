/*
 * 收款通知单打印
 */
function create_print_data(data2) {
	//获取打印字段对应
	var field = {};
	field.itemcode = "itemcode";
	var html = ajml_html(field,data2);
	$("#print_div").html(html);
	onPrint();
}
/*
 * 批量收款通知单打印
 */
function create_print_data_batch(data2) {
	//获取打印字段对应
	var field = {};
	field.name = "name";
	field.start = "start";
	field.end = "end";
	field.number = "number";
	field.unit = "unit";
	field.amount = "amount";
	field.bz = "bz";
	var html = ajml_html_batch(field,data2);
	$("#print_div").html(html);
	if(html == ""){
		alert("暂无可打印的收款单数据");
	}else{
		onPrint();
	}
}
/*
 * 『固定收费项目』收费提醒通知单打印
 */
function create_print_data_remind(data2) {
	//获取打印字段对应
	var field = {};
	field.itemcode = "itemcode";
	field.itemtype = "itemtype";
	field.itemmode = "itemmode";
	field.itemunit = "itemunit";
	field.chargecatagory = "chargecatagory";
	field.chargeprice = "chargeprice";
	field.chargeperiod = "chargeperiod";
	field.reminddate = "reminddate";
	field.nextdate = "nextdate";
	var html = remind_ajml_html(field,data2);
	$("#print_div").html(html);
	onPrint();
}
/*
 * 『抄表收费项目』收费提醒通知单打印
 */
function create_print_data_remind_meter(data2) {
	//获取打印字段对应
	var field = {};
	field.itemcode = "itemcode";
	field.watchtype = "watchtype";
	field.watch_price = "watch_price";
	field.watchnumber = "watchnumber";
	field.chargeremark = "chargeremark";
	field.reminddate = "reminddate";
	field.nextdate = "nextdate";
	var html = remind_meter_ajml_html(field,data2);
	$("#print_div").html(html);
	onPrint();
}
//?? 这个就是打印了，实际就是将数据创建一个动态的iframe，调用浏览器打印，参数这样写就行。
function onPrint() {
    $(".my_show").jqprint({
        importCSS:false,
        debug:false
    });
}
/*
 * 收款通知单打印
 */
function ajml_html(field,data,path) {
	//将打印数据，转成对象
	var data_json = JSON.parse(data);
	var html = "";
	// Math.ceil 是script函数，四舍五入的，用来判断是否达到一页，如果是一页，就给div加上page-break-after: always，强制换页
	for (var i=0;i<Math.ceil(data_json.length/16);i++) {
		var index = 0;
		if (i == (Math.ceil(data_json.length/16) -1)) {
			html += "<div class=\"my_show\">";
		}
		else {
			html += "<div class=\"my_show\" style=\"page-break-after: always;\">";
		}
		html += "<table  cellspacing=\"0\" style=\"border-collapse: collapse; border-spacing: 0;background-color: transparent;max-width: 100%\" cellpadding=\"0\" width=\"100%\" class=\"print_font\">";
        html += "<thead>";
        html += "<tr>";
        html += "<th colspan=\"7\">";
        html += "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">";
        html += "<tr align=\"center\">";
        html += "<td style=\"font-size: 26px;padding-bottom: 5px;padding-top: 0px\">昆明君明物业服务有限公司</td>";
        html += "</tr>";
        html += "<tr align=\"center\">";
        html += "<td style=\"font-size: 26px;padding-bottom: 5px;padding-top: 0px\">收款通知单</td>";
        html += "</tr>";
        html += "<tr align=\"left\">";
        html += "<td style=\"font-size: 12px;padding-bottom: 5px;padding-top: 0px\">编号：NT11-05</td>";
        html += "</tr>";
        html += "</table>";
		html += "</th>";
		html += "</tr>";
		html += "<tr style=\"font-size:12px;font-weight:800;border:1px #000 solid;text-align: center;height: 30px\">";
		html += "<th style=\"border:1px #000 solid;\">费用名称</th>";
		html += "<th style=\"border:1px #000 solid;\">起~止</th>";
		html += "<th style=\"border:1px #000 solid;\">数量</th>";
		html += "<th style=\"border:1px #000 solid;\">单价</th>";
		html += "<th style=\"border:1px #000 solid;\">金额</th>";
		html += "<th style=\"border:1px #000 solid;\">滞纳金</th>";
		html += "<th style=\"border:1px #000 solid;\">备注</th>";
		html += "</tr>";
		html += "</thead>";
		html += "<tbody >";
		//itemcode-费用名称；lastnumber-起 ；newnumber-止;newnumber-lastnumber:数量
		//chargesum/(newnumber-lastnumber)单价；chargesum-金额;滞纳金；bookmemo-备注
		for (var j=i*16;j<i*16+16;j++) {
			if (j<data_json.length) {
				html += "<tr class=\"content_tr\" style=\"font-size:12px;height:30px;border:1px #000 solid;text-align: center\">";
				html += "<td style=\"border:1px #000 solid;\">"+ (field.itemcode == undefined ? '': data_json[j][field.itemcode]) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">"+ (field.lastnumber == undefined ? '' : data_json[j][field.lastnumber])+"至"+(field.newnumber == 'NOTHING' ? '' : data_json[j][field.newnumber]) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">"+ (parseInt(field.newnumber)-parseInt(field.lastnumber)) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">暂无</td>";
				html += "<td style=\"border:1px #000 solid;\">"+ (field.chargesum == undefined ? '' : data_json[j][field.chargesum]) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">暂无</td>";
				html += "<td style=\"border:1px #000 solid;\">"+ (field.bookmemo == undefined ? '' : data_json[j][field.bookmemo]) +"</td>";
				html += "</tr>";
			}else {	//??这里判断在全部打印完后，填充满空白格
				//add by wangzibo 2014-12-21 表格最下边的统计tr start
				if(index ==0){
					html += "<tr class=\"content_tr\" style=\"font-size:12px;height:30px;border:1px #000 solid;text-align: center\">";
					html += "<td style=\"border:1px #000 solid;\">金额合计（RMB）</td>";
					html += "<td colspan=\"2\" style=\"border:1px #000 solid;\">贰佰贰拾壹元整221.00</td>";
					html += "<td style=\"border:1px #000 solid;\">抵扣款</td>";
					html += "<td style=\"border:1px #000 solid;\">0.00</td>";
					html += "<td style=\"border:1px #000 solid;\">实收金额</td>";
					html += "<td style=\"border:1px #000 solid;\">221</td>";
					
					html += "<tr class=\"content_tr\" style=\"height:30px;text-align: center\">";
					html += "<td>制表人：张三</td>";
					html += "<td colspan=\"2\">打印日期：2014-12-21 &nbsp;&nbsp;</td>";
					html += "<td>经办人：</td>";
					html += "<td>李军</td>";
					html += "<td>客户签名：</td>";
					html += "<td></td>";
					html += "</tr>";
					index +=1;
				}
				//add by wangzibo 2014-12-21 表格最下边的统计tr end
				
				html += "<tr class=\"content_tr\" style=\"height:50px;text-align: center\">";
				html += "<td></td>";
				html += "<td></td>";
				html += "<td></td>";
				html += "<td></td>";
				html += "<td></td>";
				html += "<td></td>";
				html += "<td></td>";
				html += "</tr>";
			}
		}
		html += "</tbody>";
		html += "</table>";
        html += "</div>";
	}
	console.log("html============"+html);
	
	return html;
}
/*
 * 收款通知单打印
 */
function ajml_html_batch(field,data,path) {
	//将打印数据，转成对象
	var data_json = JSON.parse(data);
	var html = "";
	//一个单元一个单元的循环处理
	var rownumber=0;
	var accountname = data_json.info[0]["accountname"];//制表人、经办人
	var printdate = data_json.info[0]["printdate"];//打印日期
	var propertyname = data_json.info[0]["propertyname"];//物业公司名称
	for(var count=1;count<data_json.info.length;count++){
		var totalAmount=0.0;
		var unitcode_="";//单元编号
		var perUnitDetail = data_json[data_json.info[count][count-1]];
		// Math.ceil 是script函数，四舍五入的，用来判断是否达到一页，如果是一页，就给div加上page-break-after: always，强制换页
		var index = 0;
		rownumber += (perUnitDetail.length+6);//当前页面已经占据的行数统计，一页按照16行进行换页
		if (rownumber <= 20) {//i == (Math.ceil(perUnitDetail.length/16) -1)
			html += "<div class=\"my_show\">";
		}else {
			console.log(data_json.info[count][count-1]);
			rownumber = 0;//换页之后 当前页总数还原为0，重新计数
			html += "<div class=\"my_show\" style=\"page-break-after: always;\">";
		}
		for (var j=0;j<perUnitDetail.length+1;j++) {
			if (j<perUnitDetail.length) {
				if(unitcode_==undefined || unitcode_==null || unitcode_==''){
					unitcode_ = perUnitDetail[j]["unitcode"];
				}else{
					break;//此处的for循环只是为了拿到一个有实实在在的unitcode，拿到之后就退出循环
				}
			}
		}
		if(unitcode_ == undefined){
			unitcode_ = "暂无";
		}
		html += "<table  cellspacing=\"0\" style=\"border-collapse: collapse; border-spacing: 0;background-color: transparent;max-width: 100%\" cellpadding=\"0\" width=\"100%\" class=\"print_font\">";
        html += "<thead>";
        html += "<tr>";
        html += "<th colspan=\"7\">";
        html += "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">";
        html += "<tr align=\"center\">";
        html += "<td style=\"font-size: 26px;padding-bottom: 5px;padding-top: 0px\">"+propertyname+"</td>";
        html += "</tr>";
        html += "<tr align=\"center\">";
        html += "<td style=\"font-size: 26px;padding-bottom: 5px;padding-top: 0px\">收款通知单</td>";
        html += "</tr>";
        html += "<tr align=\"left\">";
        html += "<td style=\"font-size: 12px;padding-bottom: 5px;padding-top: 0px\">编号："+unitcode_+"</td>";
        html += "</tr>";
        html += "</table>";
		html += "</th>";
		html += "</tr>";
		html += "<tr style=\"font-size:12px;font-weight:800;border:1px #000 solid;text-align: center;height: 30px\">";
		html += "<th style=\"border:1px #000 solid;\">费用名称</th>";
		html += "<th style=\"border:1px #000 solid;\">起~止</th>";
		html += "<th style=\"border:1px #000 solid;\">数量</th>";
		html += "<th style=\"border:1px #000 solid;\">单价</th>";
		html += "<th style=\"border:1px #000 solid;\">金额</th>";
		html += "<th style=\"border:1px #000 solid;\">滞纳金</th>";
		html += "<th style=\"border:1px #000 solid;\">备注</th>";
		html += "</tr>";
		html += "</thead>";
		html += "<tbody >";
		//itemcode-费用名称；lastnumber-起 ；newnumber-止;newnumber-lastnumber:数量
		//chargesum/(newnumber-lastnumber)单价；chargesum-金额;滞纳金；bookmemo-备注
		for (var j=0;j<perUnitDetail.length+1;j++) {
			if (j<perUnitDetail.length) {
				html += "<tr class=\"content_tr\" style=\"font-size:12px;height:30px;border:1px #000 solid;text-align: center\">";
				html += "<td style=\"border:1px #000 solid;\">"+ (perUnitDetail[j][field.name] == undefined ? '': perUnitDetail[j][field.name]) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">"+ (perUnitDetail[j][field.start] == undefined ? '' : perUnitDetail[j][field.start])+"至"+(perUnitDetail[j][field.end] == undefined ? '' : perUnitDetail[j][field.end]) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">"+ (perUnitDetail[j][field.number] == undefined ? '': perUnitDetail[j][field.number]) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">"+(perUnitDetail[j][field.unit] == undefined ? '': perUnitDetail[j][field.unit])+"</td>";
				html += "<td style=\"border:1px #000 solid;\">"+ (perUnitDetail[j][field.amount] == undefined ? '' : perUnitDetail[j][field.amount]) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">0</td>";
				html += "<td style=\"border:1px #000 solid;\">"+(perUnitDetail[j][field.bz] == undefined ? '': perUnitDetail[j][field.bz])+"</td>";
				html += "</tr>";
				if(perUnitDetail[j][field.amount]!=undefined && perUnitDetail[j][field.amount]!=null
						&& perUnitDetail[j][field.amount]!=''){
					totalAmount += perUnitDetail[j][field.amount];//计算一个单元的总费用
				}
			}else {	//这里判断在全部打印完后，填充满空白格
				//add by wangzibo 2014-12-21 表格最下边的统计tr start
				if(index ==0){
					var moneyTrans = transMoney(totalAmount);
					html += "<tr class=\"content_tr\" style=\"font-size:12px;height:30px;border:1px #000 solid;text-align: center\">";
					html += "<td style=\"border:1px #000 solid;\">金额合计（RMB）</td>";
					html += "<td colspan=\"2\" style=\"border:1px #000 solid;\">"+moneyTrans+" "+totalAmount+"</td>";
					html += "<td style=\"border:1px #000 solid;\">抵扣款</td>";
					html += "<td style=\"border:1px #000 solid;\">0.00</td>";
					html += "<td style=\"border:1px #000 solid;\">实收金额</td>";
					html += "<td style=\"border:1px #000 solid;\">"+totalAmount+"</td>";
					
					html += "<tr class=\"content_tr\" style=\"height:30px;text-align: center\">";
					html += "<td>制表人："+accountname+"</td>";
					html += "<td colspan=\"2\">打印日期："+printdate+" &nbsp;&nbsp;</td>";
					html += "<td>经办人：</td>";
					html += "<td>"+accountname+"</td>";
					html += "<td>客户签名：</td>";
					html += "<td></td>";
					html += "</tr>";
					index +=1;
					
					html += "<tr class=\"content_tr\" style=\"height:50px;text-align: center\">";
					html += "<td></td>";
					html += "<td></td>";
					html += "<td></td>";
					html += "<td></td>";
					html += "<td></td>";
					html += "<td></td>";
					html += "<td></td>";
					html += "<td></td>";
					html += "<td></td>";
					html += "</tr>";
					break;
				}
				//add by wangzibo 2014-12-21 表格最下边的统计tr end
				
			}
			
		}
		html += "</tbody>";
		html += "</table>";
        html += "</div>";
	}
	
	return html;
}
/*
 * 『固定收费项目』收费提醒通知单打印
 */
function remind_ajml_html(field,data,path) {
	//？？将打印数据，转成对象
	var data_json = JSON.parse(data);
	var html = "";
	//?? Math.ceil 是script函数，四舍五入的，用来判断是否达到一页，如果是一页，就给div加上page-break-after: always，强制换页
	for (var i=0;i<Math.ceil(data_json.length/16);i++) {
		var index = 0;
		if (i == (Math.ceil(data_json.length/16) -1)) {
			html += "<div class=\"my_show\">";
		}
		else {
			html += "<div class=\"my_show\" style=\"page-break-after: always;\">";
		}
		html += "<table  cellspacing=\"0\" style=\"border-collapse: collapse; border-spacing: 0;background-color: transparent;max-width: 100%\" cellpadding=\"0\" width=\"100%\" class=\"print_font\">";
        html += "<thead>";
        html += "<tr>";
        html += "<th colspan=\"7\">";
        html += "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">";
        html += "<tr align=\"center\">";
        html += "<td style=\"font-size: 26px;padding-bottom: 5px;padding-top: 0px\">昆明君明物业服务有限公司</td>";
        html += "</tr>";
        html += "<tr align=\"center\">";
        html += "<td style=\"font-size: 26px;padding-bottom: 5px;padding-top: 0px\">待缴费收费项目</td>";
        html += "</tr>";
        html += "<tr align=\"left\">";
        html += "<td style=\"font-size: 12px;padding-bottom: 5px;padding-top: 0px\">编号：NT11-05</td>";
        html += "</tr>";
        html += "</table>";
		html += "</th>";
		html += "</tr>";
		html += "<tr style=\"font-size:12px;font-weight:800;border:1px #000 solid;text-align: center;height: 30px\">";
		html += "<th style=\"border:1px #000 solid;\">名称</th>";
		html += "<th style=\"border:1px #000 solid;\">费用类型</th>";
		html += "<th style=\"border:1px #000 solid;\">计算方式</th>";
		html += "<th style=\"border:1px #000 solid;\">计算单位</th>";
		html += "<th style=\"border:1px #000 solid;\">收费方式</th>";
		html += "<th style=\"border:1px #000 solid;\">收费单价</th>";
		html += "<th style=\"border:1px #000 solid;\">收费周期</th>";
		html += "<th style=\"border:1px #000 solid;\">提醒日期</th>";
		html += "<th style=\"border:1px #000 solid;\">下次收费日期</th>";
		html += "</tr>";
		html += "</thead>";
		html += "<tbody >";
		
		for (var j=i*16;j<i*16+16;j++) {
			if (j<data_json.length) {
				var chargePrice;
				if(data_json[j][field.chargepriceunit]!=null && data_json[j][field.chargepriceunit] != undefinde){
					chargePrice = data_json[j][field.chargeprice]+"/"+data_json[j][field.chargepriceunit];
				}else{
					chargePrice = data_json[j][field.chargeprice];
				}
				html += "<tr class=\"content_tr\" style=\"font-size:12px;height:30px;border:1px #000 solid;text-align: center\">";
				html += "<td style=\"border:1px #000 solid;\">"+ (data_json[j][field.itemcode] == undefined ? '': data_json[j][field.itemcode]) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">"+ (data_json[j][field.itemtype] == undefined ? '' : data_json[j][field.itemtype]) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">"+ (data_json[j][field.itemmode] == undefined ? '' : data_json[j][field.itemmode]) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">"+ (data_json[j][field.itemunit] == undefined ? '' : data_json[j][field.itemunit]) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">"+ (data_json[j][field.chargecatagory] == undefined ? '' : data_json[j][field.chargecatagory]) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">"+ (chargePrice == undefined ? '' : chargePrice) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">"+ (data_json[j][field.chargeperiod] == undefined ? '' : data_json[j][field.chargeperiod]) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">"+ (data_json[j][field.reminddate] == undefined ? '' : data_json[j][field.reminddate]) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">"+ (data_json[j][field.nextdate] == undefined ? '' : data_json[j][field.nextdate]) +"</td>";
				html += "</tr>";
			}else {	//??这里判断在全部打印完后，填充满空白格
				//add by wangzibo 2014-12-21 表格最下边的统计tr start
				if(index ==0){
					html += "<tr class=\"content_tr\" style=\"font-size:12px;height:30px;border:1px #000 solid;text-align: center\">";
					html += "<td style=\"border:1px #000 solid;\">金额合计（RMB）</td>";
					html += "<td colspan=\"3\" style=\"border:1px #000 solid;\">贰佰贰拾壹元整221.00</td>";
					html += "<td style=\"border:1px #000 solid;\">抵扣款</td>";
					html += "<td style=\"border:1px #000 solid;\">0.00</td>";
					html += "<td style=\"border:1px #000 solid;\">实收金额</td>";
					html += "<td colspan=\"2\"  style=\"border:1px #000 solid;\">221</td>";
					
					html += "<tr class=\"content_tr\" style=\"height:30px;text-align: center\">";
					html += "<td>制表人：张三</td>";
					html += "<td colspan=\"3\">打印日期：2014-12-21 &nbsp;&nbsp;</td>";
					html += "<td>经办人：</td>";
					html += "<td>李军</td>";
					html += "<td>客户签名：</td>";
					html += "<td colspan=\"2\"></td>";
					html += "</tr>";
					index +=1;
				}
				//add by wangzibo 2014-12-21 表格最下边的统计tr end
				
				html += "<tr class=\"content_tr\" style=\"height:50px;text-align: center\">";
				html += "<td></td>";
				html += "<td></td>";
				html += "<td></td>";
				html += "<td></td>";
				html += "<td></td>";
				html += "<td></td>";
				html += "<td></td>";
				html += "<td></td>";
				html += "<td></td>";
				html += "</tr>";
			}
		}
		html += "</tbody>";
		html += "</table>";
        html += "</div>";
	}
	console.log("html============"+html);
	
	return html;
}
/*
 * 『抄表收费项目』收费提醒通知单打印
 */
function remind_meter_ajml_html(field,data,path) {
	//？？将打印数据，转成对象
	var data_json = JSON.parse(data);
	var html = "";
	//?? Math.ceil 是script函数，四舍五入的，用来判断是否达到一页，如果是一页，就给div加上page-break-after: always，强制换页
	for (var i=0;i<Math.ceil(data_json.length/16);i++) {
		var index = 0;
		if (i == (Math.ceil(data_json.length/16) -1)) {
			html += "<div class=\"my_show\">";
		}
		else {
			html += "<div class=\"my_show\" style=\"page-break-after: always;\">";
		}
		html += "<table  cellspacing=\"0\" style=\"border-collapse: collapse; border-spacing: 0;background-color: transparent;max-width: 100%\" cellpadding=\"0\" width=\"100%\" class=\"print_font\">";
        html += "<thead>";
        html += "<tr>";
        html += "<th colspan=\"7\">";
        html += "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">";
        html += "<tr align=\"center\">";
        html += "<td style=\"font-size: 26px;padding-bottom: 5px;padding-top: 0px\">昆明君明物业服务有限公司</td>";
        html += "</tr>";
        html += "<tr align=\"center\">";
        html += "<td style=\"font-size: 26px;padding-bottom: 5px;padding-top: 0px\">待缴费收费项目</td>";
        html += "</tr>";
        html += "<tr align=\"left\">";
        html += "<td style=\"font-size: 12px;padding-bottom: 5px;padding-top: 0px\">编号：NT11-05</td>";
        html += "</tr>";
        html += "</table>";
		html += "</th>";
		html += "</tr>";
		html += "<tr style=\"font-size:12px;font-weight:800;border:1px #000 solid;text-align: center;height: 30px\">";
		html += "<th style=\"border:1px #000 solid;\">收费项名称</th>";
		html += "<th style=\"border:1px #000 solid;\">计费类型</th>";
		html += "<th style=\"border:1px #000 solid;\">单价</th>";
		html += "<th style=\"border:1px #000 solid;\">表最后读数</th>";
		html += "<th style=\"border:1px #000 solid;\">收费备注</th>";
		html += "<th style=\"border:1px #000 solid;\">提醒日期</th>";
		html += "<th style=\"border:1px #000 solid;\">下次收费日期</th>";
		html += "</tr>";
		html += "</thead>";
		html += "<tbody >";
		
		for (var j=i*16;j<i*16+16;j++) {
			if (j<data_json.length) {
				html += "<tr class=\"content_tr\" style=\"font-size:12px;height:30px;border:1px #000 solid;text-align: center\">";
				html += "<td style=\"border:1px #000 solid;\">"+ (data_json[j][field.itemcode] == undefined ? '': data_json[j][field.itemcode]) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">"+ (data_json[j][field.watchtype] == undefined ? '' : data_json[j][field.watchtype]) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">"+ (data_json[j][field.watch_price] == undefined ? '' : data_json[j][field.watch_price]) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">"+ (data_json[j][field.watchnumber] == undefined ? '' : data_json[j][field.watchnumber]) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">"+ (data_json[j][field.chargeremark] == undefined ? '' : data_json[j][field.chargeremark]) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">"+ (data_json[j][field.reminddate] == undefined ? '' : data_json[j][field.reminddate]) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">"+ (data_json[j][field.nextdate] == undefined ? '' : data_json[j][field.nextdate]) +"</td>";
				html += "</tr>";
			}else {	//??这里判断在全部打印完后，填充满空白格
				//add by wangzibo 2014-12-21 表格最下边的统计tr start
				if(index ==0){
					html += "<tr class=\"content_tr\" style=\"font-size:12px;height:30px;border:1px #000 solid;text-align: center\">";
					html += "<td style=\"border:1px #000 solid;\">金额合计（RMB）</td>";
					html += "<td colspan=\"2\" style=\"border:1px #000 solid;\">贰佰贰拾壹元整221.00</td>";
					html += "<td style=\"border:1px #000 solid;\">抵扣款</td>";
					html += "<td style=\"border:1px #000 solid;\">0.00</td>";
					html += "<td style=\"border:1px #000 solid;\">实收金额</td>";
					html += "<td style=\"border:1px #000 solid;\">221</td>";
					html += "<tr class=\"content_tr\" style=\"height:30px;text-align: center\">";
					html += "<td>制表人：张三</td>";
					html += "<td colspan=\"2\">打印日期：2014-12-21 &nbsp;&nbsp;</td>";
					html += "<td>经办人：</td>";
					html += "<td>李军</td>";
					html += "<td>客户签名：</td>";
					html += "<td></td>";
					html += "</tr>";
					index +=1;
				}
				//add by wangzibo 2014-12-21 表格最下边的统计tr end
				
				html += "<tr class=\"content_tr\" style=\"height:50px;text-align: center\">";
				html += "<td></td>";
				html += "<td></td>";
				html += "<td></td>";
				html += "<td></td>";
				html += "<td></td>";
				html += "<td></td>";
				html += "<td></td>";
				html += "</tr>";
			}
		}
		html += "</tbody>";
		html += "</table>";
        html += "</div>";
	}
	console.log("html============"+html);
	
	return html;
}
function transMoney(num) {  
	  var strOutput = "";  
	  var strUnit = '仟佰拾亿仟佰拾万仟佰拾元角分';  
	  num += "00";  
	  var intPos = num.indexOf('.');  
	  if (intPos >= 0)  
	    num = num.substring(0, intPos) + num.substr(intPos + 1, 2);  
	  strUnit = strUnit.substr(strUnit.length - num.length);  
	  for (var i=0; i < num.length; i++)  
	    strOutput += '零壹贰叁肆伍陆柒捌玖'.substr(num.substr(i,1),1) + strUnit.substr(i,1);  
	    return strOutput.replace(/零角零分$/, '整').replace(/零[仟佰拾]/g, '零').replace(/零{2,}/g, '零').replace(/零([亿|万])/g, '$1').replace(/零+元/, '元').replace(/亿零{0,3}万/, '亿').replace(/^元/, "零元");  
}
