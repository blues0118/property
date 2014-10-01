$.extend({
    gridInit: function(gridObject,treename,tabletype,colNames,colModel,pageer,size,hidden) {
    	
    	jQuery("#"+gridObject).jqGrid({
			datatype: "local",//数据类型
			autowidth:true,
	        height: "70%",//高度
            colNames:colNames,//列名
            colModel:colModel,
            loadtext: "正在加载数据......",
            viewrecords: true,//是否在浏览导航栏显示记录总数
            rownumbers:true,//添加左侧行号
            //postData:postData,
            hiddengrid: hidden,
            pager: pageer,//分页
            emptyrecords: "没有数据",
            rowNum:0,//默认显示行数
            multiselect: true, //支持多项选择
            //multikey:"shiftKey",
            multiboxonly:true,
            caption: treename,//列表标题
            loadComplete: function() {
            	if (tabletype == '01') {
            		ajMenu();
            	}
            }           
	        
	    });
    	jQuery("#"+gridObject).jqGrid('navGrid',pageer,{edit:false,add:false,del:false,search:false});
    	if (!hidden) {
    		//初始化大小
        	$("#"+gridObject).setGridHeight(size);
    	}
    },
    loadGridData:function(_option) {
    	jQuery("#"+_option.gridObject).GridUnload();
    	
    	jQuery("#"+_option.gridObject).jqGrid({
    		url:_option.url,
			mtype:"POST",
			datatype: _option.datatype,//数据类型
			autowidth:true,
	        height: "70%",//高度
            colNames:_option.colNames,//列名
            colModel:_option.colModel,
            loadtext: "正在加载数据......",
            viewrecords: true,//是否在浏览导航栏显示记录总数
            rownumbers:true,//添加左侧行号
            postData:_option.postData,
            hiddengrid: false,
            jsonReader: {
            	root: "rows",
            	total: "totalpages",
            	page: "currpage",
            	records: "totalrecords",
            	repeatitems: false
            },
            prmNames : {
            	page:"page", // 表示请求页码的参数名称
            	rows:"rows", // 表示请求行数的参数名称
            	sort: "sidx", // 表示用于排序的列名的参数名称
            	order: "sord", // 表示采用的排序方式的参数名称
            	search:"_search", // 表示是否是搜索请求的参数名称
            	nd:"nd", // 表示已经发送请求的次数的参数名称
            	id:"id", // 表示当在编辑数据模块中发送数据时，使用的id的名称
            	oper:"oper", // operation参数名称
            	editoper:"edit", // 当在edit模式中提交数据时，操作的名称
            	addoper:"add", // 当在add模式中提交数据时，操作的名称
            	deloper:"del", // 当在delete模式中提交数据时，操作的名称
            	subgridid:"id", // 当点击以载入数据到子表时，传递的数据名称
            	npage: null,
            	totalrows:"totalrows" // 表示需从Server得到总共多少行数据的参数名称，参见jqGrid选项中的rowTotal
            },
            pager: _option.pageer,//分页
            emptyrecords: "没有数据",
            rowNum:_option.page,//默认显示行数
            //rowList:[10,20,50],
            multiselect: true, //支持多项选择
            multiboxonly:true,
            caption: _option.title,//列表标题
            rownumWidth:50,
            loadComplete: loadComplete,
            gridComplete:gridComplete,
            onSelectRow: gridSel,
    		onHeaderClick:gridState,
            beforeSelectRow : function multiSelectHandler(sid, e) {
				var grid = $(e.target).closest("table.ui-jqgrid-btable");
				var ts = grid[0], td = e.target;
				var scb = $(td).hasClass("cbox");
				if ((td.tagName == 'INPUT' && !scb) || td.tagName == 'A') {
					return true;
				}
				var sel = grid.getGridParam('selarrrow');
				var selected = $.inArray(sid, sel) >= 0;
				if (e.ctrlKey || (scb && (selected || !e.shiftKey))) {
					grid.setSelection(sid, true);
				} else {
					if (e.shiftKey) {
						var six = grid.getInd(sid);
						var min = six, max = six;
						$.each(sel, function() {
							var ix = grid.getInd(this);
							if (ix < min)
								min = ix;
							if (ix > max)
								max = ix;
						});
						while (min <= max) {
							var row = ts.rows[min++];
							var rid = row.id;
							if (rid != sid && $.inArray(rid, sel) < 0) {
								grid.setSelection(row.id, false);
							}
						}
					} else if (!selected) {
						grid.resetSelection();
					}
					if (!selected) {
						grid.setSelection(sid, true);
					} else {
						var osr = grid.getGridParam('onSelectRow');
						if ($.isFunction(osr)) {
							osr(sid, true);
						}
					}
				}
			}
	    });
		
    	jQuery("#"+_option.gridObject).jqGrid('navGrid',_option.pageer,{edit:false,add:false,del:false,search:false,refreshstate:'current'});
    	//初始化大小
    	$("#"+_option.gridObject).setGridHeight(_option.size);
    	//设置了冻结列后，选中行会报错，
    	//$("#"+_option.gridObject).jqGrid('setFrozenColumns');  
    }
});
//案卷级右键菜单
function ajMenu() {
	$("#gview_dataGrid").contextMenu('myMenu_aj', {
    	menuStyle: {
    		backgroundColor: '#fcfdfd',
            border: '2px solid #a6c9e2',
            //maxWidth: '600px',
            width: '170px', // to have good width of the menu 
            padding:'1px 1px 5px 1px'
	    },
        itemStyle : {
          //fontFamily: 'verdana',
          //backgroundColor: '#666',
          //color: '#1d5987',
          //border: 'none',
          //padding: '1px'
        },
        shadow:false,
        itemHoverStyle: {
        	border: '1px solid #79b7e7',
	        color: '#1d5987',
	        backgroundColor: '#d0e5f5',
	        cursor:'pointer'
        },
        //重写onContextMenu和onShowMenu事件  
//        onContextMenu: function(e) {  
//          if ($(e.target).attr('id') == 'dontShow') return false;  
//          else return true;  
//        },  
//    
//        onShowMenu: function(e, menu) {  
//          if ($(e.target).attr('id') == 'showOne') {  
//            $('#item_2, #item_3', menu).remove();  
//          }  
//          return menu;  
//        }  
        bindings: {
          'add': function(t) {
            add("01");
          },
          'del': function(t) {
            del();
          },
          'update_multiple': function(t) {
        	update_multiple();
          },
          'archiveImport': function(t) {
        	archiveImport();
          },
          'archiveExport': function(t) {
        	archiveExport();
          },
          'datacopy': function(t) {
        	datacopy();
          },
          'datapaster': function(t) {
        	datapaster();
          },
          'setshow': function(t) {
        	setshow('01');
          },
          'allwj': function(t) {
        	  allwj();
          },
          'doc': function(t) {
        	doc('01','');
          },
          'openprint': function(t) {
        	openprint();
          }
        }
    });
}

