package socket.ip.availability;


//import java.util.Date;
//import java.util.LinkedList;
//import java.util.List;


public class User {
	
	private String username;
	private boolean currentlylogged;
	private int logincount;
	
	//Date date = new Date();
	 
	 public User(String name) {
		this.setUsername(name);
		this.setCurrentlylogged(true);
		this.setLogincount(1);
	}

	public boolean isCurrentlylogged() {
		return currentlylogged;
	}

	public void setCurrentlylogged(boolean currentlylogged) {
		this.currentlylogged = currentlylogged;
	}

	public int getLogincount() {
		return logincount;
	}

	public void setLogincount(int logincount) {
		this.logincount = logincount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public int incrementCounter() {
		return this.logincount += 1;
	}
	
}
