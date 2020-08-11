package com.infodart.service;

import com.infodart.dao.BaseDao;

import com.infodart.dao.BaseDaoImpl;
import com.infodart.entity.Login;
import com.infodart.entity.Visitor;

public class BaseServiceImpl implements BaseService {

	private static BaseDao basedao;

	public BaseServiceImpl() {
		basedao = new BaseDaoImpl();
	}

	@Override
	public boolean login(String username, String password) {
		return basedao.login(username, password);
	}

	

	@Override
	public Login register(Login staff) {

		return basedao.register(staff);
	}

	@Override
	public Visitor registerVisitor(Visitor visitor) {
		
		return basedao.registerVisitor(visitor);
	}

	@Override
	
	
	public boolean ResetPassword(String currentpass, String newpass, String confirmpass) {
		return basedao.ResetPassword(currentpass, newpass, confirmpass);
	}
	
	@Override
	public String getRole()
	{
		return basedao.getRole();
	}
	@Override
	public int getId()
	{
		return basedao.getId();
	}
	@Override
	public boolean VisitorCheckout(int id)
	{
		return basedao.VisitorCheckout(id);
	}
	
}
