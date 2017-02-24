package ibm;

import java.io.Serializable;
public class IbmModel implements Serializable{
	private String image;
	private String class1;
	private String class2;
	private String class3;
	private String score;
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getClass1() {
		return class1;
	}
	public void setClass1(String class1) {
		this.class1 = class1;
	}
	public String getClass2() {
		return class2;
	}
	public void setClass2(String class2) {
		this.class2 = class2;
	}
	public String getClass3() {
		return class3;
	}
	public void setClass3(String class3) {
		this.class3 = class3;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
}
