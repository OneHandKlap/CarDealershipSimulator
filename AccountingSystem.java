import java.util.*;
/**
 * This class is the foundation for the dealership's transaction based accounting system
 * @param transactions map to store and track individual transactions
 * @param transIDs arraylist to track and ensure each transID is unique
 */


public class AccountingSystem{

    Map<Integer,Transaction> transactions;
    ArrayList<Integer> transIDs;

    //Getters
	public Map<Integer,Transaction> getTransactions() {
		return this.transactions;
    }
    public Transaction getTransaction(int id){
        return transactions.get(id);
    }
    //Generic class constructor
    public AccountingSystem(){
        transactions = new HashMap<Integer,Transaction>();
        transIDs = new ArrayList<Integer>();
    }
    //
    public String add(Transaction trans){
        transactions.put(trans.getId(), trans);
        return trans.display();
    }
    
    //This function simultaneously creates a transaction given the inputs, and places it within the accounting system map for transactions
    public Transaction add(Calendar date, Car item, String salesPerson, Transaction.Type type, double price){
        
        Random random = new Random();
        int transactionID = random.nextInt(98)+1; //creates a random transaction ID between 0-99
        while(transIDs.contains(transactionID)){//this while loop ensures that the generated ID# is indeed unique
            transactionID=random.nextInt(98)+1;
        }
        transIDs.add(transactionID); //updates our list of used ID's
        Transaction nextTransaction = new Transaction(transactionID, item, salesPerson, type); //creates the transaction with the inputs
        nextTransaction.setDate(date.getTime()); //updates the values that are not included in the constructor
        nextTransaction.setPrice(price);
        transactions.put(transactionID,nextTransaction); //adds it to the accountingSystem's map of transactions
        return nextTransaction;
    }

    
    public void displayAllTransactions(){ //iterates across the map of all transactions and prints them out
        for(Map.Entry<Integer,Transaction> entry: transactions.entrySet()){
            System.out.println(entry.getValue().display());
        }
    }

    public static void main(String[]args){
    }
}