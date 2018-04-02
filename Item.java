
public class Item 
{
	private int attackDamage;
	private int breakingPower;
	private int defensePower;
	private char letter;
	
	
	public Item() {
		attackDamage = 0;
		breakingPower = 0;
		defensePower = 0;
		letter = ' ';
	}

	Item(int x, int y, int z, char c)
	{
		attackDamage = x;
		breakingPower = y;
		defensePower = z;
		letter = c;
	}
	
	public int getAttackDamage() {
		return attackDamage;
	}
	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}
	public int getBreakingPower() {
		return breakingPower;
	}
	public void setBreakingPower(int breakingPower) {
		this.breakingPower = breakingPower;
	}
	public int getDefensePower() {
		return defensePower;
	}
	public void setDefensePower(int defensePower) {
		this.defensePower = defensePower;
	}

	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}
	
}
