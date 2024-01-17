package BreakoutBallGame;
import java.awt.Color;

public class Ball extends Entity {
	double randomAngle;
	double xDirection;
	double yDirection;
	double timeLastCollision=0;
	public Ball() {
		this.x=370;
		this.y=300;
		this.width=17;
		this.height=17;
		this.color=Color.YELLOW;
		this.speed=3;
		
		double rand=Math.random();
		if(rand<0.5) randomAngle=Math.toRadians(25+Math.random() * 40);
		else randomAngle=Math.toRadians(115+Math.random() * 25);
		
		this.xDirection=Math.cos(randomAngle);
		this.yDirection=Math.sin(randomAngle);
		
		this.setSize((int)width, (int)height);
	}
	
	@Override
	public void draw() {
		Game.graphics.setColor(color);
		Game.graphics.fillOval((int)(x),(int)(y),(int)width,(int)height);
	}
	@Override
	public void updatePosition() {
		if(this.y>=Game.GAME_HEIGHT) {
			Game.GAME_OVER=true;
			return;
		}
		
		if(System.currentTimeMillis()-timeLastCollision>40) {//40ms--yan yana olan iki karoya ortadan çarpıp kırmaması için
			for(Tile obj: Game.tiles) {
				if(obj.intersects(this)) {
					Game.score+=5;
					if(y>obj.y&&y<obj.y+obj.height&&y+height>obj.y&&y+height<obj.y+obj.height) {
						xDirection=-xDirection;
					}
					else yDirection=-yDirection;
					Game.tiles.remove(obj);
					if(Game.tiles.isEmpty()) Game.GAME_OVER=true;
					timeLastCollision=System.currentTimeMillis();
					break;
				}
			}
		}
		
		if(!(this.y>=Game.platform.y+Game.platform.height-this.height)) {
			if(Game.platform.intersects(this)) {
				yDirection=-yDirection;
			}
		}
		
		if(x>=Game.GAME_WIDTH-width||x<=0) xDirection=-xDirection;
		if(y<=0) yDirection=-yDirection;
		
		this.x+=speed*xDirection;
		this.y+=speed*yDirection;
		this.setRect(x, y, width, height);
	}

}

