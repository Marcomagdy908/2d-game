package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Tile.Tile;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.OBJ_Axe;
import object.OBJ_Fireball;
import object.OBJ_Key;
import object.OBJ_Rock;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

public class Player extends Entity {
	
	
	KeyHandler KeyH;
	public final int screenX;
	public final int screenY;
	 //public int hasKey=0;
	public int standCounter=0;
	public boolean attackCanceled=false;
	
	
	
	public Player (GamePanel gp , KeyHandler KeyH)  {
		
		super(gp);
		this.KeyH=KeyH;
		screenX=gp.screenWidth/2-(gp.tileSize/2);
		screenY=gp.screenHeight/2-(gp.tileSize/2);
		solidArea = new Rectangle(12,16,24,24);
		solidAreaDefaultX= solidArea.x;
		solidAreaDefaultY =solidArea.y;
		
	//	attackArea.width = 36;
		//attackArea.height = 36;
		
		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
		setItems();
		
	}
	
	public void setDefaultValues() {
	
		worldX = gp.tileSize*23;
		worldY = gp.tileSize*21;
		speed = 4;
		direction = "down";   // any direction 
		
		// PLAYER STATUS
	     level=1;
	     maxLife = 6;              // --> one live = half a heart 
	     life = maxLife;// so 6 lifes = 3 heart
	     maxMana =4;
	     mana =maxMana;
	     ammo =10;
	     strength=1;
	     dexterity=1;
	     exp=0;
	     nextLevelExp=5;
	     coin=10;
	     
	   //  currentWeapon=new OBJ_Axe(gp);
         currentWeapon=new OBJ_Sword_Normal(gp);
	     currentShield=new OBJ_Shield_Wood(gp);
	     
	//     projectile =new OBJ_Fireball(gp);
	     projectile =new OBJ_Rock(gp);
	     attack=getAttack();
	     defense=getDefense();
	}
	
	public void setDefaultPositions() {
		worldX = gp.tileSize*23;
		worldY = gp.tileSize*21;
	  direction = "down";
	}
	public void restoreLifeAndMan() {
		life=maxLife;
		mana=maxMana;
		invincible=false;
	}	
	public void setItems() {
		inventory.clear();
		inventory.add(currentWeapon);
		inventory.add(currentShield);
		inventory.add(new OBJ_Key(gp));
		}	
	public int getAttack() {
		attackArea= currentWeapon.attackArea;
		return attack =strength * currentWeapon.attackValue;
	}
	
	public int getDefense(){
		
		return defense =dexterity *currentShield.defenseValue;
	}
	
	public void getPlayerImage () {
		
	    up1=setup("/player/boy_up_1", gp.tileSize, gp.tileSize);
	    up2 = setup("/player/boy_up_2", gp.tileSize, gp.tileSize);
	    down1 = setup("/player/boy_down_1", gp.tileSize, gp.tileSize);
	    down2 = setup("/player/boy_down_2", gp.tileSize, gp.tileSize);
	    right1 = setup("/player/boy_right_1", gp.tileSize, gp.tileSize);
	    right2 = setup("/player/boy_right_2", gp.tileSize, gp.tileSize);
	    left1 = setup("/player/boy_left_1", gp.tileSize, gp.tileSize);
	    left2 = setup("/player/boy_left_2", gp.tileSize, gp.tileSize);
		
		/*try {
			
		    up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
		    up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
		    down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
		    down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
		    right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
		    right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
		    left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
		    left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			
			
		} catch (IOException e) {
			 e.printStackTrace(); 
		}*/
	}
	
