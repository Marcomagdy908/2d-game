package main;

import javax.swing.JFrame; // for making window

public class Main {
	
	public static JFrame window ;

	public static void main(String[] args) {
		
	    window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close when user press "x"
		window.setResizable(false); // cannot resize the window
		window.setTitle("Road to Glory");
		
		
		GamePanel gamepanel = new GamePanel();
		window.add(gamepanel); // adding gamepanel to this window
		
		gamepanel.config.loadConfig();
		if(gamepanel.fullScreenOn==true) {
			window.setUndecorated(true);   // To Hide The Top of Game Screen 
		}
		
		window.pack(); //  tells the JFrame (which is window in this case) to resize itself automatically to fit the preferred size and layout of all the components inside it (like your GamePanel).
		
		
		window.setLocationRelativeTo(null); // to make window displayed at the center of the screen
		window.setVisible(true); // to be able to see the window
		
		gamepanel.setupGame();
		gamepanel.startGameThread();
	}

}
