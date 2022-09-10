package pongGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Enumeration;

public class Score extends Rectangle{

	//declaring global variables
	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	private int player1;
	private int player2;
	public final static int MAX_SCORE = 5;
	private static int wins1;
	private static int wins2;
	private boolean asked = false;
	private static JLabel result = new JLabel();

	//constructor method for Score class
	Score(int GAME_WIDTH, int GAME_HEIGHT){
		Score.GAME_WIDTH = GAME_WIDTH;
		Score.GAME_HEIGHT = GAME_HEIGHT;
	}
	

	//method called when player scores
	public void increaseScoreByOne(boolean isFirstPlayer){
		
		//declaring images to be used for game over screen
		ImageIcon p1wins = new ImageIcon("player1win.png");
		ImageIcon p2wins = new ImageIcon("player2win.png");

		int d = 1;
		
		//Trivia Option
		if(Pong.getFrame().hasQuiz() && ! asked)
		{
			asked = true;
			QuizDialog dlg = new QuizDialog(Pong.getFrame());
			dlg.showIt();
			if(dlg.isRight()) d = 2;	
			GamePanel gamePanel = Pong.getFrame().getGamePanel();
			gamePanel.repaint();
		}

		//adding the points to the player
		if(isFirstPlayer) player1 += 1*d;
		else player2+= 1*d;
		
		//Troubleshooting
		System.out.println("player1: " + player1 + "; player2: " + player2);

		//checking if the game should be over
		if(player1 == MAX_SCORE || player2 == MAX_SCORE){
			GamePanel gamePanel = Pong.getFrame().getGamePanel();
			gamePanel.repaint();
			gamePanel.pause();
			if (player1 == MAX_SCORE) {
				result.setIcon(p1wins);
			} else {
				result.setIcon(p2wins);
			}
			
			GameOver go = new GameOver(" ");
			go.showIt();
			if(player1 > player2)
			{
				wins1 += 1;
			}
			else wins2 += 1;
			player2 = 0;
			player1 = 0;
			System.out.println("reset. player1: " + player1 + "; player2: " + player2);
			Pong.getFrame().startGame();
		}
	}

	public void draw(Graphics g) {
		g.setColor(Color.gray);
		g.setFont(new Font("Times New Roman",Font.BOLD,100));
		
		g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
		
		g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), (GAME_WIDTH/2)-250, 90);
		g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), (GAME_WIDTH/2)+165, 90);

		g.setFont(new Font("Times New Roman", Font.BOLD, 40));
		g.drawString("Wins: " + String.valueOf(wins1) , 200, 200);
		g.drawString("Wins: " + String.valueOf(wins2) , (GAME_WIDTH / 2) + 150, 200);
	}
}



//class for when the game is over
class GameOver extends JDialog {
	JLabel north;
	public GameOver (String text){
		super(Pong.getFrame(), true);
		north = new JLabel("<html><h1> " + text + "</h1>");
	}
	public void showIt(){

		@SuppressWarnings("unused")
		ImageIcon quitButton = new ImageIcon("quit3.png");
		ImageIcon againButton = new ImageIcon("playagain.png");


		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel left = new JLabel();
		panel.add(left, BorderLayout.WEST);
		JLabel right = new JLabel();
		panel.add(right, BorderLayout.EAST);
		JPanel c = new JPanel();
		
		//initializing play again button
		JButton btn1 = new JButton(againButton);
		btn1.setBackground(new Color(0xABCDEF));
		btn1.setOpaque(true);
		btn1.setBorderPainted(false);
		btn1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		
		//initializing quit game button
		JButton btn2 = new JButton(quitButton);
		btn2.setBackground(new Color(0xABCDEF));
		btn2.setOpaque(true);
		btn2.setBorderPainted(false);
		btn2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				 System.exit(0);
			}
		});
		
		//adding buttons to the JPanel
		c.setLayout(new GridLayout( 2, 1));
		c.add(btn1);
		c.add(btn2);
		panel.add(c, BorderLayout.CENTER);
		JPanel np = new JPanel();
		np.add(north);
		panel.add(np, BorderLayout.NORTH);
		setContentPane(panel);
		setSize(1000, 556);
		GameFrame.centerOnScreen(this);
		panel.setBackground(new Color(0xABCDEF));
		setTitle("Game Over");
		setVisible(true);
		setResizable(false);
	}
	
	

	//method for scaling image to make it fit on screen
	public Image getScaledImage(Image srcImg, int w, int h)
	{
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}
}