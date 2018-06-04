package mdb;

import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.example.ejb.dao.Clientb2bDao;
import com.example.ejb.daoRemote.Clientb2bDAORemote;
import com.example.ejb.dto.Clientb2bDTO;
import com.example.ejb.model.Clientb2b;

/**
 * Message-Driven Bean implementation class for: NotificationMDB
 */
@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") }, mappedName = "jms/queueResource")
public class NotificationListener implements MessageListener{

	@EJB
	private Clientb2bDAORemote daoClientb2b;
	
	private GoogleMailSender mailSender = new GoogleMailSender();

    /**
     * Default constructor.
     */
    public NotificationListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see MessageListener#onMessage(Message)
     */
    @Override
	public void onMessage(final Message message) {

    	String description;
    	try {
    		description = message.getStringProperty("description");
    		final List<Clientb2bDTO> subscribedClients = daoClientb2b.getAllSubscribed();
    		
    		for(Clientb2bDTO current : subscribedClients) 
        		mailSender.SendMessage(current.getEmail(), description);
		} catch (final JMSException e) {
			e.printStackTrace();
		}

    }
} 