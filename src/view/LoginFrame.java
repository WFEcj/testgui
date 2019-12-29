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
	//private JFrame frame = new JFrame("��¼����");
	private JPanel panel = new JPanel();
	private JLabel label = new JLabel("�� �� �� �� ϵ ͳ");
	private JLabel account = new JLabel("�� ��:");
	private JLabel password = new JLabel("�� ��:");
	private JTextField account1 = new JTextField();
	private JPasswordField password1 = new JPasswordField();
	private JButton button = new JButton("�� ¼");
	private JButton button1 = new JButton("�� ��");
	protected void setFontAndSoOn() {
		panel.setLayout(null);
		//panel.setBackground(Color.BLUE);
		//���ñ����С��λ��
		label.setBounds(120, 40, 340, 35);
		label.setFont(new Font("����",Font.BOLD,34));
		//�����˺Ŵ�С��λ��
		account.setBounds(94,124, 90, 30);
		account.setFont(new Font("����",Font.BOLD,24));
		account1.setBounds(204,124, 260, 30);
		account1.setFont(new Font("����",Font.BOLD,24));
		//���������С��λ��
		password.setBounds(94,174, 90, 30);
		password.setFont(new Font("����",Font.BOLD,24));
		password1.setBounds(204,174, 260, 30);
		password1.setFont(new Font("����",Font.BOLD,24));
		//���õ�¼���˳��Ĵ�С��λ��
		button.setBounds(154, 232, 100, 30);
		button.setFont(new Font("����",Font.BOLD,22));
		button1.setBounds(304, 232, 100, 30);
		button1.setFont(new Font("����",Font.BOLD,22));
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
					JOptionPane.showMessageDialog(LoginFrame.this, "�˺Ż����벻��Ϊ�գ�");
				}else {
					Client service = MySpring.getBean("socket.Client");
					String result = service.getAdmit(account2, password2);
					if(result.equals("��¼�ɹ�")) {
						//�������Խ���
						LoginFrame.this.setVisible(false);
						new ExamFrame();
					}else {
						//�������ڣ�����ʧ��
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
