package ibm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.security.cert.PKIXRevocationChecker.Option;
import java.util.ArrayList;
import java.util.List;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifierOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifierOptions.Builder;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;



public class IbmTest1 {
	
	//test20170224forgit 
	//20170224test2345
	
	public static void main(String[] args) 
	{
		VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
		service.setApiKey("5a531dbcc6f6e8ecad69eef6fa203766a5142279");
		System.out.println("Start Classify an image");		
		//MARK -- 載入資料夾
		File folder = new File("D:\\test12");
		String[] datalist1 = folder.list();
	    String ImageName1;
		String Name = null;
		int  size1;
		double Scores;
        String classifer_ID = null;
		
		try{
			//Mark -- 載入文字檔
            FileWriter dataFile = new FileWriter("D:\\out\\test.txt");
            BufferedWriter input = new BufferedWriter(dataFile);
//	    	for(int i = 0; i < datalist1.length; i++)
//	     	{	
//		       ClassifyImagesOptions options = new ClassifyImagesOptions.Builder().images(new File(folder.getPath()+"\\"+datalist1[i])).build(); 
//		       VisualClassification result = service.classify(options).execute();
//		       System.out.println(result);
//		       System.out.println(i+1);
//		       size1 = result.getImages().get(0).getClassifiers().get(0).getClasses().size();
//			   input.write("Picture:"+String.valueOf(i+1)+"\r\n");
//		       ImageName1 = result.getImages().get(0).getImage();
//		       System.out.println("Picture:"+ImageName1);
//		       input.write("PictureName:"+ImageName1+"\r\n");
//		       for(int y = 0 ; y < size1 ; y++)
//		       {     
//		          Name = result.getImages().get(0).getClassifiers().get(0).getClasses().get(y).getName();
//		          Scores = result.getImages().get(0).getClassifiers().get(0).getClasses().get(y).getScore();
//		          input.newLine();
//			      System.out.println(Name);
//			      input.write("Result"+(y+1)+":"+Name+"\r\n");
//			      System.out.println(Scores);
//			      input.write(String.valueOf("Scores"+(y+1)+":"+Scores)+"\r\n");
//		       }	
//		      options = null;
//		      System.out.println("-----------");
//		      input.write("---------------------------");
//		      input.newLine();
//	        }
//	      input.close();
		  } catch(Exception e){
                  e.printStackTrace();
                  System.out.println("exception>>>>>>>>" + e.getMessage());
		  }         
		//--------------------------Train-------------------------
		//--------------------------Train-------------------------
		File folder2 = new File("D:\\createdata");
		String[] datalist2 = folder2.list();
		//拆解classifieroption才能一次加入整個資料夾
		Builder tmpBuilder = new ClassifierOptions.Builder().classifierName("carfind1228300");//建立classifiername
		for(int i = 0; i < datalist2.length; i++)
     	{  
		   tmpBuilder.addClass(datalist2[i], new File(folder2.getPath()+"\\"+datalist2[i]));//加檔案	   
//		   ClassifierOptions createOptions = new ClassifierOptions.Builder().classifierName("carfind212")
//			        .addClass(datalist2[i], new File(folder2.getPath()+"\\"+datalist2[i]))
//			        .addClass("cantsee1", new File("D:\\createdata\\22.cantsee.zip"))
//			        .negativeExamples(new File("D:\\createdata\\3.iseeyou.zip"))
//			        .build();
     	}
		ClassifierOptions createOptions = tmpBuilder.build();//建立
        //service.deleteClassifier("carfind1228300_565207693").execute();//刪除指定Classifiers
		System.out.println("1."+service.getClassifiers().execute());//取得Classifiers資料
		if(service.getClassifiers().execute().toString().equals("[]"))  
		  {
			System.out.println("start.> ");
		    System.out.println("service.createClassifier > " + service.createClassifier(createOptions).execute());//新增
		    System.out.println("finished.> ");
		  }
	    try{
	    	 FileWriter dataFile2 = new FileWriter("D:\\out\\test_up.txt");
	         BufferedWriter input2 = new BufferedWriter(dataFile2);
		     List<VisualClassifier> resultClassifier=service.getClassifiers().execute();
		     classifer_ID = resultClassifier.get(0).getId();
		     System.out.println("classifier_id:"+resultClassifier.get(0).getId());
		     input2.write("classifier_id:"+resultClassifier.get(0).getId()+"\r\n");
		     System.out.println("classifier_name:"+resultClassifier.get(0).getName());
		     input2.write("classifier_name:"+resultClassifier.get(0).getName()+"\r\n");
		     System.out.println("classifier_CreateDate:"+resultClassifier.get(0).getCreated());
		     input2.write("classifier_CreateDate:"+resultClassifier.get(0).getCreated()+"\r\n");
		     System.out.println("classifier_Status:"+resultClassifier.get(0).getStatus());
		     input2.write("classifier_Status:"+resultClassifier.get(0).getStatus()+"\r\n");
		     int size2=resultClassifier.get(0).getClasses().size();
		     for(int x = 0;x<size2;x++)
		     {
		        System.out.println("classifier_class"+(x+1)+":"+resultClassifier.get(0).getClasses().get(x).getName());
		        input2.write("classifier_class"+(x+1)+":"+resultClassifier.get(0).getClasses().get(x).getName()+"\r\n");
		     }
		     input2.close();
	    }catch(Exception e){
            e.printStackTrace();
            System.out.println("exception>>>>>>>>" + e.getMessage());
	  }   
	    //-----------------------------用訓練的檔案做辨識-----------------------------
	    try{
			//Mark -- 載入文字檔
            FileWriter dataFile3 = new FileWriter("D:\\out\\test_classid.txt");
            BufferedWriter input3 = new BufferedWriter(dataFile3);
            //以LIST方式傳入辨識引擎模組
//            ArrayList<String> list = new ArrayList<>();
//            list.add(classifer_ID);
//            list.add("default");
	    	for(int i = 0; i < datalist1.length; i++)
	     	{	
		       ClassifyImagesOptions options = new ClassifyImagesOptions.Builder().classifierIds(classifer_ID).images(new File(folder.getPath()+"\\"+datalist1[i])).build(); 
		       VisualClassification result = service.classify(options).execute();
		       System.out.println(result);
		       System.out.println(i+1);
		       if(!(result.getImages().get(0).getClassifiers().toString().equals("[]")))
		       {
		         size1 = result.getImages().get(0).getClassifiers().get(0).getClasses().size();
			     input3.write("Picture:"+String.valueOf(i+1)+"\r\n");
		         ImageName1 = result.getImages().get(0).getImage();
		         System.out.println(ImageName1);
		         input3.write("PictureName:"+ImageName1+"\r\n");
		         for(int y = 0 ; y < size1 ; y++)
		         {      
		            Name = result.getImages().get(0).getClassifiers().get(0).getClasses().get(y).getName();
		            Scores = result.getImages().get(0).getClassifiers().get(0).getClasses().get(y).getScore();
		            input3.write("Match:Yes\r\n");
			        System.out.println(Name);
			        input3.write("Result"+(y+1)+":"+Name+"\r\n");
			        System.out.println(Scores);
			        input3.write(String.valueOf("Scores"+(y+1)+":"+Scores)+"\r\n");
		         }	
		         options = null;
		         System.out.println("-----------");
		         input3.write("---------------------------");
		         input3.newLine();
	           }
		       else
		       {   System.out.println("Picture : "+(i+1));
		    	   System.out.println("PictureName = "+result.getImages().get(0).getImage());
		    	   System.out.println("Result = null");
		    	   input3.write("Picture:"+(i+1)+"\r\n");  
		    	   input3.write("PictureName : "+result.getImages().get(0).getImage()+"\r\n"); 
		    	   input3.write("Match:No\r\n");
		    	   input3.write("Result"+" : null"+"\r\n"); 
		    	   System.out.println("-----------");
		    	   input3.write("---------------------------\r\n");
		       }
	     	}
	        input3.close();
		  } catch(Exception e){
                 e.printStackTrace();
                 System.out.println("exception>>>>>>>" + e.getMessage());
		  } 
	    System.out.println("----------END----------");
	}
}