package net.ussoft.property.vo;

import java.io.Serializable;
import java.util.List;

import net.ussoft.property.model.Chargeitem;
import net.ussoft.property.model.Lease;
import net.ussoft.property.model.Lease_agreement;
import net.ussoft.property.model.Meteritem;
import net.ussoft.property.model.Unit;

public class UnitAll implements Serializable{

	private static final long serialVersionUID = 1L;
	private Unit unit;//单元基本信息
	private Lease lease;//住户信息
	private List<Lease_agreement> agreementList;//合同信息
	private List<Meteritem> meteritemList;//合同信息
	private List<Chargeitem> chargeItemList;//收费项目
	
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	public Lease getLease() {
		return lease;
	}
	public void setLease(Lease lease) {
		this.lease = lease;
	}
	public List<Lease_agreement> getAgreementList() {
		return agreementList;
	}
	public void setAgreementList(List<Lease_agreement> agreementList) {
		this.agreementList = agreementList;
	}
	public List<Meteritem> getMeteritemList() {
		return meteritemList;
	}
	public void setMeteritemList(List<Meteritem> meteritemList) {
		this.meteritemList = meteritemList;
	}
	public List<Chargeitem> getChargeItemList() {
		return chargeItemList;
	}
	public void setChargeItemList(List<Chargeitem> chargeItemList) {
		this.chargeItemList = chargeItemList;
	}
	
	

}
