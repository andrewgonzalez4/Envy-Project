package Game.GameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Display.UI.ClickListlener;
import Display.UI.UIImageButton;
import Display.UI.UIManager;
import Game.Entities.Dynamics.BaseHostileEntity;
import Main.Handler;
import Resources.Images;

public class TalkState extends FightState {



	private boolean talking = false, exit = false;

	public TalkState(Handler handler ,BaseHostileEntity enemy, String prevState) {
		super(handler, enemy, prevState);
	}


	public void PlayerInput() {
		super.PlayerInput();

	}

	public void setUiManager() {

		uiManager = new UIManager(handler);
		uiManager.addObjects(new UIImageButton(handler.getWidth() * 22/60 - 128/2, 5*handler.getHeight()/6, 128, 64, Images.Attack, new ClickListlener() {
		
			public void onClick() {
				System.out.println("Talk");
				talking=true;
			}
		}));


		uiManager.addObjects(new UIImageButton(handler.getWidth() * 38/60 - 128/2, 5*handler.getHeight()/6, 128, 64, Images.Skill, new ClickListlener() {
			@Override
			public void onClick() {

				System.out.println("Exit");
				exit = true;
			}
		}));
	}

}
