package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import control.KeyBoardHandler;
import model.AbstractModel;
import model.Game;
import model.Menu;

public class Window {
	
	public Window()
	{
		JFrame frame = new JFrame("pong");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new GamePanel(frame);
		panel.setBackground(Color.black);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}

