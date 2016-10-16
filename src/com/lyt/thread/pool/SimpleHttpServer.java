package com.lyt.thread.pool;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author Administrator
 *
 */
public class SimpleHttpServer {
	//处理HttpRequest的线程池
	static ThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<HttpRequestHandler>(1);
	//SimpleHttpServer d的根路径-----注意：要跟HTML页面所在的目录一致
	static String basePath;
	static ServerSocket serverSocket ;
	//服务监听端口
	static int port = 8080;
	public static void setPort(int port){
		if(port > 0){
			SimpleHttpServer.port = port;
		}
	}

	public static void setBasePath(String basePath) {
		if(basePath != null && new File(basePath).exists() && new File(basePath).isDirectory()){
			SimpleHttpServer.basePath = basePath;
		}
	}

	//启动SimpleHttpServer.java
	public static void start() throws Exception{
		serverSocket = new ServerSocket(port);
		Socket socket = null;
		while((socket = serverSocket.accept()) != null){
			//接收一个客户端Socket, 生成一个HttpRequestHandler， 放入线程池中执行
			threadPool.execute(new HttpRequestHandler(socket));
		}
		serverSocket.close();
	}
	static class HttpRequestHandler implements Runnable{
		private Socket socket;
		public HttpRequestHandler(Socket socket) {
			this.socket = socket;
		}
		@Override
		public void run() {
			String line = null;
			BufferedReader br = null;
			BufferedReader reader = null;
			PrintWriter out = null;
			InputStream in = null;
			try {
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String header = reader.readLine();
				//有相对路径 计算出绝对路径
				String filePath = basePath + header.split(" ")[1];
				out = new PrintWriter(socket.getOutputStream());
				//如果请求资源的后缀为jpg或者ico，则读取资源并输出
				if(filePath.endsWith("jpg") || filePath.endsWith("ico")){
					in = new FileInputStream(filePath);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					int i = 0;
					while((i = in.read()) != -1){
						baos.wait(i);
					}
					byte[] array = baos.toByteArray();
					out.println("HTTP/1.1 200 OK");
					out.println("Server: Molly");
					out.println("Content-Type: image/jpeg");
					out.println("Content-Length: " + array.length);
					out.println("");
					socket.getOutputStream().write(array, 0, array.length);
				}else{
					br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
					out = new PrintWriter(socket.getOutputStream());
					out.println("HTTP/1.1 200 OK");
					out.println("Server: Molly");
					out.println("Content-Type: text/html; charset = UTF-8");
					out.println("");
					while((line = br.readLine()) != null){
						out.println(line);
					}
				}
				out.close();
			} catch (Exception e) {
				out.println("HTTP/1.1 500");
				out.println("");
				out.flush();
			}finally{
				close(br, in, reader, out, socket);
			}
		}
		
		private void close(BufferedReader br, InputStream in,BufferedReader reader, PrintWriter out, Socket socket2) {
			if(br != null ){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(in != null ){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(reader != null ){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out != null ){
				out.close();
			}
			if(socket2 != null ){
					try {
						socket2.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		}
		
	}
}
