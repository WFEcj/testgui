package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import domain.Question;
import socket.Client;
import util.MySpring;

@SuppressWarnings("serial")
public class ExamFrame extends BaseFrame{
	public ExamFrame() {
		super("考试界面");
		this.init();
	}
	private Client question = MySpring.getBean("socket.Client");
	private ArrayList<Question> paper = question.getQuestion();
	private TimeThread t = new TimeThread();
	private char[] answer = new char[paper.size()];
	private int newNum = 1;
	private int size = paper.size();
	private int time = 90;
	private int hour;
	private int minute;
	private int second=0;
	private boolean lock = false;
	
	JPanel mainPanel = new JPanel();
	JPanel messagePanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JTextArea examArea = new JTextArea();
	JScrollPane scroll = new JScrollPane(examArea);
	JLabel imgLabel = new JLabel();
	JLabel nowNumLabel = new JLabel("当前题号");
	JLabel totalNumLabel = new JLabel("题目总数");
	JLabel answerLabel = new JLabel("已答题数");
	JLabel unanswerLabel = new JLabel("未答题数");
	JTextField newNumField = new JTextField();
	JTextField totalNumField = new JTextField();
	JTextField answerField = new JTextField();
	JTextField unanswerField = new JTextField();
	JLabel timeLabel = new JLabel("剩余答题时间");
	JLabel realTimeLabel = new JLabel();
	JButton aButton = new JButton("A");
	JButton bButton = new JButton("B");
	JButton cButton = new JButton("C");
	JButton dButton = new JButton("D");
	JButton prevButton = new JButton("上一题");
	JButton submitButton = new JButton("提交试卷");
	JButton nextButton = new JButton("下一题");
	@Override
	protected void setFontAndSoOn() {
		// TODO Auto-generated method stub
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.LIGHT_GRAY);
		messagePanel.setLayout(null);
		messagePanel.setBounds(680,10,300,550);
		messagePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		buttonPanel.setLayout(null);
		buttonPanel.setBounds(16,470,650,90);
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		scroll.setBounds(16,10,650,450);
		examArea.setFont(new Font("黑体",Font.BOLD,34));
		imgLabel.setBounds(10,10,280,230);
		imgLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		nowNumLabel.setBounds(40,270,100,30);
		nowNumLabel.setFont(new Font("黑体",Font.PLAIN,20));
		newNumField.setBounds(150,270,100,30);
		newNumField.setFont(new Font("黑体",Font.BOLD,20));
		newNumField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		newNumField.setEnabled(false);
		newNumField.setHorizontalAlignment(JTextField.CENTER);
		totalNumLabel.setBounds(40,310,100,30);
		totalNumLabel.setFont(new Font("黑体",Font.PLAIN,20));
		totalNumField.setBounds(150,310,100,30);
		totalNumField.setFont(new Font("黑体",Font.BOLD,20));
		totalNumField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		totalNumField.setEnabled(false);
		totalNumField.setHorizontalAlignment(JTextField.CENTER);
		answerLabel.setBounds(40,350,100,30);
		answerLabel.setFont(new Font("黑体",Font.PLAIN,20));
		answerField.setBounds(150,350,100,30);
		answerField.setFont(new Font("黑体",Font.BOLD,20));
		answerField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		answerField.setEnabled(false);
		answerField.setHorizontalAlignment(JTextField.CENTER);
		unanswerLabel.setBounds(40,390,100,30);
		unanswerLabel.setFont(new Font("黑体",Font.PLAIN,20));
		unanswerField.setBounds(150,390,100,30);
		unanswerField.setFont(new Font("黑体",Font.BOLD,20));
		unanswerField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		unanswerField.setEnabled(false);
		unanswerField.setHorizontalAlignment(JTextField.CENTER);
		timeLabel.setBounds(90,460,150,30);
		timeLabel.setFont(new Font("黑体",Font.PLAIN,20));
		timeLabel.setForeground(Color.BLUE);
		realTimeLabel.setBounds(108,490,150,30);
		realTimeLabel.setFont(new Font("黑体",Font.BOLD,20));
		realTimeLabel.setForeground(Color.BLUE);
		
