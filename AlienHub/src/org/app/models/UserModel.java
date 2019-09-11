package org.app.models;

public class UserModel
{
	private int uid;
 private String name,email,password;
 private String mobile,fid,upic;
 public String getUpic() {
	return upic;
}
public String setUpic(String upic) {
	return this.upic = upic;
}
public String getFid() {
	return fid;
}
public String setFid(String fid) {
	return this.fid = fid;
}


public int getUid() {
	return uid;
}
public int setUid(int uid) {
	return this.uid = uid;
}
public String getName() {
	return name;
}
public String setName(String name) {
	return this.name = name;
}
public String getEmail() {
	return email;
}

public String setEmail(String email) {
	return this.email = email;
}
public String getPassword() {
	return password;
}
public String setPassword(String password) {
	return this.password = password;
}
public String getMobile() {
	return mobile;
}
public String setMobile(String string) {
	this.mobile = string;
	return email;
}

@Override
public String toString() {
	return "UserModel [uid=" + uid + ", name=" + name + ", email=" + email + ", password=" + password + ", mobile="
			+ mobile + ", fid=" + fid + ", upic=" + upic + "]";
}
}
