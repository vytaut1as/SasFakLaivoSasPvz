package entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Table(name = "item")
@Entity
public class Item implements Serializable {

	private static final long serialVersionUID = -8502715361751610840L;

	@Id
	@GeneratedValue
	private long id;
	
	@NotNull
	@NotBlank
	private String title;
	
	private int amount;
	
	private String weight;
	
	private double price;
	
	@JoinColumn(name = "invoice_id")
	@ManyToOne(optional = true, cascade = { CascadeType.ALL})
	private Invoice invoice;

	
	public double getTotalPrice(){
		
		return amount * price;
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
	
	
}
