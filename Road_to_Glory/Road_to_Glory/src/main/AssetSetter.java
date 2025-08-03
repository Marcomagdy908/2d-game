package main;

import entity.NPC_Merchant;
import entity.NPC_OldMan;
import monster.MON_GreenSlime;
import object.OBJ_Axe;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Coin_Bronze;
import object.OBJ_Door;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_ManaCrystal;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import tile_interactive.IT_DryTree;
// used to set objects on map
public class AssetSetter {
GamePanel gp;
public AssetSetter(GamePanel gp) {
	this.gp=gp;
}
public void setObject() {
	int i=0;
	int mapNum=0;
	//Coins location
	
	gp.obj[mapNum][i]= new OBJ_Coin_Bronze(gp);
	gp.obj[mapNum][i].worldX = 23*gp.tileSize;
	gp.obj[mapNum][i].worldY = 7*gp.tileSize;
	i++;
	gp.obj[mapNum][i]= new OBJ_Coin_Bronze(gp);
	gp.obj[mapNum][i].worldX = 23*gp.tileSize;
	gp.obj[mapNum][i].worldY = 40*gp.tileSize;
	i++;
	gp.obj[mapNum][i]= new OBJ_Coin_Bronze(gp);
	gp.obj[mapNum][i].worldX = 38*gp.tileSize;
	gp.obj[mapNum][i].worldY = 8*gp.tileSize;
	i++;
	
	// axe location
	gp.obj[mapNum][i]= new OBJ_Axe(gp);
	gp.obj[mapNum][i].worldX = 25*gp.tileSize;
	gp.obj[mapNum][i].worldY = 21*gp.tileSize;
	i++;
	
	//shield blue
	gp.obj[mapNum][i]= new OBJ_Shield_Blue(gp);
	gp.obj[mapNum][i].worldX = 35*gp.tileSize;
	gp.obj[mapNum][i].worldY = 21*gp.tileSize;
	i++;
	
	//POTION
	gp.obj[mapNum][i]= new OBJ_Potion_Red(gp);
	gp.obj[mapNum][i].worldX = 22*gp.tileSize;
	gp.obj[mapNum][i].worldY = 27*gp.tileSize;
	i++;
//	gp.obj[mapNum][i]= new OBJ_Potion_Red(gp);
//	gp.obj[mapNum][i].worldX = 21*gp.tileSize;
//	gp.obj[mapNum][i].worldY = 20*gp.tileSize;
//	i++;
//	gp.obj[mapNum][i]= new OBJ_Potion_Red(gp);
//	gp.obj[mapNum][i].worldX = 20*gp.tileSize;
//	gp.obj[mapNum][i].worldY = 20*gp.tileSize;
//	i++;
	gp.obj[mapNum][i]= new OBJ_Potion_Red(gp);
	gp.obj[mapNum][i].worldX = 17*gp.tileSize;
	gp.obj[mapNum][i].worldY = 21*gp.tileSize;
	i++;
	//heart
	gp.obj[mapNum][i]= new OBJ_Heart(gp);
	gp.obj[mapNum][i].worldX = 22*gp.tileSize;
	gp.obj[mapNum][i].worldY = 29*gp.tileSize;
	i++;
	//MANA Crystal
	gp.obj[mapNum][i]= new OBJ_ManaCrystal(gp);
	gp.obj[mapNum][i].worldX = 22*gp.tileSize;
	gp.obj[mapNum][i].worldY = 31*gp.tileSize;
	i++;
	
	// location of object
	
/* Checking rendering problem
	gp.obj[0]= new OBJ_Door(gp);
	gp.obj[0].worldX = 21*gp.tileSize;
	gp.obj[0].worldY = 22*gp.tileSize;
	
	gp.obj[1]= new OBJ_Door(gp);
	gp.obj[1].worldX = 23*gp.tileSize;
	gp.obj[1].worldY = 25*gp.tileSize;
 */	
	
/*	//the limit is 10 objects we can increase in future
	

	
	

	//doors location
	gp.obj[3]= new OBJ_Door(gp);
	gp.obj[3].worldX = 10*gp.tileSize;
	gp.obj[3].worldY = 11*gp.tileSize;
	
	gp.obj[4]= new OBJ_Door(gp);
	gp.obj[4].worldX = 8*gp.tileSize;
	gp.obj[4].worldY = 28*gp.tileSize;
	
	gp.obj[5]= new OBJ_Door(gp);
	gp.obj[5].worldX = 12*gp.tileSize;
	gp.obj[5].worldY = 22*gp.tileSize;
	
	//chest location 
	
	gp.obj[6]= new OBJ_Chest(gp);
	gp.obj[6].worldX = 10*gp.tileSize;
	gp.obj[6].worldY = 7*gp.tileSize;
	  
	// boots location
	
	gp.obj[7]= new OBJ_Boots(gp);
	gp.obj[7].worldX = 37*gp.tileSize;
	gp.obj[7].worldY = 42*gp.tileSize;*/
}
public void setNPC() {
	int mapNum=0;
	int i=0;
	
	// MAP 0
	gp.npc[mapNum][i]=new NPC_OldMan(gp);
	gp.npc[mapNum][i].worldX=gp.tileSize*21;
	gp.npc[mapNum][i].worldY=gp.tileSize*21;
	i++;
	
	// MAP 1
	mapNum=1;
	i = 0;
	gp.npc[mapNum][i]=new NPC_Merchant(gp);
	gp.npc[mapNum][i].worldX=gp.tileSize*12;
	gp.npc[mapNum][i].worldY=gp.tileSize*7;
	i++;

}
	public void setMonster() {
		int mapNum=0;
		int i=0;
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*21;
		gp.monster[mapNum][i].worldY = gp.tileSize*38;
		i++;
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*23;
		gp.monster[mapNum][i].worldY = gp.tileSize*42;
		i++;
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*24;
		gp.monster[mapNum][i].worldY = gp.tileSize*37;
		i++;
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*34;
		gp.monster[mapNum][i].worldY = gp.tileSize*42;
		i++;
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*38;
		gp.monster[mapNum][i].worldY = gp.tileSize*42;
		i++;
	}
	
	public void setInterativeTile () {
		int mapNum=0;
		int i = 0 ;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,27,12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,28,12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,29,12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,30,12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,31,12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,32,12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,33,12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,30,20); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,30,21); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,30,22); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,20,20); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,20,21); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,20,22); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,22,24); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,23,24); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,24,24); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,27,12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,28,12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,29,12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,30,12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,31,12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,32,12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,33,12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,31,20); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,18,40); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,17,40); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,16,40); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,15,40); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,14,40); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,13,41); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,12,41); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,11,41); i++;
		gp.iTile[mapNum][i] = new IT_DryTree (gp,10,41); i++;
		
		
	}
	

}
