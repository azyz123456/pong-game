package pongGame;

import java.awt.*;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.awt.GraphicsEnvironment;

public class GameFrame extends JFrame{

	GamePanel panel;
	boolean curve;
	boolean hasQuiz;
	Font pixel;

	GameFrame()
	{
		
		//declaring the images used for the main menu
		ImageIcon button1 = new ImageIcon("norma.png");
		ImageIcon button2 = new ImageIcon("quidditch.png");
		ImageIcon button3 = new ImageIcon("trivia2.png");
		ImageIcon instructions = new ImageIcon("instructions2.png");
		ImageIcon icon = new ImageIcon("pusheenMenu.png");


		//declaring the JPanel that holds the pusheen picture
		JPanel p = new JPanel(){
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				Image background = Toolkit.getDefaultToolkit().createImage("pusheenMenu.png");
				g.drawImage(background, 0, 0, null);
				this.setBackground(new Color(0xABCDEF));

			}
		};
		
		//creating JButton for normal game mode
		JButton normal = new JButton(button1);
		normal.setBackground(new Color(0xABCDEF));
		normal.setOpaque(true);
		normal.setBorderPainted(false);
		normal.setFont(new Font("Helvetica Neue", Font.BOLD, 22));
		normal.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				curve = false;
				startGame();
			}
		});
		
		//declaring JPanel that holds the 3 buttons for game mode
		JPanel n = new JPanel();
		n.setBackground(new Color(0xABCDEF));
		n.add(normal);
		
		//creating JButton for trivia pong mode
		final JButton quiz = new JButton(button3);
		quiz.setFont(pixel);
		quiz.setBackground(new Color(0xABCDEF));
		quiz.setOpaque(true);
		quiz.setBorderPainted(false);
		quiz.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				curve = false;
				hasQuiz = true;
				startGame();
			}
		});
		n.add(quiz);

		//creating JButton for quidditch game mode
		JButton quidditch = new JButton(button2);
		n.add(quidditch);
		quidditch.setFont(new Font("Helvetica Neue", Font.BOLD, 22));
		quidditch.setBackground(new Color(0xABCDEF));
		quidditch.setOpaque(true);
		quidditch.setBorderPainted(false);
		quidditch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				curve = true;
				startGame();
			}
		});

		//adding JPanel n to frame
		p.setLayout(new BorderLayout());
		p.add(n, BorderLayout.CENTER);

		
		//creating JPanel for the game title
		JPanel titlePanel = new JPanel();
		JLabel title = new JLabel();
		ImageIcon titleText = new ImageIcon("pusheenPong2.png");
		title.setIcon(titleText);
		titlePanel.add(title, BorderLayout.NORTH);
		titlePanel.setBackground(new Color(0xABCDEF));

		
		
		//creating instructions panel
		JPanel helpPanel = new JPanel();
		helpPanel.setLayout(new BorderLayout());
		JLabel help = new JLabel(instructions);
		JLabel ilabel = new JLabel();
		ilabel.setIcon(icon);
		helpPanel.add(ilabel, BorderLayout.SOUTH);
		helpPanel.setBackground(new Color(0xABCDEF));
		helpPanel.add(help, BorderLayout.CENTER);
		
		//adding panels to the JFrame
		p.add(helpPanel, BorderLayout.SOUTH);
		p.add(titlePanel, BorderLayout.NORTH);
		JLabel left = new JLabel("   ");
		p.add(left, BorderLayout.WEST);
		setContentPane(p);
		setTitle("Air Hockey Game");
		setResizable(false);
		setBackground(new Color(0xABCDEF));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		centerOnScreen(this);
		setVisible(true);
	}

	public boolean isCurve()
	{
		return curve;
	}

	public boolean hasQuiz()
	{
		return hasQuiz;
	}

	public static void centerOnScreen(Window w)
	{
		boolean visible = w.isVisible();
		w.setVisible(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension size = w.getSize();
		screenSize.height = screenSize.height / 2;
		screenSize.width = screenSize.width / 2;
		size.height = size.height / 2;
		size.width = size.width / 2;
		int y = screenSize.height - size.height;
		int x = screenSize.width - size.width;
		w.setLocation(x, y);
		w.setVisible(visible);
	}

	public void startGame(){
		panel = new GamePanel();
		setContentPane(panel);
		setTitle("Air Hockey Game");
		setResizable(false);
		setBackground(Color.white);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);

		panel.setFocusable(true);
		panel.requestFocusInWindow();
	}

	public GamePanel getGamePanel(){
		return panel;
	}
}
