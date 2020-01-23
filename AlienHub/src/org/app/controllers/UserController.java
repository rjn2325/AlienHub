package org.app.controllers;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpSession;

import org.app.daos.UserDaoImpl;
import org.app.models.UserModel;
import org.app.services.UserServices;
import org.app.utils.FireStoreConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserController 
{
	
UserModel umodel;
	private UserServices uservice= new UserServices();
	private UserDaoImpl udao = new UserDaoImpl();
	FireStoreConfig fsc;
	
	@RequestMapping("/Signup.html")
	public String showSignup()
	{
		return "Signup";
	}
  
  @RequestMapping(value="/signup" , method=RequestMethod.GET)
  public String userSignUp(@ModelAttribute("usignup") UserModel umodel) throws Exception
  {
	  
		System.out.println("Calling Firebase");
		
		
		
		try {
			System.out.println("calling fb method in firestoreConfig class");
			fsc.saveData(umodel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String firestore_id=fsc.saveData(umodel);
		
		System.out.println("fid:"+firestore_id);
		
		umodel.setFid(firestore_id);
		
		System.out.println("fid from usermodel"+umodel.getFid());
		
		
	  System.out.println("form data:"+umodel);
	  System.out.println("Calling Services");
	  uservice.addUserService(umodel);
	return "redirect:/Signin.html";
  }
	
	
	  
 
  
  
  @RequestMapping("/Signin.html")
	public String showSignin()
	{
		return "Signin";
	}
  
  
  
  
  @RequestMapping(value="/Signinn",method=RequestMethod.GET)
  @ResponseBody
 // public String userProfile(@RequestParam String email , String password)
  public String userProfile(@ModelAttribute("usignin") UserModel umodel, HttpSession session, Model model) throws InterruptedException, ExecutionException, IOException
 
 
  {
	  //System.out.println("form data:"+umodel.getEmail() + " " +umodel.getPassword());
	  System.out.println("form data =:"+umodel.getEmail() + " and " +umodel.getPassword());
//	  return "User id and password invalid try again";
	 
  //UserModel udetails=uservice.userDetails(umodel);
  UserModel udetails=uservice.userDetails(umodel);

  
  System.out.println("data return from dao is:"+udetails);
  
	if(udetails==null)
  {
		return "User Id or Password is invalid";
		
  }
	  
	  else 
	  {
		  System.out.println("Login successfully");
		  session.setAttribute("uid", udetails.getUid());
		  session.setAttribute("fid", udetails.getFid());
		  System.out.println("uid" +udetails.getUid());
		  System.out.println("fid"+udetails.getFid());
		  
		  return "success";
		  
	  }

	  
  } 
    
	  
  
  

  
	 // UserModel udetails2=uservice.userDetails(umodel);
	  
//	  System.out.println("Calling Services");
//	  System.out.println("User Details:"+udetails);
//	  System.out.println("UID:"+udetails.getUid());
//	  System.out.println("uname:"+udetails.getName());

	  
		 
		  //String data="Login Failed Invalid Credentials";
		  //System.out.println("Login Failed Invalid Credentials");
		  //model.addAttribute("login_status",data);
		  //String data="Login Failed Invalid Credentials";
		 
		  
		 // return "redirect:/Signin.html";
		  
	  //}
	  

  
  

	@RequestMapping(value="/uploadProfilePicture")
	public String uploadPicture()
	{
		return "ProfilePicture";
	}
	
	
	@RequestMapping(value="/Profile.Html",method=RequestMethod.POST)
	public String saveProfilePicture(@RequestParam MultipartFile file,HttpSession session , Model model,UserModel umodel) throws IOException, InterruptedException, ExecutionException {
	
	String img_url=fsc.storeProfilePicture(file, session, model);
	System.out.println("URL at controller:"+img_url);
	//model.addAttribute("img_url",img_url);
	 umodel.setUpic(img_url);
	 System.out.println("upic in umodel:"+umodel.getUpic());
	 UserModel udata=udao.updatePicURL(session,umodel);
	 System.out.println("Data return by updatePicURL:"+udata);
	 model.addAttribute("u_data", udata);
	return "Profile";
	
	}
	
	
	
	
	
  public String setProfilePictureURL()
  {
	  FireStoreConfig fsc= new FireStoreConfig();
	  //String img_url=fsc.getprofilePictureURL(img_url);
	 return "index";
  }

	 	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
  
	  @RequestMapping(value="/Home")
	  public String showHome()
	  {
		  return "Home";
	  }
	  
  }
  
  



























//@RequestMapping(value="/Signin",method=RequestMethod.POST)
//public String userSignin(@ModelAttribute("usignin") UserModel umodel, HttpSession session)
//{
//	 
//	  System.out.println("form data:"+umodel);
//	  System.out.println("Calling Login Service");
//	  int uid=uservice.validateLoginService(umodel);
//	  System.out.println("user id is: "+uid);
//	  
//	  if(uid==0)
//	  {
//		 
//		  System.out.println("Login Failed");
//	  }
//	  
//	  else 
//	  {
//		  System.out.println("Login done");
//	  }
//	 
//	  return "index";
//}
//


