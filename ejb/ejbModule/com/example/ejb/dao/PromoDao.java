package com.example.ejb.dao;


import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
 

import com.example.ejb.daoRemote.PromoDaoRemote;
import com.example.ejb.dto.PromoDto;
import com.example.ejb.model.Promo;

@Stateless
public class PromoDao implements PromoDaoRemote {

	@PersistenceContext
	private EntityManager entityManager;

	public Promo fromDTO(PromoDto data) {
		Promo promo = new Promo();
		promo.setId(data.getId());
		promo.setDescription(data.getDescription());
		promo.setStockSupplier(StockSupplierDao.fromDTO(data.getStockSupplier()));
		
		return promo;
	}  
	
	@Override
	public void insert(PromoDto data) {
		Promo promo = fromDTO(data); 
		entityManager.persist(promo);  

		try {
			final InitialContext ic = new InitialContext();
			final ConnectionFactory cf = (ConnectionFactory) ic.lookup("jms/myResource");
			final Queue destination = (Queue) ic.lookup("jms/queueResource");
			try (Connection conn = cf.createConnection();
					Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
					MessageProducer producer = session.createProducer(destination)) {
				conn.start();
				final Message myMessage = session.createTextMessage();
				myMessage.setStringProperty("type", "promo");
				myMessage.setStringProperty("description", promo.getDescription());
				producer.send(myMessage);
			} catch (final JMSException e) {
				e.printStackTrace();
			}

		} catch (final NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(PromoDto data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(PromoDto data) {
		// TODO Auto-generated method stub

	}
}
