import java.io.BufferedReader;
import java.io.FileReader;

import java.util.*;
/**
 * The CarDealershipSimulator implements and demonstrates the code written in the package.
 * Simulates the working of a car dealership's inventory management software with several different commands.
 * @author Peter Aboud - 500883647
 * @version 1.0
 * @since 2019-03-20 
 */

public class CarDealershipSimulator {
	
  public static void main(String[] args)
  {
		CarDealership dealer = new CarDealership();
		
	  // Create a CarDealership object
		ArrayList<Car> cars = new ArrayList<Car>(); // Create Car Array list 
		Car x1 = new Car("BMW", "Silver", 4, Vehicle.Power.GAS_ENGINE, Car.Model.SUV, 600, 90.0, true, 35000); //Create some cars
		Car passat = new Car("Volkswagen", "red", 4, Vehicle.Power.GAS_ENGINE, Car.Model.SEDAN, 700, 95.0, false, 32000);
		Car kia = new Car("Kia", "blue", 4, Vehicle.Power.GAS_ENGINE, Car.Model.SEDAN, 800, 9.5, false, 16000);
		Car honda = new Car("Honda", "red", 4, Vehicle.Power.GAS_ENGINE, Car.Model.SUV, 600, 8.5, true, 25000);	
		Car volt = new ElectricCar("Chevrolet", "green", 4, Vehicle.Power.ELECTRIC_MOTOR,Car.Model.SEDAN, 400, 9, false, 16000, 50, "Lithium-Ion");
		Car tesla = new ElectricCar("Tesla", "silver", 4, Vehicle.Power.ELECTRIC_MOTOR,Car.Model.SPORTS,500,8,false,40000,70, "Lithium-Ion");
		Car elCamino = new Car("Chevrolet", "red", 4, Vehicle.Power.GAS_ENGINE, Car.Model.SUV, 500, 5, true, 15000);
		Car odyssey = new Car("Honda","black",4, Vehicle.Power.GAS_ENGINE, Car.Model.MINIVAN,650,9.5,false,33000);
		cars.add(x1);
		cars.add(passat);
		cars.add(kia);
		cars.add(honda);
		cars.add(volt);
		cars.add(tesla);
		cars.add(elCamino);
		cars.add(odyssey);
		//Print some user interface instructions and welcome message
		System.out.print("\nWelcome to Best-Ride Car Dealership!\n\nTo use our software input one of the following commands:");
		System.out.printf("\n\n%-30.30s   %-30.30s%n", "L - to list our inventory", "Q - to quit this program");
		System.out.printf("%-30.30s   %-30.30s%n", "BUY - to buy a vehicle", "RET - to return a car");
		System.out.printf("%-30.30s   %-30.30s%n", "ADD - to add more vehicles", "SPR - to sort our inventory by price");
		System.out.printf("%-30.30s   %-30.30s%n", "SSR - to sort by safety rating", "SMR - to sort by max range");
		System.out.printf("%-30.30s   %-30.30s%n", "FEL - to filter our inventory by Electric Vehicles", "FAW - to filter for AWD");
		System.out.printf("%-30.30s   %-30.30s%n", "FPR - to filter by price", "FCL - to clear the filters");
		System.out.printf("%-30.30s   %-30.30s%n", "SALES - to show transactions", "SALES TEAM - to show the team");

		Scanner scan = new Scanner(System.in);//initialize scanner
		while (scan.hasNextLine()){
			Scanner commandLine = new Scanner(scan.nextLine());
			String choice = commandLine.nextLine();
			if((!choice.equals("L"))&&(!choice.equals("BUY"))&&(!choice.equals("RET"))&&(!choice.equals("ADD"))&&(!choice.equals("SPR"))&&(!choice.equals("SSR"))&&(!choice.equals("FEL"))&&(!choice.equals("FAW"))&&(!choice.equals("SMR"))&&(!choice.equals("FCL"))&&(!choice.equals("Q"))&&(!choice.equals("SALES"))&&(!choice.equals("SALES TEAM"))&&(!choice.equals("SALES TOPSP"))&&(!choice.equals("SALES STATS"))){
				System.out.println("Sorry I didn't understand that, try a different command.");
			}
			else if(choice.equals("L")){
				dealer.displayInventory(); //If user chooses "L", display the inventory of the Dealership
			}	  
			else if(choice.equals("Q")){
				break;
			}
			else if(choice.equals("BUY")){ // If user wishes to "BUY" a car, set new scanner to read and store their choice, and fetch that vehicle
				Scanner sc = new Scanner(System.in);
				System.out.println("Enter the VIN number of the vehicle you wish to purchase.");
				try{
				int vehicleChoice = sc.nextInt();
				System.out.println(dealer.buyCar(vehicleChoice));
				
				}catch(InputMismatchException e){// Try catch exception handling in case the user puts in letters instead of a number
					System.out.println(e+"\nSorry, I didn't understand that. Please use the VIN number of the vehicle you wish to purchase");
				}
			}
			else if(choice.equals("RET")){ //re-adds the variable stored in lastCarBought back into the inventory ArrayList
				try{
				System.out.println("Input the transaction number of the vehicle you purchased.");
				Scanner scanner = new Scanner(System.in);
				int transactionNum = scanner.nextInt();
				for(Transaction value:dealer.getAccounts().getTransactions().values()){
					if(value.getId()==transactionNum){
						dealer.returnCar(transactionNum);
						break;
					}
				}	
				}
				catch(InputMismatchException e){
					System.out.println("Please input the transaction number of the vehicle you purchased.");
				}
				
			
				
			}
			else if(choice.equals("ADD")){ //Adds a pre-set ArrayList of cars into the inventory of the dealership
				dealer.addCars(cars);
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
				if(dealer.getAccounts().transactions.isEmpty()){
					System.out.println("Sorry, no transactions yet.");
				}
				else{
					dealer.getAccounts().displayAllTransactions();
				}
			}
			else if(choice.equals("SALES TEAM")){
				System.out.println(dealer.getTeam().getTeam());
			}
			else if(choice.equals("SALES TOPSP")){
				int maxSales = 0;
				String maxSeller ="";
				for(SalesPerson temp:dealer.getTeam().getTeamList()){
					int thisTotalSales=0;
					for(Transaction temp1:temp.getSales()){
						thisTotalSales+=temp1.getPrice();
					}
					if(thisTotalSales>maxSales){
						maxSales=thisTotalSales;
						maxSeller=temp.getName();
					}
				}
				
				System.out.println("The sales associate with the most sales is: "+maxSeller+", with "+maxSales+" in sales!");
			}
			else if(choice.equals("SALES STATS")){
				Map<Integer,Transaction> trans = dealer.getAccounts().getTransactions();
				Map<Integer,Integer> monthSales = new HashMap<Integer,Integer>();
				int totalSales=0;
				int averageSales=0;
				int averageSalesPerMonth=0;
				int numberOfSales=0;
				int highestSalesMonth=0;
				int maxSalesPerMonth=0;
				int totalReturns=0;
				for(Map.Entry<Integer,Transaction> entry: trans.entrySet()){
					Transaction thisTrans = entry.getValue();
					if(thisTrans.getTransactionType()==Transaction.Type.RET){
						totalReturns+=1;
						totalSales-=thisTrans.getPrice();
						numberOfSales--;
					}
					else{
						int salesMonth = thisTrans.getDate().getInstance().get(Calendar.MONTH);
						monthSales.put(salesMonth, monthSales.get(salesMonth)+1);
						totalSales+=thisTrans.getPrice();
						numberOfSales++;
					}
				}
				for(Map.Entry<Integer,Integer> entry: monthSales.entrySet()){
					averageSalesPerMonth+=entry.getValue();
					if(entry.getValue()>maxSalesPerMonth){
						highestSalesMonth=entry.getKey();
						maxSalesPerMonth=entry.getValue();
					}
				}
				averageSalesPerMonth=averageSalesPerMonth/12;
				averageSales = totalSales/numberOfSales;
				Calendar date = new GregorianCalendar();
				date.set(Calendar.MONTH, highestSalesMonth);
				System.out.println("The total number of sales for 2019 is: "+ numberOfSales);
				System.out.println("The average sales in terms of $ per month is: "+averageSales/averageSalesPerMonth);
				System.out.println("The total number of cars sold is: "+ totalSales);
				System.out.println("the highest sales month in terms of # of cars sold is: "+date.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT,Locale.CANADA ));
			}
		}
  	}
}