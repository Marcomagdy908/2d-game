package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Blue extends Entity{
	public OBJ_Shield_Blue(GamePanel gp) {
		super(gp);
		
		type=type_shield;

		name="Blue Shield";
		down1 =setup("/objects/icon29",gp.tileSize,gp.tileSize);
		defenseValue=2;	
		description ="درع من البلاتينيوم";
		price = 50;
		}

}
