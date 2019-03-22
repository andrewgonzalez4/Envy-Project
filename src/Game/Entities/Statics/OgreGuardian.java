package Game.Entities.Statics;
import java.awt.Graphics;
import java.awt.Rectangle;

import Main.Handler;
import Resources.Animation;
import Resources.Images;


public class OgreGuardian extends BaseStaticEntity{
	Rectangle collision;
	Animation stop;
	Animation pass;
	int width, height;
	public OgreGuardian(Handler handler, int xPosition, int yPosition) {
		super(handler, xPosition, yPosition);
		
		width = 70;
		height = 100;
		

		this.setXOffset(xPosition);
		this.setYOffset(yPosition);
		
		
		stop = new Animation(200, Images.ogre_front_stop);
		pass = new Animation(200, Images.ogre_front_pass);
		collision = new Rectangle();
	}
	
	@Override
	public void tick() {
		stop.tick();
	}
	
	@Override
	public void render(Graphics g) {
		
		if(handler.getEntityManager().getPlayer().questComplete = false) {
			g.drawImage(stop.getCurrentFrame(), (int)(handler.getXDisplacement() + xPosition),(int)( handler.getYDisplacement() + yPosition), width, height, null);
			collision = new Rectangle((int)(handler.getXDisplacement() + xPosition + 35), (int)(handler.getYDisplacement() + yPosition + 50), width/4, height/2);
		}
		
		else {
			
		}
		
	}
	
	@Override
	public Rectangle getCollision() {
		return collision;
	}
}
