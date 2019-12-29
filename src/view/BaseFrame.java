package view;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public abstract class BaseFrame extends JFrame{
	public BaseFrame() {
		super("µÇÂ¼´°¿Ú");
	}
	public BaseFrame(String title) {
		// TODO Auto-generated constructor stub
		super(title);
	}
	protected void init() {
		this.setFontAndSoOn();
		this.addElement();
		this.addListener();
		this.setFrameSelf();
	}
	protected abstract void setFontAndSoOn();
	protected abstract void addElement();
	protected abstract void addListener();
	protected abstract void setFrameSelf();
}
