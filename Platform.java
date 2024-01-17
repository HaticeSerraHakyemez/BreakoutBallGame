package BreakoutBallGame;
import java.awt.Color;

public class Platform extends Entity {
	public Platform(){
		this.x = 330;
        this.y = 545;
        this.width = 100;
        this.height = 7;
        this.color=Color.GREEN; 
        this.speed=0;
	}
	@Override
	public void draw() {
		Game.graphics.setColor(color);
		Game.graphics.fill(this);
	}
	@Override
	public void updatePosition() {
		this.x+=speed;
		this.setRect(x, y, width, height); 
		if(this.x<=0) this.x=0;
		if(this.x>=Game.GAME_WIDTH-this.width) this.x=Game.GAME_WIDTH-this.width;
		this.speed=0;
	}
}
