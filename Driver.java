import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

public class Driver {
	private static Scanner userInput = new Scanner(System.in);
	private static Player player;
	private static String [][]board = new String[80][30];
	private static LinkedList droppedItems = new LinkedList();
	private static FileWriter saver;
	
	public static void main(String[] args) throws IOException 
	{
		saver = new FileWriter("saved.txt");
		File file = new File("saved.txt");
		
		createBoard(board);
		//outputBoard(board);
		initialRoom(board);
		System.out.println();
		fillFindables();
		System.out.println("Please input player's name: ");
		
		player = new Player(userInput.next());
		
		player.setxPos(26);
		player.setyPos(27);
	//	player.setxPos(1);
		//player.setyPos(1);
		board[player.getxPos()][player.getyPos()] = "P";
		initialRoom(board);
		startStory();
		
		//outputInitialRoom();
		player.getInventory().addNodeToTail(new Node('S'));
		player.getInventory().addNodeToTail(new Node('A'));
		System.out.println();
		String in = userInput.next();
		while(!in.equals("-1"))
		{
			checkInput(in);
			
			in = userInput.next();
		}
	}
	public static void fillFindables()
	{
		Node node = new Node('S');
		droppedItems.addNodeToTail(node);
		node = new Node('K');
		droppedItems.addNodeToTail(node);
		node = new Node('M');
		droppedItems.addNodeToTail(node);
		node = new Node('A');
		droppedItems.addNodeToTail(node);
		node = new Node('P');
		droppedItems.addNodeToTail(node);

	}
	
	
	public static void startStory() {
		System.out.println("You are a spy and have been captured intentionally. ");
		System.out.println("You need to recover stolen launch codes from the command room but first you must get out of this cell. ");
		System.out.println("See if there is anything you can use to get out of this room. Good Luck " +player.getStats().getPlayerName());
		System.out.println("If you run into Enemies you will lose health!!! Currently, if you escape the building, you win.");
		System.out.println("Press t to display your stats.");
		System.out.println("Press w/s/a/d to move.");
		System.out.println("Press i to look at your inventory.");
		System.out.println("Press n to interact with surroundings.");
	}
	public static void checkInput(String input) throws IOException
	{
		if(input.equalsIgnoreCase("W") || input.equalsIgnoreCase("S") || input.equalsIgnoreCase("A") || input.equalsIgnoreCase("D"))
		{
			board = player.moveFunction(input.toUpperCase(), board);
			
			if(player.getyPos() > 22)
			{
				initialRoom(board);
			}
			else
			{
				if(player.getyPos() == 22)
				{
					System.out.println("You made it out of your cell, now you have a choice. ");
					System.out.println("Eventually, you need to get to the command room but the way there is packed with guards.");
					System.out.println("You can try to fight your way through this way or find another path… Good Luck " +player.getStats().getPlayerName());
					System.out.println("Enter w to continue.");
					userInput.next();
				}
				
				outputBoard(board);
			}
		}
		else if(input.equalsIgnoreCase("n"))
		{
			player.checkSurroundings(board, droppedItems);
			if(player.getyPos() > 22)
			{
				initialRoom(board);
			}
			else
			{
				outputBoard(board);
			}
				
		}
		else if(input.equalsIgnoreCase("i"))
		{
			player.getInventory().printLinkedList();
			Node n = player.selectItem(userInput);
			String s = "";
			if(n.getItem() != null)
			{
				 System.out.println("Press q to cancel.");
				 System.out.println("Press e to equip Armor or Hand.");
				 s = userInput.next();
			
			}
			
			if(s.equalsIgnoreCase("q"))
			{
				player.getInventory().dropNode(n);
			}
			else if(s.equalsIgnoreCase("e"))
			{
				if(n.getItem().getDefensePower() > 2)
				{
					player.getStats().setArmor(n.getItem());
					player.getStats().setHealth(player.getStats().getHealth() + n.getItem().getDefensePower());
				}
				else
					player.getStats().setHand(n.getItem());
			}
		}
			
		if(input.equals("t"))
		{
			player.getStats().displayStats();
		}
		if(input.equalsIgnoreCase("P"))
		{
			saver.write(player.getStats().getPlayerName());
			saver.write(player.getStats().getLevel());
			saver.write(player.getStats().getHealth());
			saver.write(player.getStats().getHand().getLetter());
			saver.write(player.getStats().getArmor().getDefensePower());
			saver.write(player.getStats().getProgressDistance());
		}
	}
	
