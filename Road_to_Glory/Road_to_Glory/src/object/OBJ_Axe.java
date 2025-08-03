package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity {

	public OBJ_Axe(GamePanel gp) {
		super(gp);
		
		type=type_axe;

		name ="Woodcutter's Axe";
		down1 = setup("/objects/axe3",gp.tileSize, gp.tileSize);
		attackValue =2;
		attackArea.width =30 ;
		attackArea.height =30 ;
		description ="فأس عشان تضرب بيه \nو تقطع بيه الشجر \nبس المدى بتاعه اقصر";
		price = 30;
	}

}
