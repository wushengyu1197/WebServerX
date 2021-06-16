package com.chiyu.Util;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HttpRequestUtil {
	
	private static Logger log = LoggerFactory.getLogger(HttpRequestUtil.class);
    /**
     * 向指定URL发送GET方法的请求
     */
    public static String sendGet(String url, String param)   {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();

            // 设置通用的请求属性
            connection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setConnectTimeout(3000);
            // 建立实际的连接
            connection.connect();

            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    
    /**
     * 向指定URL发送GET方法的请求   验证许 用户数量
     */
    public static String sendGetlength(String url, String param,String token) {
    	//long time =System.currentTimeMillis();
    	int code=0;
        String result = "";
        BufferedReader in = null;
        //接收请求到的数据
        //SysUserInfo userlist=new SysUserInfo();
        try {
            String urlNameString = url + param;
           // System.out.println(urlNameString+"#####");
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            //URLConnection connection = realUrl.openConnection();
            HttpURLConnection connection =(HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Authorization", "Bearer "+token);
            connection.addRequestProperty("token", token);
          //2.获得所有头的名称
            // 建立实际的连接
            connection.connect();
            code=connection.getResponseCode();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        //log.info("接收get结果cost："+(System.currentTimeMillis()-time)+"s");
       // userlist.setCode(code);
        //userlist.setResult(result);
        return result;
    }
	/**
	 * 发送post请求
	 * @param url
	 * @param param
	 * @return
	 */
    public static String sendPost2(String url, String param) {
    	//long time=System.currentTimeMillis();
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
           // conn.setRequestProperty("connection", "Keep-Alive");
           // conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //1.获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            //2.中文有乱码的需要将PrintWriter改为如下
            //out=new OutputStreamWriter(conn.getOutputStream(),"UTF-8")
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
                
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        //log.info("接收post结果cost："+(System.currentTimeMillis()-time)+"s");
        return result;
    }
   /**
    * 带token的post参数
    * @param url
    * @param param
    * @param token
    * @return
    */
    public static String sendPost(String url, String param,String token) throws Exception {
        DataOutputStream out = null;
        BufferedReader in = null;
        String result = "";
        try {
        	//url = url + "?" + param;
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            //HttpClient client= (HttpClient) realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            // conn.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8"); // 设置发送数据的格式
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Authorization", "Bearer "+token);
            conn.addRequestProperty("token", token);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //1.获取URLConnection对象对应的输出流
            out = new DataOutputStream(conn.getOutputStream());
            //2.中文有乱码的需要将PrintWriter改为如下
            // out=new DataOutputStream(conn.getOutputStream());

            // 发送请求参数
            out.write(param.getBytes(StandardCharsets.UTF_8));

            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));

            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            log.info("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 带Authorization的post参数
     * @param url
     * @param param
     * @param token
     * @return
     */
    public static String sendPost1(String url, String param,String token) {
        //PrintWriter out = null;
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";

        try {
            //url = url + "?" + param;
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8"); // 设置发送数据的格式
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Authorization", token);
            conn.setRequestProperty("Authorization", "Bearer "+token);
            conn.addRequestProperty("token", token);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //1.获取URLConnection对象对应的输出流
            //out = new PrintWriter(conn.getOutputStream());
            //2.中文有乱码的需要将PrintWriter改为如下
           out=new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
            // 发送请求参数
           //out.print(param);
            out.write(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        System.out.println("post推送结果："+result);
        return result;
    }


    public static String httpPutForDeviceBracelets(String url, String param) throws Exception {
        // 凭空获取request
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        request.setCharacterEncoding("utf-8");

        //初始HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建Put对象
        HttpPut httpPut = new HttpPut(url);
        //设置Content-Type
        httpPut.setHeader("Content-Type", "application/json");
        //设置其他Header
        // httpPut.setHeader("Authorization", "Bearer "+token);
        // httpPut.setHeader("token", token);

        //写入JSON数据
        httpPut.setEntity(new StringEntity(param, "UTF-8"));
        //发起请求，获取response对象
        CloseableHttpResponse response = httpClient.execute(httpPut);
        //获取请求码
        //response.getStatusLine().getStatusCode();
        //获取返回数据实体对象
        HttpEntity entity = response.getEntity();
        //转为字符串
        return EntityUtils.toString(entity, "UTF-8");
    }


    

    
    
}
