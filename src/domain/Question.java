package domain;

import java.io.Serializable;

public class Question implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String answer;
	public Question() {}
	public Question(String title,String answer) {
		this.title = title;
		this.answer = answer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		if(obj instanceof Question) {
			Question annotherQuestion = (Question)obj;
			String title = this.getTitle();
			String annotherTitle = annotherQuestion.getTitle();
			String tTitle = title.substring(0,title.indexOf("br"));
			String aTitle = title.substring(0,annotherTitle.indexOf("br"));
			return tTitle.equals(aTitle);
		}
		return false;
	}
	@Override
	public int hashCode() {
		return this.getTitle().hashCode();
	}
}
