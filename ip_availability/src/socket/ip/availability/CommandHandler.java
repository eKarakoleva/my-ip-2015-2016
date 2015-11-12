package socket.ip.availability;

import java.io.IOException;

public class CommandHandler{

	Users users = new Users();

	
	public synchronized void execute(String command) throws IOException{		
		if(command.contains(":")){
			String[] split_command = command.split(":");
			if(split_command.length == 2){
				switch (split_command[1]) {
				
				case "shutdown": 
					// to do
					break;
				case "login":
					//to do
					break;
					}
				}
			}
		}
	}
