import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Render extends Canvas implements Runnable {

	private boolean isRun;
	public int move_x = 0 , move_y = 0;
	
	private BufferedImage image;
	private BufferedImage image_tree;
	private BufferedImage image_tree_2;
	private BufferedImage image_char;
	private BufferedImage image_grass_1;
	private BufferedImage grass_stone;
	private double delta = 0;
	
	private Tile[][] buffer_map = new Tile[70][70];
	
	public Render()
	{
		System.setProperty("sun.java2d.opengl", "true");
		try {
			
			image = ImageIO.read(new File ("textures/grass.png"));
			image_tree = ImageIO.read(new File ("textures/tree.png"));
			image_tree_2 = ImageIO.read(new File ("textures/tree_2.png"));
			image_char = ImageIO.read(new File ("textures/character.png"));
			image_grass_1 = ImageIO.read(new File ("textures/grass_1.png"));
			grass_stone = ImageIO.read(new File ("textures/water.png"));
		} catch (Exception e) {
			
			System.out.println(e);
		}
	}
	
	public void DrawFPS(Graphics g)
	{
		g.setColor(Color.YELLOW);
		g.drawString(String.valueOf((int)(1000/delta)), 40, 40);
	}
	
	public void drawObjects(Graphics g)
	{
		int x_0, y_0;
		
		x_0 = buffer_map[0][0].getX();
		y_0 = buffer_map[0][0].getY();
		
		if (x_0 % 64 == 32) x_0  = x_0 - 32;
		
		ArrayList<WorldObject> buff_objects = new ArrayList<WorldObject>();
		
		for (int i = 0 ; i < Engine.map.objects.size(); i++)
		{
			int distance = (int) Math.sqrt(Math.pow(Game.engine.map.getPlayer().getX() - Engine.map.objects.get(i).getX(),2) 
					+ Math.pow(Game.engine.map.getPlayer().getY() - Engine.map.objects.get(i).getY(),2));
			int render_distance = (int) Math.sqrt(Math.pow(this.getHeight(),2) + Math.pow(this.getWidth(),2));
			if (distance < render_distance)
			{
				buff_objects.add(Engine.map.objects.get(i));
			
			}
		
			g.drawImage(image_tree,  Engine.map.objects.get(i).getX()  - x_0 - move_x -64 ,
					Engine.map.objects.get(i).getY()  - y_0  - move_y - 16, null);
			
			//move_x = Game.engine.map.getPlayer().getX() % 64;
			//	move_y = Game.engine.map.getPlayer().getY() % 16;
		
		}
		
	}
	
	public void drawBackground(Graphics g)
	{
		BufferedImage image_draw = null;
		int x_0, y_0;
		
		
		
		
		
		
		
		int new_x = 0, new_y = 0;
		for (int i = Game.engine.map.getPlayer().getX()/64 - 32; 
				i < Game.engine.map.getPlayer().getX()/64 + 32; i++)
		{
			for (int j = Game.engine.map.getPlayer().getY()/16 - 32; 
					j < Game.engine.map.getPlayer().getY()/16 + 32; j++)
			{
				
				buffer_map[new_x][new_y] = Game.engine.map.tile_map[i][j];
				new_y++;
				
			}
			new_x++;
			new_y = 0;
		}
		
		move_x = Game.engine.map.getPlayer().getX() % 64;
		move_y = Game.engine.map.getPlayer().getY() % 16;
		
		
		x_0 = buffer_map[0][0].getX();
		y_0 = buffer_map[0][0].getY();
	
		
		for (int i = 0; i < getWidth()/64 + 3; i++)
		{
			for (int j = 0; j <  getHeight()/16 + 2; j++)
			{
				if (x_0 % 64 == 32) x_0  = x_0 - 32;
				
				switch (buffer_map[i][j].get_id())
				{
					case 0 :  image_draw = image; break;
					
					case 1 : image_draw = image_grass_1; break;
					
					case 2 : image_draw = grass_stone; break;
				}
				
				g.drawImage(image_draw, buffer_map[i][j].getX() - x_0 - move_x -64 , buffer_map[i][j].getY() - y_0 - move_y - 16, null);
				
				
				}
			}
	}
	
	public void drawPlayer(Graphics g)
	{
		g.drawImage(image_char, getWidth()/2 - 16, getHeight()/2 - 42, null );
	}
	
	public void drawGrid(Graphics g)
	{
		for (int i = -2 ; i < getWidth()/64 + 2; i++)
		{
			for (int j = -2 ; j < getHeight()/16 + 2; j++)
			{
				
				Polygon polygon = new Polygon();
				
				if (j % 2 == 0)
				{
					polygon.addPoint(64*i + 32 - move_x, 32*j - move_y);
					polygon.addPoint(64*i + 64 - move_x, 32*j + 16 - move_y);
					polygon.addPoint(64*i + 32 - move_x, 32*j + 32 - move_y);
					polygon.addPoint(64*i - move_x ,	 16*j + 16 - move_y);
				}
			
				g.drawPolygon(polygon);
			}
		}
	}
	
	
	public void render()
	{
	
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			requestFocus();
			return;
		}
		Graphics g = bs.getDrawGraphics(); 
		
	
		g.setColor(Color.GRAY); 
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		Image buffer = createImage(this.getWidth(), this.getHeight());
	    Graphics gBuf = buffer.getGraphics();	
		drawBackground(g);
		drawPlayer(g);
		drawObjects(g);
		DrawFPS(g);
	//	drawGrid(g);
	  //g.drawImage(buffer, 0, 0,null);
		g.dispose();
		bs.show(); 
		
		
			
	}
	public void stop()
	{
		isRun = false;
	}
	public void resume()
	{
		isRun = true;
	}
	public void start()
	{
		isRun = true;
		new Thread(this).start();
	}
	
	public void setMoveX(int x)
	{
		this.move_x = x;
	}
	
	public void setMoveY(int y)
	{
		this.move_y = y;
	}
	
	@Override
	public void run() 	
	{
		
		while(isRun)
		{
			long first_time = System.currentTimeMillis();
			render();	
			delta = System.currentTimeMillis() - first_time;
		}
	
	}

}