		aButton.setBounds(40,10,120,30);
		bButton.setBounds(190,10,120,30);
		cButton.setBounds(340,10,120,30);
		dButton.setBounds(490,10,120,30);
		prevButton.setBounds(40,50,100,30);
		nextButton.setBounds(510,50,120,30);
		submitButton.setBounds(276,50,120,30);
		submitButton.setForeground(Color.RED);
		submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		examArea.setEditable(false);
	}
	@Override
	protected void addElement() {
		// TODO Auto-generated method stub
		messagePanel.add(imgLabel);
		messagePanel.add(nowNumLabel);
		messagePanel.add(newNumField);
		messagePanel.add(totalNumLabel);
		messagePanel.add(totalNumField);
		messagePanel.add(answerLabel);
		messagePanel.add(answerField);
		messagePanel.add(unanswerLabel);
		messagePanel.add(unanswerField);
		messagePanel.add(timeLabel);
		messagePanel.add(realTimeLabel);
		buttonPanel.add(aButton);
		buttonPanel.add(bButton);
		buttonPanel.add(cButton);
		buttonPanel.add(dButton);
		buttonPanel.add(prevButton);
		buttonPanel.add(nextButton);
		buttonPanel.add(submitButton);
		mainPanel.add(scroll);
		mainPanel.add(messagePanel);
		mainPanel.add(buttonPanel);
		this.add(mainPanel);
	}
	@Override
	protected void addListener() {
		// TODO Auto-generated method stub
		ActionListener l = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ExamFrame.this.setWhite();
				JButton answerB = (JButton)e.getSource();
				ExamFrame.this.answer[newNum-1]=answerB.getText().charAt(0);
				answerB.setBackground(Color.yellow);
			}
		};
		aButton.addActionListener(l);
		bButton.addActionListener(l);
		cButton.addActionListener(l);
		dButton.addActionListener(l);
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(++ExamFrame.this.newNum>5) {
					JOptionPane.showMessageDialog(ExamFrame.this, "题目已答完！");
					ExamFrame.this.newNum--;
				}else {
					ExamFrame.this.srs();
				}
			}
			
		});
		prevButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(--ExamFrame.this.newNum<1) {
					JOptionPane.showMessageDialog(ExamFrame.this, "本题以是第一题！");
					ExamFrame.this.newNum++;
				}else {
					ExamFrame.this.srs();
				}
			}
			
		});
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int value=JOptionPane.showConfirmDialog(ExamFrame.this, "确定是否交卷？");
				if(value==0) {
					ExamFrame.this.getScore();
					ExamFrame.this.clearButton(false);
					ExamFrame.this.setWhite();
					ExamFrame.this.lock=true;
				}
			}
			
		});
	}
	@Override
	protected void setFrameSelf() {
		// TODO Auto-generated method stub
		this.setBounds(260,130,1000,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.showQuestion();
		t.start();
		this.setField();
		this.setVisible(true);
	}
	private void showQuestion() {
		Question rquestion = paper.get(newNum-1);
		String rTitle = rquestion.getTitle().replace("<br>", "\n");
		examArea.setText(newNum+"."+rTitle);
	}
	private void setWhite() {
		aButton.setBackground(null);
		bButton.setBackground(null);
		cButton.setBackground(null);
		dButton.setBackground(null);
	}
	private void restoreButton() {
		char answerB = answer[newNum-1];
		switch(answerB) {
			case'A':aButton.setBackground(Color.YELLOW);
			break;
			case'B':bButton.setBackground(Color.YELLOW);
			break;
			case'C':cButton.setBackground(Color.YELLOW);
			break;
			case'D':dButton.setBackground(Color.YELLOW);
			break;
			default:break;
		}
	}
	private void setField() {
		newNumField.setText(Integer.toString(newNum));
		totalNumField.setText(Integer.toString(size));
		answerField.setText(Integer.toString(newNum));
		unanswerField.setText(Integer.toString(size-newNum));
	}
	private void srs() {
		ExamFrame.this.setWhite();
		ExamFrame.this.restoreButton();
		ExamFrame.this.showQuestion();
		ExamFrame.this.setField();
	}
	private void getScore() {
		Integer score = 100;
		for(int i =0;i<this.size;i++) {
			if(paper.get(i).getAnswer().charAt(0)!=answer[i]) {
				score-=100/this.size;
			}
		}
		examArea.setText("考试结束，您的分数为："+score);
		question.sendScore(score);
	}
	private void clearButton(boolean flag) {
		aButton.setEnabled(flag);
		bButton.setEnabled(flag);
		cButton.setEnabled(flag);
		dButton.setEnabled(flag);
		nextButton.setEnabled(flag);
		prevButton.setEnabled(flag);
		submitButton.setEnabled(flag);
	}
	private void realTime() {
		this.hour = this.time/60;
		this.minute = this.time%60;
	}
	private class TimeThread extends Thread{
		public void run() {
			ExamFrame.this.realTime();
			while(true) {
				StringBuilder stb = new StringBuilder();
				if(second<=0) {
					if(minute<=0) {
						if(hour<=0) {
							ExamFrame.this.clearButton(false);
							JOptionPane.showMessageDialog(ExamFrame.this, "时间到！请提交试卷！");
							break;
						}else {
							hour--;
							minute+=59;
							second+=59;
						}
					}else {
						minute--;
						second+=59;
					}
				}else {
					second--;
				}
				if(hour<10) {
					stb.append("0");
					stb.append(hour);
					stb.append(":");
				}else {
					stb.append(hour);
					stb.append(":");
				}
				if(minute<10) {
					stb.append("0");
					stb.append(minute);
					stb.append(":");
				}else {
					stb.append(minute);
					stb.append(":");
				}
				if(second<10) {
					stb.append("0");
					stb.append(second);
				}else {
					stb.append(second);
				}
				realTimeLabel.setText(stb.toString());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(ExamFrame.this.lock) {
					break;
				}
			}
		}
	}
}
