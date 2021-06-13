package war;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Voina extends JFrame {
//	create needed fields and panels/buttons etc.
	JPanel main;
	JButton test;
	JButton begin;
	JLabel southCall;
	JLabel eastCall;
	JLabel northCall;
	JLabel westCall;

	String[] playerNames;

	int score1 = 0;
	int score2 = 0;

	int p1wins = 0;
	int p2wins = 0;
	String[] deck = { "2♣", "3♣", "4♣", "5♣", "6♣", "7♣", "8♣", "9♣", "10♣", "J♣", "Q♣", "K♣", "A♣", 
					  "2♢", "3♢", "4♢", "5♢", "6♢", "7♢", "8♢", "9♢", "10♢", "J♢", "Q♢", "K♢", "A♢",
					  "2♡", "3♡", "4♡", "5♡", "6♡", "7♡", "8♡", "9♡", "10♡", "J♡", "Q♡", "K♡", "A♡", 
					  "2♠", "3♠", "4♠", "5♠", "5♠", "6♠", "7♠", "8♠", "9♠", "10♠", "J♠", "Q♠", "K♠", "A♠" };

	int row1 = (int) (Math.random() * 4);
	int col1 = (int) (Math.random() * 13);
	int row2 = (int) (Math.random() * 4);
	int col2 = (int) (Math.random() * 13);
	int row3 = (int) (Math.random() * 4);
	int col3 = (int) (Math.random() * 13);
	int row4 = (int) (Math.random() * 4);
	int col4 = (int) (Math.random() * 13);

	public static final Color LIGHT_Green = new Color(0, 255, 91);
	public static final Color LIGHT_Blue = new Color(81, 153, 255);
	public static final Color LIGHT_Red = new Color(255, 102, 102);

	Queue<String> teste1 = new LinkedList<>();
	Queue<String> teste2 = new LinkedList<>();
	String card1;
	String card2;
	String card11;
	String card12;
	String card13;
	String card21;
	String card22;
	String card23;
	int warplayer1 = 0;
	int warplayer2 = 0;
	String score;
	int exceptionTesteSize;
	JLabel scoreSheet = new JLabel();
	File file = new File("src/war/failat.txt");
	String randomText;
	JTextField txt = new JTextField(randomText);
	String size1;
	String size2;

	public Voina() {

		super("Voina");
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Stack<String> teste = new Stack<String>();
		for (int i = 0; i < 52; i++) {
			teste.add(deck[i]);
		}

		main = new JPanel();
		add(main);
		main.setLayout(null);
		main.setBackground(LIGHT_Green);

		JButton deal = new JButton("Deal");
		deal.setBounds(600, 600, 100, 40);
		main.add(deal);
		deal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				empty the score file and deal the cards
				file.delete();
				deal.setVisible(false);
				Collections.shuffle(teste);
				for (int i = 0; i < 52; i++) {
					if (i % 2 == 1) {
						teste1.add(teste.pop());
					} else {
						teste2.add(teste.pop());
					}

				}
				System.out.println(teste1);
				System.out.println(teste2);
			}
		});

		JLabel table = new JLabel("Score");
		table.setBounds(360, 50, 100, 50);
		main.add(table);

		scoreSheet.setBounds(310, 100, 200, 50);
		main.add(scoreSheet);
		
		JLabel winner = new JLabel();
		winner.setBounds(350, 0, 100, 50);
		main.add(winner);
		
		JLabel player1card1 = new JLabel();
		main.add(player1card1);
		player1card1.setBounds(200, 200, 100, 50);
		player1card1.setFont(new Font("Default", Font.PLAIN, 50));
		
		JLabel player2card1 = new JLabel();
		main.add(player2card1);
		player2card1.setBounds(500, 650, 100, 50);
		player2card1.setFont(new Font("Default", Font.PLAIN, 50));
		
		JLabel p1teste = new JLabel();
		main.add(p1teste);
		p1teste.setBounds(300, 200, 100, 50);
		
		JLabel p2teste = new JLabel(size2);
		main.add(p2teste);
		p2teste.setBounds(400, 650, 100, 50);
		
		JLabel warCards = new JLabel();
		main.add(warCards);
		warCards.setBounds(310, 350, 180, 100);
		warCards.setFont(new Font("Default", Font.PLAIN, 40));
		
		JLabel warCardsp1 = new JLabel();
		main.add(warCardsp1);
		warCardsp1.setBounds(200, 250, 200, 100);
		warCardsp1.setFont(new Font("Default", Font.PLAIN, 20));
		
		JLabel warCardsp2 = new JLabel();
		main.add(warCardsp2);
		warCardsp2.setBounds(400, 550, 200, 100);
		warCardsp2.setFont(new Font("Default", Font.PLAIN, 20));
 		
		JButton play = new JButton("Play");
		main.add(play);
		play.setBounds(600, 650, 100, 40);
		play.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				warCards.setText("");
				warCardsp1.setText("");
				warCardsp2.setText("");
