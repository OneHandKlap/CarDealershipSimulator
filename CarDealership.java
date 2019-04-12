import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CarDealership{
    private ArrayList<Car> cars;
    private SalesTeam team;
    private AccountingSystem accounts;
    private boolean filterElectric;
    private boolean filterAWD;
    private boolean filterPrice;
    private double minPrice;
    private double maxPrice;
    private Car lastCarBought;
    public static void main (String args[]){
        CarDealership dealer = new CarDealership();
        ArrayList<Car> nucars = new ArrayList<Car>();
        Car x1 = new Car("BMW", "Silver", 4, Vehicle.Power.GAS_ENGINE, Car.Model.SUV, 600, 90.0, true, 35000);
		Car passat = new Car("Volkswagen", "red", 4, Vehicle.Power.GAS_ENGINE, Car.Model.SEDAN, 700, 95.0, false, 32000);
		Car kia = new Car("Kia", "blue", 4, Vehicle.Power.GAS_ENGINE, Car.Model.SEDAN, 800, 9.5, false, 16000);
        Car honda = new Car("Honda", "red", 4, Vehicle.Power.GAS_ENGINE, Car.Model.SUV, 600, 8.5, true, 25000);	
        nucars.add(x1);
		nucars.add(passat);
		nucars.add(kia);
        nucars.add(honda);
        dealer.addCars(nucars);
        Collections.sort(nucars, new rangeSort());
        //dealer.displayInventory();
        //System.out.println(dealer.buyCar(passat.getVIN()));
        dealer.displayInventory();
        Transaction that = new Transaction(69, honda, "Me", Transaction.Type.BUY);
        
        dealer.accounts.add(that);
        dealer.returnCar(69);
        dealer.accounts.displayAllTransactions();
        dealer.displayInventory();

    }


    //Getter and Setter methods for instance variables. Most of these are unused because of the filter methods because their is no child class that needs to access them
	public ArrayList<Car> getCars()
	{
		return this.cars;
	}
	public void setCars(ArrayList<Car> cars)
	{
		this.cars = cars;
	}
	public Car getLastcarbought()
	{
		return this.lastCarBought;
	}
	public void setLastcarbought(Car lastCarBought)
	{
		this.lastCarBought = lastCarBought;
	}
	public boolean getFilterelectric()
	{
		return this.filterElectric;
	}
	public void setFilterelectric(boolean filterElectric)
	{
		this.filterElectric = filterElectric;
	}
	public boolean getFilterawd()
	{
		return this.filterAWD;
	}
	public void setFilterawd(boolean filterAWD)
	{
		this.filterAWD = filterAWD;
	}
	public boolean getFilterprice()
	{
		return this.filterPrice;
	}
	public void setFilterprice(boolean filterPrice)
	{
		this.filterPrice = filterPrice;
    }
    public SalesTeam getTeam() {
		return this.team;
	}

	public void setTeam(SalesTeam team) {
		this.team = team;
	}

	public AccountingSystem getAccounts() {
		return this.accounts;
	}

	public void setAccounts(AccountingSystem accounts) {
		this.accounts = accounts;
	}

    //Generic constructor
    public CarDealership(){
        cars = new ArrayList<Car>();
        filterElectric=false;
        filterAWD=false;
        filterPrice=false;
        minPrice=0;
        maxPrice=0;
        lastCarBought=null;
        team = new SalesTeam();
        accounts = new AccountingSystem();
    }

    //Instance Methods
    //addCars takes an exisiting arraylist of cars and adds them to the dealership inventory denoted by the instance variable 'cars'
    public void addCars(ArrayList<Car> newCars){
        for(int i=0;i<newCars.size();i++){
            cars.add(newCars.get(i));
        }
    }
    //buyCar removes a car from the inventory, stores the object in the holder place of lastCarBought, and displays the purchase
    //There is also a try/catch exception handling function that prevents the user from entering an index that exceeds the bounds of the ArrayList
    public String buyCar(int vin){
        Random random = new Random();
        try{
            
            if(cars.isEmpty()){
                return ("Sorry, there are no cars available for purchase.");
            }
            Iterator<Car> iter = cars.listIterator();
            while(iter.hasNext()){
                Car temp = iter.next();
                if(temp.getVIN()==vin){
                    
                    cars.remove(temp);
                    String salesPerson = team.getRandomPerson().getName();
                    Calendar date = new GregorianCalendar();
                    date.set(2019, random.nextInt(12), random.nextInt(25));
                    int transID =  accounts.add(date, temp, salesPerson, Transaction.Type.BUY, temp.getPrice()).getId();
                    String seller = accounts.getTransaction(transID).getSalesPerson();
                    team.getPerson(seller).getSales().add(accounts.getTransaction(transID));
                    return accounts.getTransaction(transID).display();
                    
                    
                }
            }
        }catch (Exception e){
            return ("No vehicle matching that VIN was found. Try a new number.");  
        }
        return ("Sorry, no vehicle matching that VIN was found. Try a new number.");  
    }
    //returnCar uses the object stored in lastCarBought and puts it back into the inventory, and shows a success message
    //There is also a try/catch exception handling function that prevents the user from trying to return a car if none was ever bought
    public void returnCar(int transactionNum){
        try{
            Transaction thisReturn = accounts.getTransaction(transactionNum);
            Car returnedCar = thisReturn.getItem();
            Calendar date = new GregorianCalendar();
            Random random = new Random();
            date.set(thisReturn.getDate().get(Calendar.YEAR),thisReturn.getDate().get(Calendar.MONTH)+random.nextInt(2),thisReturn.getDate().get(Calendar.DAY_OF_YEAR)+random.nextInt(3));
            cars.add(returnedCar);
            String sellerName = accounts.getTransaction(transactionNum).getSalesPerson();
            SalesPerson salesPerson = team.getPerson(sellerName);
            salesPerson.getSales().remove(thisReturn);
            SalesPerson returner = team.getRandomPerson();
            accounts.add(date, returnedCar, returner.getName(), Transaction.Type.RET, returnedCar.getPrice());
            System.out.println("Car returned successfully");
           
        }
        catch(Exception e){
            System.out.println(e+"Error, transaction number does not match any in our records");
        }
        
    }

    //displayInventory evaluates the logic state of the three boolean variables implicit in CarDealerships. These represent the filter conditions. 
    //Based on which filters have been set this function, only displays those that meet the conditions
    public void displayInventory(){
        for(int i = 0;i<cars.size();i++){//iterate through the entire inventory
            if(filterElectric==true&&filterAWD==true&&filterPrice==true){//this if statement represents 1/8 boolean scenarios (2^3 = 8)
                if((cars.get(i).getAwd()==true)&&(cars.get(i).getPower()==Vehicle.Power.ELECTRIC_MOTOR) && (cars.get(i).getPrice()>=minPrice) && (cars.get(i).getPrice()<=maxPrice)){//this if statement verifies if each element meets the required conditions for being displayed
                    System.out.println(cars.get(i).display());//if the object does meet the criteria, display!
                }
            }
            else if(filterElectric==true&&filterAWD==true&&filterPrice==false){//this if statement represents boolean scenario 2/8
                if(cars.get(i).getAwd()==true&& (cars.get(i).getPower()==Vehicle.Power.ELECTRIC_MOTOR)){
                    System.out.println(cars.get(i).display());
                }
            }
            else if(filterElectric==true&&filterAWD==false&&filterPrice==false){//this if statement represents boolean scenario 3/8
                if(cars.get(i).getPower()==Vehicle.Power.ELECTRIC_MOTOR){
                    System.out.println(cars.get(i).display());
                }
            }
            else if(filterElectric==true&&filterAWD==false&&filterPrice==true){//this if statement represents boolean scenario 4/8
                if((cars.get(i).getPower()==Vehicle.Power.ELECTRIC_MOTOR) && (cars.get(i).getPrice()>=minPrice) && (cars.get(i).getPrice()<=maxPrice)){
                    System.out.println(cars.get(i).display());
                }
            }
            else if(filterElectric==false&&filterAWD==true&&filterPrice==true){//this if statement represents boolean scenario 5/8
                if(cars.get(i).getAwd()==true&&cars.get(i).getPrice()>=minPrice&&cars.get(i).getPrice()<=maxPrice){
                    System.out.println(cars.get(i).display());
                }
            }
            else if(filterElectric==false&&filterAWD==true&&filterPrice==false){//this if statement represents boolean scenario 6/8
                if(cars.get(i).getAwd()==true){
                    System.out.println(cars.get(i).display());
                }
            }
            else if(filterElectric==false&&filterAWD==false&&filterPrice==true){//this if statement represents boolean scenario 7/8
                if(cars.get(i).getPrice()>=minPrice&&cars.get(i).getPrice()<=maxPrice){
                    System.out.println(cars.get(i).display());
                }
            }
            else if(filterElectric==false&&filterAWD==false&&filterPrice==false){//this if statement represents boolean scenario 8/8
                    System.out.println(cars.get(i).display());
            }
        }
    }
    //This is an innerclass that implements the Comparator class
    // it compares the safety ratings of two objects and then returns either a positive integer,negative integer, or 0
    //based on that information the Collections interface is able to sort the ArrayList accordingly, extending our sort capabilities
    public static class safetySort implements Comparator<Car>{
        public int compare(Car c1, Car c2){
            return (int) (c1.getSafetyrating()-c2.getSafetyrating());
        }
    }
    //This is an innerclass that implements the Comparator class
    //it compares the ranges of two objects and then returns either a positive integer,negative integer, or 0
    //based on that information the Collections interface is able to sort the ArrayList accordingly, extending our sort capabilities
    public static class rangeSort implements Comparator<Car>{
        public int compare(Car c1, Car c2){
            return (int)(c1.getMaxrange()-c2.getMaxrange());
        }
    }
    //This method calls the Collections.sort method in order to sort the ArrayList based on price
    //This does not require an additional comparator because the Car method already extends Comparable via price comparison
    public void sortByPrice(){
        Collections.sort(getCars());
    }
    //This method calls the Collections.sort method in order to sort the ArrayList based on safety
    //In order to sort the ArrayList, we create an instance of the safetySort class that we implemented above
    public void sortBySafetyRating(){
        Collections.sort(getCars(), new safetySort());;
      
    }
    //This method calls the Collections.sort method in order to sort the ArrayList based on safety
    //In order to sort the ArrayList, we create an instance of the rangeSort class that we implemented above
    public void sortByMaxRange(){
        Collections.sort(getCars(), new rangeSort());
        
    }
    //This method allows us to reset the boolean values of the class variables to false     
    public void filtersClear(){
        this.filterAWD = false;
        this.filterElectric = false;
        this.filterPrice = false;
        this.minPrice=0;
        this.maxPrice=0;
    }
    //This method sets the boolean variable of filterByElectric to true, making sure that the inventory will only display Electric vehicles
    public void filterByElectric(){
        setFilterelectric(true);
    }
    //This method sets the boolean variable of filterByAWD to true, making sure that the inventory will only display AWD Vehicles
    public void filterByAWD(){
        setFilterawd(true);
    }
    //This method sets the boolean variable of filterByPrice to true, making sure that the inventory will only display vehicles that fall within the price range specified
    public void filterByPrice(double min, double max){
        setFilterprice(true);
        this.minPrice = min;
        this.maxPrice = max;
    }
    public static ArrayList<Car> importCarData(String filePath) {

		ArrayList<Car> imported = new ArrayList<Car>();
		try {
			File importFile = new File(filePath);
			BufferedReader reader = new BufferedReader(new FileReader(importFile));
			String line;
			while ((line = reader.readLine()) != null) {
				StringTokenizer params = new StringTokenizer(line);
				int nParam = 0;
				Car.Model model = null;
				Vehicle.Power power = null;
				String mfr = null, color = null, batteryType = "LITHIUM ION";
				boolean awd = false;
				double safetyRating = 0, price = 0;
				int range = 0, rechargeTime = 0;
				while (params.hasMoreTokens()) {
					if (nParam == 0)
						mfr = params.nextToken();
					if (nParam == 1)
						color = params.nextToken();
					if (nParam == 2)
						model = Car.Model.valueOf(params.nextToken());
					if (nParam == 3)
						power = Vehicle.Power.valueOf(params.nextToken());
					if (nParam == 4)
						safetyRating = Double.parseDouble(params.nextToken());
					if (nParam == 5)
						range = Integer.parseInt(params.nextToken());
					if (nParam == 6)
						awd = (params.nextToken().equals("AWD")) ? true : false;
					if (nParam == 7)
						price = Double.parseDouble(params.nextToken());
					if (nParam == 8)
						rechargeTime = Integer.parseInt(params.nextToken());
					nParam++;
				}
				if (rechargeTime != 0)
					imported.add(new ElectricCar(mfr, color, 4, power, model, range, safetyRating, awd, price, rechargeTime, "Lithium-Ion"));
				else
					imported.add(new Car(mfr, color, 4, power, model, range, safetyRating, awd, price));
			}
		} catch (Exception e) {

			System.err.println(e);
		}
		return imported;
	}
	
    
}