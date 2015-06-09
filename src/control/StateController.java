package control;

import view.GamePanel;

public class StateController {
	
	private GamePanel game;
	
	public StateController(GamePanel game)
	{
		this.game = game;
	}
	
	public void switchState(int state)
	{
		switch(state)
		{
		case 0:
			game.setState(0);
			break;
		case 1:
			game.setState(1);
			break;
		case 2:
			
		}
	}

}
