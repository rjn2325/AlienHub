package org.app.daos;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.app.models.UserModel;

public interface UserDao 
{
	
	String addUser(UserModel model);
	UserModel userDetails(UserModel umodel);
	String updateUser();
	String deleteUser();
	String fetchUser();
	int validateLogin(UserModel umodel);
	UserModel updatePicURL(HttpSession session,UserModel model);
	
}
