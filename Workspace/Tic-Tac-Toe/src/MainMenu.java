import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
/*
 * Tic-Toe-Toe, WIth an unbeatable A.I
 * Written by Jonathan Heavin
 */

public class MainMenu {

	private JFrame frame = new JFrame("Tic-Tac-Toe");

	MainMenu(Point position) {

		frame.setLocation(position);
		frame.getContentPane().setBackground(Color.BLACK);

		frame.setVisible(true);
		frame.setSize(501, 451);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnSinglePlayer = new JButton("Single Player");
		btnSinglePlayer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

		
				new CreateGUI(true, frame.getLocation());
				frame.dispose();
			}
		});
		btnSinglePlayer.setBounds(171, 168, 145, 46);
		frame.getContentPane().add(btnSinglePlayer);

		JButton btnPlayers = new JButton("2 Players");
		btnPlayers.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				new CreateGUI(false, frame.getLocation());
				frame.dispose();
			}
		});
		btnPlayers.setBounds(171, 258, 145, 46);
		frame.getContentPane().add(btnPlayers);

		JLabel lblTictactoe = new JLabel("Tic-Tac-Toe");
		lblTictactoe.setForeground(Color.RED);
		lblTictactoe.setBounds(207, 40, 82, 14);
		frame.getContentPane().add(lblTictactoe);

		JLabel lblByJonathanHeavin = new JLabel("By: Jonathan Heavin");
		lblByJonathanHeavin.setForeground(Color.RED);
		lblByJonathanHeavin.setBounds(186, 66, 124, 14);
		frame.getContentPane().add(lblByJonathanHeavin);

	}

}
