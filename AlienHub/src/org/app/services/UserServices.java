package org.app.services;

import java.util.List;

import org.app.daos.UserDaoImpl;
import org.app.models.UserModel;



public class UserServices 
{
	private UserDaoImpl udaoimpl=new UserDaoImpl();
	private UserModel umodel=new UserModel();
	
	
	
	public String addUserService(UserModel umodel)
	{
		System.out.println("Data in Services"+umodel);
		System.out.println("Calling Dao");
		return udaoimpl.addUser(umodel);
		
	}
	
	public UserModel userDetails(UserModel umodel)
	{
		UserModel umodelser=udaoimpl.userDetails(umodel);
		
		System.out.println("In service layer:"+umodelser);
		
		if(umodelser==null)
		{
			return null;
		
		
	}
		else 
		{
			return umodelser;
		}
	}
	public int validateLoginService(UserModel umodel)
	{
		System.out.println("Validate Login Service calling Validate Login in DAO ");
		return udaoimpl.validateLogin(umodel);
	}
}
