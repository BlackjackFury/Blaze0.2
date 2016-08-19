
public class Engine implements Runnable
{
	private Render render = new Render();
	private Network network = new Network();
	private Window window = new Window(render);
	
	public static Tile[][] tile_map = new Tile[60][60];
	public static Map map = new Map();
	private boolean isRun;
	private Player player;
	public boolean isReady;
	
	private int vector_x, vector_y;
	private double cos, sin;
	
	
	private int distance;
	private double real_x, real_y;
	
	public void start()

	{
		isRun = true;
		//move_to_x =  render.getWidth()/2;
	//	move_to_y = render.getHeight()/2;
		new Thread(this).start();
		render.start();
		
	}
	
	public void genMap()
	{
		
	}
	
	
	public Render getRender()
	{
		return render;
	}
	
	public void mousePressed(int move_to_x, int move_to_y)
	{
	
		vector_x = move_to_x  - render.getWidth()/2;
		vector_y = move_to_y  - render.getHeight()/2;
		
		distance = (int) Math.sqrt(Math.pow(vector_x, 2) + Math.pow(vector_y, 2));
	}
	
	public void move()
	{
		
		int speed = 3;
		
	//	System.out.println(distance);
		if ((distance > speed || distance < -1*speed))
		{
			cos = vector_x/Math.sqrt(Math.pow(vector_x,2) + Math.pow(vector_y,2));
			sin = Math.sqrt(1 - Math.pow(cos, 2));
		
			if (vector_y < 0)
			{
				sin = -1*sin;
			}
			
		//	System.out.println(Math.pow(cos*speed,2) + Math.pow(sin*speed,2));
			real_x = real_x + cos*speed;
			real_y = real_y + sin*speed;
			
			map.getPlayer().setX((real_x + cos*speed));
			map.getPlayer().setY((real_y + sin*speed));
			distance = distance - speed;
		}

	}

	@Override
	public void run()
	{
		real_x = map.getPlayer().getX();
		real_y = map.getPlayer().getY();
		
		while(isRun)
		{
			isReady = false;
		
			long tick_start = System.currentTimeMillis();
				
				
				
				isReady = true;
				
				 long tick_finish = System.currentTimeMillis();
				 
					if (tick_finish - tick_start < 20)
					{
						
						try {
							Thread.sleep(20 - (tick_finish - tick_start));
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
						
					}
					tick_finish = System.currentTimeMillis();
					
					move();
			}
		
			
		}
	
}
