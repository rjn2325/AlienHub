package org.app.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.apache.tomcat.util.security.MD5Encoder;
import org.app.crud.connect;
import org.app.models.UserModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.mysql.cj.result.Row;

public class UserDaoImpl implements UserDao
{
UserModel umodel;

	@Override
	public String addUser(UserModel umodel) {
	
		String query="insert into Users(uname,umobile,uemail,upassword,fid) values(?,?,?,md5(?),?)";
		JdbcTemplate conn=connect.getTemplate();
		int i=conn.update(query, new Object[] {umodel.getName(),umodel.getMobile(),umodel.getEmail(),umodel.getPassword() ,umodel.getFid()});
		if(i>0)
		{
			System.out.println("Data inserted");
		
		}
		else
		{
			System.out.println("Data insertion failed");
		}
		return "index.jsp";
	}
	

	public UserModel userDetails(UserModel umodel) {
		System.out.println("normal password is:"+umodel.getPassword());
		String query="Select * from users where uemail=? AND upassword=md5(?)";
		System.out.println("password is:"+umodel.getPassword());
	
		List<UserModel>  data=connect.getTemplate().query(query, new Object[] {umodel.getEmail(),umodel.getPassword()}, new UserMapper());
		System.out.println(data.size());
		if(data.size()>0)
		{
			System.out.println("Login Sucsess");
			 System.out.println("Data at 0 in DAo"+data.get(0));
			 return data .get(0);
			 
		}
		else 
		{
			System.out.println("Login Failed");
		
		}
		
		return umodel;	
	}
	
	
	@Override 
	public UserModel  updatePicURL(HttpSession session,UserModel umodel)
	{
		int u_id=(int)session.getAttribute("uid");
		String f_id=(String)session.getAttribute("fid");
		System.out.println("in DAo:"+u_id + "" +f_id);
		String query="update users set upic='"+umodel.getUpic() +"' where uid='"+u_id +"'and fid='"+f_id +"'";
		int pp=connect.getTemplate().update(query);
			if(pp>0)
			{
				String fetch_query="select uname, umobile,uemail,upic from users where uid='"+u_id +"'";
				List <UserModel> udata=connect.getTemplate().query(fetch_query, new UserMapper());
				System.out.println("udata at Daoimpl:"+udata);
				
			
			}
		
		return umodel;
	}
	
	public int validateLogin(UserModel umodel)
	{
		System.out.println("normal password is:"+umodel.getPassword());
		String query="Select uid from users where uemail=? AND upassword=md5(?)";
		System.out.println("password is:"+umodel.getPassword());
	
		int uid= (int)connect.getTemplate().queryForObject(query, new Object[] {umodel.getEmail(),umodel.getPassword()},Integer.class);
		System.out.println("Userid in DAO: "+uid);
		
		return  uid;
	}

	
	
	
	
	
	
	
	
	
	class UserMapper implements RowMapper<UserModel>
	{

		@Override
		public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserModel udata=new UserModel();
			int id=udata.setUid(rs.getInt("uid"));
			String n=udata.setName(rs.getString("uname"));
			String p=udata.setPassword(rs.getString("upassword"));
			String e=udata.setEmail(rs.getString("uemail"));
			String m=udata.setMobile(rs.getString("umobile"));
			String f_id=udata.setFid(rs.getString("fid"));
			String url=udata.setUpic(rs.getString("upic"));
			System.out.println("Uname:"+n);
			System.out.println("password is:"+p);
			System.out.println("udata:"+udata);
			return udata;
		}
		
	}
	

	@Override
	public String updateUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String fetchUser() {
		// TODO Auto-generated method stub
		return null;
	}


	


	
}
