/**  
* <p>Title: UPloadUtil.java</p>  
* <p>Description: </p>  
* @author koko 
* @date 2018年11月14日  
* @version 1.0  
*/ 
package com.shx.web.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**  
* <p>Title: UPloadUtil</p>  
* <p>Description: </p>  
* @author koko  
* @date 2018年11月14日  
*/
public class UPloadUtil {
	public String uploadFile(MultipartFile file,HttpServletRequest  request)
	{
		 System.out.println("文件长度: " + file.getSize()); 
		 System.out.println("文件类型: " + file.getContentType()); 
		 System.out.println("文件名称: " + file.getName());    
		 System.out.println("文件原名: " + file.getOriginalFilename()); 
		 System.out.println("开始"); 
		 String str = file.getOriginalFilename();
		 String[] aa=str.split("\\.");
		 String bb=aa[aa.length-1];
		 long sj = System.currentTimeMillis();
		 String path = request.getSession().getServletContext().getRealPath("upload/");  
		 File localFile = new File(path+"/"+sj+"."+bb);
		 try {
			file.transferTo(localFile);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sj+"."+bb;	
	}
	public static String loadJSON(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream(),"utf-8"));//防止乱码
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return json.toString();
    }
	public String upload(HttpServletRequest request) {
		List<String> filenames = new ArrayList<String>();
		 //创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
            	
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                	System.out.println(file.getSize() );
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){  
                        System.out.println(myFileName);  
                        //重命名上传后的文件名  
                        String fileName = System.currentTimeMillis()+(int)(Math.random()*10000)+myFileName.substring(myFileName.lastIndexOf("."));  
                        //定义上传路径  
                        String path = request.getServletContext().getRealPath("/upload/"+fileName) ;  
                        File localFile = new File(path);  
                        try {
							file.transferTo(localFile);
							filenames.add(fileName);
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  
                    }  
                }  
                
               
            }  
        }
        StringBuffer sb = new StringBuffer();
			for(String it:filenames){
				sb.append(it+",");
			}
			if(sb.length()>0){
				return sb.substring(0, sb.length()-1);
			}
			return null;
	
	}
	public String upload1(MultipartFile[] file1,HttpServletRequest request) {
		List<String> filenames = new ArrayList<String>();
		
                if(file1.length>0){ 
                	for(MultipartFile file:file1){
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){  
                        System.out.println(myFileName);  
                        //重命名上传后的文件名  
                        String fileName = System.currentTimeMillis()+(int)(Math.random()*10000)+myFileName.substring(myFileName.lastIndexOf("."));  
                        //定义上传路径  
                        String path = request.getServletContext().getRealPath("/upload/"+fileName) ;  
                        File localFile = new File("/data/wwwuploads/shx/upload/"+fileName); 
                        if(!localFile.exists())    
                        {    
                            try {    
                            	localFile.createNewFile();    
                            } catch (IOException e) {    
                                e.printStackTrace();    
                            }    
                        } 
                        try {
							file.transferTo(localFile);
							filenames.add(fileName);
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  
                    }  
                }  
                
               
            }  
        
        StringBuffer sb = new StringBuffer();
    	for (int i = 0; i < filenames.size(); i++) {
			if(i == 0 ){
				sb.append(filenames.get(i));
			}else{
				sb.append(","+filenames.get(i));
			}
		}
			if(sb.length()>0){
				return sb.toString();
			}
			return null;
	}
	public static String download(String urlString,HttpServletRequest request,String image) throws IOException{  
		File file = new File("/data/wwwuploads/shx/upload/");
		if(!file.exists()){
			file.mkdirs();
		}
        // 构造URL  
       URL url = new URL(urlString);  
       // 打开连接  
        URLConnection con = url.openConnection();  
      //设置请求超时为5s  
       con.setConnectTimeout(5*1000);  
       // 输入流  
       InputStream is = con.getInputStream();  
        // 1K的数据缓冲  
        byte[] bs = new byte[1024];  
       // 读取到的数据长度  
       int len;  
       // 输出的文件流  
      /*File sf=new File(savePath);  
      if(!sf.exists()){  
          sf.mkdirs();  
       } */ 
       long sj = System.currentTimeMillis()+(int)(Math.random()*10000);
       OutputStream os = new FileOutputStream("/data/wwwuploads/shx/upload/"+sj+".jpg");
       // 开始读取  
        while ((len = is.read(bs)) != -1) {  
          os.write(bs, 0, len);  
        }  
       // 完毕，关闭所有链接  
        os.close();  
       is.close();  
       return sj+".jpg";
    }
}
