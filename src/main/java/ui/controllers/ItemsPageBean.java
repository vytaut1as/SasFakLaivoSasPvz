package ui.controllers;

import entities.Invoice;
import entities.Item;
import entities.repositories.InvoiceRepository;
import entities.repositories.ItemRepository;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ItemsPageBean {

	static final Logger log = LoggerFactory.getLogger(ItemsPageBean.class);
	
	private InvoiceRepository invoiceRepo;
	private ItemRepository itemRepo;
	
	private InvoicesPageBean invoicesPageBean;
	
	private Item newItem;
	
	public void init() {
		newItem = new Item();
	}
	
	public String addNew() {
		
		Invoice invoice = invoicesPageBean.getData().getCurrentInvoice();
		log.info("Adding new item to invoice : {}", invoice);
		log.info("New item info: {}", newItem);
		newItem.setInvoice(invoice);
		invoice.addItem(newItem);
		invoiceRepo.save(invoice);
		
		return InvoicesPageBean.NAV_LIST_INVOICES;
	}
	
	public void deleteSelected(Item item) {
		itemRepo.deleteById(item.getId());
		invoicesPageBean.getData().getCurrentInvoice().removeItem(item);
	}

	public InvoiceRepository getInvoiceRepo() {
		return invoiceRepo;
	}

	public void setInvoiceRepo(InvoiceRepository invoiceRepo) {
		this.invoiceRepo = invoiceRepo;
	}

	public ItemRepository getItemRepo() {
		return itemRepo;
	}

	public void setItemRepo(ItemRepository itemRepo) {
		this.itemRepo = itemRepo;
	}

	public InvoicesPageBean getInvoicePageBean() {
		return invoicesPageBean;
	}

	public void setInvoicePageBean(InvoicesPageBean invoicePageBean) {
		this.invoicesPageBean = invoicePageBean;
	}

	public Item getNewItem() {
		return newItem;
	}

	public void setNewItem(Item newItem) {
		this.newItem = newItem;
	}
	
	public List<Item> getItemList() {
		return invoicesPageBean.getData().getCurrentInvoice().getItems();
	}
	
}
