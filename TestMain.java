import java.io.File;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;

public class TestMain {
    private static int getLineCount(String path) throws IOException {
        File file = new File(path);
        int lineCount = 0;

        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            while(reader.readLine() != null) {
                lineCount++;
            }
        }
        catch(IOException e) {
            throw e;
        }
        return lineCount;
    }

    private static Players[] readPlayersFromFile(String path) throws IOException{
        int numPlayers = getLineCount(path) - 1;
        Players[] players = new Players[numPlayers];

        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = reader.readLine();

            for(int i = 0; i < numPlayers; i++) {
                line = reader.readLine();
                String[] columns = line.split(",");

                int playerID = Integer.parseInt(columns[0]);
                String name = columns[1];
                String nationality = columns[2];
                int age = Integer.parseInt(columns[3]);
                String position = columns[4];
                int goalsScored = Integer.parseInt(columns[5]);
                String injured = columns[6];
                double wage = Double.parseDouble(columns[7]);

                players[i] = new Players(playerID, name, nationality, age, position, goalsScored, injured, wage);
            }
        }
        catch(IOException e) {
            throw e;
        }
        return players;
    }

    public static void main(String[] args) {

        String filePath = "PlayersNew.csv";

        Players[] players = null;

        try {
            players = readPlayersFromFile(filePath);
        }
        catch(IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        catch(NumberFormatException e) {
            System.out.println("Error parsing number: " + e.getMessage());
        }
        catch(Exception e) {
            System.out.println("An unexpected error occcured: " + e.getMessage());
        }

        Scanner input = new Scanner(System.in);
        int selection;
        String name;

        System.out.print("Hello Coach! Enter your name to start building your team: ");
        name = input.nextLine();
        Coach coach = new Coach(name, players);

        boolean active = true;

        while(active) {
            System.out.println("\nShow all players: Enter 1");
            System.out.println("Show all hired players: Enter 2");
            System.out.println("Show players by position: Enter 3");
            System.out.println("Show players by goals scored and up: Enter 4");
            System.out.println("Sort all players by age: Enter 5");
            System.out.println("Sort all players by nationality: Enter 6");
            System.out.println("Show player by name: Enter 7");
            System.out.println("Hire player: Enter 8");
            System.out.println("Fire player: Enter 9");
            System.out.println("Show all healthy players: Enter 10");
            System.out.println("Exit program: Enter 0");
            System.out.print("Enter your selection here: ");

            try {
                selection = input.nextInt();
                input.nextLine();

         
                switch(selection) {
                    case 1:
                        coach.printAllPlayers();
                        break;

                    case 2:
                        coach.printAllHiredPlayers();
                        break;

                    case 3:
                        System.out.print("Enter position: ");
                        String position = input.nextLine();
                        input.nextLine();
                        coach.printByPosition(position);
                        break;

                    case 4:
                        System.out.print("Enter goals scored: ");
                        int goalScored = input.nextInt();
                        input.nextLine();
                        coach.printByGoalsScored(goalScored);
                        break;

                    case 5: 
                        coach.sortByAge();
                        System.out.println("Here is the sorted array:");
                        coach.printAllPlayers();
                        break;

                    case 6: 
                        coach.sortByNationality();
                        System.out.println("Here is the sorted array:");
                        coach.printAllPlayers();
                        break;

                    case 7:
                        {
                            System.out.print("Enter player name: ");
                            String playerName = input.nextLine();

                            if (playerName.isEmpty()) {
                                System.out.println("Error: You must enter a name!");
                                break;
                            }

                            coach.printByPlayer(playerName);
                            break;}

                    case 8:{
                            System.out.print("Enter player name to hire: ");
                            String hireName = input.nextLine();

                            if (hireName.isEmpty()) {
                                System.out.println("Error: You must enter a name!");
                                break;
                            }

                            coach.hirePlayer(hireName);
                            break;}

                    case 9:
                        {
                            System.out.print("Enter player name to fire: ");
                            String fireName = input.nextLine();

                            if (fireName.isEmpty()) {
                                System.out.println("Error: You must enter a name!");
                                break;
                            }

                            coach.firePlayer(fireName);
                            break;}

                    case 10:
                        coach.printAvailablePlayers();
                        break;

                    case 0:
                        System.out.println("Goodbye!");
                        active = false;
                        break;

                    default:
                        System.out.println("Invalid Input! Please enter a number 0-10");
                        break;

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input! Please enter a number 0-10");
                input.nextLine();
            }
        }
    }
}
