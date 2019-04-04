import java.util.*;


public class AccountingSystem{

    Map<Integer,Transaction> transactions;

    public AccountingSystem(){
        transactions = new HashMap<Integer,Transaction>();
    }

    public String add(Calendar date, Car item, String salesPerson, Transaction.Type type, double price){
        Random random = new Random();
        int transactionID = random.nextInt(99);
        Transaction nextTransaction = new Transaction(transactionID, item, salesPerson, type);
        nextTransaction.setDate(date.getTime());
        nextTransaction.setPrice(price);
        transactions.put(transactionID,nextTransaction);
        return nextTransaction.display();
    }
    public Transaction getTransaction(int id){
       return transactions.get(id);
    }
    public void displayAllTransactions(){
        for(Map.Entry<Integer,Transaction> entry: transactions.entrySet()){
            System.out.println(entry.getValue().display());
        }
    }

    public static void main(String[]args){
        Car BMW = new Car("BMW", "Red", 4, Vehicle.Power.GAS_ENGINE, Car.Model.SEDAN, 700, 9.5, true, 40000);
        SalesTeam newTeam = new SalesTeam();
        //Transaction firstSale = new Transaction(85, BMW, newTeam.getPerson(),Transaction.Type.BUY);
        AccountingSystem ourSystem = new AccountingSystem();
        Date date = new Date();
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        ourSystem.add(cal, BMW, newTeam.getPerson(), Transaction.Type.BUY, BMW.getPrice());
        ourSystem.displayAllTransactions();
    }
}