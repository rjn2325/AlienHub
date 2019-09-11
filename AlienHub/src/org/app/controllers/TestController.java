package org.app.controllers;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.util.DateTime;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Acl.Role;
import com.google.cloud.storage.Acl.User;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.google.firebase.database.core.Path;

import io.opencensus.metrics.export.Summary.Snapshot;


@Controller
public class TestController 
{
@RequestMapping(value="/test")
public String showTest()
{
	return "ProfilePicture";
}

@SuppressWarnings("deprecation")
@RequestMapping(value="/testimage",method=RequestMethod.POST)
public String upload(@RequestParam CommonsMultipartFile file,HttpSession session,Model model) throws IOException{  
       
	InputStream serviceAccount = null;
		try {
			serviceAccount = new FileInputStream("D://firebase key/cloudserviceAccountkey.json");
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		GoogleCredentials credentials= null;
		try {
			credentials = GoogleCredentials.fromStream(serviceAccount);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		FirebaseOptions options = new FirebaseOptions.Builder()
		    .setCredentials(credentials)
		    .setStorageBucket("alienhub-4c22a.appspot.com")
		    .build();
		
					
			 FirebaseApp.initializeApp(options);
	
	
	String path=session.getServletContext().getRealPath("/");  
        String filename=file.getOriginalFilename();  
          
        System.out.println("file path is:"+path+" "+"file name is :"+filename);  
        System.out.println("Bytes:"+file.getBytes());
        System.out.println("Original file name:"+file.getOriginalFilename());
        try{  
        byte barr[]=file.getBytes();  
          
        BufferedOutputStream bout=new BufferedOutputStream(  
                 new FileOutputStream(path+"/"+filename));  
        bout.write(barr);  
        bout.flush();  
        bout.close();  
          
        }catch(Exception e){System.out.println(e);}  

        Storage storage = StorageOptions.getDefaultInstance().getService();
        System.out.println("storage:"+storage);
        System.out.println("calling bucket function");
        System.out.println("storage client start");
StorageClient storageClient = StorageClient.getInstance(FirebaseApp.getInstance());
System.out.println("input stream start");
//InputStream testFile = new FileInputStream(path); 
System.out.println("blobstring start");
String blobString = "users-images/" +filename;      







storageClient.bucket().create(blobString, file.getInputStream() , Bucket.BlobWriteOption.userProject("alienhub-4c22a"));
//System.out.println("MAp data:"+tt);

	





String bucketName="alienhub-4c22a.appspot.com";
  BlobInfo blobInfo =
      storage.create(
          BlobInfo
              .newBuilder(bucketName, filename)
              
              .setAcl(new ArrayList<>(Arrays.asList(Acl.of(User.ofAllUsers(), Role.READER))))
              .build(),
          file.getInputStream());
 
 
  
  System.out.println(blobInfo.getMediaLink());



model.addAttribute("img_url", blobInfo.getMediaLink());
return "Home";

}
}