//案卷级右键菜单
function wjMenu() {
	$("#gview_dataGrid_w").contextMenu('myMenu_wj', {
    	menuStyle: {
    		backgroundColor: '#fcfdfd',
            border: '2px solid #a6c9e2',
            //maxWidth: '600px',
            width: '170px', // to have good width of the menu 
            padding:'1px 1px 5px 1px'
	        },
	        itemStyle : {
	          //fontFamily: 'verdana',
	          //backgroundColor: '#666',
	          //color: '#1d5987',
	          //border: 'none',
	          //padding: '1px'
	        },
	        shadow:false,
	        itemHoverStyle: {
	        	border: '1px solid #79b7e7',
            color: '#1d5987',
            backgroundColor: '#d0e5f5',
            cursor:'pointer'
	        },
	        bindings: {
	          'add': function(t) {
	            add("02");
	          },
	          'del': function(t) {
	            del("02");
	          },
	          'update_multiple': function(t) {
	        	update_multiple("02");
	          },
	          'archiveImport': function(t) {
	        	archiveImport("02");
	          },
	          'archiveExport': function(t) {
	        	archiveExport("02");
	          },
	          'datacopy': function(t) {
	        	datacopy("02");
	          },
	          'datapaster': function(t) {
	        	datapaster("02");
	          },
	          'setshow': function(t) {
	        	setshow('02');
	          },
	          'doc': function(t) {
	        	doc('02','');
	          },
	          'openprint': function(t) {
	        	openprint("02");
	          }
	        }
    });
}

//案卷级右键菜单
function aj_picMenu() {
	$(".xiangce").contextMenu('myMenu_aj_pic', {
    	menuStyle: {
    		backgroundColor: '#fcfdfd',
            border: '2px solid #a6c9e2',
            //maxWidth: '600px',
            width: '170px', // to have good width of the menu 
            padding:'1px 1px 5px 1px'
	        },
	        itemStyle : {
	          //fontFamily: 'verdana',
	          //backgroundColor: '#666',
	          //color: '#1d5987',
	          //border: 'none',
	          //padding: '1px'
	        },
	        shadow:false,
	        itemHoverStyle: {
	        	border: '1px solid #79b7e7',
            color: '#1d5987',
            backgroundColor: '#d0e5f5',
            cursor:'pointer'
	        },
	        bindings: {
	          'add': function(t) {
	            add("01");
	          },
	          'archiveImport': function(t) {
	        	archiveImport();
	          },
	          'archiveExport': function(t) {
	        	archiveExport();
	          },
	          'setshow': function(t) {
	        	setshow('01');
	          }
	        }
    });
}

