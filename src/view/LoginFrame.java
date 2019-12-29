package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import socket.Client;
import util.MySpring;

@SuppressWarnings("serial")
public class LoginFrame extends BaseFrame{
	public LoginFrame() {
		this.init();
	}
	public LoginFrame(String title) {
		super(title);
		this.init();
	}
	//private JFrame frame = new JFrame("登录窗口");
	private JPanel panel = new JPanel();
	private JLabel label = new JLabel("在 线 考 试 系 统");
	private JLabel account = new JLabel("账 号:");
	private JLabel password = new JLabel("密 码:");
	private JTextField account1 = new JTextField();
	private JPasswordField password1 = new JPasswordField();
	private JButton button = new JButton("登 录");
	private JButton button1 = new JButton("退 出");
	protected void setFontAndSoOn() {
		panel.setLayout(null);
		//panel.setBackground(Color.BLUE);
		//设置标题大小与位置
		label.setBounds(120, 40, 340, 35);
		label.setFont(new Font("黑体",Font.BOLD,34));
		//设置账号大小与位置
		account.setBounds(94,124, 90, 30);
		account.setFont(new Font("黑体",Font.BOLD,24));
		account1.setBounds(204,124, 260, 30);
		account1.setFont(new Font("黑体",Font.BOLD,24));
		//设置密码大小与位置
		password.setBounds(94,174, 90, 30);
		password.setFont(new Font("黑体",Font.BOLD,24));
		password1.setBounds(204,174, 260, 30);
		password1.setFont(new Font("黑体",Font.BOLD,24));
		//设置登录与退出的大小与位置
		button.setBounds(154, 232, 100, 30);
		button.setFont(new Font("黑体",Font.BOLD,22));
		button1.setBounds(304, 232, 100, 30);
		button1.setFont(new Font("黑体",Font.BOLD,22));
	}
	protected void addElement() {
		panel.add(label);
		panel.add(account);
		panel.add(account1);
		panel.add(password);
		panel.add(password1);
		panel.add(button);
		panel.add(button1);
		this.add(panel);
	}
	protected void addListener() {
		ActionListener l = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String account2 = account1.getText();
				String password2 = new String(password1.getPassword());
				if(account2.equals("") || password2.equals("")) {
					JOptionPane.showMessageDialog(LoginFrame.this, "账号或密码不能为空！");
				}else {
					Client service = MySpring.getBean("socket.Client");
					String result = service.getAdmit(account2, password2);
					if(result.equals("登录成功")) {
						//弹出考试界面
						LoginFrame.this.setVisible(false);
						new ExamFrame();
					}else {
						//弹出窗口，告诉失败
						JOptionPane.showMessageDialog(LoginFrame.this, result);
						account1.setText("");
						password1.setText("");
					}
				}
			}
		};
		button.addActionListener(l);
	}
	protected void setFrameSelf() {
		this.setBounds(600, 280, 560, 340);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
