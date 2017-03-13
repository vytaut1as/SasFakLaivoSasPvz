package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;




//@Table(indexes = @Index(columnList = "name ASC, lastname DESC", name = "AUTH_NAME_IDX", unique=false) , name = "AUTHOR")
//@DiscriminatorColumn(discriminatorType = DiscriminatorType.CHAR, length = 1, name = "AUTHOR_TYPE")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorValue("G") // general
@Entity
@Table(name = "invoice")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Invoice implements Serializable {

	private static final long serialVersionUID = -765511469134241231L;

	@Id
	@GeneratedValue
	private long id;
	
	@NotNull
	private long number;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private String company;
	private String recipient;
	
	@OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	private List<Item> items;

	public void addItem(Item item) {
		
		if(getItems()==null){
			setItems(new ArrayList<Item>());
		}if(!getItems().contains(item)){
			getItems().add(item);
		}	
	}
	
	public void removeItem(Item item) {
		
		if(getItems().contains(item)){
			getItems().remove(item);
		}
		
	}
	
	public double getTotalPrice() {
		double price = 0;
		 for(Item i : this.items) {
			 price+=i.getTotalPrice();
		 }
		 return price;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	
	
}


