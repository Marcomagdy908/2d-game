package entity;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import main.GamePanel;
import main.Sound;

public class NPC_OldMan extends Entity {
    private Sound sound = new Sound();
    private int currentDialogueIndex = 14;
    private Clip currentClip;
    
    public NPC_OldMan(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
        
        // Register the GamePanel as a key listener if it isn't already
        if (!gp.isFocusable()) {
            gp.setFocusable(true);
        }
        
        System.out.println("NPC_OldMan initialized");
    }
    
    public void getImage() {
        up1 = setup("/npc/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/oldman_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/oldman_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/oldman_right_2", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/oldman_left_2", gp.tileSize, gp.tileSize);
    }
    
    public void setDialogue() {
        dialogues[14] = "ياااااه... إنت جيت! عارف إني مستنيك من زمان؟ الدنيا دي ملهاش أمان، واللي يدخلها لازم يبقى قلبه جامد!";
        dialogues[15] = "كان عندي عصاية سحرية بتخلي الحجر يتكلم، بس واحد حرامي غشيم سرقها... ولحد دلوقتي الحجر ساكت!";
        dialogues[16] = "أقولك إيه، ما تصدقش العفاريت اللي بتلف في السوق. دول هيشتتوك عن هدفك، وهدفك إيه؟ إني ألاقيلك العصاية!";
        dialogues[17] = "هاه! افتكرتني راجل عجوز وخلاص؟ ما تنساش... اللي يتكلم مع الحجر، الحجر هيحكيله أسرار كتير... بس الأول، رجعلي العصاية!";
    }
    
    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) direction = "up";
            else if (i <= 50) direction = "down";
            else if (i <= 75) direction = "left";
            else direction = "right";
            actionLockCounter = 0;
        }
    }
    
    public void speak() {
        System.out.println("speak() called with index: " + currentDialogueIndex);
        if (dialogues[currentDialogueIndex] != null) {
            gp.ui.currentDialogue = dialogues[currentDialogueIndex];
            playVoice(currentDialogueIndex);
        }
        
        // Advance dialogue index for next interaction
        if (currentDialogueIndex < 17) {
            currentDialogueIndex++;
        } else {
            currentDialogueIndex = 14; // Reset to beginning
        }
    }
    
    public void playVoice(int index) {
        System.out.println("playVoice() called with index: " + index);
        stopCurrentAudio();
        if (index >= 14 && index <= 17) {
            sound.setFile(index);
            sound.play();
            currentClip = sound.clip;
        }
    }
    
    public void stopCurrentAudio() {
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
        }
    }
    
    // No need for KeyListener implementation here
}