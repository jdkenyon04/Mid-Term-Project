import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

		if(players != null) {
			for(int i = 0; i <players.length; i++) {
				System.out.println(players[i]);
			}
		}
		
	}

}
