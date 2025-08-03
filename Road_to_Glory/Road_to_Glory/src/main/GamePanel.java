package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import Tile.TileManager;
import entity.Entity;
import entity.Player;
import tile_interactive.InteractiveTile;


// JPanel class used to group & organize component inside a window
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable {
	
	//Screen Settings
	final int originalTileSize = 16; // 16x16 tile the size of the player character and NPCs
	final int scale = 3;  // to scale up the 16 pixel character to be able to be seen on the monitor
	
	public final int tileSize = originalTileSize * scale; // 48x48 tile
	//Screen Size
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 960 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	//world settings
	public final int maxWorldCol=50;
	public final int maxWorldRow=50;
	public final int worldWidth=tileSize*maxWorldCol;
	public final int worldHight=tileSize*maxWorldRow;
	public final int maxMap=10;
	public int currentMap=0;
	
	// FOR FULL SCREEEN
	int screenWidth2 =screenWidth ;
	int screenHeight2 =screenHeight ;
	BufferedImage tempScreen;
	Graphics2D g2;
	public boolean fullScreenOn = false ;
	
	//FBS
	int FBS = 60;
	TileManager tileM= new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionChecker cChecker= new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter (this);
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler (this) ;
	Config config =new Config(this);
	
	
	//entity and object
	public Player player = new Player(this , keyH);
	public Entity obj[][] =new Entity[maxMap][20]; // slots of objects
	Thread gameThread; // to apply the concept of FBS in the game
	public Entity npc[][]=new Entity[maxMap][10];
	public Entity monster[][] = new Entity[maxMap][20];
	public InteractiveTile iTile[][] = new InteractiveTile[maxMap][50] ;
	//This part for Render order problem
	public ArrayList<Entity> projectileList = new ArrayList<> ();
	ArrayList<Entity> entityList = new ArrayList<>();  // Our sort will be the Entity that has the (Lowest worldY ) comes in index 0 .
	
	
	//set Player's default position
	int playerX = 100;							  
	int playerY = 100;
	int playerSpeed = 4;
	
	
	//game state
	public int gameState;
	public final int titleState =0;
	public final int playState=1;
	public final int pauseState=2;
	public final int  dialogueState = 3 ;
	public final int characterState=4;
	public final int optionState = 5;
	public final int gameOverState = 6;
	public final int transitionState =7;
	public final int tradeState=8;

	
	
	public GamePanel () {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set the size of JPanel class
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // Enabling this can improve game's rendering performance
		this.addKeyListener(keyH);
		this.setFocusable(true); 
		this.setFocusable(true);
		this.requestFocusInWindow();
		// GamePanel focus to get key input
		
	}
    public void setupGame() {
	// to add other setup stuff in the future
	aSetter.setObject();
	aSetter.setNPC();
	aSetter.setMonster();
	aSetter.setInterativeTile();
//	playMusic(0);
	gameState=titleState;
	
    // FULL SCREEN
	tempScreen = new BufferedImage(screenWidth , screenHeight , BufferedImage.TYPE_INT_ARGB ) ; //We Will make a Blank Buffered Image like Our mintor size & This is for our Game Window not our Mintor .
	g2 = (Graphics2D)tempScreen.getGraphics() ; // We attach this ( g2 ) to tempScreen BufferedImage Then TO JPanel . 
	
	/* 
	 * Until Now : WE Were Drawing Every thing in g2 --> JPanel .
	 * This time : We will Draw Every thing in    g2 --> tempScreen --> JPanel
	 * As Now with this We can  Make it in any Size as it will resize Everything Automatically.
	 * And We Now Will Not use the paintComponent()  any more as it was drawing every thing  Forward to the JPanel .
	 * Now We Have two g2 first one in the paintComponent Second in The tempScreen We just made
	 * */
	if (fullScreenOn==true) {
	setFullScreen() ;}
     }
     public void retry() {
    	 player.setDefaultPositions();
    	 player.restoreLifeAndMan();
    	 aSetter.setNPC();
    	 aSetter.setMonster();
     }
     public void restart() {
    	 player.setDefaultValues();
    	 player.setItems();
    	 aSetter.setObject();
    	 aSetter.setNPC();
    	 aSetter.setMonster();
    	 aSetter.setInterativeTile();
    	 
     }
     



	public void setFullScreen() {
		// GET LOCAL SCREEN DEVICE
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment() ;  // To get Our mintor information
		GraphicsDevice gd = ge.getDefaultScreenDevice() ;  
		gd.setFullScreenWindow(Main.window);
		
		// GET FULL SCREEN WIDTH & HEIGHT
		screenWidth2 = Main.window.getWidth();		
		screenHeight2 = Main.window.getHeight();		// Those Will Return Our Full Screen Width & Height 
		
	}

 	public void startGameThread() {
		
		// to instantiate a Thread
		gameThread = new Thread(this); // Pass GamePanel class to Thread constructor
		gameThread.start(); // this will automatically call run method
	}
	
	// RUN method with sleep method
//	@Override
//	public void run() {  // we will create a Game loop which is the core of our Game
//		
//		double drawInterval = 1e9/FBS; // Every 0.01666 sec. = 1 FBS
//		double nextDrawTime = System.nanoTime() + drawInterval;
//		
//		while (gameThread != null) {
//			
//			// 1.UPDATE: update information such as character positions
//			update();
//			
//			// 2.Draw: draw the screen with the updated information
//			repaint(); // This is how to call the paintComponent method
//			
//			try {
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime /= 1e6; // sleep method accepts time in millisecond
//				
//				if (remainingTime < 0) {
//					remainingTime = 0;
//				}
//				
//				Thread.sleep((long) remainingTime);
//				
//				nextDrawTime += drawInterval;
//				
//			} catch (InterruptedException e) {
//
//				e.printStackTrace();
//			}
//			
//		}
//		
//	}
	
	// Run method but with delta method rather than sleep method
	
	@Override
	public void run() {
		
		double drawInterval = 1e9 / FBS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while (gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if (delta >= 1) {
				update();
				drawToTempScreen(); // Draw everything to the Buffered Image
				drawToScreen () ;   // Draw Buffered Image To Screen
				delta--;
				drawCount++;
			}
			
			if (timer >= 1e9) {
				System.out.println("FBS: " + drawCount);
				timer = 0;
				drawCount = 0;
			}
		}
	}
	
	public void update() {
		
		if (gameState==playState) {
			//player
		player.update();
		//npc 
		for (int i=0;i<npc[1].length;i++) {
			if (npc[currentMap][i]!=null)
				npc[currentMap][i].update();
		}
		for (int i = 0; i  < monster[1].length; i++) {
			if (monster[currentMap][i] != null) {
				if (monster[currentMap][i].alive == true && monster[currentMap][i].dying == false) {
					monster[currentMap][i].update();
				}
				if (monster[currentMap][i].alive == false) {
					monster[currentMap][i].checkDrop();
					monster[currentMap][i] = null;
				}
			}
		}
		for (int i = 0; i  < projectileList.size(); i++) {
			if (projectileList.get(i) != null) {
				if (projectileList.get(i).alive == true) {
					projectileList.get(i).update();
				}
				else {
					projectileList.remove(i);
					i--;
			 	}
			  }
		    }
		for (int i = 0 ; i< iTile[1].length; i++ ) {
			   if(iTile [currentMap][i] != null)
			   { iTile[currentMap][i].update(); }
		   }
		}
		if (gameState==pauseState) {
			//nothing
		}
	}
	
	
	public void drawToTempScreen() {
		
			  g2.clearRect ( 0, 0 , screenWidth2, screenHeight2);
		      //debug
				long drawStart=0;
				if (keyH.showDebugText==true) {
				drawStart=System.nanoTime();
				}
				
				//title screen 
				if(gameState==titleState) {
					ui.draw(g2);
				}
				
				//others 
				else {
					
				
					//TILE               
					tileM.draw(g2);
					
					//INTERACTIVE TILE
					// We won't Draw the InteactiveTile With the entityLIst Array but with the normal one
					for (int i = 0 ; i< iTile[1].length ; i++ ) {
						   if(iTile[currentMap] [i] != null) {
							   iTile[currentMap][i].draw(g2);
						   }
					  }
					
					// ADD Entities To The List
					entityList.add(player);
					for(int i=0 ; i < npc[1].length; i++) {
						if(npc[currentMap][i] != null ) {
							entityList.add(npc[currentMap][i]);
							
						}
					}
					
					for(int i=0 ; i < obj[1].length ; i++) {
						if(obj[currentMap][i] != null ) {
							entityList.add(obj[currentMap][i]);
							
						}
					}
					
					for(int i=0 ; i < monster[1].length ; i++) {
						if(monster[currentMap][i] != null ) {
							entityList.add(monster[currentMap][i]);
							
						}
					}
					for(int i=0 ; i <projectileList.size() ; i++) {
						if(projectileList.get(i) != null ) {
							entityList.add(projectileList.get(i));
							
						}
					}
					
					// SORT
					Collections.sort(entityList , new Comparator<Entity>() {
						
					

						@Override
						public int compare(Entity e1, Entity e2) {
							
							int result = Integer.compare(e1.worldY, e2.worldY);
							return result;
						}
						
					});
					
					// DRAW ENTITIES
					for(int i=0; i < entityList.size(); i++) {
						entityList.get(i).draw(g2);
					}
					// EMPITY ENTITY LIST
					entityList.clear();
					
					
					// UI
					ui.draw(g2);
				}
				
					//debug
					if(keyH.showDebugText == true) {
						
					
						long drawEnd=System.nanoTime();
						long passed=drawEnd-drawStart;
						g2.setColor(Color.white);
						g2.setFont(new Font("Arial",Font.PLAIN,20));
						int x=10;
						int y=400;
						int lineHeight=20;
						
						      						  
						g2.drawString("worldX: "+player.worldX, x, y);    						  y += lineHeight;
						g2.drawString("worldY: "+player.worldY, x, y); 							  y += lineHeight;		
						g2.drawString("row: "+(player.worldX+player.solidArea.x)/tileSize, x, y); y += lineHeight;
						g2.drawString("col: "+(player.worldY+player.solidArea.y)/tileSize, x, y); y += lineHeight;
						g2.drawString("draw time: "+passed, x, y);

					}
   }
	
	
	public void drawToScreen () {
		
		Graphics g = getGraphics() ;
		g.drawImage(tempScreen, 0 , 0 , screenWidth2, screenHeight2, null ) ;
		g.dispose();
	}
	
	public void playMusic(int i) {
	 music.setFile(i);
	 music.play();
	 music.loop();
 }
 public void stopMusic() {
	 music.stop();
 }
 public void playSE(int i) {
	 se.setFile(i);
	 se.play();
 }
}
