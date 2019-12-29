package domain;

import java.io.Serializable;
import java.util.ArrayList;

public class SocketQuestion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Question> paper;
	public SocketQuestion() {}
	public SocketQuestion(ArrayList<Question> p) {
		this.paper=p;
	}
	public ArrayList<Question> getPaper() {
		return paper;
	}
	public void setPaper(ArrayList<Question> paper) {
		this.paper = paper;
	}
}
