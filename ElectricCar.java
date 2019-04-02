/**
 * The Car contains all the code for the ElectricCar object.
 * Methods for displaying electric cars
 * @author Peter Aboud - 500883647
 * @version 1.0
 * @since 2019-03-20 
 */

public class ElectricCar extends Car{
    private int rechargeTime;
    private String batteryType;


	//Getters and Setters for the instance variables
	public int getRechargetime()
	{
		return this.rechargeTime;
	}
	public void setRechargetime(int rechargeTime)
	{
		this.rechargeTime = rechargeTime;
	}
	public String getBatterytype()
	{
		return this.batteryType;
	}
	public void setBatterytype(String batteryType)
	{
		this.batteryType = batteryType;
	}

	//Constructor
    public ElectricCar(String mfr, String color, int numWheels, Power powType, Model mod, int range, double safe, boolean awd, double price, int time, String battery){
        super(mfr, color, numWheels, powType, mod, range, safe, awd, price);
        this.rechargeTime=time;
        this.batteryType=battery;
	}
	//Display method that uses the super.display to show all the information stored in the parent classes Car and Vehicle
    public String display(){
        return super.display()+" "+this.rechargeTime+" "+this.batteryType;
    }
}