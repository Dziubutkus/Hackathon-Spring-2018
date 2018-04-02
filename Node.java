
public class Node 
{
	private Node link;
	private Item item;
	
	Node()
	{
		setLink(null);
		setItem(null);
	}
	Node(char c)
	{
		if(c == 'S')
		{
			item = new Item(4, 0,1,c);
		}
		else if(c == 'K')
			item = new Item(0, 1, 0, c);
		else if (c == 'M')
			item = new Item(0,0,0,c);
		else if(c == 'A')
			item = new Item(0, 0, 5, c);
		else 
			item = new Item(2,4, 0, c);
			
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Node getLink() {
		return link;
	}

	public void setLink(Node link) {
		this.link = link;
	}
	@Override
	public String toString()
	{
		return item.getLetter() + "";
		
	}
}
