import java.io.Serializable;

public class Tile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1684195506148019157L;
	private int level;
	public int id;
	private int x, y;
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public Tile(int id, int x, int y)
	{
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public Tile(int level, int id )
	{
		this.id = id;
		this.level = level;
	}
	
	public int get_id()
	{
		return id;
	}
	
	public int get_level()
	{
		return level;
	}
}
