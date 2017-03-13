package entities.repositories.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entities.Invoice;
import entities.repositories.InvoiceRepository;


public class InvoiceDaoImpl implements InvoiceRepository{

	static final Logger log = LoggerFactory.getLogger(InvoiceDaoImpl.class);
	
	private EntityManagerFactory entityManagerFactory;

	
	
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public Invoice findByNumber(Long number) {
		
		EntityManager em = getEntityManager();
		try { 
			TypedQuery<Invoice> invoiceQuery = em.createQuery("SELECT i From Invoice i WHERE i.number =:number",
					Invoice.class);
			invoiceQuery.setParameter("number", number);
			invoiceQuery.setMaxResults(1);
			
			return invoiceQuery.getSingleResult();
		} finally {
			em.close();
		}
		
	}

	public List<Invoice> findAll() {

		EntityManager em = getEntityManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Invoice> cq = cb.createQuery(Invoice.class);
			Root<Invoice> root = cq.from(Invoice.class);
			cq.select(root);
			TypedQuery<Invoice> tq = em.createQuery(cq);
			return tq.getResultList();
		} finally {
			em.close();
		}
		
	}

	public void save(Invoice newInvoice) {
		
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			if(!em.contains(newInvoice))
				newInvoice = em.merge(newInvoice);
			em.persist(newInvoice);
			em.getTransaction().commit();
			log.info("save new invoice");
		} finally {
			em.close();
		}
		
	}

	public void delete(Invoice invoice) {
		
		EntityManager em = getEntityManager();
		
		try {
			em.getTransaction().begin();
			invoice = em.merge(invoice);
			em.remove(invoice);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		
	}
	
	private EntityManager getEntityManager(){
		
		return entityManagerFactory.createEntityManager();
	}

}
