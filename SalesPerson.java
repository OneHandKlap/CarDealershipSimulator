import java.util.ArrayList;
/**
 * I thought it would make certain functions in the main easier if I had an object for each person to attach certain values and be able to retrieve them
 * @param sales arraylist which holds each sale/return that the person makes
 * @param name string value of the individuals name
 * @param totalSales numerical value of the number of sales they have made
 */
public class SalesPerson{

    String name;
    int totalSales;
    ArrayList<Transaction> sales;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalSales() {
		return this.totalSales;
	}

	public void setTotalSales(int totalSales) {
		this.totalSales = totalSales;
	}

	public ArrayList<Transaction> getSales() {
		return this.sales;
	}

	public void setSales(ArrayList<Transaction> sales) {
		this.sales = sales;
    }//Generic Constructor
    public SalesPerson(){
		name="unknown";
		totalSales=0;
		sales = new ArrayList<Transaction>();
	}//Specific constructor
    public SalesPerson(String n){
        name = n;
        totalSales=0;
        sales = new ArrayList<Transaction>();
	}//This method was never used, however it could be implemented in the future if you wanted to display the sales of a specific employee
	public String printSales(){
		if(!sales.isEmpty()){
			for(Transaction temp: sales){
				System.out.println(temp.display());
			}
		}	
		else{
			return "No sales found for this employee.";
		}
		return name;
	}
    public static void main(String[]args){
    
    }

}