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
					String name = split_command[0];		
					if(!(users.synusersInfo.containsKey(name))){
						User user = new User(name);
						users.synusersInfo.put(name,user);
						System.out.println(users.synusersInfo.get(name).getUsername());
						
					}
					if(users.synusersInfo.get(name).isCurrentlylogged()==false){

						users.synusersInfo.get(name).incrementCounter();
						users.synusersInfo.get(name).setCurrentlylogged(true);
						System.out.println(users.synusersInfo.get(name).getUsername());
						
					}
					break;
				case "logout":
					String namelogout = split_command[0];	
					
					if(users.synusersInfo.containsKey(namelogout)){
						if(users.synusersInfo.get(namelogout).isCurrentlylogged()){
							users.synusersInfo.get(namelogout).setCurrentlylogged(false);
						}else{
						System.out.println("NOT LOGIN");
						}
					}else{System.out.println("NOT LOGIN");};
					break;
				case "listabsent":
					System.out.print("ok");
					  for (String userName : users.synusersInfo.keySet()){
						   User user = users.synusersInfo.get(userName);
							   if(!user.isCurrentlylogged()){
								   System.out.print(":" + userName);
							   }
					  }
					break;
				case "listavailable":
					  for (String userName : users.synusersInfo.keySet()){
						   User user = users.synusersInfo.get(userName);
							   if(user.isCurrentlylogged()){
								   System.out.print(":" + userName);
							   }
					  }
					break;
					}
				}else if (split_command.length == 3 && "info".contains(split_command[1]) ){
					String nameask = split_command[0];
					String namegive = split_command[2];
					if(users.usersInfo.get(nameask).isCurrentlylogged()){
						 System.out.println(users.usersInfo.get(namegive).getLogincount());
						 System.out.println(users.usersInfo.get(namegive).isCurrentlylogged());
					}
			}
		}
	}
}
