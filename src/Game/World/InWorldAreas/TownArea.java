package Game.World.InWorldAreas;

import Main.GameSetUp;
import Main.Handler;
import Resources.Images;
import Resources.Animation;
import java.awt.*;
import java.util.ArrayList;

import Game.Entities.EntityManager;
//import Game.Entities.Dynamics.QuestGiver;
import Game.Entities.Dynamics.QuestGiver;
import Game.Entities.Statics.LightStatue;
import Game.World.Walls;

public class TownArea extends BaseArea {

    Rectangle exit;
    Rectangle playerRect;
    public static boolean isInTown = false;
    
    private int imageWidth = 3680, imageHeight = 4000;
    public final static int playerXSpawn = -880, playerYSpawn = -3180;
    //1800, 3900,

    private Rectangle background = new Rectangle(3000, 3000);
    
    private Animation meditate = new Animation (200, Images.questgiver_front);

    public static ArrayList<InWorldWalls> townWalls;

    public TownArea(Handler handler, EntityManager entityManager) {
        super(handler, entityManager);
        name="Town";
        handler.setXInWorldDisplacement(playerXSpawn);
        handler.setYInWorldDisplacement(playerYSpawn);

        playerRect = new Rectangle((int) handler.getWidth() / 2 - 5, (int) (handler.getHeight() / 2) + 300, 70, 70);

        this.entityManager = entityManager;


        
        // 184, 1640
        this.entityManager.AddEntity(handler.newEnemy(Images.questgiver_front,handler,184, 1640,"InWorldState","Weird Entity","Town","QuestGiver",150,25,80,1,8,12,20,10,20,10,1,10,"None","Thunder",null,null)); //lvl 2 difficulty
        this.entityManager.AddEntity(new LightStatue (handler, 184, 1640));
        

        
    

        townWalls = new ArrayList<>();
        AddWalls();

    }

    public void tick() {
        super.tick();

        for (Walls w : townWalls) {
            w.tick();
        }
        if(!GameSetUp.LOADING) {
            entityManager.tick();
        }

    }

    @Override
    public void render(Graphics g) {
        super.render(g);


        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.black);
        g2.fill(background);

        g.drawImage(Images.ScaledTown, handler.getXInWorldDisplacement(), handler.getYInWorldDisplacement(), null);

        if (GameSetUp.DEBUGMODE) {
            for (Walls w : townWalls) {

                if (w.getType().equals("Wall"))
                    g2.setColor(Color.black);
                else
                    g2.setColor(Color.PINK);

                w.render(g2);
            }
        }


        entityManager.render(g);

    }

    private void AddWalls() {


        townWalls.add(new InWorldWalls(handler, 100, 0, 10, imageHeight, "Wall"));								// Left Border
        townWalls.add(new InWorldWalls(handler, 0, imageHeight, imageWidth, 50, "Wall"));					// Bottom LeftBorder (Left side relative to Entrance)
//        townWalls.add(new InWorldWalls(handler, imageWidth/2-350, imageHeight-100, imageWidth/4, 50, "Wall"));	// Bottom RightBorder (Right side relative to Entrance)
        townWalls.add(new InWorldWalls(handler, 0, 0, imageWidth, 10, "Wall"));								//
        townWalls.add(new InWorldWalls(handler, imageWidth , 0, 10, imageHeight, "Wall"));					// Right Border


 

        townWalls.add(new InWorldWalls(handler, 216, 50, 1005, 1040, "Wall"));									// Lava Lake
 //       townWalls.add(new InWorldWalls(handler, 1246, 518, 300, 415, "Wall"));									//
        townWalls.add(new InWorldWalls(handler, 100, 1128, 1110, 115, "Wall"));									//
 //       townWalls.add(new InWorldWalls(handler, 184, 1640, 100, 100, "Wall"));									// Lava Hole



        townWalls.add(new InWorldWalls(handler, 176, 140, 455, 345, "Wall"));									// TopLeft Side Wall with torch
       

        townWalls.add(new InWorldWalls(handler, /*imageWidth/3*/ 1500, imageHeight, 300, 50, "Wall"));					// Entrance

        townWalls.add(new InWorldWalls(handler, 1700, 0, 280, 100, "Start Exit"));							// Exit at Start
        townWalls.add(new InWorldWalls(handler, 1730, 3900, 280, 100, "End Exit"));							// Exit at End
        townWalls.add(new InWorldWalls(handler, 0, 1800, 100, 280, "Left Exit"));
        townWalls.add(new InWorldWalls(handler, imageWidth - 100, 1800,100, 280, "Right Exit"));
        townWalls.add(new InWorldWalls(handler,164, 1640, 100, 120, "Wall"));
        

    }

    @Override
    public ArrayList<InWorldWalls> getWalls() {
        return townWalls;
    }
}




