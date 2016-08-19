import java.util.ArrayList;
import java.util.Random;

public class Map {
	
	private static final int MAP_SIZE = 1000;
	
	public Tile[][] tile_map = new Tile[MAP_SIZE][MAP_SIZE];
	private Player player = new Player(3000,3000);
	public ArrayList<WorldObject> objects = new ArrayList<WorldObject>();
	
	public Map()
	{
		for (int i = 0 ; i < MAP_SIZE; i ++)
		{
			for (int j = 0; j < MAP_SIZE; j ++)
			{
				Random rand = new Random();
		
				
					if (j % 2 == 0)
					{
						tile_map[i][j] = new Tile(rand.nextInt(2), i*64, j*16);
					}
					else 
					{
						tile_map[i][j] = new Tile(rand.nextInt(2), i*64 + 32, j*16);
					}
			}
		}
		Random rand = new Random();
		int seed_x = (rand.nextInt(1000));
		int seed_y = (rand.nextInt(1000));
		
		if (seed_x < 50) 
			seed_x = 51;
		
		if (seed_y < 50) 
			seed_y = 51;
		
		if (seed_x > 950) 
			seed_x = 900;
		
	    if (seed_y > 950) 
			seed_y = 900;
		
		for (int i = seed_x - 50; i < seed_x + 50; i ++)
		{
			for (int j = seed_y - 50; j < seed_y + 50; j ++)
			{
				tile_map[i][j].id = 2;
			}
			
		}
			
		for (int i = 0; i < 6000; i ++)
			objects.add(new WorldObject(1, new Random().nextInt(64000), new Random().nextInt(64000)));
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	
}
