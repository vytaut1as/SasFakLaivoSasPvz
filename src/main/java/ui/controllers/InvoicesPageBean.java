package ui.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entities.Invoice;
import entities.repositories.InvoiceRepository;
import ui.controllers.InvoicesPageBean.InvoicesPageData;


public class InvoicesPageBean {
	
	static final Logger log = LoggerFactory.getLogger(InvoicesPageBean.class);
	
	public static final String NAV_SHOW_ADD_ITEM = "show-add-item";
	public static final String NAV_SHOW_VIEW = "show-view-page";
	public static final String NAV_LIST_INVOICES = "list-invoices";
	
	public static class InvoicesPageData implements Serializable {

		private static final long serialVersionUID = -3924198083822619907L;
		
		@Valid
		private Invoice newInvoice;
		
		@Valid
		private Invoice currentInvoice;
		
		private List<Invoice> foundInvoices;
		
		public void init() {
			newInvoice = new Invoice();
			foundInvoices = new ArrayList<Invoice>();
		}

		public Invoice getNewInvoice() {
			return newInvoice;
		}

		public void setNewInvoice(Invoice newInvoice) {
			this.newInvoice = newInvoice;
		}

		public Invoice getCurrentInvoice() {
			return currentInvoice;
		}

		public void setCurrentInvoice(Invoice currentInvoice) {
			this.currentInvoice = currentInvoice;
		}

		public List<Invoice> getFoundInvoices() {
			return foundInvoices;
		}

		public void setFoundInvoices(List<Invoice> foundInvoices) {
			this.foundInvoices = foundInvoices;
		}

		
	}
	
	private InvoicesPageData data;
	
	private InvoiceRepository invoiceRepo;
	
	public String addNew() {
		log.info("New invoice added");
		invoiceRepo.save(data.newInvoice);
		data.newInvoice = new Invoice();
		
		return NAV_LIST_INVOICES;
	}
	
	public String deleteSelected(Invoice invoice) {
		log.info("Deleting invoice: "+invoice.getNumber());
			invoiceRepo.delete(invoice);
		
		return NAV_LIST_INVOICES;
	}
	
	public String showAddItemPage(Invoice invoice) {
		log.info("Intention to add new item to invoice nr: "+invoice.getNumber());
		data.currentInvoice = invoice;
		return NAV_SHOW_ADD_ITEM;
	}
	
	public String showViewPage(Invoice invoice) {
		data.currentInvoice = invoice;
		return NAV_SHOW_VIEW;
	}

	public InvoicesPageData getData() {
		return data;
	}

	public void setData(InvoicesPageData data) {
		this.data = data;
	}

	public InvoiceRepository getInvoiceRepo() {
		return invoiceRepo;
	}

	public void setInvoiceRepo(InvoiceRepository invoiceRepo) {
		this.invoiceRepo = invoiceRepo;
	}
	
	public List<Invoice> getInvoiceList() {
		return invoiceRepo.findAll();
	}
	
}


