	public static void initialRoom(String [][]board)
	{
		for(int j=22; j<30; j++)
		{
			System.out.println();
			for(int i=0; i<80; i++)
			{
				System.out.print(board[i][j]);
			}
		}
		System.out.println();

	}
	public static void outputBoard(String [][]board)//outputs initial room.
	{
		for(int j=0; j<30; j++)
		{
			System.out.println();
			for(int i=0; i<80; i++)
			{
				System.out.print(board[i][j]);
			}
		}
	}
	public static void createBoard(String [][]board)
	{
		for(int e = 0; e < 80; e++)
			for(int u = 0; u < 30; u++)
			{
				board[e][u] = " ";
			}
		for(int a=0; a<23; a++)
		{
			board[0][a] = "|";
			for(int i=1; i<79; i++)
			{
				board[i][a] = " ";
			}
			board[79][a] = "|";
		}
		// line 1
		for(int i=0; i<80; i++)
		{
			board[i][0] = "_";
		}
		board[4][0] = "-";
		// line 2
		board[50][1] = "/";
		// line 3
		board[0][2] = "|";
		for(int i=1; i<50; i++)
		{
			board[i][2] = "_";
		}
		board[50][2] = "|";
		//line 4
		board[20][3]= "|";
		//line 5
		for(int i=3; i<18; i++)
		{
			board[i][4] = "_";
		}
		board[20][4]= "|";
		for(int i=23; i<50; i++)
		{
			board[i][4] = "_";
		}
		// line 6
		board[17][5] = "|";
		board[20][5] = "|"; 
		board[50][5] = "|";
		//line 7
		for(int i=1; i<14; i++)
		{
			board[i][6] = "_";
		}
		board[17][6] = "|";
		board[20][6] = "|"; 
		board[50][6] = "|";
		for(int i=21; i<47; i++)
		{
			board[i][6] = "_";
		}
		for(int i=51; i<79; i++)
		{
			board[i][6] = "_";
		}
		//line 8
		board[17][7] = "|";
		board[50][7] = "|";
		board[75][7] = "|";
		//line 9
		board[17][8] = "|";
		board[50][8] = "|";
		board[75][8] = "|";
		for(int i=4; i<50; i++)
		{
			board[i][8] = "_";
		}
		for(int i=54; i<72; i++)
		{
			board[i][8] = "_";
		}
		//line 10
		board[17][9] = "|";
		board[26][9] = "|";
		board[36][9] = "|";
		board[50][9] = "|";
		board[72][9] = "|";
		board[75][9] = "|";
		//line 11
		for(int i=1; i<14; i++)
		{
			board[i][10] = "_";
		}
		board[17][10] = "|";
		board[26][10] = "|";
		board[36][10] = "|";
		board[50][10] = "|";
		board[72][10] = "|";
		board[75][10] = "|";
		for(int i=51; i<69; i++)
		{
			board[i][10] = "_";
		}
		//line 12
		board[17][11] = "|";
		board[22][11] = "|";
		board[26][11] = "|";
		board[31][11] = "|";
		board[36][11] = "|";
		board[43][11] = "|";
		board[72][11] = "|";
		board[75][11] = "|";
		//line 13
		board[17][12] = "|";
		board[22][12] = "|";
		board[26][12] = "|";
		board[31][12] = "|";
		board[36][12] = "|";
		board[43][12] = "|";
		board[72][12] = "|";
		board[75][12] = "|";
		for(int i=4; i<17; i++)
		{
			board[i][12] = "_";
		}
		// line 14
		
		board[22][13] = "|";
		board[26][13] = "|";
		board[31][13] = "|";
		board[36][13] = "|";
		board[43][13] = "|";
		board[72][13] = "|";
		board[75][13] = "|";
		// line 15
		for(int i=1; i<72; i++)
		{
			board[i][14] = "_";
		}
		board[75][14] = "|";
		for(int i=76; i<79; i++)
		{
			board[i][14] = "_";
		}
		// line 16
		board[43][15] = "|";
		board[75][15] = "|";
		// line 17
		board[43][16] = "|";
		board[17][16] = "E";
		board[75][16] = "|";
		for(int i=31; i<40; i++)
		{
			board[i][16] = "_";
		}
		// line 18
		board[31][17] = "|";
		board[43][17] = "|";
		board[75][17] = "|";
		// line 19
		board[31][18] = "|";
		board[43][18] = "|";
		board[75][18] = "|";
		board[20][18] = "E";
		board[50][18] = "E";
		for(int i=35; i<43; i++)
		{
			board[i][18] = "_";
		}
		// line 20
		board[31][19] = "|";
		board[75][19] = "|";
		board[30][19] = "+";
		// line 21
		for(int i=4; i<79; i++)
		{
			board[i][20] = "_";
		}
		board[31][20] = "|";
		board[75][20] = "|";
		//board[30][20] = "+";
		// line 22
		board[40][21] = "E";
		board[43][21] = "|";
		// line 23
		for(int i=1; i<79; i++)
		{
			board[i][22] = "_";
		}
		board[43][22] = "|";
		board[37][22] = "-";
		board[38][22] = "-";
		
		//line 24
		board[25][23] = "|";
		board[50][23] = "|";
		//line 25
		board[25][24] = "|";
		board[50][24] = "|";
		//line 26
		board[25][25] = "|";
		board[50][25] = "|";
		//line 27
		board[25][26] = "|";
		board[50][26] = "|";
		//line 28
		board[25][27] = "|";
		board[50][27] = "|";
		//line 29
		board[25][28] = "|";
		board[50][28] = "|";
		//line 30
		for(int i=25; i<50; i++)
		{
			board[i][29] = "_";
		}
		board[25][29] = "|";
		board[50][29] = "|";
	}

}
