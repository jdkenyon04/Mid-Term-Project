import java.io.IOException;

public class Coach  {
    private Players[] allPlayers;
    private Players[] hiredPlayers;
    private int totalPlayers;
    private int totalHiredPlayers;
    private final int teamCap = 30;
    
    public Coach() {
        hiredPlayers = new Players[30];
        totalHiredPlayers = 0;
    }
    
    public void loadPlayers(String path) throws IOException {
        try {
            totalPlayers = TestMain.getLineCount(path);
            allPlayers = TestMain.readPlayersFromFile(path);
        } catch (IOException e) {
            throw e;
        }
    }
    
    public void printAllPlayers(){
        for(int i = 0; i < allPlayers.length; i++){
            System.out.println(allPlayers[i]);
        }
    }
    
    //public void printAvailablePlayers(){
      //  for(int i = 0; i < allPlayers.length; i++){
        //    if(!allPlayers[i].getInjured()) {
          //      System.out.println(allPlayers[i]);
            //}
        //}
    //}
    
    public void printByPosition(String position){
        for(int i = 0; i < allPlayers.length; i++){
            if(allPlayers[i].getPosition().equals(position)){
                System.out.println(allPlayers[i]);
            }
        }
    }
    
    public void printByGoalsScored(int goalsScored){
        for(int i = 0; i < allPlayers.length; i++){
            if(allPlayers[i].getGoalsScored() >= goalsScored){
                System.out.println(allPlayers[i]);
            }
        }
    }
    
    public void sortByAge(Players[] hiredPlayers){
        if(hiredPlayers == null || hiredPlayers.length == 0){
            return;
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
    
    public void printByPlayer(String name){
        for(int i = 0; i < allPlayers.length; i++){
            //Is there a way we can change this so it looks at it without capitals
            if(allPlayers[i].getName().equals(name)){
                System.out.println(allPlayers[i]);
            }
        }
    }
    
    public void hirePlayer(Players player){
        if(player == null){
            return;
        }
        if(totalHiredPlayers >= 30){
            return;
        }
        hiredPlayers[totalHiredPlayers] = player;
        totalHiredPlayers++;
    }
    
    
    //Need to go over this one ... I dont know if I rezise the array correctly or if there is another way of doing this.
    public void firePlayer(Players player){
        if(player == null || totalHiredPlayers == 0){
            return;
        }
        boolean found = false;
        
        for(int i = 0; i < totalHiredPlayers; i++) {
            if(hiredPlayers[i] == player){
                found = true;
            }
            for(int j = i; j < totalHiredPlayers - 1; j++){
                hiredPlayers[j] = hiredPlayers[j+1];
            }
            hiredPlayers[totalHiredPlayers - 1] = null;
            totalHiredPlayers--;
            break;
        }
    }
}