import java.util.GregorianCalendar;

import sun.util.calendar.Gregorian;

public class Transaction{

    private int id;
    private GregorianCalendar date;
    private Car item;
    private String salesPerson;
    private double price;
    private Type transactionType;
    enum Type {BUY, RET};
    public Transaction(int id, Car item, String seller, Type transact){
        GregorianCalendar
        this.id=id;
        this.item=item;
        this.salesPerson=seller;
        this.transactionType = transact;
        this.price=item.getPrice();
        this.date=GregorianCalendar.from;
        
    }
}