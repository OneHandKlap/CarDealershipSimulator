import java.util.*;

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
        try{
            Random random = new Random();
            Iterator<Car> iter = cars.listIterator();
            while(iter.hasNext()){
                int index =0;
                if(iter.next().getMfr().equals("Kia")){
                    /*
                    Car temp = cars.get(index);
                    cars.remove(cars.get(index));                
                    String seller = this.team.getPerson();
                    Calendar date = new GregorianCalendar();
                    date.set(random.nextInt(19)+2000, random.nextInt(12), random.nextInt(28));
                    System.out.println(temp.display());
                    return accounts.add(date, temp, seller, Transaction.Type.BUY, temp.getPrice());
                    */
                    return(iter.next().getMfr());
                }
                index++;
            }
        }catch (Exception e){
            return ("No vehicle matching that VIN was found. Try a new number.");  
        }
        return ("No vehicle matching that VIN was found. Try a new number.");
    }
    //returnCar uses the object stored in lastCarBought and puts it back into the inventory, and shows a success message
    //There is also a try/catch exception handling function that prevents the user from trying to return a car if none was ever bought
    public void returnCar(Car car){
        try{
            cars.add(car);
        }catch(NullPointerException e){
            System.err.println(e);
        }
    }

    //displayInventory evaluates the logic state of the three boolean variables implicit in CarDealerships. These represent the filter conditions. 
    //Based on which filters have been set this function, only displays those that meet the conditions
    public void displayInventory(){
        for(int i = 0;i<cars.size();i++){//iterate through the entire inventory
            if(filterElectric==true&&filterAWD==true&&filterPrice==true){//this if statement represents 1/8 boolean scenarios (2^3 = 8)
                if((cars.get(i).getAwd()==true)&&(cars.get(i).getPower()==Vehicle.Power.ELECTRIC_MOTOR) && (cars.get(i).getPrice()>=minPrice) && (cars.get(i).getPrice()<=maxPrice)){//this if statement verifies if each element meets the required conditions for being displayed
                    System.out.println(i+ " " +cars.get(i).display());//if the object does meet the criteria, display!
                }
            }
            else if(filterElectric==true&&filterAWD==true&&filterPrice==false){//this if statement represents boolean scenario 2/8
                if(cars.get(i).getAwd()==true&& (cars.get(i).getPower()==Vehicle.Power.ELECTRIC_MOTOR)){
                    System.out.println(i+ " " +cars.get(i).display());
                }
            }
            else if(filterElectric==true&&filterAWD==false&&filterPrice==false){//this if statement represents boolean scenario 3/8
                if(cars.get(i).getPower()==Vehicle.Power.ELECTRIC_MOTOR){
                    System.out.println(i+ " " +cars.get(i).display());
                }
            }
            else if(filterElectric==true&&filterAWD==false&&filterPrice==true){//this if statement represents boolean scenario 4/8
                if((cars.get(i).getPower()==Vehicle.Power.ELECTRIC_MOTOR) && (cars.get(i).getPrice()>=minPrice) && (cars.get(i).getPrice()<=maxPrice)){
                    System.out.println(i+ " " +cars.get(i).display());
                }
            }
            else if(filterElectric==false&&filterAWD==true&&filterPrice==true){//this if statement represents boolean scenario 5/8
                if(cars.get(i).getAwd()==true&&cars.get(i).getPrice()>=minPrice&&cars.get(i).getPrice()<=maxPrice){
                    System.out.println(i+ " " +cars.get(i).display());
                }
            }
            else if(filterElectric==false&&filterAWD==true&&filterPrice==false){//this if statement represents boolean scenario 6/8
                if(cars.get(i).getAwd()==true){
                    System.out.println(i+ " " +cars.get(i).display());
                }
            }
            else if(filterElectric==false&&filterAWD==false&&filterPrice==true){//this if statement represents boolean scenario 7/8
                if(cars.get(i).getPrice()>=minPrice&&cars.get(i).getPrice()<=maxPrice){
                    System.out.println(i+ " " +cars.get(i).display());
                }
            }
            else if(filterElectric==false&&filterAWD==false&&filterPrice==false){//this if statement represents boolean scenario 8/8
                    System.out.println(i+ " " +cars.get(i).display());
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
        //dealer.displayInventory();
        Collections.sort(nucars, new rangeSort());
        dealer.displayInventory();
        System.out.println(dealer.buyCar(honda.getVIN()));
        dealer.displayInventory();
        dealer.accounts.displayAllTransactions();
    }
}