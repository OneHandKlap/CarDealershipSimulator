import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * The CarDealershipSimulator simulates the working of a car dealership's inventory management..
 * Has increased capabilities over version 1.0:
 * can display sales stats, transactions, sales people etc.
 * @author Peter Aboud - 500883647
 * @version 2.0
 * @since 2019-04-13 
 */

public class CarDealershipSimulator2 {

  public static void main(String[] args)
  {
	  	// Create a CarDealership object
		CarDealership dealer = new CarDealership();
		
	  
		// Create some car objects by reading from a file
		ArrayList<Car> readCars = dealer.importCarData("cars.txt");
		//Print some user interface instructions and welcome message
		System.out.print("\nWelcome to Best-Ride Car Dealership!\n\nTo use our software input one of the following commands:");
		System.out.printf("\n\n%-40.40s   %-40.40s%n", "L - to list our inventory", "Q - to quit this program");
		System.out.printf("%-40.40s   %-40.40s%n", "BUY - to buy a vehicle", "RET - to return a car");
		System.out.printf("%-40.40s   %-40.40s%n", "ADD - to add more vehicles", "SPR - to sort our inventory by price");
		System.out.printf("%-40.40s   %-40.40s%n", "SSR - to sort by safety rating", "SMR - to sort by max range");
		System.out.printf("%-40.40s   %-40.40s%n", "FEL - to filter our inventory by Elect", "FAW - to filter for AWD");
		System.out.printf("%-40.40s   %-40.40s%n", "FPR - to filter by price", "FCL - to clear the filters");
		System.out.printf("%-40.40s   %-40.40s%n", "SALES - to show transactions", "SALES TEAM - to show the team");
		System.out.printf("%-40.40s   %-40.40s%n", "SALES TOPSP - to show best SalesPerson", "SALES STATS - to show stats for 2019");
		System.out.printf("%-40.100s%n", "SALES m - (where 'm' is a numerical value for month) to show sales for that month");

		Scanner scan = new Scanner(System.in);//initialize scanner
		while (scan.hasNextLine()){ //While loop to keep the program running as long as user doesnt input Q
			try{
			Scanner commandLine = new Scanner(scan.nextLine());
			String command = commandLine.nextLine();
			String[] input = command.split("[^a-zA-Z0-9]+"); //This line uses a delimiter in order to split the line that the user inputs into alpha numeric 'words'

			if(input.length==1){ //If the command is only 1 word in length then it evaluates against all the 1-word options in the menu.
				String choice = input[0];
				
			if((!choice.equals("MENU"))&&(!choice.equals("L"))&&(!choice.equals("BUY"))&&(!choice.equals("RET"))&&(!choice.equals("ADD"))&&(!choice.equals("SPR"))&&(!choice.equals("SSR"))&&(!choice.equals("FEL"))&&(!choice.equals("FAW"))&&(!choice.equals("SMR"))&&(!choice.equals("FCL"))&&(!choice.equals("Q"))&&(!choice.equals("SALES"))&&(!choice.equals("MENU"))&&(!choice.equals("FPR"))){
				System.out.println("Sorry I didn't understand that, try a different command.1"); //If the 1 word input by user does not match any of the 1-word commands it prompts the user to try again
			}

			else if(choice.equals("L")){ //Prints the list of cars currently held in the dealership object
				if(dealer.getCars().isEmpty()){
					System.out.println("Sorry, there are currently no vehicles in the lot. Add some by using the 'L' command."); //Error statement if list function is called on an empty dealership
				}
				else{
				dealer.displayInventory(); //If user chooses "L", display the inventory of the Dealership
				}
			}
			else if(choice.equals("MENU")){ //Reprints the menu options
				System.out.print("\nWelcome to Best-Ride Car Dealership!\n\nTo use our software input one of the following commands:");
				System.out.printf("\n\n%-40.40s   %-40.40s%n", "L - to list our inventory", "Q - to quit this program");
				System.out.printf("%-40.40s   %-40.40s%n", "BUY - to buy a vehicle", "RET - to return a car");
				System.out.printf("%-40.40s   %-40.40s%n", "ADD - to add more vehicles", "SPR - to sort our inventory by price");
				System.out.printf("%-40.40s   %-40.40s%n", "SSR - to sort by safety rating", "SMR - to sort by max range");
				System.out.printf("%-40.40s   %-40.40s%n", "FEL - to filter our inventory by Elect", "FAW - to filter for AWD");
				System.out.printf("%-40.40s   %-40.40s%n", "FPR - to filter by price", "FCL - to clear the filters");
				System.out.printf("%-40.40s   %-40.40s%n", "SALES - to show transactions", "SALES TEAM - to show the team");
				System.out.printf("%-40.40s   %-40.40s%n", "SALES TOPSP - to show best SalesPerson", "SALES STATS - to show stats for 2019");
				System.out.printf("%-40.100s%n", "SALES m - (where 'm' is a numerical value for month) to show sales for that month");
			}	  
			else if(choice.equals("Q")){ //Quit function
				break;
			}
			else if(choice.equals("BUY")){ // If user wishes to "BUY" a car, execute the following
				Scanner sc = new Scanner(System.in);
				System.out.println("Enter the VIN number of the vehicle you wish to purchase.");//prompt user to input a VIN number of vehicle
				try{
				int vehicleChoice = sc.nextInt(); //Store their choice
				int count=0; //simple variable to indicate whether a matching vehicle was found. I realize there is probably a more elegant way of doing this.
				for(Car temp:dealer.getCars()){ //iterate through the cars available in the dealership
					if(temp.getVIN()==vehicleChoice){
						System.out.println(dealer.buyCar(vehicleChoice)); //if a matching VIN is found in the lot, execute the buyCar function on that vehicle. Print the result
						count++;//increment our variable so we know it was successful
						break;
					}
				}
				if(count==0){
						System.out.println("No vehicle with that VIN could be found. Please try again.");//if no vehicle was found, print a statement to show that.

				}
				
				
				}catch(InputMismatchException e){// Try catch exception handling in case the user puts in letters instead of a number
					System.out.println(e+"\nSorry, I didn't understand that. Please use the VIN number of the vehicle you wish to purchase");
				}
			}
			else if(choice.equals("RET")){
				try{
				System.out.println("Input the transaction number of the vehicle you purchased.");//prompt the user for the Transaction ID of the purchase they wish to return
				Scanner scanner = new Scanner(System.in);
				int transactionNum = scanner.nextInt(); // store that value
				int count2=0; //simple variable to determine whether or not a transaction with that number was found
				for(Transaction value:dealer.getAccounts().getTransactions().values()){ //iterate through our map of transactions held in the accounting system
					if(value.getId()==transactionNum){
						dealer.returnCar(transactionNum);//if the TransactionID matches a sale in our system, then reverse the sale with the returnCar function
						count2++;//increment in order to show that the return was successful
						break;
						}					
					}
				if(dealer.getAccounts().getTransactions().isEmpty()){
					System.out.println("Sorry, no cars have been bought yet."); //error statement that lets the user know that they cannot return a car if none have been bought
				}
				else if(count2==0){ //if there are sales, but none match the ID that was supplied print a different error statement
					System.out.println("No transaction with that ID number was found. Check the number and try again please.");
				}
			}
				catch(InputMismatchException e){
					System.out.println("Please input the transaction number of the vehicle you purchased.");
				}	
			}
			else if(choice.equals("ADD")){ //Adds a pre-set ArrayList of cars into the inventory of the dealership
				//dealer.addCars(cars);
				dealer.addCars(readCars);
				System.out.println("Cars added to dealership");

			}
			else if(choice.equals("SPR")){ //Uses the sort function comparator defined in CarDealership in order to sort the collection of cars in ascending order by price
				dealer.sortByPrice();
				System.out.println("Inventory sorted by price, in descending order");
			}
			else if(choice.equals("SSR")){//Uses the sort function comparator defined in CarDealership in order to sort the collection of cars in ascending order by safety-rating
				dealer.sortBySafetyRating();
				System.out.println("Inventory sorted by safety-rating.");

			}
			else if(choice.equals("SMR")){//Uses the sort function comparator defined in CarDealership in order to sort the collection of cars in ascending order by max-range
				dealer.sortByMaxRange();
				System.out.println("Inventory sorted by max range, in ascending order");
			}
			else if(choice.equals("FPR")){ //Takes in two variables for min and max prices, and then filters out all car instances that do not fall within that range
				Scanner sc = new Scanner(System.in);
				try{
				System.out.println("Enter minimum price:");
				double min = sc.nextDouble();
				System.out.println("Enter maximum price:");
				double max = sc.nextDouble();
				dealer.filterByPrice(min, max);
				System.out.println("Inventory filtered: min = "+min+", max = "+max);
			}catch(InputMismatchException e){
				System.err.println(e);
				System.out.println("Sorry, that input was not recognized try to filter again, and use numbers to set min and max prices");
			}
		}
			else if(choice.equals("FEL")){ //Filters out all cars that are not ElectricCars from the inventory
				dealer.filterByElectric();
				System.out.println("Inventory filtered for 'Electric' vehicles");

			}
			else if(choice.equals("FAW")){ //Filters out all cars that are not AWD
				dealer.filterByAWD();
				System.out.println("Inventory filtered for 'AWD' vehicles");

			}
			else if(choice.equals("FCL")){ //Resets all the filters so that nothing is being filtered
				dealer.filtersClear();
				System.out.println("Inventory filters reset");
			
			}
			else if(choice.equals("SALES")){
				if(dealer.getAccounts().transactions.isEmpty()){ //if there have been no transactions, then print a simple error statement
					System.out.println("Sorry, no transactions yet.");
				}
				else{
					dealer.getAccounts().displayAllTransactions(); //else, print all the transactions held in the accounting system. See AccountingSystem.java for more information on this function.
				}
			}
		}
		if(input.length>1){ //If the user inputs more than 1 word into their command, then we only really need to evaluate the second word because all of our 2 word commands begin with the same word 'SALES'
			String choice=input[1]; //this is the key value 
			int nuChoice;
			if((!choice.equals("TEAM"))&&(!choice.equals("TOPSP"))&&(!choice.equals("STATS"))){ //If the user is asking for something other than these 3 options then there are two cases: 1. they want the 'Sales m' function, or 2. they made a mistake
				try{
					nuChoice = Integer.parseInt(choice); //We try to convert the second word to an integer, if successful we assume they want to fetch sales for a certain month
					if(nuChoice>11){
						throw new IllegalArgumentException(); //however if that integer is greater than 11 (the last month in the year) we throw an exception
					}
					Calendar date = new GregorianCalendar();
					date.set(Calendar.MONTH, nuChoice);
					System.out.println("The sales for the month of "+date.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT, Locale.CANADA)
									+ " are:"); //preliminary print message for the sales of that month
					int count=0;
					for(Map.Entry<Integer,Transaction> entry: dealer.getAccounts().getTransactions().entrySet()){ //iterate through the map of transactions held in accountingSystem
						if(entry.getValue().getDate().get(Calendar.MONTH)==nuChoice){ 
							System.out.println(entry.getValue().display());//if the value of the month indicated corresponds to that of one in the accounting system then we print
							count++;//simple determinant for whether or not we found a match
						}
					}
					if(count==0){//if we didnt find a match then print an error
						System.out.println("Sorry, there were no sales for that month. Business must have been slow, or Tolaz was too busy talking about CS.");
					}
				}

				catch(Exception e){
					System.out.println("Sorry I didn't understand that. Please try again.");
				}
				
			}

			else if(choice.equals("TEAM")){//uses the getTeam method to return the string value for names of all the salesPersons in the SalesTeam
				System.out.println("Sales team at Best-Ride Car Dealership: "+dealer.getTeam().getTeam());
			}
			else if(choice.equals("TOPSP")){//This function works because as we 'buyCar' we update the SalesPerson that sold the car, and attach the transaction to that object. Kind of like a logbook of all their sales
				int maxSales = 0;
				String maxSeller ="";
				int count=0;
				for(SalesPerson temp:dealer.getTeam().getTeamList()){ //Iterate through each SalesPerson in the SalesTeam
					int thisTotalSales=0;
					for(Transaction temp1:temp.getSales()){//Iterate through each of that individual's sales
						thisTotalSales+=temp1.getPrice();//add up the total value of all the sales they made
						count++;//simple way to determine if any sales have been made at all
					}
					if(thisTotalSales>maxSales){//if this person has sold the most in terms of value so far, then...
						maxSales=thisTotalSales;//update the maxSales variable
						maxSeller=temp.getName();//update the variable to reflect the name of the person who has so far sold the most
					}
				}
				if(count==0){//if there have been no sales yet, then print a simple error message
					System.out.println("There are no sales yet, so no top salesperson.");
				}
				else{//print statement to say who has sold the most, and how much they sold.
					System.out.println("The sales associate with the most sales is: "+maxSeller+", with "+maxSales+" in sales!");
				}
				
			}
			else if(choice.equals("STATS")){//this function works by first creating a map<int,int> where the first int is the numerical value of the month, and the second is ther number of sales for that month
				Map<Integer,Transaction> trans = dealer.getAccounts().getTransactions();
				Map<Integer,Integer> monthSales = new HashMap<Integer,Integer>();
				for(int i=0;i<12;i++){ //initialize the map for each month, setting initial sales variable to 0
					monthSales.put(i, 0);
				}
				int totalSales=0;
				int averageSalesPerMonth=0;
				int numberOfSales=0;
				int highestSalesMonth=0;
				int maxSalesPerMonth=0;
				int totalReturns=0;
				for(Map.Entry<Integer,Transaction> entry: trans.entrySet()){ //iterate through our map of transactions held in the accounting system
					Transaction thisTrans = entry.getValue();
					if(thisTrans.getTransactionType()==Transaction.Type.RET){ //we want to decrement certain values if there has been a return, so we do that here.
						totalReturns+=1;
						totalSales-=thisTrans.getPrice();
						numberOfSales--;
					}
					else{ //otherwise we want to update our new monthSales map
						int salesMonth = thisTrans.getDate().get(Calendar.MONTH); //first get the month value for the transaction
						monthSales.put(salesMonth, monthSales.get(salesMonth)+1); //update the monthSales map by adding a sale into that (key,value) object
						totalSales+=thisTrans.getPrice(); //add the value of that sale to the totalSales for the year
						numberOfSales++;//increment total number of sales for the year
					}
				}
				for(Map.Entry<Integer,Integer> entry: monthSales.entrySet()){//once all the transactions have been allocated to their certain month, we iterate through each month
					
					if(entry.getValue()>maxSalesPerMonth){ //this allows us to find which month had the most sales. If this month has more sales than any other, then:
						
						maxSalesPerMonth=entry.getValue(); //update the number which has made it the highest sales month
						highestSalesMonth=entry.getKey(); //update the highest month value
					}
				}
				averageSalesPerMonth=totalSales/12; //calculate the average sales for each month
				
				//then print!
				Calendar date = new GregorianCalendar();
				date.set(Calendar.MONTH, highestSalesMonth);
				System.out.println("The total number of sales for 2019 is: "+ numberOfSales);
				System.out.println("The average sales in terms of $ per month is: "+averageSalesPerMonth);
				System.out.println("The total value of cars sold is: "+ totalSales);
				if(maxSalesPerMonth==0){
					System.out.println("The highest sales month in terms of # of cars sold is: none, with "+maxSalesPerMonth+" sales!");
				}
				else{
					System.out.println("The highest sales month in terms of # of cars sold is: "+date.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT,Locale.CANADA) +", with "+maxSalesPerMonth+" sales!");
				}
					
				System.out.println("The total number of returns this year is: "+totalReturns);
			}
		}
			
			}
			catch(NoSuchElementException e){
				System.out.println("Please enter a command. To see the menu again enter 'MENU'.");
			}
		}
	}
}