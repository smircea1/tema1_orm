package standalone_client;

import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.example.ejb.daoRemote.Clientb2cDAORemote; 
import com.example.ejb.daoRemote.OrderDAORemote;
import com.example.ejb.daoRemote.UserDAORemote;
import com.example.ejb.dto.Clientb2cDTO;
import com.example.ejb.dto.OrderDTO;
import com.example.ejb.dto.UserDTO;

public class Source {

	public static void main(String[] args) throws NamingException{ 
		InitialContext context = new InitialContext();
		
		UserDAORemote daoUser = (UserDAORemote)  context.lookup("java:global/ear/ejb/UserDao!com.example.ejb.daoRemote.UserDAORemote");
		Clientb2cDAORemote daoClientb2c = (Clientb2cDAORemote)  context.lookup("java:global/ear/ejb/Clientb2cDao!com.example.ejb.daoRemote.Clientb2cDAORemote");
		OrderDAORemote daoOrder = (OrderDAORemote)  context.lookup("java:global/ear/ejb/OrderDao!com.example.ejb.daoRemote.OrderDAORemote");

		Date dateRegister = new Date();
		dateRegister.setYear(2018);
		dateRegister.setMonth(3);
		
		Clientb2cDTO client = new Clientb2cDTO();
		client.setUsername("cool");
		client.setPassword("done");
		
		client.setCnp("123566745");
		client.setNume("Testingg");
		client.setPrenume("Stuff");
		client.setDateRegister(dateRegister);

		daoClientb2c.insert(client);  
		
		UserDTO user = daoUser.findByUsername(client.getUsername()); 	// updates userID 
		  
		System.out.println("user passed. id = " + user.getId());
		  
		
		client = daoClientb2c.findByCnp(client.getCnp());	// updates client ID
		
		client.setNume(client.getNume() + "g");
		daoClientb2c.update(client);
		System.out.println("client passed. id = " + client.getId());
		
		OrderDTO order = new OrderDTO();
		order.setClientb2c(client);
		order.setDate(1231242321);
		order.setOrderNo("2123312"); 

		daoOrder.insert(order); 

		System.out.println("run ended.");
	}

}
