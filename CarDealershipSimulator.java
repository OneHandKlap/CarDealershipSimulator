import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
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

		Scanner scan = new Scanner(System.in);//initialize scanner
		while (scan.hasNextLine()){
			Scanner commandLine = new Scanner(scan.nextLine());
			String choice = commandLine.next();
			if((!choice.equals("L"))&&(!choice.equals("BUY"))&&(!choice.equals("RET"))&&(!choice.equals("ADD"))&&(!choice.equals("SPR"))&&(!choice.equals("SSR"))&&(!choice.equals("FEL"))&&(!choice.equals("FAW"))&&(!choice.equals("SMR"))&&(!choice.equals("FCL"))&&(!choice.equals("Q"))&&(!choice.equals("FPR"))){
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
				System.out.println("Enter the number of the vehicle you wish to purchase.");
				try{
				int vehicleChoice = sc.nextInt();
				dealer.buyCar(vehicleChoice);
				try{ 
					System.out.println(dealer.getLastcarbought().getMfr()+ " " +dealer.getLastcarbought().getModel()+" was bought.");
					}
					catch(NullPointerException e){//Try catch exception handling in case the user puts in an integer that is not within the index
						System.out.println(e+"\nSorry, that selection is invalid. Make sure to choose a car that is displayed in the inventory");
					}
				}catch(InputMismatchException e){// Try catch exception handling in case the user puts in letters instead of a number
					System.out.println(e+"\nSorry, I didn't understand that. Please use the index number of the vehicle you wish to purchase");
				}
			}
			else if(choice.equals("RET")){ //re-adds the variable stored in lastCarBought back into the inventory ArrayList
				try{
				System.out.println(dealer.getLastcarbought().display()+ " returned");
				dealer.returnCar(dealer.getLastcarbought());
				dealer.setLastcarbought(null); //sets the lastCarBought variable to null
				}catch(NullPointerException e){
					System.out.println("Sorry, there is no car to return. Try a different command.");
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
		}
  }
}