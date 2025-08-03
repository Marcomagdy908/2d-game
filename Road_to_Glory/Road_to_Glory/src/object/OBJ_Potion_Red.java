package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity{

	GamePanel gp;

	public OBJ_Potion_Red(GamePanel gp) {
		super(gp);
		this.gp=gp;
		
		type=type_consumable;
		name = "Red Potion";
		 value =5;
		down1 =setup ("/objects/Health potion",gp.tileSize,gp.tileSize);
		description =" أزازة عشان تهيل بيها " + value ;
		price = 5;
		stackable = true;
	}
	public boolean use(Entity entity) {
		gp.gameState =gp.dialogueState;
		gp.ui.currentDialogue ="You drank the " + name +"\n your life has been recovered by"
				+ value ;
		entity.life+=value;
		if(gp.player.life>gp.player.maxLife) {
			gp.player.life =gp.player.maxLife;
			
		}
		gp.playSE(2);
		return true;
	}

}
