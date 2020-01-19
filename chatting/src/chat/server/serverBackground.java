package chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class serverBackground {

	// 이슈1. 뭔가 메시지를 주고받고 싶어요.
	// 이슈2. 하기전에 먼저 GUI 를 만들도록 하겠습니다.
	// 이슈3. 연동
	
	private ServerSocket serverSocket;
	private Socket socket; 	//귀를 열어주는 작업.
	private DataInputStream in;
	private DataOutputStream out;
	private ServerGui gui;
	private String msg;
	
	
	public final void setGui(ServerGui gui) {
		this.gui = gui;
	}

	public void setting() {
		try {
			//서버 개방 
			serverSocket = new ServerSocket(7777);
			System.out.println("서버 대기중...");
			//서버 열렸으니, 하는 말을 들어야겠죠?
			socket = serverSocket.accept();		//클라이언트가 들어올때까지 계속 대기한다. (accept)
			System.out.println(socket.getInetAddress()+"에서 접속했습니다.");
			
			//서로에게 통신할 수 있는 통로를 만듦!
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			
			msg = in.readUTF();
			System.out.println("클라이언트로부터의 메시지 : " + msg);
			gui.appendMsg(msg);
			
			while(in != null) {
				msg = in.readUTF();
				gui.appendMsg(msg);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		serverBackground serverBackground = new serverBackground();
		serverBackground.setting();
	}

	public void sendMessage(String msg) {
		try {
			out.writeUTF("서버 : " + msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
