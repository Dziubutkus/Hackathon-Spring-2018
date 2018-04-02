
public class LinkedList 
{
	private Node head;
	private Node tail;
	private int count;
	
	public LinkedList()
	{
		head = null;
		setTail(head);
		setCount(0);
	}
	public Node findNode(char letter)
	{
		Node current = head;
		while(current.getItem().getLetter() != letter)
		{
			current = current.getLink();
		}
		return current;
	}
	public Node findNode(int location)
	{
		Node current = head;
		for(int i = 0; i < location; i++)
		{
			current = current.getLink();
		}
		return current;
	}

	public Node dropNode(Node toDrop)
	{
		Node current = head;
		
		
		return toDrop;
		
	}
	
	public void addNodeToTail(Node node)
	{
		if(count == 0)
		{
			head = node;
			tail = head;
			count++;
		}
		else if(count == 1)
		{
			tail.setLink(node);
			tail = node;
			count++;
		}
		else
		{
			tail.setLink(node);
			tail = node;
			count++;
		}
	}
	
	public void printLinkedList()
	{
		if(count == 0)
		{
			System.out.println("Your inventory is empty");
		}
		else
		{
			Node current = head;
			System.out.print(head.toString() + " ");
			for(int i = 1; i < count; i++)
			{
				if(current.getLink() != null)
				{
					current = current.getLink();
					System.out.print(current.toString());
				}
			}
			System.out.println(); 
		}
	}
	
	// Getters and Setters
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Node getTail() {
		return tail;
	}
	public void setTail(Node tail) {
		this.tail = tail;
	}
	public Node getHead() {
		return head;
	}
	public void setHead(Node head) {
		this.head = head;
	}
}
