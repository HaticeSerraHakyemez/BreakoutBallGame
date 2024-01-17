package BreakoutBallGame.src.src;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import javax.swing.Timer;

public class mainClass { 
    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
    	
    	 new Game();
   	 
		 int delay = 10;  
		  ActionListener al = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		    	  Game.updateScreen(); 
		      }
		  };
		  new Timer(delay, al).start();
    }
}
