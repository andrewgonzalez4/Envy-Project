package Game.Entities.Statics;
import java.awt.Graphics;
import java.awt.Rectangle;

import Main.Handler;
import Resources.Animation;
import Resources.Images;


public class QuestGiver extends BaseStaticEntity{
	Rectangle collision;
	Animation meditate;
	int width, height;
	public QuestGiver(Handler handler, int xPosition, int yPosition) {
		super(handler, xPosition, yPosition);
		
		width = 2000;
		height =4000;
		

		this.setXOffset(xPosition);
		this.setYOffset(yPosition);
		
		
		meditate = new Animation(200, Images.questgiver_front);
		
		collision = new Rectangle();
	}
	
	@Override
	public void tick() {
		meditate.tick();
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(meditate.getCurrentFrame(), (int)(handler.getXDisplacement() + xPosition),(int)( handler.getYDisplacement() + yPosition), width, height, null);
		collision = new Rectangle((int)(handler.getXDisplacement() + xPosition + 35), (int)(handler.getYDisplacement() + yPosition + 50), width/4, height/2);
	}
	
	@Override
	public Rectangle getCollision() {
		return collision;
	}
}
