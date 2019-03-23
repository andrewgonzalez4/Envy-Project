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
    
    private int imageWidth = 3443, imageHeight = 1350;
    //3443, 1350
    public final static int playerXSpawn = -880, playerYSpawn = 0;
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
        this.entityManager.AddEntity(handler.newEnemy(Images.questgiver_front,handler,464, 700,"InWorldState","Weird Entity","Town","QuestGiver",150,25,80,1,8,12,20,10,20,10,1,10,"None","Thunder",null,null)); //lvl 2 difficulty
        

        
    

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

        g.drawImage(Images.ScaledNw_Town, handler.getXInWorldDisplacement(), handler.getYInWorldDisplacement(), null);

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


        townWalls.add(new InWorldWalls(handler, 0, 0, 10, imageHeight, "Wall"));								// Left Border
        townWalls.add(new InWorldWalls(handler, 0, imageHeight, imageWidth, 50, "Wall"));					// Bottom LeftBorder (Left side relative to Entrance)
        townWalls.add(new InWorldWalls(handler, 0, 0, imageWidth, 10, "Wall"));								//
        townWalls.add(new InWorldWalls(handler, imageWidth , -100, 10, imageHeight, "Wall"));					// Right Border

        townWalls.add(new InWorldWalls(handler, 325, 100, 500, 600, "Wall"));									// Lava Lake
       // townWalls.add(new InWorldWalls(handler, 100, 1128, 1110, 115, "Wall"));									//

      // townWalls.add(new InWorldWalls(handler, 0, 140, 455, 345, "Wall"));									// TopLeft Side Wall with torch
       

        townWalls.add(new InWorldWalls(handler, /*imageWidth/3*/ 1500, imageHeight, 300, 50, "Wall"));					// Entrance

        townWalls.add(new InWorldWalls(handler, 2300, 0, 100, 100, "Start Exit"));							// Exit at Start
        townWalls.add(new InWorldWalls(handler,2415, 1300, 280, 100, "End Exit"));							// Exit at End
        townWalls.add(new InWorldWalls(handler, 0, 95, 100, 780, "Left Exit"));
        townWalls.add(new InWorldWalls(handler, imageWidth - 100, 595,100, 280, "Right Exit"));
        
        townWalls.add(new InWorldWalls(handler,444, 700, 100, 130, "Wall"));//x +40, y = -200
        
        townWalls.add(new InWorldWalls(handler,275, 645, 75, 90, "Wall"));//Boxes
        townWalls.add(new InWorldWalls(handler,815, 645, 75, 90, "Wall"));//^^
        townWalls.add(new InWorldWalls(handler,890, 660, 75, 90, "Wall"));//^^

        townWalls.add(new InWorldWalls(handler,195, 895, 125, 110, "Wall"));//Chest under house
        
        townWalls.add(new InWorldWalls(handler,1105, 750, 410, 350, "Vendor"));//Vendor
        
        townWalls.add(new InWorldWalls(handler,1705, 865, 410, 200, "Wall"));//Pond
        townWalls.add(new InWorldWalls(handler,1925, 795, 410, 600, "Wall"));//Tree1
        
        townWalls.add(new InWorldWalls(handler,1360, 100, 120, 130, "Wall"));//Barrels
        townWalls.add(new InWorldWalls(handler,1500, 200, 120, 175, "Old Man"));//Old Man
        townWalls.add(new InWorldWalls(handler,1640, 100, 490, 410, "Wall"));//Garden
        
        townWalls.add(new InWorldWalls(handler,imageWidth - 400, -200, 410, 600, "Wall"));//Tree2
        
        townWalls.add(new InWorldWalls(handler,2560, 470, 600, 60, "Wall"));//Bridge Lowerhandle
        townWalls.add(new InWorldWalls(handler,2670, 770, 410, 30, "Wall"));//Bridge Lowerhandle
        townWalls.add(new InWorldWalls(handler,2730, 845, 800, 600, "Wall"));//Lake
    }

    @Override
    public ArrayList<InWorldWalls> getWalls() {
        return townWalls;
    }
}




