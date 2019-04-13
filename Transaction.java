import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Transaction.java class is the object for all sales/returns for the dealership
 * @author Peter Aboud - 500883647
 * @version 2.0
 * @since 2019-04-13 
 * @param id - transaction ID which is a unique identifier for the object
 * @param date - calendar value associated with each object identifying the date the transaction took place
 * @param item - what was sold
 * @param salesPerson - who sold it
 * @param price - how much it was sold for
 * @param type - what type of transaction it was
 */

public class Transaction{

    private int id;
    private Calendar date;
    private Car item;
    private String salesPerson;
    private double price;
    private Type transactionType;
    enum Type {BUY, RET};


	//Getters and setters
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getDate() {
		return this.date;
	}

	public void setDate(Date day) {
		this.date.setTime(day);
	}

	public Car getItem() {
		return this.item;
	}

	public void setItem(Car item) {
		this.item = item;
	}

	public String getSalesPerson() {
		return this.salesPerson;
	}

	public void setSalesPerson(String salesPerson) {
		this.salesPerson = salesPerson;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Type getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(Type transactionType) {
		this.transactionType = transactionType;
	}
	//Generic constructor
    public Transaction(){
		Date day = new Date();
        this.date = new GregorianCalendar();
        date.setTime(day);
	}
	//Specific constructor
    public Transaction(int id, Car item, String seller, Type transact){
        Date day = new Date();
        
        this.date = new GregorianCalendar();
        date.setTime(day);
        this.id=id;
        this.item=item;
        this.salesPerson=seller;
        this.transactionType = transact;
        this.price=item.getPrice();
        
	}
	//display function. A specific format for each type of transaction
    public String display(){
        if(this.transactionType == Transaction.Type.BUY){
            return "#"+this.id+" "+ this.salesPerson+" sold: "+this.item.getMfr()+" "+ new SimpleDateFormat("EEE, MMM d, yyyy").format(this.date.getTime());
        }
        else{
            return"#"+this.id+" "+ this.salesPerson+" returned: "+this.item.getMfr()+" "+new SimpleDateFormat("EEE, MMM d, yyyy").format(this.date.getTime());
        }
        
    }
    public static void main(String[]args){
    }
}