	// We make a new method in case if we have more than weapon (To switch Bet. weapons)
	public void getPlayerAttackImage() {
		if(currentWeapon.type==type_sword) {
		attackUp1 = setup("/player/boy_attack_up_1", gp.tileSize, gp.tileSize*2);
		attackUp2 = setup("/player/boy_attack_up_2", gp.tileSize, gp.tileSize*2);
		attackDown1 = setup("/player/boy_attack_down_1", gp.tileSize, gp.tileSize*2);
		attackDown2 = setup("/player/boy_attack_down_2", gp.tileSize, gp.tileSize*2);
		attackLeft1 = setup("/player/boy_attack_left_1", gp.tileSize*2, gp.tileSize);
		attackLeft2 = setup("/player/boy_attack_left_2", gp.tileSize*2, gp.tileSize);
		attackRight1 = setup("/player/boy_attack_right_1", gp.tileSize*2, gp.tileSize);
		attackRight2 = setup("/player/boy_attack_right_2", gp.tileSize*2, gp.tileSize);
		}
		if(currentWeapon.type==type_axe) {
			attackUp1 = setup("/player/boy_axe_up_1", gp.tileSize, gp.tileSize*2);
			attackUp2 = setup("/player/boy_axe_up_2", gp.tileSize, gp.tileSize*2);
			attackDown1 = setup("/player/boy_axe_down_1", gp.tileSize, gp.tileSize*2);
			attackDown2 = setup("/player/boy_axe_down_2", gp.tileSize, gp.tileSize*2);
			attackLeft1 = setup("/player/boy_axe_left_1", gp.tileSize*2, gp.tileSize);
			attackLeft2 = setup("/player/boy_axe_left_2", gp.tileSize*2, gp.tileSize);
			attackRight1 = setup("/player/boy_axe_right_1", gp.tileSize*2, gp.tileSize);
			attackRight2 = setup("/player/boy_axe_right_2", gp.tileSize*2, gp.tileSize);
			}
		
	}

	
	public void update () {
		
		if (attacking == true) {
			attacking();
		}
		
		else if (KeyH.upPressed == true || KeyH.downPressed == true
				||KeyH.leftPressed == true || KeyH.rightPressed == true || KeyH.enterPressed == true)    // this condition to stop the moving animation while not pressing any key
	 {
			if(KeyH.upPressed == true) {
				direction ="up";
			}
			else if (KeyH.downPressed == true) {
				direction ="down";
			}
			else if (KeyH.leftPressed == true) {
				direction ="left";
			}
			else if (KeyH.rightPressed == true) {
				direction ="right";
			}
			
			//check tile collision
			collisionOn=false;
			gp.cChecker.checkTile(this);
			
			//check object collision
		int objIndex =gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			//check NPC COLLISION
			int npcIndex=gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			//CHECK MONSTER COLLISION
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);
			
			//CHECK INTERACTIVE TILE COLLISION		
			 int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile ) ;
			
			
			//Check Event
			gp.eHandler.checkEvent();
			
			//It's better to put it After we checked everything
			//gp.keyH.enterPressed =false ;
			
			//if collision is false , player can move && enter pressed condition without it player can move when pressing enter
			if (collisionOn==false && KeyH.enterPressed == false) {
				switch(direction) {
				case"up": worldY -= speed; break;
				case"down": worldY += speed; break;
				case"right": worldX  += speed; break;
				case"left":worldX -= speed; break;
				
				}
			}
			if (KeyH.enterPressed==true && attackCanceled==false) {
				gp.playSE(7);
				attacking=true;
				spriteCounter=0;
			}
			attackCanceled=false;
			
			gp.keyH.enterPressed = false;
			