//案卷级右键菜单
function wj_picMenu() {
	$(".xiangce").contextMenu('myMenu_wj_pic', {
    	menuStyle: {
    		backgroundColor: '#fcfdfd',
            border: '2px solid #a6c9e2',
            //maxWidth: '600px',
            width: '170px', // to have good width of the menu 
            padding:'1px 1px 5px 1px'
	        },
	        itemStyle : {
	          //fontFamily: 'verdana',
	          //backgroundColor: '#666',
	          //color: '#1d5987',
	          //border: 'none',
	          //padding: '1px'
	        },
	        shadow:false,
	        itemHoverStyle: {
	        	border: '1px solid #79b7e7',
            color: '#1d5987',
            backgroundColor: '#d0e5f5',
            cursor:'pointer'
	        },
	        bindings: {
	          'add': function(t) {
	            add("02");
	          },
	          'archiveImport': function(t) {
	        	archiveImport("02");
	          },
	          'archiveExport': function(t) {
	        	archiveExport("02");
	          },
	          'update_pic': function(t) {
	        	  upload_pic_multiple('IMAGE');
	          },
	          'update_video': function(t) {
	        	  upload_pic_multiple('VIDEO');
	          },
	          'setshow': function(t) {
	        	setshow('02');
	          },
	          'return_aj': function(t) {
	        	  returnAj();
	          }
	        }
    });
	
	$(".pic_li").contextMenu('myMenu_wj_pic_li', {
    	menuStyle: {
    		backgroundColor: '#fcfdfd',
            border: '2px solid #a6c9e2',
            //maxWidth: '600px',
            width: '170px', // to have good width of the menu 
            padding:'1px 1px 5px 1px'
	        },
	        itemStyle : {
	          //fontFamily: 'verdana',
	          //backgroundColor: '#666',
	          //color: '#1d5987',
	          //border: 'none',
	          //padding: '1px'
	        },
	        shadow:false,
	        itemHoverStyle: {
	        	border: '1px solid #79b7e7',
            color: '#1d5987',
            backgroundColor: '#d0e5f5',
            cursor:'pointer'
	        },
	        bindings: {
	          'del': function(t) {
	        	  del("02",t.id);
	          },
	          'edit':function(t) {
	        	edit(t.id,"02");  
	          },
	          'show': function(t) {
	        	  show(t.id,"02");
	          },
	          'showvideo': function(t) {
				    var slttype = $(t).attr("slttype");
					if (slttype == "VIDEO") {
						showvideo($(t).attr("id"));
					} else {
						alert("只有音频视频格式才能播放。");
					}
	          },
	          'setCover': function(t) {
	        	  setCover($(t).attr("id"));
	          },
	          'upload': function(t) {
	        	  upload_pic_single($(t).attr("id"));
	          },
	          'download': function(t) {
	        	  down_pic($(t).attr("id"));
	          }
	        }
    });
	
	$('.tip').mouseover(function(e){
		var img = new Image();
		img.src =this.src ;
		var h = img.height;
		
		if (h > 300) {
			h = h/2;
		}
		var $tip=$('<div id="tip"><div class="t_box"><div><s><i></i></s><img height="'+h+'" id="tipImg" src="'+this.src+'" /></div></div></div>');
		
		$('body').append($tip);
		$('#tip').show('fast');
		
		var imgHeight = $('#tipImg').height();
		//var oTop = $('#tip').offset().top;
		//var oHeight = $('#tip').height();
		var bheight = $('body').height();
		
		var imgWidth = $('#tipImg').width();
		var bWidth = $('body').width();
		
		var t = (bheight - e.pageY);
		var x = (bWidth - e.pageX);
		
		if (imgHeight > t) {
			var bb = imgHeight - t;
			$('#tip').css({"top":(e.pageY - bb)+"px","left":(e.pageX+30)+"px"});
		}
		else {
			$('#tip').css({"top":(e.pageY-60)+"px","left":(e.pageX+30)+"px"});
		}
		
		if (imgWidth > x) {
			var xx = bWidth - imgWidth - x -30;
			$('#tip').css({"left":xx+"px"});
		}
		
	}).mouseout(function(){
	   $('#tip').remove();
	}).mousemove(function(e){
		var imgHeight = $('#tipImg').height();
		//var oTop = $('#tip').offset().top;
		//var oHeight = $('#tip').height();
		var bheight = $('body').height();
		
		var imgWidth = $('#tipImg').width();
		var bWidth = $('body').width();
		
		var t = (bheight - e.pageY);
		var x = (bWidth - e.pageX);
		
		if (imgHeight > t) {
			var bb = imgHeight - t;
			$('#tip').css({"top":(e.pageY - bb)+"px","left":(e.pageX+30)+"px"});
		}
		else {
			$('#tip').css({"top":(e.pageY-60)+"px","left":(e.pageX+30)+"px"});
		}
		
		if (imgWidth > x) {
			var xx = bWidth - imgWidth - x -30;
			$('#tip').css({"left":xx+"px"});
		}
	})
}