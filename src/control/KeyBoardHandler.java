package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.GamePanel;

public class KeyBoardHandler implements KeyListener{
	
	private GamePanel window;

	public KeyBoardHandler(GamePanel window)
	{
		this.window = window;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		window.getState().keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		window.getState().keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
