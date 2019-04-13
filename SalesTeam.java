import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.*;

/**
 * Sales team object is a collection of SalesPersons
 * @author Peter Aboud - 500883647
 * @version 2.0
 * @since 2019-04-13 
 * @param teamlist - linked list of strings for each person on the team
 */

public class SalesTeam{
    LinkedList<SalesPerson> teamList;
    String [] names = {"Adam","Alan", "Andrew", "Cameron","Carl", "Cynthia", "Brenda", "Brian","Breanne", "Dominic", "Doris","Dell", "Erika", "Elvis", "Fergie", "Fred","Francine", "Gertrude", "Gerry", "Henry", "Harriet", "Idris", "Iris","Ian", "Linda","Lex", "Luke", "Rose","Romeo", "Roger", "Tina","Tiffany", "Tristan", "Xena", "Hercules", "Ygritte","Cersei","Jaime","Arya","Sansa","Roose","Paetyr"};
    
    //Getter
    public LinkedList<SalesPerson> getTeamList() {
		return this.teamList;
	}
    //generic constructor
    public SalesTeam(){
        
        teamList = new LinkedList<SalesPerson>();

        for(int i = 0;i<5;i++){ //randomly adds 5 values stored in the array to the teamList
            Random random = new Random();
            String name = names[random.nextInt(names.length)];
            SalesPerson newSeller = new SalesPerson(name);
            teamList.add(newSeller);
        }
    }

    public SalesPerson getRandomPerson() { //returns a random SalesPerson
        return teamList.get((int)(Math.random()*(5)));
    }
    public SalesPerson getPerson(String n){ //returns a specific SalesPerson based on their name
        for(SalesPerson temp:teamList){
            if (temp.getName().equals(n)){
                return temp;
            }
        }
        return null;
    }
    public String getPersonName(){ //returns the persons name at a random position in the team roster
        return teamList.get((int)(Math.random()*(5))).getName();
    }

    public String getTeam(){ //returns the names of everyone on the team
        Iterator<SalesPerson> iter = teamList.iterator();
        String result = "";
        while(iter.hasNext()){
            result=result+iter.next().getName()+" ";
        }
        return result;
    }
    public SalesPerson getPersonAt(int Index){ //returns the salesPerson at a specific index in the roster
        return teamList.get(Index);
    }
    
}