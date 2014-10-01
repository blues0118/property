package net.ussoft.property.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.ussoft.property.dao.FunctionDao;
import net.ussoft.property.model.Sys_function;
import net.ussoft.property.service.IFunctionService;

import org.springframework.stereotype.Service;

@Service
public class FunctionService implements IFunctionService {

	
	@Resource
	private FunctionDao functionDao;
	
	@Override
	public Sys_function getFunction(Sys_function function) {
		return functionDao.searchOne(function);
	}

	@Override
	public List<Sys_function> list() {
		return functionDao.getAll();
	}
	


}
