package gameLogic;

public class CodeNameCard {
	
	private String Name;
	private String Color;
	private int location;
	
	public void setName(String n) {
		this.Name = n;
	}
	
	public String getName() {
		return this.Name;
	}
	
	public void setColor(String c) {
		this.Color = c;
	}
	
	public String getColor() {
		return this.Color;
	}
	
	public void setLocation(int l) {
		this.location = l;
	}
	
	public int getLocation() {
		return this.location;
	}
	
	public CodeNameCard(String n, String c, int l) {
		this.Name = n;
		this.Color = c;
		this.location = l;
	}

}
