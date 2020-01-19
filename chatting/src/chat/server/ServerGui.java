package chat.server;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerGui extends JFrame implements ActionListener{

	private JTextArea	jta = new JTextArea(40, 25);
	private JTextField	jtf = new JTextField(25);
	//GUI에서 서버를 동작시키기 위해 객체를 생성.
	// 연동부분!!
	private serverBackground server = new serverBackground();
	
	
	public ServerGui() {
		
		add(jta, BorderLayout.CENTER);
		add(jtf, BorderLayout.SOUTH);
		jtf.addActionListener(this);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(200, 100, 400, 600);
		setTitle("서버부분");
		
		
		server.setGui(this);
		server.setting();
	}
	
	public static void main(String[] args) {
		new ServerGui();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String msg = jtf.getText()+"\n";
		jta.append("서버 : " + msg);
		System.out.print(msg);
		
		server.sendMessage(msg);
		
		jtf.setText("");
		
	}

	public void appendMsg(String msg) {
		jta.append(msg);
		System.out.print("날아온 메시지 : " + msg);
		
		
	}
}
