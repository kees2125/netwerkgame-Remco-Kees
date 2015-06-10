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
		game.setState(state);
	}
	
	public enum State
	{
		menu, game, createGame, joinGame;
	}
}
