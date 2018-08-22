package cn.ffcs.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	
	private static final Log log = LogFactory.getLog(HttpClientUtil.class);

	public static String get(String url){
        //初始化一个httpClient
        
        /* 带Cookie方式初始化  httpclient 作为全局变量，不要关闭
        CloseableHttpClient httpclient;
        CookieStore cookieStore;
        String cookies ="user=155;pwd=2545454weafadfwef";
        if (cookieStore==null) {
            cookieStore = new BasicCookieStore(); 
            String[] ckArr = cookies.split(";");
            for (String ck : ckArr) {
                String[] tempArr = ck.split("=");
                if (tempArr.length==2) {
                    BasicClientCookie cookie = new BasicClientCookie(tempArr[0].trim(), tempArr[1].trim()); 
                    cookie.setVersion(1); 
                    cookie.setDomain("pub.alimama.com");
                    cookie.setPath("/"); 
                    cookieStore.addCookie(cookie); 
                }
                
            }
        }
        httpclient =  HttpClients.custom().setDefaultCookieStore(cookieStore).build(); 
        */    
        
        //普通方式构造 httpclient
        CloseableHttpClient httpclient = HttpClients.createDefault();
        return get(url,httpclient);
    }
    
    /**
     * get方式获取网页内容
     * @param url  网址
     * @param httpclient  httpclient 实例
     * @param referer  构造来源地址
     * @return
     */
    public static String get(String url,CloseableHttpClient httpclient,String referer){
        String html ="";
        try {
            HttpGet httpGet = new HttpGet(url);
            Integer timeOut = 30000; //30 秒 超时
            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeOut) .setConnectTimeout(timeOut).setSocketTimeout(timeOut).build();
            httpGet.setConfig(requestConfig);
            if (referer!=null) {
                httpGet.setHeader("referer", referer);
            }
            CloseableHttpResponse response = null;
            try {
                response = httpclient.execute(httpGet);
                HttpEntity entity = response.getEntity();
                html = EntityUtils.toString(entity);
            } catch (ClientProtocolException e) {
                log.error("http协议异常："+e.getMessage());
            } catch (IOException e) {
                log.error("http读取异常："+e.getMessage());
            }catch (Exception e) {
                log.error("httpException："+e.getMessage());
            }finally {
                 try {
                    response.close();
                } catch (IOException e) {
                    log.error("http输出流关闭错误："+e.getMessage());
                }
            }
        } finally {
        }
        return html;
    }
    
    /**
     * get方式获取网页内容
     * @param url  网址
     * @param httpclient  httpclient 实例
     * @return
     */
    public static String get(String url,CloseableHttpClient httpclient){
        return get(url, httpclient, null);
    }
    
    /**
     * post方式提交数据，获取网页内容
     * @param url  网址
     * @param parmas  Map<String, String>方式存放数据名称和值
     * @param httpclient  httpclient 实例
     * @param referer  构造来源地址
     * @return
     */
    public static String post(String url, Map<String, String> parmas,CloseableHttpClient httpclient,String referer){
        String html ="";
        try {
            HttpPost httpPost = new HttpPost(url);
            Integer timeOut = 30000; //30 秒 超时
            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeOut) .setConnectTimeout(timeOut).setSocketTimeout(timeOut).build();
            httpPost.setConfig(requestConfig);
            if (referer!=null) {
                httpPost.setHeader("referer", referer);
            }
            CloseableHttpResponse response = null;
            try {
                //参数设置
                List <NameValuePair> nvps = new ArrayList <NameValuePair>();
                for (String key : parmas.keySet()) {
                    nvps.add(new BasicNameValuePair(key, parmas.get(key)));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(nvps));
                response = httpclient.execute(httpPost);
                HttpEntity entity = response.getEntity();
                html = EntityUtils.toString(entity);
            } catch (ClientProtocolException e) {
                log.error("http协议异常："+e.getMessage());
            } catch (IOException e) {
                log.error("http读取异常："+e.getMessage());
            }catch (Exception e) {
                log.error("httpException："+e.getMessage());
            }finally {
                 try {
                    response.close();
                } catch (IOException e) {
                    log.error("http输出流关闭错误："+e.getMessage());
                }
            }
        } finally {
            
        }
        return html;
    }
    
    /**
     * post方式提交数据，获取网页内容
     * @param url  网址
     * @param parmas  Map<String, String>方式存放数据名称和值
     * @param httpclient  httpclient 实例
     * @return
     */
    public static String post(String url, Map<String, String> parmas,CloseableHttpClient httpclient){
        return post(url, parmas, httpclient, null);
    }
    
    
    /**
     * 简单方式，post方式提交数据，获取网页内容
     * @param url  网址
     * @param parmas  Map<String, String>方式存放数据名称和值
     * @return
     */
    public static String post(String url, Map<String, String> parmas){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        return post(url, parmas,httpclient);
    }
    
    
    /**
     * 上传图片
     * @param postUrl
     * @param imageFileName
     * @return
     */
    public static String uploadImag(String postUrl, String imageFileName) {
    	CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost post = new HttpPost(postUrl);
		File file = new File(imageFileName);
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();         
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addBinaryBody
		  ("image", file, ContentType.DEFAULT_BINARY, imageFileName);
		HttpEntity entity = builder.build();
		post.setEntity(entity);
		HttpResponse response;
		String result = "";
		try {
			response = httpclient.execute(post);
			HttpEntity resEntity = response.getEntity();
			result = EntityUtils.toString(resEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
    }
    
    /*** 
     * 下载图片到本地 
     * @param imgUri 
     * @param fileName 
     * @return  
     */  
    public static String downloadImg(String imgUri,String directory, String fileName) {  
        HttpClient client = HttpClients.createDefault();  
        HttpGet get = new HttpGet(imgUri);  
        FileImageOutputStream fios=null;  
        InputStream in=null;  
        ByteArrayOutputStream baos =null;  
        try {  
            baos= new ByteArrayOutputStream();  
            HttpResponse hr = client.execute(get);  
            HttpEntity entity = hr.getEntity();  
             in= entity.getContent();  
            byte[] buffer = new byte[1024];  
            int len = -1;  
            while ((len = in.read(buffer))>0) {  
                baos.write(buffer,0,len);  
            }  
            byte[] b = baos.toByteArray();  
            //创建目录  
            File dir = new File(directory);  
            if(!dir.exists()){  
                dir.mkdirs();  
                File file=new File(dir,fileName);  
                if(!file.exists()){  
                    file.createNewFile();  
                     fios = new FileImageOutputStream(file);  
                    fios.write(b);  
                }  
            }else{  
                File file=new File(dir,fileName);  
                file.createNewFile();  
                fios = new FileImageOutputStream(file);  
                fios.write(b);  
            }  
            return dir+"/"+fileName;  
        } catch (IOException e) {  
            //异常处理  
            if(null!=fios){  
                try {  
                    fios.close();  
                } catch (IOException e1) {  
                       
                }  
            }  
              
        }   
        return null;  
    }  
    
    /**
     * 执行文件下载
     * @param remoteFileUrl
     * @param directory
     * @param fileName
     * @return
     */
    public static boolean executeDownloadFile(String remoteFileUrl, String directory, String fileName)  {
    	HttpClient client = HttpClients.createDefault(); 
    	HttpResponse response = null;
        InputStream in = null;
        FileOutputStream fout = null;
        try {
            HttpGet httpget = new HttpGet(remoteFileUrl);
            response = client.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return false;
            }
            in = entity.getContent();
            
            //创建目录  
            File dir = new File(directory);  
            if(!dir.exists()){  
                dir.mkdirs();  
            }
            String localFilePath = directory+"/"+fileName;
            File file = new File(localFilePath);
            fout = new FileOutputStream(file);
            int l;
            byte[] tmp = new byte[1024];
            while ((l = in.read(tmp)) != -1) {
                fout.write(tmp, 0, l);
                // 注意这里如果用OutputStream.write(buff)的话，图片会失真
            }
            // 将文件输出到本地
            fout.flush();
            EntityUtils.consume(entity);
            return true;
        } catch (Exception e) {  
            //异常处理  
            e.printStackTrace();
        } finally {
            // 关闭低层流。
            if (fout != null) {
                try {
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        }
        return false;
    }
    
}
