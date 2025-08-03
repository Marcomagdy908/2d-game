package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Wood extends Entity{

	public OBJ_Shield_Wood(GamePanel gp) {
		super(gp);
		
		type=type_shield;

		name="Shield Wood";
		down1 =setup("/objects/icon2",gp.tileSize,gp.tileSize);
		defenseValue=1;	
		description ="درع خشب";
		price = 25;
		}

}
