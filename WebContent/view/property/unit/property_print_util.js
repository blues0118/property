

function ajml_html(field,data,path) {
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
		
		for (var j=i*16;j<i*16+16;j++) {
			if (j<data_json.length) {
				html += "<tr class=\"content_tr\" style=\"font-size:12px;height:30px;border:1px #000 solid;text-align: center\">";
				html += "<td style=\"border:1px #000 solid;\">"+ (field.lastnumber == 'NOTHING' ? '': data_json[j][field.lastnumber]) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">"+ (field.lastnumber == 'NOTHING' ? '' : data_json[j][field.lastnumber]) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">"+ (field.lastnumber == 'NOTHING' ? '' : data_json[j][field.lastnumber]) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">"+ (field.lastnumber == 'NOTHING' ? '' : data_json[j][field.lastnumber]) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">"+ (field.lastnumber == 'NOTHING' ? '' : data_json[j][field.lastnumber]) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">"+ (field.lastnumber == 'NOTHING' ? '' : data_json[j][field.lastnumber]) +"</td>";
				html += "<td style=\"border:1px #000 solid;\">"+ (field.lastnumber == 'NOTHING' ? '' : data_json[j][field.lastnumber]) +"</td>";
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
//?? 这个就是打印了，实际就是将数据创建一个动态的iframe，调用浏览器打印，参数这样写就行。
function onPrint() {
    $(".my_show").jqprint({
        importCSS:false,
        debug:false
    });
}

function create_print_data(data2) {
	//获取打印字段对应
	var field = {};
	field.lastnumber = "lastnumber";
	field.newnumber = "newnumber";
	var html = ajml_html(field,data2);
	$("#print_div").html(html);
	onPrint();
}
