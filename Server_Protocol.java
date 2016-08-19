import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Server_Protocol implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tile[][] tile_map;
	private Player player;
	
	
	public Tile[][] getTileMap()
	{
		return tile_map;
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException {
        in.defaultReadObject();
        
        this.tile_map = (Tile[][]) in.readObject();
        this.player = (Player) in.readObject();
        
}

	
}
