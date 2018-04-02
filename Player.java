import java.util.Scanner;

public class Player 
{
	
	private LinkedList inventory = new LinkedList();
	private PlayerStats stats;
	private int x;
	private int y;
	
	public Player(String playerName) 
	{
		setStats(new PlayerStats(playerName, 3));
		x =0;
		y =0;
		
	}
	public void checkSurroundings(String [][]board, LinkedList drops)
	{
		if (board[x][y-1].equals("+"))
		{
			addToInventory(4, drops);
			board[x][y-1] = " ";
		}
		else if (board[x][y+1].equals("+"))
		{
			addToInventory(4, drops);
			board[x][y+1] = " ";
		}
		else if (board[x-1][y].equals("+"))
		{
			addToInventory(4, drops);
			board[x-1][y] = " ";
		}
		else if (board[x+1][y].equals("+"))
		{
			addToInventory(4, drops);
			board[x+1][y] = " ";
		}
		
		if (board[x][y-1].equals("-") || board[x][y-1].equals("/"))
		{
			board[x][y-1] = " ";
		}
		else if (board[x][y+1].equals("-") || board[x][y+1].equals("/"))
		{
			board[x][y+1] = " ";
		}
		else if (board[x-1][y].equals("-") || board[x-1][y].equals("/"))
		{
			board[x-1][y] = " ";
		}
		else if (board[x+1][y].equals("-") || board[x+1][y].equals("/"))
		{
			
			board[x+1][y] = " ";
		}
	}
	
	public void addToInventory(int num, LinkedList drops)
	{
		int rand = 0;
		rand = (int)(num*Math.random());
		System.out.println(rand);
		switch(rand)
		{
		case 0:	
			{
				Node node = drops.getHead();
				node.setLink(null);
				inventory.addNodeToTail(drops.getHead());
				break;
			}
		case 1:	
		{
			Node node = drops.getHead();
			node = node.getLink();
			node.setLink(null);
			inventory.addNodeToTail(node);
			break;
		}
		case 2:	
		{
			Node node = drops.getHead();
			node = node.getLink();
			node = node.getLink();
			node.setLink(null);
			inventory.addNodeToTail(node);
			break;
		}
		case 3:	
		{
			Node node = drops.getHead();
			for(int i = 0; i < 3; i++)
				node = node.getLink();
			node.setLink(null);
			inventory.addNodeToTail(node);
			break;
		}
		case 4:	
		{
			Node node = drops.getHead();
			for(int i = 0; i < 4; i++)
				node = node.getLink();
			inventory.addNodeToTail(node);
			break;
		}
		}
	}
	public String[][] moveFunction(String move, String[][] board)
	{
		if (move.equals("W"))
		{
			
			if (board[x][y-1].equals(" "))
			{
				board[x][y] = " ";
				board[x][y-1] = "P";
				y--;
				this.getStats().setProgressDistance(this.getStats().getProgressDistance()+1);
				if(this.getStats().getProgressDistance() == 30)
				{
					System.out.println("You escaped. Good Job.");
					System.exit(0);
				}
			}
			else if(y > 22)
			{
				System.out.println("You cannot walk though this type of wall.");
				this.getStats().setHealth(this.getStats().getHealth()-1);
				System.out.println("Health: " + getStats().getHealth());
			}
			if (board[x][y-1].equals("E"))
			{
				this.getStats().setHealth(this.getStats().getHealth()-1);
				System.out.println("Health: " + getStats().getHealth());
				if(getStats().getHealth() <= 0)
				{
					System.out.println("You Died " + this.getStats().getPlayerName());
					System.exit(0);
				}
			}
		}
		else if(move.equals("S"))
		{
			if (board[x][y+1].equals( " "))
			{
				board[x][y] = " ";
				board[x][y+1] = "P";
				y++;
				this.getStats().setProgressDistance(this.getStats().getProgressDistance()-1);
				
			}
			else if(y > 22)
			{
				System.out.println("You cannot walk though this type of wall.");
				this.getStats().setHealth(this.getStats().getHealth()-1);
				System.out.println("Health: " + getStats().getHealth());
			}
			if (board[x][y+1].equals( "E"))
			{
				this.getStats().setHealth(this.getStats().getHealth()-1);
				System.out.println("Health: " + getStats().getHealth());
				if(getStats().getHealth() <= 0)
				{
					System.out.println("You Died " + this.getStats().getPlayerName());
					System.exit(0);
				}	
			}
		}
		else if (move.equals("A"))
		{
			if (board[x-1][y].equals(" "))
			{
				board[x][y] = " ";
				board[x-1][y] = "P";
				x--;
			}
			else if(y > 22)
			{
				System.out.println("You cannot walk though this type of wall.");
				this.getStats().setHealth(this.getStats().getHealth()-1);
				System.out.println("Health: " + getStats().getHealth());
			}
			if (board[x-1][y].equals("E"))
			{
				this.getStats().setHealth(this.getStats().getHealth()-1);
				System.out.println("Health: " + getStats().getHealth());
				if(getStats().getHealth() <= 0)
				{
					System.out.println("You Died " + this.getStats().getPlayerName());
					System.exit(0);
				}
			}
		}
		else
		{
			if (board[x+1][y].equals(" "))
			{
				board[x][y] = " ";
				board[x+1][y] = "P";
				x++;
			}
			else if(y > 22)
			{
				System.out.println("You cannot walk though this type of wall.");
				this.getStats().setHealth(this.getStats().getHealth()-1);
				System.out.println("Health: " + getStats().getHealth());
			}
			if (board[x+1][y].equals("E"))
			{
				this.getStats().setHealth(this.getStats().getHealth()-1);
				System.out.println("Health: " + getStats().getHealth());
				if(getStats().getHealth() <= 0)
				{
					System.out.println("You Died " + this.getStats().getPlayerName());
					System.exit(0);
				}
			}
		}
		return board;
	}
	
	public Node selectItem(Scanner input) {
		Node node = new Node();
		System.out.println("Enter which item to select starting at 1 or enter 0 to cancel.");
		int i = input.nextInt();
		if(i == 0)
		{
			System.out.println("Inventory Closed.");
		}
		else
		{	
			i--;
			if(i < inventory.getCount())
			{
				node = inventory.findNode(i);
				System.out.println(node.toString());
			}
		}
		return node;
		
	}
	public PlayerStats getStats() {
		return stats;
	}

	public void setStats(PlayerStats stats) {
		this.stats = stats;
	}

	public LinkedList getInventory() {
		return inventory;
	}

	public void setInventory(LinkedList inventory) {
		this.inventory = inventory;
	}

	public int getxPos() {
		return x;
	}

	public void setxPos(int xPos) {
		this.x = xPos;
	}

	public int getyPos() {
		return y;
	}

	public void setyPos(int yPos) {
		this.y = yPos;
	}
	
}
