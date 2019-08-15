package Gui;

import Game.*;

import javax.swing.*;
import java.awt.*;

/**
 * The entire game view
 */
public class GameView extends Canvas {
	private static final int CELL_SIZE = 18;
	private static final int GAME_WIDTH = 26;
	private static final int GAME_HEIGHT = 27;
	private static final int CANVAS_WIDTH = GAME_WIDTH * CELL_SIZE;
	private static final int CANVAS_HEIGHT = GAME_HEIGHT * CELL_SIZE;

	private Game game;
	private Window window;
	private Player currentPlayer;
	private GameController controller;

	/**
	 * Create the game view
	 *
	 * @param window
	 */
	public GameView(Window window, Game game, Player player) {
		this.game = game;
		this.window = window;
		this.currentPlayer = player;
		this.controller = new GameController(this);

		window.removeAll();
		window.setLayout(new BoxLayout(window, BoxLayout.Y_AXIS));

		// create game canvas
		setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		setMaximumSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		addMouseListener(controller);
		window.add(this);

		// footer panel sizes
		int footerSize = (window.getHeight() - CANVAS_HEIGHT) / 2;

		// button panel
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(new JButton("Roll Dice"));
		buttonPanel.add(new JButton("Accuse"));
		buttonPanel.setSize(new Dimension(window.getWidth(), footerSize));
		window.add(buttonPanel);

		// card panel
		JPanel cardPanel = new JPanel(new FlowLayout());
		cardPanel.add(new JButton("Card1"));
		cardPanel.add(new JButton("Card2"));
		cardPanel.setSize(new Dimension(window.getWidth(), footerSize));
		window.add(cardPanel);


		window.redraw();



	}

	/**
	 * Draw the current state of the board
	 *
	 * TODO: Improve the drawing to include images.
	 */
	public void paint(Graphics g) {
		Board board = game.getBoard();

		for (int y = 0; y < GAME_HEIGHT; y++) {
			for (int x = 0; x < GAME_WIDTH; x++) {
				Cell cell = board.getCell(x, y);
				cell.draw((Graphics2D) g, x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE);
			}
		}

		g.dispose();

		window.redraw();
	}
}