			spriteCounter++;		// This update method is called 60 time per sec (because it's inside the game loop) so in every frame  
		 if(spriteCounter > 12 )    // update() will be called and increase the spriteCounter	by 1 and when it hits 12 it changes the sprite 1 --> 2 or 2 --> 1 
		  {							// (player image changes in every 12 frames and will draw it on screen making the movement animation)
				if(spriteNum == 1 ) {
					spriteNum = 2;
				}
				else if (spriteNum == 2 ) {
					spriteNum = 1;
				}
				spriteCounter = 0 ;
		   
		  }
	   }
		else {standCounter++;
		if (standCounter==20) {
			spriteNum=1;
			standCounter=0;
			
		}
		
		}
		
		if(gp.keyH.shotKeyPressed ==true && projectile.alive==false&&shotavailabeCounter ==30
				&& projectile.haveResource(this)==true) {
			// set default coordinates ,direction and user
			projectile = new OBJ_Fireball(gp);
			projectile.set( worldX, worldY, direction ,true ,this);
			
			// subtract the cost 
			projectile.subtractResource(this);
			
			//add it to the list
			gp.projectileList.add(projectile);
			shotavailabeCounter =0;
			gp.playSE(10);
		}
		// This needs to be outside of key if statement!
		
		// Invincible Counter
		if (invincible == true) {
			invincibleCounter++;
			if (invincibleCounter > 60) { // 60 FBS
				invincible = false;
				invincibleCounter = 0;
			}
		}
		if(shotavailabeCounter <30) {
			shotavailabeCounter ++;
		}
		if(life>maxLife) {
			life =maxLife;
		}
		if(mana>maxMana) {
			mana =maxMana;
		}
		if (life<=0) {
			gp.gameState=gp.gameOverState;
			gp.ui.commandNum=-1;
			gp.stopMusic();
			gp.playSE(12);
		}
	}
	
	public void attacking() {
		
		spriteCounter++;
		
		// Show image 1 for 5 frames before attacking
		if (spriteCounter <= 5) {
			spriteNum = 1;
		}
		// Show image 2 for 5 to 25 frames while attacking
		if (spriteCounter > 5 && spriteCounter <= 25) {
			spriteNum = 2;
			// Save the current worldX, worldY and solidArea
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			// Adjust player's worldX/Y for the attackArea
			// Hit Detection
			switch(direction) {
			case "up": worldY -= attackArea.height; break;
			case "down": worldY += attackArea.height; break;
			case "right": worldX += attackArea.width; break;
			case "left": worldX -= attackArea.width; break;
			}
			// attackArea becomes solidArea
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			// Check monster collision with the updated worldX, worldY and solidArea
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			damageMonster(monsterIndex,attack);
			
			//CHECK HIT DETICTION
			int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile ) ;
			damageInteractiveTile(iTileIndex) ;
			// After checking collision, restore the original data
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}
		// Reset image after attacking
		if (spriteCounter > 25) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}
	
	public void pickUpObject(int i) {
		if(i!=999) {
			
			//pickup only items
			if(gp.obj[gp.currentMap][i].type ==type_pickupOnly) {
				gp.obj[gp.currentMap][i].use(this);
				gp.obj[gp.currentMap][i]=null;
				
			}
			
			// inventory items 
			else{
			String text ; 
			if(canObtainItem(gp.obj[gp.currentMap][i]) == true) {
				gp.playSE(1);
				text =" احلى " + gp.obj[gp.currentMap][i].name + "عليك";
			}
			else {
				text = "الجيب اتملى بس هل قلوبنا اتملت؟";
			}
			gp.ui.addMessage(text);
			gp.obj[gp.currentMap][i]= null;
			
		}
			}
			/*String objectName =gp.obj[i].name;
			switch(objectName) {
			case"Key"->{
				gp.playSE(1);
				hasKey++;
				gp.obj[i]= null;
				System.out.println("Key:"+hasKey);
				gp.ui.showMessage("أحلى مفتاح على أحلى لاعب");
			}
			case "Door" ->{
				if(hasKey>0) {
					gp.obj[i]=null;
					hasKey--;
					gp.playSE(3);
gp.ui.showMessage("خش برجلك اليمين");
				
				
				System.out.println("Key:"+hasKey);}
			else {
				gp.ui.showMessage("المفايتح ينجم");

			}}
			case "Boots" ->{
				gp.playSE(2);
				speed+=2;
				gp.obj[i]=null;
				gp.ui.showMessage("امسكوا الحصااااان");

			}
			case "Chest" ->{
				gp.ui.gameFinished =true;
				gp.stopMusic();
				gp.playSE(4);
			}
			}
		}
	}*/
	}
	
	public void damageMonster(int i,int attack) {
		
		if (i != 999) {
			
			if (gp.monster[gp.currentMap][i].invincible == false) { // Monster Receives damage only he is invincible
				
				gp.playSE(5);
				int damage =attack-gp.monster[gp.currentMap][i].defense;
				if(damage<0)
					damage=0;
				gp.monster[gp.currentMap][i].life -= damage;
				gp.ui.addMessage(damage+" damage!!");
				gp.monster[gp.currentMap][i].invincible = true;
				gp.monster[gp.currentMap][i].damageReaction();
				
				if (gp.monster[gp.currentMap][i].life <= 0) {
					gp.monster[gp.currentMap][i].dying = true;
					gp.ui.addMessage("Killed "+gp.monster[gp.currentMap][i].name+"!!");
					gp.ui.addMessage("Exp increased by "+gp.monster[gp.currentMap][i].exp+"!!");
					exp+=gp.monster[gp.currentMap][i].exp;
					checkLevelUp();
				}
			}
		}
	 }
	public void damageInteractiveTile(int i) {
		if( i != 999 && gp.iTile[gp.currentMap][i].destructible == true && gp.iTile[gp.currentMap][i].isCorrectItem(this) == true 
				&& gp.iTile[gp.currentMap][i].invincible == false) {
			gp.iTile[gp.currentMap][i].playSE();
			gp.iTile[gp.currentMap][i].life--;
			gp.iTile[gp.currentMap][i].invincible = true ;
			if(gp.iTile[gp.currentMap][i].life == 0 ) {
			gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm() ;
			}
		}
	}
 	public void checkLevelUp() {
		if(exp>=nextLevelExp) {
			level++;
			nextLevelExp*=2;
			maxLife+=2;
			strength++;
			dexterity++;
			attack=getAttack();
			defense=getDefense();
			gp.playSE(8);
			gp.gameState=gp.dialogueState;
			gp.ui.currentDialogue="YOU ARE LEVEL "+level+" NOW! \n "+" FEEL STRONGER ?!";
		}
		
	}
	
 	public void interactNPC(int i) {
		
		if (gp.keyH.enterPressed == true) {
			
			if(i!=999) {
				attackCanceled=true;
					System.out.println("حاسب ياعم"); 
					gp.gameState =gp.dialogueState;
					gp.npc[gp.currentMap][i].speak();		
			}	
		}
	}
	public void contactMonster(int i) {
		
		// PLAYER TOUCHES A MONSTER
		if (i != 999) {
			if (invincible == false &&gp.monster[gp.currentMap][i].dying==false ) { // Monster Receives damage only when he is invincible
				gp.playSE(6);
				int damage =gp.monster[gp.currentMap][i].attack-defense;
				if(damage<0)
					damage=0;
				life -= damage;
				invincible = true;
			}
		}
	}
	
	public void draw (Graphics2D g2) {
		// g2.setColor(Color.white); // Set drawing color for objects g2.fillRect(x, y,
		// gp.tileSize, gp.tileSize); // Draw a rectangle and fill it with a specified  color
		 
		 
		
		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		
		switch (direction) {
		case "up":
			if (attacking == false) {		
				if(spriteNum ==1 ) {image = up1;}
				if(spriteNum ==2 ) {image = up2;}
			}
			if (attacking == true) {
				tempScreenY = screenY - gp.tileSize;
				if(spriteNum ==1 ) {image = attackUp1;}
				if(spriteNum ==2 ) {image = attackUp2;}
			}
			break;
		case "down":
			if (attacking == false) {
				if(spriteNum == 1 ) {image = down1;}
				if(spriteNum == 2 ) {image = down2;}
			}
			if (attacking == true) {
				if(spriteNum == 1 ) {image = attackDown1;}
				if(spriteNum == 2 ) {image = attackDown2;}
			}
			break;		
		case "left":
			if (attacking == false) {
				if(spriteNum == 1 ) {image = left1;}
				if(spriteNum == 2 ) {image = left2;}
			}
			if (attacking == true) {
				tempScreenX = screenX - gp.tileSize;
				if(spriteNum == 1 ) {image = attackLeft1;}
				if(spriteNum == 2 ) {image = attackLeft2;}
			}
			break;
		case "right":
			if (attacking == false) {
				if(spriteNum == 1 ) {image = right1;}
				if(spriteNum == 2 ) {image = right2;}	
			}
			if (attacking == true) {
				if(spriteNum == 1 ) {image = attackRight1;}
				if(spriteNum == 2 ) {image = attackRight2;}
			}
			break;
			
		}
		
		if (invincible == true) {
			// We make it half transparent
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
		}
		
		g2.drawImage( image, tempScreenX, tempScreenY, null);  // drawImage() : draw an image on Screen
		
		// Reset alpha (make Player 0% transparent after taking damage
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		// DEBUG Text
//		g2.setFont(new Font("Arial", Font.PLAIN, 26));
//		g2.setColor(Color.white);
//		g2.drawString("Invincible:"+invincibleCounter, 10, 400);
	}	
	public void selectItem() {
		int itemIndex =gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);
		 if(itemIndex < inventory.size()) {
			 Entity selectedItem =inventory.get(itemIndex);
			 if(selectedItem.type ==type_sword||selectedItem.type==type_axe) {
				 
				 currentWeapon =selectedItem ;
				 attack =getAttack();
				 getPlayerAttackImage();
			 }
			 if(selectedItem.type ==type_shield) {
				 currentShield =selectedItem;
				 defense =getDefense();
			 }
			 if(selectedItem.type == type_consumable) {
				 
				 if (selectedItem.use(this) == true) {
					 if (selectedItem.amount > 1) {						 
						 selectedItem.amount--;
					 }
					 else {
						 inventory.remove(itemIndex);
					 }
				 }
			 }
		 }
	}
	public int searchItemInInventory(String itemName) {
		
		int itemIndex = 999;
		
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).name.equals(itemName)) {
				itemIndex = i;
				break;
			}
		}
		return itemIndex;
	}
	public boolean canObtainItem(Entity item) {
		
	    boolean canObtain = false;
	    
	    // 	CHAECK IF STACKABLE
	    if (item.stackable == true) {
	    	
	        int index = searchItemInInventory(item.name);
	        
	        if (index != 999) {
	            inventory.get(index).amount++;
	            canObtain = true;
	        } 
	        else { // New item so need to check vacancy
	            if (inventory.size() != maxInventorySize) {
	                inventory.add(item);
	                canObtain = true;
	            }
	        }
	    } 
	    else { // NOT STACKABLE so check vacancy
	        if (inventory.size() != maxInventorySize) {
	            inventory.add(item);
	            canObtain = true;
	        }
	    }

	    return canObtain;
	}
}









