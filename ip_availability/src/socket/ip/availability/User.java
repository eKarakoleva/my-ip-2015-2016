package socket.ip.availability;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.LinkedList;
//import java.util.List;
import java.util.LinkedList;
import java.util.List;


public class User {
	
	private String username;
	private boolean currentlylogged;
	private int logincount;

	
	final List<String> logInDates = new LinkedList<String>();
	final List<String> logOutDates = new LinkedList<String>();
	//final List<String> loggingDates = new LinkedList<String>();
	final List<String> loggingDates = new LinkedList<String>();
	
	DateFormat df = new SimpleDateFormat("yyyy­MM­dd'T'HH'_'mm'_'ss.SSSZ");

	 
	 public User(String name) {
		this.setUsername(name);
		this.setCurrentlylogged(true);
		this.setLogincount(1);
		this.logInDates.add(df.format(new Date()));
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
	
	public void setInDate(Date date){
		this.logInDates.add(df.format(new Date()));
		System.out.println(date);
	}
	
	public void setOutDate(Date date){

		this.logOutDates.add(df.format(new Date()));
		System.out.println(date);
	}
	
	public List<String> getLoggingTime(){
	
		String login = this.logInDates.toString().replace("[", "").replace("]", "");

		String logout = this.logOutDates.toString().replace("[", "").replace("]", "");
		
		String[] logindates = login.split(",");
		String[] logoutdates = logout.split(",");
		String a = "";
		for(int i=0;i<logInDates.size(); i++){
			if(logOutDates.size()< logInDates.size()){
				if(logInDates.size()-1 == i){
					a = ":"+logindates[i];	
				}else{
					a = ":"+logindates[i] + ":"+logoutdates[i];
					}
				
			}else{
				a = ":"+logindates[i] + ":"+logoutdates[i];
				}

			loggingDates.add(a);			
		}
		return loggingDates;
	}

}
