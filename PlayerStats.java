
public class PlayerStats 
{
	private int level;
	private String playerName;
	private int health;
	private Item hand = new Item();
	private Item armor = new Item();
	private int progressDistance;
	
	
	PlayerStats(String playerName, int progress)
	{
		this.playerName = playerName;
		level = 1;
		setHealth(20);
		progressDistance = progress;
	}
	
	public void displayStats()
	{
		System.out.println(playerName);
		System.out.println("Player Level: " + level);
		System.out.println("Health: " + health);
		System.out.println("Hand: " + hand.getLetter());
		System.out.println("Armor: " + armor.getDefensePower());
		System.out.println("Progress: " + progressDistance + "/30");
	}
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}


	public int getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
	}


	public Item getHand() {
		return hand;
	}

	public void setHand(Item hand) {
		this.hand = hand;
	}

	public Item getArmor() {
		return armor;
	}

	public void setArmor(Item armor) {
		this.armor = armor;
	}

	public int getProgressDistance() {
		return progressDistance;
	}

	public void setProgressDistance(int progressDistance) {
		this.progressDistance = progressDistance;
	}
	
}
