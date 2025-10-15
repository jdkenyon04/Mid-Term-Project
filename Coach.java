import java.io.IOException;
import java.util.Calendar;

public class Coach  {
    private String coachName;
    private Players[] allPlayers;
    private Players[] hiredPlayers;
    private int totalPlayers;
    private int totalHiredPlayers;
    private int totalWage;
    private double totalWageLeft;
    private final int teamCap = 11;
    private final int wageCap = 100000;

    public Coach(String coachName, Players[] allPlayers) {
        if(coachName == null || coachName.isEmpty()){
            throw new IllegalArgumentException("coachName cannot be null or empty");
        }
        if(allPlayers == null){
            throw new IllegalArgumentException("allPlayers cannot be null!");
        }
        hiredPlayers = new Players[11];
        totalHiredPlayers = 0;
        this.allPlayers = allPlayers;
    }

    
    public void printAllPlayers(){
        for(int i = 0; i < allPlayers.length; i++){
            if(allPlayers[i].getInjured().equals("Yes")){
                System.out.println(allPlayers[i]);
            }
            else {
            System.out.println(allPlayers[i]);}
        }
    }

    public void printAllHiredPlayers(){
        for(int i = 0; i < hiredPlayers.length; i++){
            if(hiredPlayers[i]!= null){
                if(hiredPlayers[i].getInjured().equals("Yes")){
                    System.out.println(allPlayers[i]);
                }
                System.out.println(hiredPlayers[i]);
            }
        }
    }

    public void printAvailablePlayers(){
        for(int i = 0; i < allPlayers.length; i++){
            if(allPlayers[i].getInjured().equals("No")) {
                System.out.println(allPlayers[i]);
            }
        }
    }

    public void printByPosition(String position){
        if(position == null || position.isEmpty()){
            throw new IllegalArgumentException("Position cannot be null or empty");
        }
        for(int i = 0; i < allPlayers.length; i++){
            if(allPlayers[i].getPosition().equals(position)){
                System.out.println(allPlayers[i]);
            }
        }
    }

    public void printByGoalsScored(int goalsScored){
        if(goalsScored < 0){
            throw new IllegalArgumentException("Goals scored cant be negative!");
        }
        for(int i = 0; i < allPlayers.length; i++){
            if(allPlayers[i].getGoalsScored() >= goalsScored){
                System.out.println(allPlayers[i]);
            }
        }
    }

    public void sortByAge(Players[] hiredPlayers){
        if(hiredPlayers == null || hiredPlayers.length == 0){
            throw new IllegalArgumentException("hiredPlayer[] must not be null and be have a length of at least 1");
        }
        for(int i = 0; i < hiredPlayers.length - 1; i++){
            int minIndex = i;
            for(int j = i + 1; j < hiredPlayers.length; j++){
                if(hiredPlayers[j].getAge() < hiredPlayers[minIndex].getAge()){
                    minIndex = j;
                }
            }
            if(minIndex != i){
                Players temp = hiredPlayers[i];
                hiredPlayers[i] = hiredPlayers[minIndex];
                hiredPlayers[minIndex] = temp;
            }
        }
    }

    public void sortByNationality() {
        int length = 0;
        if(totalHiredPlayers == 0){
            throw new IllegalArgumentException("Must have at least 1 hired player!");
        }

        for(int i = 0; i < hiredPlayers.length; i++){
            if(hiredPlayers[i] != null) {
                length++;
            }
        }
        for(int i = 0; i < length - 1; i++){
            int minIndex = i;
            for(int j = i + 1; j < length; j++) {
                String str1 = hiredPlayers[minIndex].getNationality();
                String str2 = hiredPlayers[j].getNationality();
                if(str1.compareTo(str2) > 0){
                    minIndex = j;
                }
            }
            if (minIndex!=i){
                Players temp = hiredPlayers[i];
                hiredPlayers[i] = hiredPlayers[minIndex];
                hiredPlayers[minIndex] = temp;

            }
        }
    }

    public void printByPlayer(String name){
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        for(int i = 0; i < allPlayers.length; i++){
            //Is there a way we can change this so it looks at it without capitals
            if(allPlayers[i].getName().equalsIgnoreCase(name)){
                System.out.println(allPlayers[i]);
            }
        }
    }

    public Players findPlayer(String name){
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        for(int i = 0; i < allPlayers.length; i++){
            if(allPlayers[i].getName().equals(name)){
                return allPlayers[i]; 
            } 
        }
        return null;
    }

    public void hirePlayer(String name){
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if(totalHiredPlayers >= 11){
            throw new IllegalArgumentException("Cannot have more than 11 players");
        }
        if(findPlayer(name).getInjured().equals("Yes")) {
            throw new IllegalArgumentException(name + "is Injured!");
        }
        int tempWage = totalWage;
        tempWage += findPlayer(name).getWage();
        if(tempWage >= wageCap) {
            throw new IllegalArgumentException(name + " Exceeds Wage Cap!");
        }
        hiredPlayers[totalHiredPlayers] = findPlayer(name);
        totalWage += findPlayer(name).getWage();
        totalHiredPlayers++;

        //Couldnt use date class, oracle said deprecated to calender
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 2);
        calendar.add(Calendar.WEEK_OF_YEAR, 2);

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        System.out.println(name + " was hired! Contract expires: " + month + "/" + day + "/" + year);
        System.out.println("Total wage is now $" + totalWage + ". ");
        System.out.println(totalHiredPlayers + " players hired.");
    }
    
    //Need to go over this one ... I dont know if I rezise the array correctly or if there is another way of doing this.
    public void firePlayer(String name){
        if(findPlayer(name) == null || totalHiredPlayers == 0){
            throw new IllegalArgumentException("Player does not exist!");
        }

        for(int i = 0; i < totalHiredPlayers; i++) {
            if(hiredPlayers[i] == findPlayer(name)){
                for(int j = i; j < totalHiredPlayers - 1; j++){
                    hiredPlayers[j] = hiredPlayers[j+1];
                }
                hiredPlayers[totalHiredPlayers - 1] = null;
                totalHiredPlayers--;
                break;
            }
        }
        totalWage -= findPlayer(name).getWage();
        System.out.println(name + " was fired! Total wage is now " + totalWage);
    }
}
