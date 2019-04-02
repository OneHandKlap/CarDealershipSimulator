import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class SalesTeam{
    LinkedList<String> teamList;
    String [] names = {"Adam", "Andrew", "Cameron", "Cynthia", "Brenda", "Brian", "Dominic", "Doris", "Erika", "Elvis", "Fergie", "Fred", "Gertrude", "Gerry", "Henry", "Harriet", "Idris", "Iris", "Linda", "Luke", "Rose", "Roger", "Tina", "Tristan", "Xena"};

    public SalesTeam(){
        teamList = new LinkedList<String>();
        for(int i = 0;i<5;i++){
            Random random = new Random();
            teamList.add(names[random.nextInt(names.length)]);
        }
    }
    public String getPerson(){
        return teamList.get((int)(Math.random()*(5)));
    }

    public String getTeam(){
        Iterator<String> iter = teamList.iterator();
        String result = "";
        while(iter.hasNext()){
            result=result+iter.next()+" ";
        }
        return result;
    }
    public String getPersonAt(int Index){
        return teamList.get(Index);
    }
    public static void main(String[]args){
        SalesTeam alpha = new SalesTeam();
        System.out.println(alpha.getTeam());
    }
}