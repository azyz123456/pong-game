package pongGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

//Class for the quiz mode
public class QuizDialog extends JDialog{
	ButtonGroup bg;
	boolean isRight ;
	Quiz quiz;
	final static Quiz[] QUIZ = new Quiz[]{
			new Quiz("Is pusheen the cutest cat on earth?", new String[]{"duh", "no", "who's pusheen?", "I hate cats"}, 0),
			new Quiz("What is the most fun subject at STL?", new String[]{"math", "com sci", "history", "I hate school"}, 1),
			new Quiz("Cats spend what fraction of their lives sleeping?", new String[]{"two thirds", "half", "a quarter", "one tenth"}, 0),
			new Quiz("What is a group of cats called?", new String[]{"murder", "flock", "clowder", "pack"}, 2),
			new Quiz("What is the fastest flying bird in the world?", new String[]{"hawk", "bald eagle", "hummingbird", "peregrine falcon"}, 3),
			new Quiz("Which country consumes the most chocolate per capita?", new String[]{"Switzerland", "Canada", "Mexico", "India"}, 0),
			new Quiz("What species of spider is the world's deadliest?", new String[]{"Pusheen", "Daddy Long Legs", "Brazilian Wandering Spider", "Brown Recluse"}, 2),
	};

	public QuizDialog(JFrame f){
		super(f, true);
		quiz = QUIZ[ (int)(Math.random()* QUIZ.length)];
	}

		public boolean isRight(){
		return isRight;
	}
	public void showIt(){
		setTitle("Quiz");
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel south = new JPanel();
		JButton ok = new JButton("Ok");
		ok.addActionListener(new ActionListener()
							 {
								 public void actionPerformed(ActionEvent e)
								 {
								 	 Enumeration en = bg.getElements();
								 	 int i = 0;
								 	 while(en.hasMoreElements()){
								 	 	JRadioButton r = (JRadioButton) en.nextElement();
								 	 	if(r.isSelected()){
											System.out.println("Selected:" + r.getText() + "; i:" + i);
											if(i == QuizDialog.this.quiz.rightIndex){
												QuizDialog.this.isRight =true;
												System.out.println("right.");
											}
											break;
										}
								 	 	i++;
									 }
									 if(QuizDialog.this.isRight){
										 JOptionPane.showMessageDialog(QuizDialog.this, "Congrats! You're right! You earned 2 points!");
									 }else{
										 JOptionPane.showMessageDialog(QuizDialog.this, "Sorry. Try your luck next time.");
									 }

									 dispose();

								 }
							 });
		south.add(ok);
		panel.add(south, BorderLayout.SOUTH);
		JPanel north = new JPanel();
		north.add(new JLabel(this.quiz.title));
		panel.add(north, BorderLayout.NORTH);
		bg = new ButtonGroup();
		JPanel btns = new JPanel();
		btns.setLayout(new GridLayout(this.quiz.choices.length, 1));

		for(int i = 0; i < this.quiz.choices.length; i++)
		{
			JRadioButton rb = new JRadioButton(this.quiz.choices[i]);
			bg.add(rb);
			btns.add(rb);
		}
		panel.add(btns, BorderLayout.CENTER);
		setContentPane(panel);
		setSize(600, 250);
		GameFrame.centerOnScreen(this);
		setVisible(true);
	}
}


class Quiz{
	String title;
	String[] choices;
	int rightIndex;
	Quiz(String t, String[] c, int r)
	{
		title = t;
		choices = c;
		rightIndex = r;
	}
}