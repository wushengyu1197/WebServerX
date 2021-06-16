package com.m3u8;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrintStream extends Thread {
 
    java.io.InputStream __is = null;
 
    public PrintStream(java.io.InputStream is) {
        __is = is;
    }
 
    @Override
    public void run() {
        try {
            while (this != null) {
                int _ch = __is.read();
                if (_ch != -1)
                    System.out.print((char) _ch);
                else
                    break;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        // 为了在博客里好发，单独写了一个简单demo，大家根据自己的需求，整合成工具类吧
        // mp4路径
        String mp4Path = "C:\\Users\\Administrator\\Desktop\\m3u8\\1.m3u8";
        // String mp4Path = "D:\\ml\\qq\\mmexport1583668101896.mp4";
        // ffmpeg 路径
        String ffmpegPath = "C:\\Users\\Administrator\\Desktop\\ffmpeg\\bin\\ffmpeg.exe";
        // m3u8输出路径
        String m3u8PathPrefix = "D:\\ml\\";
        try {
            List<String> command = new ArrayList<String>();
            command.add(ffmpegPath + "");
            command.add("-i");
            command.add(mp4Path);
            command.add("-hls_time");
            command.add("20");
            command.add("-hls_list_size");
            command.add("0");
            command.add("-c:a");
            command.add("aac");
            command.add("-strict");
            command.add("-2");
            command.add("-f");
            command.add("hls");
            command.add(m3u8PathPrefix + "test.mp4");
            // command.add(m3u8PathPrefix + "test.m3u8");
            Process videoProcess = new ProcessBuilder(command).redirectErrorStream(true).start();
            new PrintStream(videoProcess.getInputStream()).start();
            videoProcess.waitFor();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}