package war;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class MainPage extends JFrame {
	
	
	  public static final Color VERY_LIGHT_Blue= new Color(51,204,255);
	String addPlayerActionCommand = "addPlayer";
	String trainActionCommand = "openNewWindow";
	JPanel main;
	JPanel leftSide;
	JPanel rightSide;
	JButton theTrain;
	JTextField name;
	JButton addPlayer;
	
	
	int i = 0;

	String[] players = new String[10];

	
	
	private static void GUI() {
		
		MainPage app = new MainPage();

		
	}
	
	
	
	public MainPage() {
		
		
		
		
		 super("The Drinking Game App");
		setSize(450,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		for(int l = 0; l<10; l++) {
			
			players[l] = new String();
		}
		
		main = new JPanel();
		super.add(main);	
		main.setLayout(new BoxLayout(main, BoxLayout.X_AXIS));
		main.setBackground(VERY_LIGHT_Blue);

		
		
		leftSide = new JPanel();
		main.add(leftSide);
		leftSide.setLayout(new BoxLayout(leftSide,BoxLayout.Y_AXIS));
		leftSide.setBackground(VERY_LIGHT_Blue);
		
		
		rightSide = new JPanel();
		main.add(rightSide);
		rightSide.setLayout(null);
		rightSide.setBackground(VERY_LIGHT_Blue);
		
		
		//leftSIde
		
		theTrain = new JButton("The Train");
		leftSide.add(theTrain);
//		theTrain.addActionListener(this); 
//		theTrain.setActionCommand(trainActionCommand);
		theTrain.setBackground(Color.GREEN);
		theTrain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
//				TheTrainPrep ak = new TheTrainPrep(players);
			}});
		
		JButton cardGame = new JButton("Voina");
		cardGame.setBackground(Color.green);
		cardGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Voina war = new Voina();
				
			}});
		
		JButton roulette = new JButton("Roulette");
		roulette.setBackground(Color.red);
		JButton commingSoon = new JButton ("Comming soon");
		commingSoon.setBackground(Color.red);
		
		
		
		
		leftSide.add(cardGame);
		leftSide.add(roulette);
		leftSide.add(commingSoon);
		

		
		
		//rightSide
		
		
	
		
	
		
		
		
		
		name = new JTextField();
		rightSide.add(name);
		name.setBounds(30,90,150,30);
		
		
		addPlayer = new JButton("Add player");
		rightSide.add(addPlayer);
		addPlayer.setBounds(30,130,100, 30);
		addPlayer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
		
				String playerName = name.getText();
				players[i] = playerName;
				i++;
				
				name.setText(null);
				
			}});
		addPlayer.setActionCommand(addPlayerActionCommand);
		addPlayer.setBackground(Color.GREEN);
		
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		
	
	GUI();

	
	
	}



//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		
//	}



//	@Override
//	
//		public void actionPerformed(ActionEvent arg0) {
//			// TODO Auto-generated method stub
//		setVisible(false);
//			TheTrainPrep ak = new TheTrainPrep(players);
//	
//			
//		}
//		
	

	

}