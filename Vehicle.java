import java.util.concurrent.ThreadLocalRandom;

public class Vehicle{

    private String mfr;
    private String color;
    private int numWheels;
    private Power powerType;
    private int VIN;

     enum Power{
        ELECTRIC_MOTOR, GAS_ENGINE;
    }

    public Vehicle(){
        this.VIN = ThreadLocalRandom.current().nextInt(100, 499 + 1);
        this.mfr="unknown";
        this.color="unknown";
        this.numWheels=4;
    }
    public Vehicle(String mfr, String color, int num, Power powType){
        this.VIN = ThreadLocalRandom.current().nextInt(100, 499 + 1);
        this.mfr=mfr;
        this.color=color;
        this.numWheels=num;
        this.powerType= powType;
    }

    //Getters
    public String getMfr(){
        return this.mfr;
    }
    public String getColor(){
        return this.color;
    }
    public int getNumWheels(){
        return this.numWheels;
    }
    public Power getPower(){
        return this.powerType;
    }
    //Setters
    public void setMfr(String mfr){
        this.mfr=mfr;
    }
    public void setSolor(String col){
        this.color=col;
    }
    public void setNumWheels(int num){
        this.numWheels=num;
    }
    public void setPowerType(Power pow){
        this.powerType = pow;
    } 

    //Instance methods
    public boolean equals(Object Other){
        Vehicle nuVehicle = (Vehicle)(Other);
        if(this.mfr.equals(nuVehicle.mfr)&&this.numWheels==nuVehicle.numWheels&&this.powerType==nuVehicle.powerType){
            return true;
        }else{
            return false;
        }
    }
    public String display(){
        return ("VIN#: "+this.VIN +" "+ this.getMfr()+" "+this.getColor());
    }

    public static void main(String[]args){
        Vehicle bmw = new Vehicle("BMW", "Silver", 4, Vehicle.Power.GAS_ENGINE);
        System.out.println(bmw.display());
    }
    
}