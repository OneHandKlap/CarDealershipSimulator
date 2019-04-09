import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.*;

public class SalesTeam{
    LinkedList<SalesPerson> teamList;

	public LinkedList<SalesPerson> getTeamList() {
		return this.teamList;
	}

	public void setTeamList(LinkedList<SalesPerson> teamList) {
		this.teamList = teamList;
	}

    String [] names = {"Adam", "Andrew", "Cameron", "Cynthia", "Brenda", "Brian", "Dominic", "Doris", "Erika", "Elvis", "Fergie", "Fred", "Gertrude", "Gerry", "Henry", "Harriet", "Idris", "Iris", "Linda", "Luke", "Rose", "Roger", "Tina", "Tristan", "Xena"};


    public SalesTeam(){
        
        teamList = new LinkedList<SalesPerson>();

        for(int i = 0;i<5;i++){
            Random random = new Random();
            String name = names[random.nextInt(names.length)];
            SalesPerson newSeller = new SalesPerson(name);
            teamList.add(newSeller);
        }
    }

    public SalesPerson getRandomPerson() {
        return teamList.get((int)(Math.random()*(5)));
    }
    public SalesPerson getPerson(String n){
        for(SalesPerson temp:teamList){
            if (temp.getName().equals(n)){
                return temp;
            }
        }
        return null;
    }
    public String getPersonName(){
        return teamList.get((int)(Math.random()*(5))).getName();
    }

    public String getTeam(){
        Iterator<SalesPerson> iter = teamList.iterator();
        String result = "";
        while(iter.hasNext()){
            result=result+iter.next().getName()+" ";
        }
        return result;
    }
    public SalesPerson getPersonAt(int Index){
        return teamList.get(Index);
    }
    public static void main(String[]args){
        SalesTeam alpha = new SalesTeam();
        System.out.println(alpha.getTeam());
    }
}