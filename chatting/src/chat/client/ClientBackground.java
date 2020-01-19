package chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientBackground {

	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private ClientGui gui;
	
	private String msg;

	public void setGui(ClientGui gui) {
		this.gui = gui;
	}

	public void connect() {
		try {
			socket = new Socket("127.0.0.1", 7777);
			System.out.println("서버 연결됨.");
			
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			
			out.writeUTF("안녕하세요, 나는 클라이언트입니다.");
			System.out.println("클라이언트 : 메시지 전송완료!");
			
			//서버가 말하는 동안, 계속 들어야 한다!!!
			while(in != null) {
				msg = in.readUTF();
				gui.appendMsg(msg);
				
				
			}
			//ClientBackground는 지금 while문 안에 계속 갇혀 있는 상태다.
			//그렇기 때문에, ClientGUI 에서 메시지를 보내고 싶다!!!
			//뷰 딴에 메시지를 입력해서, 보내고 싶은데 Background는 while문 안에 있느라 처리해 줄 여력이 없다.
			//따라서, 리스닝 하는 백단 부분을 쓰레드로 만들어줘서, 2가지 작업을 할 수 있도록 만들어줘야 한다.
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ClientBackground clientBackground = new ClientBackground();
		clientBackground.connect();
	}

	public void sendMessge(String msg2) {
		try {
			out.writeUTF("클라이언트 : " + msg2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
