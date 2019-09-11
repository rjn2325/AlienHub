package utils;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpSession;

import org.app.models.UserModel;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.api.core.ApiFuture;
import com.google.api.services.storage.StorageRequest;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.storage.Acl.Role;
import com.google.cloud.storage.Acl.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.cloud.StorageClient;

public class FireStoreConfig
{
	Firestore db = FirestoreClient.getFirestore();
	CollectionReference docRef = db.collection("users");
	DocumentReference doc =  docRef.document();
	
	
	public static String img_url;
	private static FireStoreConfig firestoreconfig=null;
	
	public static FireStoreConfig getInstance() {
		if(firestoreconfig == null)
			firestoreconfig= new FireStoreConfig();
		
		return firestoreconfig;
	}
	
	static {
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
	}
	
	
	
	//@RequestMapping(value="/firebasetest.html")
	public String saveData(UserModel umodel) throws Exception
	{
		
		String fid=doc.getId();
		System.out.println("Doc id:"+doc.getId());
		
		
		Map<String , Object> d= new HashMap<>();
		d.put("uname",umodel.getName());
		d.put("uemail",umodel.getEmail());
		d.put("umobile", umodel.getMobile());
		
		
		ApiFuture<WriteResult> result = doc.set(d,SetOptions.merge());
		System.out.println("Update time : " + result.get().getUpdateTime());
	
		return fid;
	}
	
	
	
	//@RequestMapping(value="/saveProfilePicture",method=RequestMethod.POST)
	public String storeProfilePicture(@RequestParam MultipartFile file,HttpSession session , Model model) throws IOException, InterruptedException, ExecutionException {
		
		  
		 
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

Blob token= storageClient.bucket().create(blobString, file.getInputStream() , Bucket.BlobWriteOption.userProject("alienhub-4c22a"));
System.out.println("end:"+token.toString());
System.out.println(token.getBlobId());


String bucketName="alienhub-4c22a.appspot.com";
  @SuppressWarnings("deprecation")
BlobInfo blobInfo =
      storage.create(
          BlobInfo
              .newBuilder(bucketName, filename)
              
              .setAcl(new ArrayList<>(Arrays.asList(Acl.of(User.ofAllUsers(), Role.READER))))
              .build(),
          file.getInputStream());
  // return the public download link
 
  
  
  
  
img_url=blobInfo.getMediaLink();
System.out.println("Download Image URL:"+img_url);

Map<String , Object> newdata=new HashMap<>();
newdata.put("upic", img_url);
String d_id=(String) session.getAttribute("fid");
System.out.println("fid from session:"+d_id);
ApiFuture<WriteResult> future = db.collection("users").document(d_id).set(newdata, SetOptions.merge());
System.out.println("Update time : " + future.get().getUpdateTime());

return img_url;

  //model.addAttribute("img_url", blobInfo.getMediaLink());
  



}

	
	
	
	
	

	public String getprofilePictureURL(String img_url)
	{
		System.out.println("URL in getPP:"+img_url);
		return img_url;
		
	}
	
}
