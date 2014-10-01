
#初始化系统数据库

#帐户
insert into sys_account (id,accountcode,password,accountname,accountstate,accountmemo,roleid,projectid)
value ('1','admin','5f4dcc3b5aa765d61d8327deb882cf99','admin',1,'超级帐户','1','');

#角色
insert into sys_role (id,rolecode,rolememo)
value ('1','超级帐户','系统初始化默认角色。不可删除。拥有最大权限。不建议生产环境使用。');

#功能。一级、二级菜单。三级功能没有添加，可以在开发时在加
insert into sys_function (id,funcode,funencode,funpath,funsort,funparent,funtype)
value ('1','系统维护','SYSTEM','system/index.do',1,0,1);

insert into sys_function (id,funcode,funencode,funpath,funsort,funparent,funtype)
value ('2','物业管理','PROPERTY','property/index.do',2,0,1);

insert into sys_function (id,funcode,funencode,funpath,funsort,funparent,funtype)
value ('3','抄表管理','METER','meter/index.do',3,0,1);

insert into sys_function (id,funcode,funencode,funpath,funsort,funparent,funtype)
value ('4','台账管理','STANDINGBOOK','standingbook/index.do',4,0,1);

insert into sys_function (id,funcode,funencode,funpath,funsort,funparent,funtype)
value ('5','支出管理','PAY','pay/index.do',5,0,1);

insert into sys_function (id,funcode,funencode,funpath,funsort,funparent,funtype)
value ('6','统计分析','STATISTICS','statistics/index.do',6,0,1);

insert into sys_function (id,funcode,funencode,funpath,funsort,funparent,funtype)
value ('7','设备维护','EQUIPMENT','equipment/index.do',1,5,2);

insert into sys_function (id,funcode,funencode,funpath,funsort,funparent,funtype)
value ('8','人员管理','STAFF','staff/index.do',2,5,2);

insert into sys_function (id,funcode,funencode,funpath,funsort,funparent,funtype)
value ('9','其他支出','OTHERPAY','otherpay/index.do',3,5,2);

insert into sys_function (id,funcode,funencode,funpath,funsort,funparent,funtype)
value ('10','帐户管理','ACCOUNT','account/index.do',1,1,2);

insert into sys_function (id,funcode,funencode,funpath,funsort,funparent,funtype)
value ('11','角色管理','ROLE','role/index.do',2,1,2);

insert into sys_function (id,funcode,funencode,funpath,funsort,funparent,funtype)
value ('13','系统配置','CONFIG','config/index.do',3,1,2);

insert into sys_function (id,funcode,funencode,funpath,funsort,funparent,funtype)
value ('14','收费项目','CHARGE','charge/index.do',4,1,2);

insert into sys_function (id,funcode,funencode,funpath,funsort,funparent,funtype)
value ('15','代码维护','code','code/index.do',5,1,2);

#系统配置表
insert into sys_config (id,configkey,configvalue,configmemo,configname)
value ('1','PASSWORD','password','创建帐户时，默认的初始化密码','初始密码');

insert into sys_config (id,configkey,configvalue,configmemo,configname)
value ('2','SYSNAME','物业管理系统','系统名称','系统名称');

#系统初始化表
insert into sys_init (id,initkey,initvalue,initmemo)
value ('1','database','mysql','数据库连接类型');
insert into sys_init (id,initkey,initvalue,initmemo)
value ('2','systemtype','','');
insert into sys_init (id,initkey,initvalue,initmemo)
value ('3','registcode','','');


#数据字典表----

#单元状态
insert into sys_datadic (tablename,fieldname,fieldcode,memo)
value ('unit','unitstate','1:自用  2:已出租  3:其他','单元状态');
#单元类型
insert into sys_datadic (tablename,fieldname,fieldcode,memo)
value ('unit','itemtype','1:收入  2:支出','费用类型');

#费用类别
insert into sys_datadic (tablename,fieldname,fieldcode,memo)
value ('chargeitem','itemcatagory','1:正常  2:押金   3:预收款','费用类型');
#计算方式
insert into sys_datadic (tablename,fieldname,fieldcode,memo)
value ('chargeitem','itemmode','1:套内面积 2:个数  3:建筑面积','计算方式');
#计算单位
#insert into sys_datadic (tablename,fieldname,fieldcode,memo)
#value ('itemunit','itemmode','1:按次收费 2:按天收费  3:按月收费  4:按年收费','计算单位');
#收费方式 
insert into sys_datadic (tablename,fieldname,fieldcode,memo)
value ('chargeitem','chargecatagory','1:周期性 2:一次性  3:临时性  4:季节性','收费方式');
#收费单价为数字chargeprice。chargepriceunit为收费单价核算标准
insert into sys_datadic (tablename,fieldname,fieldcode,memo)
value ('chargeitem','chargepriceunit','1:日 2:月  3:年','收费单价');
#收费周期	chargeperiod 为数字，例如1，2.    chargeperiodunit为收费周期单位标准
insert into sys_datadic (tablename,fieldname,fieldcode,memo)
value ('chargeitem','chargeperiodunit','1:日 2:月  3:年','收费周期');
#按表计费类型
insert into sys_datadic (tablename,fieldname,fieldcode,memo)
value ('chargeitem','watchtype','1:水费 2:电费 3:燃起','按表计费类型');

#账期状态
insert into sys_datadic (tablename,fieldname,fieldcode,memo)
value ('standingbookterm','tremstatus','1:未结束 2:已结束','账期状态');

#单元账期状态
insert into sys_datadic (tablename,fieldname,fieldcode,memo)
value ('unitterm','unittermstatus','1:未结束 2:已结束','单元账期状态');

#台账状态
insert into sys_datadic (tablename,fieldname,fieldcode,memo)
value ('standingbook','chargestatus','1:未结束 2:已结束','台账状态');

#收款单状态
insert into sys_datadic (tablename,fieldname,fieldcode,memo)
value ('chargenote','chargestatus','1:未结束 2:已结束','收款单状态');

#账户状态
insert into sys_datadic (tablename,fieldname,fieldcode,memo)
value ('sys_account','accountstate','0:禁用 2:启用','账户状态');

#功能类型 3的不需要有路径，只是判断账户是否可以使用功能就可以
insert into sys_datadic (tablename,fieldname,fieldcode,memo)
value ('sys_function','funtype','1:一级菜单 2:二级菜单  3:模块下功能','功能类型');





