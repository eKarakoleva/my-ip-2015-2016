package ip.availability;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public class LogingUsers {
	
	public static void main(String[] args) {
		final List<String> currentlyLoggedUser = new LinkedList<String>();
		final Map<String,Integer> usersToLoginCount =
				new HashMap<String,Integer>();
		
		Scanner in = new Scanner(System.in);
		boolean x = true;
		String log = "";
		
			while(x){
				System.out.print("Въведете команда:");
				final String command = in.next();

				if(command.equals("shutdown")){break;}
				
				if(command.contains(":")){
					String[] split_command = command.split(":");
					if(split_command.length == 2){
						switch (split_command[1]) {
						
							case "shutdown": 
							String namesh = split_command[0];

							if(currentlyLoggedUser.contains(namesh)){
								System.out.println("ok");
								x = false;
								in.close();
							}else{
								System.out.println("error:notlogged");
								}
							break;
							
							case "login": 
								String name = split_command[0];
								
								System.out.println("ok");
								Integer val= usersToLoginCount.get(name);
								if(val == null){val=0;}
								if(!(currentlyLoggedUser.contains(name))){
									currentlyLoggedUser.add(name);
									val++;
								}
								usersToLoginCount.put(name, val);
							break;
							case "logout":
								//usersToLoginCount.remove(split_command[0]);
								String namel = split_command[0];
								if(currentlyLoggedUser.contains(namel)){
									currentlyLoggedUser.remove(namel);
									System.out.println("ok");
								}else{System.out.println("error:notlogged");}
							break;
							case "listavailable":
								String namelist = split_command[0];
								if(currentlyLoggedUser.contains(namelist)){
									System.out.print("ok");
									for (int i=0;i < currentlyLoggedUser.size();i++)
									{
										System.out.printf(":%s",currentlyLoggedUser.get(i));
									}
								}else{System.out.println("error:notlogged");}
							break;
							default: System.out.println("error:unknowncommand");
		                    break;
						}
					}else if (split_command.length == 3 && "info".contains(split_command[1]) ){
						if(currentlyLoggedUser.contains(split_command[0])){
						    Integer val= usersToLoginCount.get(split_command[2]);
						    if(currentlyLoggedUser.contains(split_command[2])){log = "true"; } else {log = "false";}
						    if(val == null){val=0;}
						    System.out.printf("ok:%s:%s:%d\n",split_command[2],log,val);
						}else{System.out.println("error:notlogged");}
					}else{System.out.println("error:unknowncommand");}	
				}else{System.out.println("error:unknowncommand");}
			}
	}

}
