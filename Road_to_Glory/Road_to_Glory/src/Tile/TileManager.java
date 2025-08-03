package Tile;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class TileManager {
GamePanel gp;
public Tile[] tile;
public int mapTileNum[] [] [];

    public TileManager (GamePanel gp) {
    	
	this.gp=gp;
	
	tile=new Tile[50];
	
	mapTileNum=new int [gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
	
	getTileImage();
	LoadMap("/maps/worldV3.txt",0);
	LoadMap("/maps/interior01.txt",1);

	
     }
    
    public void getTileImage() {
    	//placeholder
    	setup(0,"g1",false);
    	setup(1,"g1",false);
    	setup(2,"g1",false);
    	setup(3,"g1",false);
    	setup(4,"g1",false);
    	setup(5,"g1",false);
    	setup(6,"g1",false);
    	setup(7,"g1",false);
    	setup(8,"g1",false);
    	setup(0,"g1",false);
    	//placeholder
    	setup(10,"g1",false);
    	setup(11,"g1",false);
    	setup(12,"w0",true);
    	setup(13,"w0",true);
    	setup(14,"w22",true);
    	setup(15,"w7",true);
    	setup(16,"w3",true);
    	setup(17,"w5",true);
    	setup(18,"w6",true);
    	setup(19,"w1",true);
    	setup(20,"w8",true);
    	setup(21,"w4",true);
    	setup(22,"w0",true);
    	setup(23,"w0",true);
    	setup(24,"w0",true);
    	setup(25,"w0",true);
    	setup(26,"nr0",false);
    	setup(27,"nr6",false);
    	setup(28,"nr4",false);
    	setup(29,"nr5",false);
    	setup(30,"nr2",false);
    	setup(31,"nr1",false);
    	setup(32,"nr7",false);
    	setup(33,"nr3",false);
    	setup(34,"nr8",false);
    	setup(35,"g1",false);
    	setup(36,"g1",false);
    	setup(37,"g1",false);
    	setup(38,"g1",false);
    	setup(39,"earth",false);
    	setup(40,"wall",true);
    	setup(41,"tree3",true);
    	setup(42,"hut",false);
    	setup(43,"floor01",false);
    	setup(44,"table01",true);

    	
    	//old rendering
    	/*try {
    		tile[0]=new Tile();
    		tile[0].image= ImageIO.read(getClass().getResourceAsStream("/Tiles/grass01.png"));
    		
	tile[1]=new Tile();
            
    		tile[1].image= ImageIO.read(getClass().getResourceAsStream("/Tiles/wall.png"));
    		tile[1].collision=true;
    		
	tile[2]=new Tile();
            
    		tile[2].image= ImageIO.read(getClass().getResourceAsStream("/Tiles/water01.png"));
    		tile[2].collision=true;

	tile[3]=new Tile();
            
    		tile[3].image= ImageIO.read(getClass().getResourceAsStream("/Tiles/earth.png"));
    		
	tile[4]=new Tile();
            
    		tile[4].image= ImageIO.read(getClass().getResourceAsStream("/Tiles/tree.png"));
    		tile[4].collision=true;
    		
	tile[5]=new Tile();
            
    		tile[5].image= ImageIO.read(getClass().getResourceAsStream("/Tiles/sand.png"));
    		
    	}catch(IOException e) {
    		
    		e.printStackTrace();
    		
    	}*/
    	 	
    }
 	public void setup(int index,String imageName,boolean collision){
 		UtilityTool uTool=new UtilityTool();
 		try {
 			tile[index]=new Tile();
 			tile[index].image= ImageIO.read(getClass().getResourceAsStream("/Tiles/"+imageName+".png"));
 			tile[index].image=uTool.scaleImage(tile[index].image,gp.tileSize, gp.tileSize);
 			tile[index].collision=collision;
 			
 			
 		}
 		catch(IOException e) {
 			e.printStackTrace();
 		}
		
	}
 	
    public void LoadMap(String filePath,int map) {
    	
    	try {
    		InputStream is= getClass().getResourceAsStream(filePath);
    		BufferedReader br=new BufferedReader(new InputStreamReader(is));
    		
    		int col=0;
    		int row=0;
    		while (col<gp.maxWorldCol &&row< gp.maxWorldRow) {
    			String line =br.readLine();
    			while (col<gp.maxWorldCol) {
    				
    				String numbers[]=line.split(" ");
    				int num=Integer.parseInt(numbers[col]);
    				mapTileNum[map][col][row]=num;
    				col++;
    				
    			}
    			if (col==gp.maxWorldCol) {
    				col=0;
    				row++;
    			}
    		}
    		br.close();
    		
    		
    	}catch (Exception e) {
    		
    	}
    	
    	
    	
    }
    
    
    
    
    public void draw(Graphics2D g2) {
    	int worldCol=0;
    	int worldRow=0;
    	
    	
    	while (worldCol<gp.maxWorldCol&& worldRow<gp.maxWorldRow) {
    		int tileNum=mapTileNum[gp.currentMap][worldCol][worldRow];
    		
    		int worldX=worldCol*gp.tileSize;
    		int worldY=worldRow*gp.tileSize;
    		int screenX=worldX -gp.player.worldX+gp.player.screenX;
    		int screenY=worldY -gp.player.worldY+gp.player.screenY;
    		
    		
    		if(worldX+gp.tileSize>gp.player.worldX-gp.player.screenX&&
    		   worldX-gp.tileSize<gp.player.worldX+gp.player.screenX&&
    		   worldY+gp.tileSize>gp.player.worldY-gp.player.screenY&&
    		   worldY-gp.tileSize<gp.player.worldY+gp.player.screenY
    				) {
    		
    	g2.drawImage(tile[tileNum].image, screenX, screenY, null);
    		}
    	worldCol++;
    	
    
    	if (worldCol==gp.maxWorldCol) {
    		worldCol=0;
    		worldRow++;
    		
    	}
    	
    }
    
    
    
    }
    
    
}
