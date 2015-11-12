package socket.ip.availability;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Users {
	
	//final Map<String,User> users=
	//		Collections.synchronizedMap(new HashMap<String,User>());
	
	Map<String,User>  usersInfo = new HashMap<String,User>();
	Map<String,User> synusersInfo = Collections.synchronizedMap(usersInfo);

}
