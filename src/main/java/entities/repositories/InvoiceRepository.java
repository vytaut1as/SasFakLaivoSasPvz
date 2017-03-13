package entities.repositories;

import java.util.List;

import entities.Invoice;

public interface InvoiceRepository {

	public Invoice findByNumber(Long number);
	
	public List<Invoice> findAll();
	
	public void save(Invoice newInvoice);
	
	public void delete(Invoice invoice);
	
	
}
