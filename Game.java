package BreakoutBallGame;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

public class Game {
	
	public static Graphics2D graphics;
	public static ArrayList<Tile> tiles;
	public static Ball ball;
	public static Platform platform;
	public static JFrame frame;
	public static int score;
	public static final int GAME_WIDTH=730; 
	public static final int GAME_HEIGHT=580;
	public static boolean GAME_OVER;
	public static boolean GAME_START;
	public static JPanel startScreen,gameScreen,endScreen,container;
	public static CardLayout cl;
	
	 public Game() {
		 
		 score =0;
		 GAME_OVER=false;
		 GAME_START=false;
		 
		 tiles=new ArrayList<Tile>();
		 frame = new JFrame("Breakout Ball Game");
		 cl=new CardLayout();
		 container=new JPanel(cl);
		 
		 startScreen=new JPanel() {
	           @Override
	           protected void paintComponent(Graphics g) {
	          	 Game.graphics = (Graphics2D) g;
	          	 graphics.setColor(Color.green);
	          	 graphics.setFont(new Font("Serif", Font.BOLD, 25));
	          	 graphics.drawString("PRESS ENTER TO START THE GAME", 150,200);
	           }
	           
	       };
     
     startScreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"),
    		 "start");
     startScreen.getActionMap().put("start",new AbstractAction() {
         @Override
         public void actionPerformed(ActionEvent e) {
        	 GAME_START=true;
         }
     });
     
     init();
     
   gameScreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0),"left");
   gameScreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"),"right");
   gameScreen.getActionMap().put("left",new AbstractAction() {
       @Override
       public void actionPerformed(ActionEvent e) {
    	   platform.setSpeed(-18);
       }
   });
   gameScreen.getActionMap().put("right",new AbstractAction() {
	   @Override
	   public void actionPerformed(ActionEvent e) {
		   platform.setSpeed(18);
	   }
   }); 	 
   
   endScreen=new JPanel() {
       @Override
       protected void paintComponent(Graphics g) {
      	 Game.graphics = (Graphics2D) g;
      	 graphics.setColor(Color.YELLOW);
      	 graphics.setFont(new Font("Serif", Font.BOLD, 30));
      	 graphics.drawString("YOUR SCORE IS: "+score, 200,100);
      	 graphics.drawString("PRESS ENTER TO TRY AGAIN",130,200);
       }
       
   };
   endScreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"),"again");
   endScreen.getActionMap().put("again",new AbstractAction() {
       @Override
       public void actionPerformed(ActionEvent e) {
      	 GAME_START=true;
      	 init();
         score=0;
       }
   });
   
   container.add(startScreen,"start");
   container.add(gameScreen,"game");
   container.add(endScreen,"end");
   frame.add(container);
   cl.show(container, "start");
   frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
   frame.setBounds(0,0,750,600);
   frame.setBackground(Color.BLACK);
   frame.setResizable(false);
   frame.setVisible(true);
	 }

	 public static void init() {
	     for(int i=0;i<7;i++) {
	   	  for(int j=0;j<3;j++) {
	   		  Game.tiles.add(new Tile(i,j));
	   	  }
	     }
	     
	     platform = new Platform();
	     ball = new Ball();   
		 
	     gameScreen=new JPanel() {
		       @Override
		       protected void paintComponent(Graphics g) {
		      	 Game.graphics = (Graphics2D) g;
		      	 graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		         for(Tile obj: Game.tiles) {
		       	  if(obj!=null)  {
		       		  obj.updatePosition();
		       		  obj.draw();
		       	  }
		          }  
		         ball.updatePosition();
		         ball.draw();
		         platform.updatePosition();
		         platform.draw();
		       }
		       
		   };
	 }
	 
	 public static void updateScreen() {
		 if(GAME_OVER) {
			 GAME_OVER=false;
			 tiles.clear();//!!!
			 //tiles=new ArrayList<Tile>();
			 cl.show(container, "end");
		 }
		 if(GAME_START) {
			 GAME_START=false;
			 cl.show(container, "game");
		 }
         frame.revalidate();
         frame.repaint();
         frame.setVisible(true);
	 }
}




