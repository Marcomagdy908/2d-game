package main;

import java.awt.Rectangle;

import entity.Entity;

public class EventHandler {
	GamePanel gp;
	EventRect eventRect [] [] [];
	
	int previousEventX , previousEventY ;
	boolean canTouchEvent = true ; 
	int tempMap,tempCol,tempRow;
	
	
	public EventHandler (GamePanel gp) {
		
		this.gp = gp ; 
		
		eventRect = new EventRect[gp.maxMap] [gp.maxWorldCol ] [gp.maxWorldRow] ;
		int map=0;
		int col=0;
		int row=0;
		while (map<gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
			eventRect[map][col][row] = new EventRect();      // We will make it small 2*2 
			eventRect[map][col][row].x = 23 ;     
			eventRect[map][col][row].y = 23 ;
			eventRect[map][col][row].width  = 2 ;
			eventRect[map][col][row].height = 2 ;
			eventRect[map][col][row].eventRectDefualtX = eventRect[map][col][row].x ;
			eventRect[map][col][row].eventRectDefualtY = eventRect[map][col][row].y ;
			// We want it this small to make the event trigger when our player moves a bit further into the tile.
			
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
			if(row == gp.maxWorldRow) {
				row = 0;
				map++;
			}
			
		}
		
		
	}
		
	public void checkEvent () {
		  
		//Check if the player character is more than 1 tile away from last event. 
		int xDistance = Math.abs( gp.player.worldX - previousEventX);
		int yDistance = Math.abs( gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance) ;
		if(distance > gp.tileSize) {
			canTouchEvent = true ;
		}
		
		if(canTouchEvent ==  true) {
//			if(hit(27,16,"right") == true ) { teleport(27,16,gp.dialogueState)  ; }
			if(hit(0,27,16,"right") == true ) { damagePit(gp.dialogueState)  ; } //We will make a method called ( damagePit ) as it will be more efficient With  more events We add
			else if(hit(0,23,19,"any") == true ) { damagePit(gp.dialogueState)  ; } 
			else if(hit(0,23,12,"up")    == true ) { healingPool(gp.dialogueState) ; }
			else if(hit(0,10,39,"any") == true) {teleport(1,12,13);}
			else if(hit(1,12,13,"any") == true) {teleport(0,10,39);}
			else if(hit(1,12,9,"up") == true) {speak(gp.npc[1][0]);}
			}
	}
	
	public boolean hit (int map ,int col , int row , String reqDirection ) {
		
		
		boolean hit = false ;
		
		if (map==gp.currentMap) {
		 // Getting eventRect's SolidArea positions 
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x ;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y ;
		eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x ;
		eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y ;
		
		//Checking if player's solidArea is colliding with eventRect's solidArea.
		if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false /*We Add this condition if you want the event to happen only once */) {
			
			if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				
				hit = true ;
				//We will set some kind of merging and make it so if the event happen won't happen again until player move away from event rectangle by one tile distance to make sure event won't happen repeatedly 
				previousEventX = gp.player.worldX;
				previousEventY = gp.player.worldY;
				//Based on this information We check the distance between player character and event rectangle --> in the checkEvent method.
			}
			
		}
					
		//After checking the collision reset the solidArea a and y .
		gp.player.solidArea.x = gp.player.solidAreaDefaultX ;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY ;
		eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefualtX ;
		eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefualtY ;
		}
		  		
		
		return hit ;
	}
	
	
	public void teleport (int map,int col ,int row ) {
		gp.ui.currentDialogue = "Teleport!. \nYou found a hidden place." ;
		gp.gameState=gp.transitionState;
		tempMap=map;
		tempRow=row;
		tempCol=col;
		
		/*gp.player.worldX = gp.tileSize * col;
		gp.player.worldY = gp.tileSize * row;
		previousEventX = gp.player.worldX;
		previousEventY = gp.player.worldY;*/
		canTouchEvent = false;
		gp.playSE(13);
	}
	public void speak(Entity entity) {
		gp.gameState = gp.transitionState;
		gp.player.attackCanceled = true;
		entity.speak();
	}
	
	public void damagePit  (int gameState) {
    	
    	gp.gameState = gameState ;
    	gp.playSE(6);
    	gp.ui.currentDialogue = "You fall into Pit!" ;
    	gp.player.life -=1 ;
    	//eventRect[map][col][row].eventDone =true;       // if you want the event to happen only once
    	canTouchEvent = false;
    }
    public void healingPool (int gameState) {
    	if(gp.keyH.enterPressed == true) {
    		gp.gameState = gameState ;
    		gp.player.attackCanceled=true;
    		gp.playSE(2);
    		gp.ui.currentDialogue = "بعد ما شرب من البركة، حس بقوة جديدة بتمشى في جسمه وصحته رجعت زي الأول!" ;
    		gp.player.life = gp.player.maxLife ;
    		gp.player.mana =gp.player.maxMana;
    		gp.playSE(19);
    		//eventRect[map][col][row].eventDone =true;      // if you want the event to happen only once
    		gp.aSetter.setMonster();
    	}

    }
	
	
}
