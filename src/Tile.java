package BreakoutBallGame.src.src;
import java.awt.Color;

public class Tile extends Entity {
	double spacing=3;
	public Tile(double i, double j) {
		this.width = 80;
	    this.height = 40;
		this.x = 80+(width+spacing)*i;
        this.y = 40+(height+spacing)*j;
        this.color=Color.WHITE;
        this.speed=0; 
        this.setRect(x, y, width, height);
	}
	@Override
	public void draw() {
		Game.graphics.setColor(color);
		Game.graphics.fill(this);
	}
}
