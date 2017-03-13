package entities.repositories.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import entities.Item;
import entities.repositories.ItemRepository;

public class ItemImpl implements ItemRepository {

	private EntityManagerFactory entityManagerFactory;
	

	
	
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public void insertOrUpdate(Item item) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			boolean merged = false;
			for(Item i : item.getInvoice().getItems()) {
				if(!em.contains(i)) {
					em.merge(i);
					merged = true;
				} else 
					em.persist(i);
			}
			if(merged)
				em.merge(item);
			else
				em.persist(item);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		
	}

	public void delete(Item item) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.remove(item);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		
	}

	public void deleteById(Long itemId) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			Item item = em.find(Item.class, itemId);
			if(item != null)
				em.remove(item);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		
	}

	public List<Item> findAll(){
		EntityManager em = getEntityManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Item> cq = cb.createQuery(Item.class);
			Root<Item> root = cq.from(Item.class);
			cq.select(root);
			TypedQuery<Item> tq = em.createQuery(cq);
			return tq.getResultList();
		} finally {
			em.close();
		}
		
	}

	public Long countAllItems() {
		EntityManager em = getEntityManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Long> cq = cb.createQuery(Long.class);
			cq.select(cb.count(cq.from(Item.class)));
			TypedQuery<Long> q = em.createQuery(cq);
			return q.getSingleResult();
		} finally {
			em.close();
		}
	}
	
	private EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

}
