package net.ussoft.property.service;

import java.util.List;

import net.ussoft.property.model.Meter;


public interface IMeterService {
	public List<Meter> list(Meter t);
	public List<Meter> list();
}
