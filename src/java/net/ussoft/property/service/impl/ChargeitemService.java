package net.ussoft.property.service.impl;

/**
 * 收费项目管理
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import net.ussoft.property.dao.ChargeitemDao;
import net.ussoft.property.model.Chargeitem;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.service.IChargeitemService;
import net.ussoft.property.util.ChargeitemUtil;
import net.ussoft.property.util.DateUtil;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChargeitemService implements IChargeitemService {
	
	@Resource
	private ChargeitemDao chargeitemDao;
	

	@Override
	public Chargeitem getById(String id) {
		return chargeitemDao.get(id);
	}
	
	@Override
	public List<Chargeitem> list() {
		return chargeitemDao.getAll("accountcode");
	}
	
	@Override
	public PageBean<Chargeitem> list(Chargeitem t,PageBean<Chargeitem> pageBean) {
		pageBean = chargeitemDao.search(t, pageBean);
		return pageBean;
	}

	@Transactional("txManager")
	@Override
	public Chargeitem insert(Chargeitem chargeitem) {
		chargeitemDao.save(chargeitem);
		return chargeitem;
	}

	@Transactional("txManager")
	@Override
	public int update(Chargeitem chargeitem) {
		Chargeitem tmp = chargeitemDao.update(chargeitem);
		if (null != tmp) {
			return 1;
		}
		return 0;
	}

	@Transactional("txManager")
	@Override
	public int delete(String id) {
		
		return chargeitemDao.del(id);
	}
	@Override
	public List<Chargeitem> list(Chargeitem chargeitem) {
		return chargeitemDao.search(chargeitem);
	}
	@Override
	public List<Map<String,Object>> list(String projeuctid,String unitid,String isWatch) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String date_now = sdf.format(new Date());   
		StringBuffer sql = new StringBuffer("select ci.id,ci.itemcode,ci.chargeremark,ci.itemcontent,ci.iswatch,ci.watchtype,").
				append("ci.itemsort,ci.projectid,ci.unitid,ci.watchnumber,ci.begindate,ci.reminddate,ci.nextdate,u.unitcode, ").
				append(" mi.lastnumber,mi.newnumber ").
				append(" from chargeitem ci left join unit u on ci.unitid=u.id").
				append(" left join meteritem mi on mi.chargeitemid= ci.id ").
				append(" where 1=1");
		if(unitid!=null && !"".equals(unitid)){
			if(unitid.split(",").length==1){
				sql.append(" and ci.unitid in ('"+unitid+"')");
			}else{
				String unitids = "'"+unitid.split(",")[0]+"'";
				for(int i=1;i<unitid.split(",").length;i++){
					unitids += "'"+unitid.split(",")[i]+"'";
				}
				sql.append(" and ci.unitid in ("+unitids+")");
			}
		}
		if(isWatch!=null && !"".equals(isWatch)){
			sql.append(" and ci.isWatch='"+isWatch+"'");
		}
		sql.append(" and ci.reminddate<='"+date_now+"'");
		List<Object> sqlValues = new ArrayList<Object>();
		return chargeitemDao.searchForMap(sql.toString() , sqlValues);
	}
	@Transactional("txManager")
	@Override
	public int addChargeitem(String unitid, String ids) {
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String[] addUnitStrings = unitid.split(",");
			String[] addStrings = ids.split(",");
			Chargeitem chargeitem = null;
			Chargeitem chargeitemNew = null;
			for(String unitidi:addUnitStrings){
				for (String id : addStrings) {
					chargeitem = new Chargeitem();
					chargeitem.setId(id);
					chargeitemNew = chargeitemDao.search(chargeitem).get(0);
					Map<String,Object> itemcontent = ChargeitemUtil.translateItemcontent(chargeitemNew.getItemcontent());
					
					chargeitemNew.setId(UUID.randomUUID().toString());
					chargeitemNew.setUnitid(unitidi);
					chargeitemNew.setBegindate(format.format(new Date()));//开始日期默认为录入时间
					if("日".equals(itemcontent.get("chargeperiodunit"))){
						chargeitemNew.setNextdate(DateUtil.add(chargeitemNew.getBegindate(),0,0,1));//下次缴费日期：开始日期+收费周期
					}else if("月".equals(itemcontent.get("chargeperiodunit"))){
						chargeitemNew.setNextdate(DateUtil.add(chargeitemNew.getBegindate(),0,1,0));//下次缴费日期：开始日期+收费周期
					}else if("年".equals(itemcontent.get("chargeperiodunit"))){
						chargeitemNew.setNextdate(DateUtil.add(chargeitemNew.getBegindate(),1,0,0));//下次缴费日期：开始日期+收费周期
					}
					if("日".equals(itemcontent.get("chargeperiodunit"))){
						chargeitemNew.setReminddate(chargeitemNew.getNextdate());//提醒日期：下次缴费日期向前推5天
					}else{
						chargeitemNew.setReminddate(DateUtil.add(chargeitemNew.getNextdate(),0,0,5));//提醒日期：下次缴费日期向前推5天
					}
					
					
					chargeitemDao.save(chargeitemNew);
				}
			}
		}catch(Exception e){
			return 0;
		}
		return 1;
	}
}