package Game.Entities.Dynamics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import Game.GameStates.FightState;
import Game.GameStates.State;
import Game.GameStates.TalkState;
import Main.GameSetUp;
import Main.Handler;
import Resources.Images;
import Resources.Animation;


public class QuestGiver extends BaseHostileEntity {

	private Rectangle detector;
	private boolean chasingPlayer;
	private static boolean e_pressed =false;
	Rectangle questgiver;
	int width, height;
	Animation meditate;
	public QuestGiver(Handler handler, int xPosition, int yPosition, String state, String name, String area, BufferedImage[] animFrames) {
		super(handler, xPosition, yPosition,state,name,area,animFrames);
		width = 30;
		height = 30;
		speed = 1;
		type="QuestGiver";
		this.setXOffset(xPosition);
		this.setYOffset(yPosition);

		meditate = new Animation(200, Images.questgiver_front);

		this.foundState = state;
		questgiver = new Rectangle();
	}


	@Override
	public void tick() {
		

		if(!Player.isinArea) {
			//super.tick();
			meditate.tick();
			
			 if (canMove) {
				 PlayerDetector();
				
			 }
		}
		
		
		
	}

	@Override
	public void render(Graphics g) {
		super.render(g);

		Graphics2D g2 = (Graphics2D) g;

		
		
		
        if(handler.getArea().equals(this.Area)) {
            if (!Player.checkInWorld) {
                questgiver = new Rectangle((int) (handler.getXDisplacement() + getXOffset()),
                        (int) (handler.getYDisplacement() + getYOffset()), 45, 45);

            } else {
                questgiver = new Rectangle((int) (handler.getXInWorldDisplacement() + getXOffset()),
                        (int) (handler.getYInWorldDisplacement() + getYOffset()), 70, 70);

            }

			g2.setColor(Color.black);
			if (GameSetUp.DEBUGMODE) {
				g2.draw(detector);
				g2.draw(nextArea);
			}

			g.drawImage(meditate.getCurrentFrame(),questgiver.x,questgiver.y,questgiver.width,questgiver.height,null);
			String words = new String();
			String instr = new String();
			
			if (chasingPlayer){	
				
				if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E) &&
						 handler.getEntityManager().getPlayer().questInProgress== false) {					

					 handler.getEntityManager().getPlayer().questInProgress = true;
					 words = "Come back after killing Jovan";
					 instr = "";
					 e_pressed = true;
				}
				else if (e_pressed == false) {
					words = "Hello Stranger, will you accept my quest?";
					instr = "Press E To Accept";
					
				}
				else if ( handler.getEntityManager().getPlayer().questInProgress == true&&
						handler.getEntityManager().getPlayer().questComplete == false){
					 words = "Come back after killing Jovan";
					 instr = "";
				}
				else if (handler.getEntityManager().getPlayer().questComplete == true) {
					words= "Quest Complete. Good Job.";
					instr= "";
//					handler.getEntityManager().getPlayer().setSkill("Freeze");
				}
				g2.drawString(words,questgiver.x,questgiver.y);
				g2.drawString(instr,questgiver.x -10,questgiver.y +100);
			}
			if (questgiver.intersects(handler.getEntityManager().getPlayer().getCollision())) {
				handler.getEntityManager().getPlayer().facing = "Left";
				
				 if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {					

					 handler.getEntityManager().getPlayer().questInProgress = true;
					 words = "Come back after killing Sergio";
					 g2.drawString(words,questgiver.x,questgiver.y);
//					if (Player.questInProgress == false) {
//						Player.questInProgress = true;
//						System.out.println("Changed");
//						words = "Come back after killing Sergio";
//						
//					}
					if ( handler.getEntityManager().getPlayer().questComplete == true) {
						words = "Good Job";
						handler.getEntityManager().getPlayer().setSkill("Freeze");
					}
				}
				
				
			}
		}
	}

	private void PlayerDetector() {

		detector = this.getCollision();

		detector.setRect(detector.getX() - detector.getWidth() * 4.5, detector.getY() - detector.getHeight() * 4.5,
				detector.getWidth() * 10, detector.getHeight() * 10);

		chasingPlayer = handler.getEntityManager().getPlayer().getCollision().intersects(detector);

		
	}
	
	@Override
	public Rectangle getCollision() {
		return questgiver;
	}

	//GETTERS AND SETTERS FOR FIGHT STATS

	double health=100,mana=25,xp=0l,lvl=1,defense=12,str=8,intl=20, mr = 10,cons=20,acc=10,evs=2,initiative=10, maxHealth = 100;
	String Class = "none",skill = "none";
	String[] buffs = {},debuffs = {};

	@Override
	public double getMaxHealth() {
		return maxHealth;
	}
	@Override
	public double getMaxMana() {
		return 100;
	}
	@Override
	public double getHealth() {
		return health;
	}

	@Override
	public void setHealth(double health) {
		this.health=health;
	}

	public void setMaxHealth(double maxHP) {
		this.maxHealth=maxHP;
	}

	@Override
	public double getMana() {
		return mana;
	}

	@Override
	public void setMana(double mana) {
		this.mana=mana;
	}

	@Override
	public double getXp() {
		return xp;
	}

	@Override
	public void setXp(double xp) {
		this.xp=xp;
	}

	@Override
	public double getLvl() {
		return lvl;
	}

	@Override
	public void setLvl(double lvl) {
		this.lvl=lvl;
	}

	@Override
	public double getDefense() {
		return defense;
	}

	@Override
	public void setDefense(double defense) {
		this.defense=defense;
	}

	@Override
	public double getStr() {
		return this.str;
	}

	@Override
	public void setStr(double str) {
		this.str=str;
	}

	@Override
	public double getIntl() {
		return intl;
	}

	@Override
	public void setIntl(double intl) {
		this.intl=intl;
	}

	@Override
	public double getMr() {
		return mr;
	}

	@Override
	public void setMr(double mr) {
		this.mr = mr;	
	}

	@Override
	public double getCons() {
		return cons;
	}

	@Override
	public void setCons(double cons) {
		this.cons=cons;
	}

	@Override
	public double getAcc() {
		return this.acc;
	}

	@Override
	public void setAcc(double acc) {
		this.acc=acc;
	}

	@Override
	public double getEvs() {
		return evs;
	}

	@Override
	public void setEvs(double evs) {
		this.evs=evs;
	}

	@Override
	public double getInitiative() {
		return initiative;
	}

	@Override
	public void setInitiative(double initiative) {
		this.initiative=initiative;
	}

	@Override
	public String getclass() {
		return Class;
	}

	@Override
	public void setClass(String aClass) {
		this.Class=aClass;
	}

	@Override
	public String getSkill() {
		return this.skill;
	}

	@Override
	public void setSkill(String skill) {
		this.skill=skill;
	}

	@Override
	public String[] getBuffs() {
		return buffs;
	}

	@Override
	public void setBuffs(String[] buffs) {
		this.buffs=buffs;
	}

	@Override
	public String[] getDebuffs() {
		return debuffs;
	}

	@Override
	public void setDebuffs(String[] debuffs) {
		this.debuffs=debuffs;
	}

	public void lvlAdjust() {
		if(lvl > 1) {
			health += 10 + 5*(lvl-1);
			maxHealth = health;
			mana += 10 + 5*(lvl-1);
			if(mana > 100)
				mana = 100;
			str += 1 + 1 *(int)((lvl - 1)/2);
			acc += 1 + 1 *(int)((lvl - 1)/2);
			defense += 1 + 1 *(int)((lvl - 1)/2);
			intl += 1 + 1 *(int)((lvl - 1)/2);
			mr += 1 + 1 *(int)((lvl - 1)/2);
			cons += 1 + 1 *(int)((lvl - 1)/2);
			if(lvl%4 ==0)
				evs += (lvl -lvl%4)/4;
			xp += 20 *(lvl);
		}
	}

}
