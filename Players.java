
public class Players {

	private int playerID;
	private String name;
	private String nationality;
	private int age;
	private String position;
	private int goalsScored;
	private String injured;
	private double wage;
	
	
	
	public Players(int playerID, String name, String nationality, int age, String position, int goalsScored, String injured, double wage) {
		setPlayerID(playerID);
		setName(name);
		setNationality(nationality);
		setAge(age);
		setPosition(position);
		setGoalsScored(goalsScored);
		setInjured(injured);
		setWage(wage);
	}
	
	public int getPlayerID(){
		return playerID;
	}
	
	public void setPlayerID(int playerID) {
		if(playerID < 0) {
			throw new IllegalArgumentException("PLayerID must be positive");
		}
		this.playerID = playerID;
	}
	
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if(name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Name cannot be null or invalid");
		}
		
		this.name = name;
	}
	
	public String getNationality() {
		return nationality;
	}
	
	public final void setNationality(String nationality) {
		if(nationality == null || nationality.isEmpty()) {
			throw new IllegalArgumentException("Nationality cannot be null or invalid");
		}
		
		this.nationality = nationality;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		if(age < 0) {
			throw new IllegalArgumentException("Age must be positive");
		}
		this.age = age;
	}
	
	
	public String getPosition() {
		return position;
	}
	
	public final void setPosition(String position) {
		if(position == null || position.isEmpty()) {
			throw new IllegalArgumentException("Position cannot be null or invalid");
		}
		
		this.position = position;
	}
	
	public int getGoalsScored() {
		return goalsScored;
		
	}
	
	public final void setGoalsScored(int goalsScored) {
		if(goalsScored < 0) {
			throw new IllegalArgumentException("GoalsScored must be positive");
		}
		this.goalsScored = goalsScored;
	}
	
	public String getInjured() {
		return injured;
	}
	
	public  void setInjured(String injured) {
		if(injured == null || injured.isEmpty()) {
			throw new IllegalArgumentException("Injured cannot be null or invalid");
		}
		
		this.injured = injured;
	}
	
	
	public double getWage() {
		return wage;
	}
	
	public void setWage(double wage) {
		if(wage < 0) {
			throw new IllegalArgumentException("Wage must be positive");
		}
		this.wage = wage;
	}
	
	@Override
	public String toString() {
		return String.format("Player ID(%d):\t%s\t%s\t%s\t%d Years Old\tGoals Scored %d\tInjured:%s\tWage: $%.02f", playerID, position, name, nationality, age, goalsScored, injured, wage);
	}
	
}
