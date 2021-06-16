package com.m3u8;

import com.chiyu.Util.CommonUtils;
import org.apache.commons.io.FileUtils;
import org.junit.Test;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class demo {
    public static void main(String[] args) {
        File directory = new File("");//设定为当前文件夹
        try {
            System.out.println(directory.getCanonicalPath());//获取标准的路径
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(directory.getAbsolutePath());//获取绝对路径
    }

    @Test
    public void a(){
        String s="2011-01-21 21:06:55";
        String str = CommonUtils.getISO8601TimestampFromDateStr(s);
        System.out.println("str = " + str);
    }
    @Test
    public void huasejj() throws Exception {
        String OS = System.getProperty("os.name");
        String pwd = System.getProperty("user.dir");
        String from = "";
        if (OS.contains("indows")) {
            from = pwd.substring(0, pwd.lastIndexOf('\\') + 1) + "ts\\";
        } else {

        }

        try {
            String strUrl = "http://127.0.0.1:8080/1.m3u8";
            URL url = new URL(strUrl);

            int index1 = strUrl.indexOf("//") + 2;
            int index2 = strUrl.indexOf("/", index1);
            String domainHttp = strUrl.substring(0, index2);
            String domain = strUrl.substring(index1, index2);

            URLConnection URLconnection = url.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) URLconnection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                //                System.err.println("成功");
                InputStream in = httpConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(in);
                BufferedReader bufr = new BufferedReader(isr);
                String str;
                int indexTS = 0;
                //com.qq.download
                while ((str = bufr.readLine()) != null) {
                    if (str.contains(".mp4")) {
                        String pathname = from + domain + "/" + String.format("%4d", indexTS++).replace(" ", "0") + ".ts";
                        System.out.println(domainHttp + str);
                        System.out.println(pathname);
                        FileUtils.copyURLToFile(new URL(domainHttp + str), new File(pathname));
                        //break;
                    }
                }

                if (OS.contains("indows")) {
                    Runtime rt = Runtime.getRuntime();
                    Process pr = rt.exec("cmd /c copy /b " + from + domain + "\\*.ts  " + from + domain + ".mp4");
                    BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream(), "GBK"));
                    String line = null;
                    while ((line = input.readLine()) != null) {
                        System.out.println(line);
                    }
                    System.out.println();
                }

                bufr.close();
                System.out.println("succ");
            } else {
                System.err.println("失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
