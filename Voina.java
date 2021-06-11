package war;

import java.awt.Color;
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

public class Voina extends JFrame {
	
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
	String[] deck = { "7♣", "8♣", "9♣", "10♣", "J♣", "Q♣", "K♣", "A♣", "7♢", "8♢", "9♢", "10♢", "J♢", "Q♢", "K♢", "A♢",
			"7♡", "8♡", "9♡", "10♡", "J♡", "Q♡", "K♡", "A♡", "7♠", "8♠", "9♠", "10♠", "J♠", "Q♠", "K♠", "A♠" };

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

	public Voina() {

		super("Voina");
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Stack<String> teste = new Stack<String>();
		for (int i = 0; i < 32; i++) {
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
				file.delete();
				deal.setVisible(false);
				Collections.shuffle(teste);
				for (int i = 0; i < 32; i++) {
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
		table.setBounds(350, 50, 100, 50);
		main.add(table);

		scoreSheet.setBounds(300, 100, 200, 50);
		main.add(scoreSheet);
		
		JLabel winner = new JLabel();
		winner.setBounds(350, 0, 100, 50);
		main.add(winner);

		JButton play = new JButton("Play");
		main.add(play);
		play.setBounds(600, 650, 100, 40);
		play.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(teste1.size()==0) {
					winner.setText("Player 2 wins!");
					play.setVisible(false);
				} else if(teste2.size() == 0) {
					winner.setText("Player 1 wins!");
					play.setVisible(false);
				} else {
					card1 = teste1.remove();
					System.out.println(card1);
					card2 = teste2.remove();
					System.out.println(card2);
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
					if (teste1.size() < 3 || teste2.size() < 3) {
						if (teste1.size() < teste2.size()) {
							exceptionTesteSize = teste1.size();
							scoreSheet.setText("PLAYER 2 WINS");
						} else if (teste1.size() > teste2.size()) {
							exceptionTesteSize = teste2.size();
							scoreSheet.setText("PLAYER 1 WINS");
						} else if (teste1.size() == teste2.size()) {
							exceptionTesteSize = teste2.size();
							scoreSheet.setText("DRAW");
						}
						try {
							exc(exceptionTesteSize);
						} catch (NotEnoughCardsException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
//						new NotEnoughCardsException(exceptionTesteSize);
					}
					card11 = teste1.remove();
					card12 = teste1.remove();
					card13 = teste1.remove();
					card21 = teste2.remove();
					card22 = teste2.remove();
					card23 = teste2.remove();
					warplayer1 = cardValue(card11) + cardValue(card12) + cardValue(card13);
					warplayer2 = cardValue(card21) + cardValue(card22) + cardValue(card23);
				
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
//						String[] midDeck = {card1, card2, card11, card12, card13, card21, card22, card23};
//						Stack<String> midTeste = new Stack<>();
//						for (int i = 0; i < 8; i++) {
//							midTeste.add(midDeck[i]);
//						}
//						Collections.shuffle(teste);
//						for (int i = 0; i < 8; i++) {
//							if (i % 2 == 1) {
//								teste1.add(teste.pop());
//							} else {
//								teste2.add(teste.pop());
//							}
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
			}
		});

		setVisible(true);

	}

	public int cardValue(String card) {
		int value = 0;
		if (card.contains("7")) {
			value = 7;
		} else if (card.contains("8")) {
			value = 8;
		} else if (card.contains("9")) {
			value = 9;
		} else if (card.contains("10")) {
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

//	public void war(Queue<String> teste1, Queue<String> teste2) throws NotEnoughCardsException {
//		if (teste1.size() > 2 && teste2.size() > 2) {
//			card11 = teste1.remove();
//			card12 = teste1.remove();
//			card13 = teste1.remove();
//			card21 = teste2.remove();
//			card22 = teste2.remove();
//			card23 = teste2.remove();
//			warplayer1 = cardValue(card11) + cardValue(card12) + cardValue(card13);
//			warplayer2 = cardValue(card21) + cardValue(card22) + cardValue(card23);
//			if (warplayer1 > warplayer2) {
//				teste1.add(card1);
//				teste1.add(card2);
//				teste1.add(card11);
//				teste1.add(card21);
//				teste1.add(card12);
//				teste1.add(card22);
//				teste1.add(card13);
//				teste1.add(card23);
//				p1wins += 1;
//			} else if (warplayer1 < warplayer2) {
//				teste2.add(card1);
//				teste2.add(card2);
//				teste2.add(card11);
//				teste2.add(card21);
//				teste2.add(card12);
//				teste2.add(card22);
//				teste2.add(card13);
//				teste2.add(card23);
//				p2wins += 1;
//			} else if (warplayer1 == warplayer2) {
//				war(teste1, teste2);
//			}
//		} else if(teste1.size() < 3 || teste2.size() < 3) {
//			if(teste1.size() < teste2.size()) {
//				exceptionTesteSize = teste1.size();
//				scoreSheet.setText("PLAYER 2 WINS");
//			} else if(teste1.size() > teste2.size()) {
//				exceptionTesteSize = teste2.size();
//				scoreSheet.setText("PLAYER 1 WINS");
//			} else if(teste1.size() == teste2.size()) {
//				exceptionTesteSize = teste2.size();
//				scoreSheet.setText("DRAW");
//			}
//			throw new NotEnoughCardsException(exceptionTesteSize);
//		}
//		try {
//			FileWriter writer = new FileWriter("src/war/teste1.txt", true);
//			for(int i = 0; i < teste1.size(); i++) {
//				writer.write(teste1.remove() + System.lineSeparator());
//			}
//			writer.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public void exc(int exceptionTesteSize) throws NotEnoughCardsException {
		throw new NotEnoughCardsException(exceptionTesteSize);
	}

	public static void main(String[] args) {

	}
	
	
}