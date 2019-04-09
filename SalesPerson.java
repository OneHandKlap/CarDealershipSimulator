import java.util.ArrayList;

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
    }
    public SalesPerson(){
		name="unknown";
		totalSales=0;
		sales = new ArrayList<Transaction>();
	}
    public SalesPerson(String n){
        name = n;
        totalSales=0;
        sales = new ArrayList<Transaction>();
	}
	public String printSales(){
		if(!sales.isEmpty()){
			for(Transaction temp: sales){
				return temp.display();
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