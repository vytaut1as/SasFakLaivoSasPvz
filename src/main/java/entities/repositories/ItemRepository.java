package entities.repositories;

import java.util.List;

import entities.Item;

public interface ItemRepository {

	public void insertOrUpdate(Item item);
	
	public void delete(Item item);
	
	public void deleteById(Long itemId);
	
	public List<Item> findAll();
	
	public Long countAllItems();
	
	
	
}
