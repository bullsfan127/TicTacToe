/*
 * Tic-Toe-Toe, WIth an unbeatable A.I
 * Written by Jonathan Heavin
 */

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CreateGUI implements ActionListener {

	private JFrame frame = new JFrame("Tic-Tac-Toe");

	private JButton buttons[] = new JButton[9];
	private int turn = 0;
	private int winner = -1;
	private int[][] winningCombos = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, { 0, 4, 8 },
			{ 2, 4, 6 } };
	boolean singlePlayer = true;

	// SHould we check to play corners first
	private boolean corner = true;

	CreateGUI(boolean isSinglePlayer, Point position) {

		frame.setLocation(position);
		frame.setVisible(true);
		frame.setSize(501, 451);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(3, 3));

		singlePlayer = isSinglePlayer;

		// put all buttons in array, add action listener, add to frame
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
			frame.add(buttons[i]);
			buttons[i].addActionListener(this);
		}

	}

	// action listener for buttons
	public void actionPerformed(ActionEvent a) {

		String letter = "O";

		if (turn % 2 == 0) {
			letter = "X";
		}

		JButton pressed = (JButton) a.getSource();
		pressed.setText(letter);
		pressed.setEnabled(false);

		if (!singlePlayer && letter.equals("O")) {
			pressed.setBackground(Color.BLACK);
		} else
			pressed.setBackground(Color.RED);
		// increase turn counter
		turn++;

		if (!CheckforWin() && singlePlayer) {

			// let A.I play
			AI();
		}

	}

	/*
	 * A.I player, is unbeatable, plays defensively.
	 */
	private void AI() {

		boolean rand = false;
		// If first turn and opponent did not play in middle, play in middle
		if (turn == 1 && buttons[4].isEnabled()) {
			buttons[4].setText("O");
			buttons[4].setEnabled(false);
			buttons[4].setBackground(Color.BLACK);
			// dont play on corner next
			corner = false;

		}

		// If first turn and opponent played in middle, play in corner
		else if (turn == 1 && !buttons[4].isEnabled()) {

			buttons[8].setText("O");
			buttons[8].setEnabled(false);
			buttons[8].setBackground(Color.BLACK);

		}

		// check if we can win
		else if (WinOrLose("O")) {

		}

		// check if opponent can win
		else if (WinOrLose("X")) {

		}

		// dont play on corner first
		else if (!corner) {
			if (!CheckMiddles()) {

				if (!CheckCorners())
					rand = true;
			}

		}

		// play on coner first
		else if (corner) {
			if (!CheckCorners()) {
				if (!CheckMiddles())
					rand = true;
			}

		}

		// stopped all possible wins, pick random spot
		if (turn != 9 && rand) {
			Random r = new Random();
			int index = r.nextInt(9);

			while (!buttons[index].isEnabled()) {
				index = r.nextInt(9);

			}
			buttons[index].setText("O");
			buttons[index].setEnabled(false);
			buttons[index].setBackground(Color.BLACK);
		}

		// increase turn counter
		turn++;

		// see if game is over
		CheckforWin();
	}

	/*
	 * param letter - "X" or"O" checks if there are 2 squares in a row and
	 * either blocks the win if letter == "X" or takes the win if letter=="O"
	 */
	private boolean WinOrLose(String letter) {

		int count = 0;
		int[] enabled;

		for (int i = 0; i < winningCombos.length; i++) {
			enabled = new int[3];
			count = 0;

			for (int j = 0; j < winningCombos[i].length; j++) {
				int temp = winningCombos[i][j];
				if (!buttons[temp].isEnabled()) {
					if (buttons[temp].getText().equals(letter))
						count++;

				}
				enabled[j] = temp;
			}

			if (count == 2) {
				if (buttons[enabled[0]].isEnabled()) {
					buttons[enabled[0]].setText("O");
					buttons[enabled[0]].setEnabled(false);
					buttons[enabled[0]].setBackground(Color.BLACK);
					return true;
				} else if (buttons[enabled[1]].isEnabled()) {
					buttons[enabled[1]].setText("O");
					buttons[enabled[1]].setEnabled(false);
					buttons[enabled[1]].setBackground(Color.BLACK);
					return true;
				}

				else if (buttons[enabled[2]].isEnabled()) {
					buttons[enabled[2]].setText("O");
					buttons[enabled[2]].setEnabled(false);
					buttons[enabled[2]].setBackground(Color.BLACK);
					return true;
				}

			}
		}
		return false;
	}

	/*
	 * Checks if the game is over, diplays dialog if it is
	 */
	private boolean CheckforWin() {

		boolean win = false;
		// Horizontal
		if (buttons[0].getText() == buttons[1].getText() && buttons[2].getText() == buttons[0].getText()
				&& buttons[0].getText() != "") {
			win = true;
		}
		if (buttons[3].getText() == buttons[4].getText() && buttons[5].getText() == buttons[3].getText()
				&& buttons[3].getText() != "") {
			win = true;
		}
		if (buttons[6].getText() == buttons[7].getText() && buttons[8].getText() == buttons[6].getText()
				&& buttons[6].getText() != "") {
			win = true;
		}

		// Vertical
		if (buttons[0].getText() == buttons[3].getText() && buttons[6].getText() == buttons[0].getText()
				&& buttons[0].getText() != "") {
			win = true;
		}
		if (buttons[1].getText() == buttons[4].getText() && buttons[7].getText() == buttons[1].getText()
				&& buttons[1].getText() != "") {
			win = true;
		}
		if (buttons[2].getText() == buttons[5].getText() && buttons[8].getText() == buttons[2].getText()
				&& buttons[2].getText() != "") {
			win = true;
		}

		// Diagonal
		if (buttons[0].getText() == buttons[4].getText() && buttons[8].getText() == buttons[0].getText()
				&& buttons[0].getText() != "") {
			win = true;
		}
		if (buttons[2].getText() == buttons[4].getText() && buttons[6].getText() == buttons[2].getText()
				&& buttons[2].getText() != "") {
			win = true;
		}

		// if game was won, display dialog
		if (win) {
			if (turn % 2 == 0)
				winner = 2;

			else
				winner = 1;

			JOptionPane.showMessageDialog(null, "Player " + winner + " WINS!");
			ResetBoard();

		} else {
			// display TIE
			if (turn == 9 && winner == -1) {
				JOptionPane.showMessageDialog(null, "Tie Game!");
				win = true;
				ResetBoard();
			}

		}
		return win;

	}

	/*
	 * Get rid of gameboard, display main menu
	 */
	private void ResetBoard() {

		frame.dispose();
		new MainMenu(frame.getLocation());
	}

	private boolean CheckCorners() {

		// check corners
		if (buttons[8].isEnabled()) {
			buttons[8].setText("O");
			buttons[8].setEnabled(false);
			buttons[8].setBackground(Color.BLACK);
			return true;
		} else if (buttons[6].isEnabled()) {
			buttons[6].setText("O");
			buttons[6].setEnabled(false);
			buttons[6].setBackground(Color.BLACK);
			return true;
		}

		else if (buttons[2].isEnabled()) {
			buttons[2].setText("O");
			buttons[2].setEnabled(false);
			buttons[2].setBackground(Color.BLACK);
			return true;
		}

		else if (buttons[0].isEnabled()) {
			buttons[0].setText("O");
			buttons[0].setEnabled(false);
			buttons[0].setBackground(Color.BLACK);
			return true;
		}

		return false;
	}

	private boolean CheckMiddles() {

		//Make sure A.A plays in surrounded corners
		if (!buttons[3].isEnabled() && !buttons[1].isEnabled() && buttons[0].isEnabled()) {
			buttons[0].setText("O");
			buttons[0].setEnabled(false);
			buttons[0].setBackground(Color.BLACK);
			return true;
		}

		if (!buttons[3].isEnabled() && !buttons[7].isEnabled() && buttons[6].isEnabled()) {
			buttons[6].setText("O");
			buttons[6].setEnabled(false);
			buttons[6].setBackground(Color.BLACK);
			return true;
		}

		if (!buttons[1].isEnabled() && !buttons[5].isEnabled() && buttons[1].isEnabled()) {
			buttons[2].setText("O");
			buttons[2].setEnabled(false);
			buttons[2].setBackground(Color.BLACK);
			return true;
		}
		if (!buttons[5].isEnabled() && !buttons[7].isEnabled() && buttons[8].isEnabled()) {
			buttons[8].setText("O");
			buttons[8].setEnabled(false);
			buttons[8].setBackground(Color.BLACK);
			return true;
		}
		
		// Make sure that a row is not filled when placing a marker
		if (buttons[3].isEnabled() && (buttons[4].isEnabled() || buttons[5].isEnabled())) {
			buttons[3].setText("O");
			buttons[3].setEnabled(false);
			buttons[3].setBackground(Color.BLACK);
			return true;
		}

		else if (buttons[1].isEnabled() && (buttons[4].isEnabled() || buttons[7].isEnabled())) {
			buttons[1].setText("O");
			buttons[1].setEnabled(false);
			buttons[1].setBackground(Color.BLACK);
			return true;
		} else if (buttons[7].isEnabled() && (buttons[4].isEnabled() || buttons[1].isEnabled())) {
			buttons[7].setText("O");
			buttons[7].setEnabled(false);
			buttons[7].setBackground(Color.BLACK);
			return true;
		}

		else if (buttons[5].isEnabled() && (buttons[3].isEnabled() || buttons[4].isEnabled())) {
			buttons[5].setText("O");
			buttons[5].setEnabled(false);
			buttons[5].setBackground(Color.BLACK);
			return true;
		}
		return false;
	}

}