//				check if somebody is winning
				if(teste1.size()==0) {
					winner.setText("Player 2 wins!");
					play.setVisible(false);
				} else if(teste2.size() == 0) {
					winner.setText("Player 1 wins!");
					play.setVisible(false);
				} else {
//					check who has the better card
					card1 = teste1.remove();
					player1card1.setText(card1);
					card2 = teste2.remove();
					player2card1.setText(card2);
				if (cardValue(card1) > cardValue(card2)) {
					teste1.add(card1);
					teste1.add(card2);
					p1wins++;
					if(p1wins >= 100) {
						winner.setText("Player 1 wins!");
						play.setVisible(false);
					}
				} else if (cardValue(card1) < cardValue(card2)) {
					teste2.add(card1);
					teste2.add(card2);
					p2wins++;
					if(p2wins >= 100) {
						winner.setText("Player 2 wins!");
						play.setVisible(false);
					}
				} else if (cardValue(card1) == cardValue(card2)) {
//					check if there are enough cards for a war
					if (teste1.size() < 3 || teste2.size() < 3) {
						if (teste1.size() < teste2.size()) {
							exceptionTesteSize = teste1.size();
							winner.setText("PLAYER 2 WINS");
						} else if (teste1.size() > teste2.size()) {
							exceptionTesteSize = teste2.size();
							winner.setText("PLAYER 1 WINS");
						} else if (teste1.size() == teste2.size()) {
							exceptionTesteSize = teste2.size();
							winner.setText("DRAW");
						}
						try {
							exc(exceptionTesteSize);
						} catch (NotEnoughCardsException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
//					play the war
					warCards.setText(card1 + "VS" + card2);
					card11 = teste1.remove();
					card12 = teste1.remove();
					card13 = teste1.remove();
					card21 = teste2.remove();
					card22 = teste2.remove();
					card23 = teste2.remove();
					warplayer1 = cardValue(card11) + cardValue(card12) + cardValue(card13);
					warplayer2 = cardValue(card21) + cardValue(card22) + cardValue(card23);
					warCardsp1.setText(card11 + " " + card12 + " " + card13 + " score: " + warplayer1);
					warCardsp2.setText(card21 + " " + card22 + " " + card23 + " score: " + warplayer2);
				
					if (warplayer1 > warplayer2) {
						teste1.add(card1);
						teste1.add(card2);
						teste1.add(card11);
						teste1.add(card21);
						teste1.add(card12);
						teste1.add(card22);
						teste1.add(card13);
						teste1.add(card23);
						p1wins += 1;
					} else if (warplayer1 < warplayer2) {
						teste2.add(card1);
						teste2.add(card2);
						teste2.add(card11);
						teste2.add(card21);
						teste2.add(card12);
						teste2.add(card22);
						teste2.add(card13);
						teste2.add(card23);
						p2wins += 1;

					} else if (warplayer1 == warplayer2) {
						LinkedList<String> midTeste = new LinkedList<>();
						midTeste.add(card1);
						midTeste.add(card2);
						midTeste.add(card11);
						midTeste.add(card12);
						midTeste.add(card13);
						midTeste.add(card21);
						midTeste.add(card22);
						midTeste.add(card23);
						Collections.shuffle(midTeste);
						for(int i = 1; i <= 8; i++) {
							if(i%2==1) {
								teste1.add(midTeste.remove());
							} else {
								teste2.add(midTeste.remove());
							}
						}
			}
				}
				try {
					FileWriter writer = new FileWriter("src/war/failat.txt", true);
					writer.write("Player 1  " + p1wins + " - " + p2wins + "  Player 2" + System.lineSeparator());
					writer.close();
				} catch (IOException ex) {
					System.out.println("An error occured. ");
					ex.printStackTrace();
				}
				System.out.println(teste1);
				System.out.println(teste2);

				try {
//					print the result

					Scanner myReader = new Scanner(new FileReader("src/war/failat.txt"));
					while (myReader.hasNext()) {
						scoreSheet.setText(myReader.nextLine());

					}
					myReader.close();
				} catch (Exception a) {
					System.out.println("An error occured. ");
					a.printStackTrace();
				}

			}

				p1teste.setText("Deck 1:" + String.valueOf(teste1.size()));
				p2teste.setText("Deck 2:" + String.valueOf(teste2.size()));
				
			}
		});

		setVisible(true);

	}
// find the value of the card
	public int cardValue(String card) {
		int value = 0;
		if (card.contains("2")) {
			value = 2;
		} else if (card.contains("3")) {
			value = 3;
		} else if (card.contains("4")) {
			value = 4;
		} else if (card.contains("5")) {
			value = 5;
		} else if (card.contains("6")) {
			value = 6;
		} else if (card.contains("7")) {
			value = 7;
		} else if (card.contains("8")) {
			value = 8;
		} else if (card.contains("9")) {
			value = 9;
		} else if (card.contains("10")){
			value = 10;
		} else if (card.contains("J")) {
			value = 11;
		} else if (card.contains("Q")) {
			value = 12;
		} else if (card.contains("K")) {
			value = 13;
		} else if (card.contains("A")) {
			value = 14;
		}
		return value;
	}


	
	public void exc(int exceptionTesteSize) throws NotEnoughCardsException {
		throw new NotEnoughCardsException(exceptionTesteSize);
	}

	public static void main(String[] args) {

	}
	
	
}