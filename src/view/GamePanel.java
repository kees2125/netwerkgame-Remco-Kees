package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.AbstractModel;
import model.Game;
import model.Menu;
import control.KeyBoardHandler;
import control.StateController;

public class GamePanel extends JPanel implements ActionListener 
{
	
	/**
	 * 
	 */
	private JFrame frame;
	private static final long serialVersionUID = 1L;
	private ArrayList<AbstractModel> models = new ArrayList<>();
	private Timer timer;
	private int abstractModel;
	private KeyBoardHandler keyHandler;
	private StateController controller;
	
	/* Constructor */
	public GamePanel(JFrame frame)
	{
		setPreferredSize(new Dimension(600,600));
		this.controller = new StateController(this);
		models.add(new Menu(frame, controller));
		models.add(new Game());
		models.get(1).init(1,2);
		this.timer = new Timer(1000/120, this);
		timer.start();
		this.abstractModel = 0; //set model
		this.keyHandler = new KeyBoardHandler(this);
		addKeyListener(keyHandler);
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	public void setState(int state)
	{
		this.abstractModel = state;
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		models.get(abstractModel).draw(g2);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		models.get(abstractModel).update();
	}
	
	public AbstractModel getState()
	{
		return models.get(abstractModel);
	}
	
	public enum State
	{
		menu, game, createGame, joinGame;
	}
}