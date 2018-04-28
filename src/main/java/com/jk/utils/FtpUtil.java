package com.jk.utils;


import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.SocketException;
import java.util.logging.Logger;

/**
 * ftp操作工具类
 */
public class FtpUtil extends FTPClient {

    private String username = "admin";

    private String password = "admin";

    private String service_url = "192.168.71.87";

    private int port = 21;

    private static FtpUtil instance = null;

    private static FTPClient ftpClient = null;


    /**
     * 初始化
     * @return
     */
    public static FtpUtil getInstance(){
        if(instance == null){
            instance = new FtpUtil();
        }

        ftpClient = new FTPClient();
        return instance;
    }

    /**
     * 连接FTP服务器
     * @return
     */
    private boolean connect(){
        boolean stat = false;
        try {
            if(ftpClient.isConnected()) {
                return true;
            }
            ftpClient.connect(service_url, port);
            stat = true;
        } catch (SocketException e) {
            stat = false;
            e.printStackTrace();
        } catch (IOException e) {
            stat = false;
            e.printStackTrace();
        }
        return stat;
    }


    /**
     * 打开FTP服务器
     * @return
     */
    public boolean open(){
        if(!connect()){
            return false;
        }

        boolean stat = false;
        try {
            stat = ftpClient.login(username, password);
            // 检测连接是否成功
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                close();
                stat = false;
            }
        } catch (IOException e) {
            stat = false;
        }
        return stat;
    }

    /**
     * 关闭FTP服务器
     */
    public void close(){
        try {
            if(ftpClient != null){
                if(ftpClient.isConnected()){
                    ftpClient.logout();
                    ftpClient.disconnect();
                }

                ftpClient = null;
            }
        } catch (IOException e) {
        }
    }

    /**
     * 上传文件到FTP服务器
     * @param filename
     * @param path
     * @param input
     * @return
     */
    public boolean upload(String filename,String path,InputStream input){
        boolean stat = false;
        try {
            cd(path);
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding("GBK");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            stat = ftpClient.storeFile(filename, input);
            input.close();  //关闭输入流
        } catch (IOException e) {
        }
        return stat;
    }

    /**
     * 循环切换目录
     * @param dir
     * @return
     */
    public boolean cd(String dir){
        boolean stat = true;
        try {
            String[] dirs = dir.split("/");
            if(dirs.length == 0){
                return ftpClient.changeWorkingDirectory(dir);
            }

            String s = ftpClient.printWorkingDirectory();
            System.out.println("当前目录"+s);

           // stat = ftpClient.changeToParentDirectory();

            String s1 = ftpClient.printWorkingDirectory();
            System.out.println("当前目录"+s1);
            for(String dirss : dirs){
                stat = stat && ftpClient.changeWorkingDirectory(dirss);
                String s2 = ftpClient.printWorkingDirectory();
                System.out.println("当前目录"+s2);
            }
        } catch (IOException e) {
            stat = false;
        }
        return stat;
    }

    /***
     * 创建目录
     * @param dir
     * @return
     */
    public boolean mkdir(String dir){
        boolean stat = false;
        try {
            ftpClient.makeDirectory(dir);
            stat = true;
        } catch (IOException e) {
            stat = false;
        }
        return stat;
    }

    /**
     * 删除文件夹
     * @param pathname
     * @return
     */
    public boolean rmdir(String pathname){
        try{
            return ftpClient.removeDirectory(pathname);
        }catch(Exception e){
            return false;
        }
    }

    /**
     * 删除文件
     * @param pathname
     * @return
     */
    public boolean removeFile(String pathname){
        boolean stat = false;
        try{
            stat = ftpClient.deleteFile(pathname);
        }catch(Exception e){
            stat = false;
        }
        return stat;
    }

    public void getFile(String fileName) throws Exception {
        OutputStream is = new FileOutputStream("j:/234.txt");
        boolean b = ftpClient.retrieveFile(fileName, is);
        is.flush();
        is.close();
    }


}
