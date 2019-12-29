package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import domain.Question;
import domain.SocketQuestion;

public class Client {
	private Socket client;
	private String answer = "";
	private SocketQuestion socketQuestion;
	private InputStream inputStream;
	private OutputStream outputStream;
	public String getAdmit(String username,String password) {
		try {
			client = new Socket("100.71.1.91",9999);
			System.out.println("我应该是成功的连接到服务器啦");
			inputStream = client.getInputStream();
			outputStream = client.getOutputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader reader = new BufferedReader(inputStreamReader);
			PrintWriter printWriter = new PrintWriter(outputStream);
			printWriter.println(username+"-"+password);
			printWriter.flush();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answer = reader.readLine();
			//reader.close();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(answer.trim().equals("登录成功")) {
				ObjectInputStream ois = new ObjectInputStream(inputStream);
				try {
					socketQuestion = (SocketQuestion)ois.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return answer;
	}
	public void sendScore(Integer score) {
		try {
			client = new Socket("100.71.1.91",9999);
			PrintWriter printWriter = new PrintWriter(outputStream);
			printWriter.println(score.toString());
			printWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(client!=null) {
					client.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public ArrayList<Question> getQuestion() {
		return socketQuestion.getPaper();
	}
}
