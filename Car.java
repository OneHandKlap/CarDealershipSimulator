import java.util.*;
/**
 * The Car contains all the code for the Car object.
 * Methods for displaying and comparing of cars. Extends comparable in order to allow for ArrayList sorting by price
 * @author Peter Aboud - 500883647
 * @version 1.0
 * @since 2019-03-20 
 */

public class Car extends Vehicle implements Comparable<Car>{

    private Model model;
    private int maxRange;
    private double safetyRating;
    private boolean AWD;
	private double price;
	

	public static void main(String[]args){
		Car x1 = new Car("BMW", "red", 4, Vehicle.Power.ELECTRIC_MOTOR, Car.Model.MINIVAN, 400, 9.5, true, 30000);
		System.out.println(x1.getVIN());
	}



	//Getters and Setters
	public Model getModel()
	{
		return this.model;
	}
	public void setModel(Model model)
	{
		this.model = model;
	}
	public int getMaxrange()
	{
		return this.maxRange;
	}
	public void setMaxrange(int maxRange)
	{
		this.maxRange = maxRange;
	}
	public double getSafetyrating()
	{
		return this.safetyRating;
	}
	public void setSafetyrating(double safetyRating)
	{
		this.safetyRating = safetyRating;
	}
	public boolean getAwd()
	{
		return this.AWD;
	}
	public void setAwd(boolean AWD)
	{
		this.AWD = AWD;
	}
	public double getPrice()
	{
		return this.price;
	}
	public void setPrice(double price)
	{
		this.price = price;
	}
     enum Model{
        SEDAN, SUV, SPORTS, MINIVAN;
    }
	//Constructor
    public Car(String mfr, String color, int numWheels, Power powerType, Model mod, int range, double safe, boolean AWD, double price){
        super(mfr, color, numWheels, powerType);
        this.model=mod;
        this.maxRange=range;
        this.safetyRating=safe;
        this.AWD=AWD;
		this.price=price;
		Random random = new Random();
		this.setVIN(random.nextInt(499));
		
		
	}
	//Instance Methods
	//This method calls the display function of the super class first, then displays all of the relevant information of this object
    public String display(){
		if(this.AWD==true){
			return super.display()+" "+model+" "+this.price+"$ SF: "+this.safetyRating+" RNG: " +this.maxRange+" AWD";
		}
		else{
			return super.display()+" "+model+" "+this.price+"$ SF: "+this.safetyRating+" RNG: " +this.maxRange+" 2WD";
		}
        
	}
	//This method first checks to see if the two objects are equal in terms of their vehicle variables, then checks the other variables intrinsic to Cars
	//Only returns true if mfr/numWheels/powerType/model/AWD are all the same
    public boolean equals(Object other){
        Car nuCar = (Car)other;
        if(super.equals(nuCar)){
            if(this.model==nuCar.model&&this.AWD==nuCar.AWD){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
	}
	//This method allows us to use the Collections.sort() method.
	//This compareTo method compares the object it is called on, against another object of the same type "CAR"
	//It compares their prices and then outputs either a 1,-1,or 0 depending on the values
    public int compareTo(Car other){
		return (int)(other.price-this.price); 
	}  
}