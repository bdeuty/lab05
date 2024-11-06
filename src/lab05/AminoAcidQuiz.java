package lab05;

import java.util.Random;
import javax.swing.Timer;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AminoAcidQuiz extends JFrame
{
	public static String[] SHORT_NAMES = 
		{ "A","R", "N", "D", "C", "Q", "E", 
		"G",  "H", "I", "L", "K", "M", "F", 
		"P", "S", "T", "W", "Y", "V" };
	
	public static String[] FULL_NAMES = 
		{
		"alanine","arginine", "asparagine", 
		"aspartic acid", "cysteine",
		"glutamine",  "glutamic acid",
		"glycine" ,"histidine","isoleucine",
		"leucine",  "lysine", "methionine", 
		"phenylalanine", "proline", 
		"serine","threonine","tryptophan", 
		"tyrosine", "valine"};
	
	private String currentAA = "";
	private int count = 30;
	private int numCorrect = 0;
	private int numIncorrect = 0;
	private JLabel timerLabel = new JLabel("Time remaining: " + count);
	private JLabel promptLabel = new JLabel();
	private JLabel correctLabel = new JLabel("Correct: " + numCorrect);
	private JLabel incorrectLabel = new JLabel("Inccorect: " + numIncorrect);
	private JTextField answerField = new JTextField(10);
	private JButton startQuizButton = new JButton("Start Quiz");
	private JButton cancelButton = new JButton("Cancel");
	Random random = new Random();
	private Timer timer;
	
	public String nextAA()
	{
		int y = random.nextInt(20);
		currentAA = FULL_NAMES[y];
		return currentAA;
	}
	
	private void updateTextField()
	{
		answerField.setText("");
		validate();
	}
	public AminoAcidQuiz()
	{
		setTitle("Amino Acid Quiz");
		setSize(600,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		JPanel inputPanel = new JPanel();
		inputPanel.add(promptLabel);
		inputPanel.add(answerField);
		inputPanel.add(cancelButton);
		
		JPanel controlPanel = new JPanel();
		controlPanel.add(startQuizButton);
		
		JPanel scorePanel = new JPanel();
		scorePanel.add(timerLabel);
		scorePanel.add(correctLabel);
		scorePanel.add(incorrectLabel);
		
		add(inputPanel, BorderLayout.NORTH);
		add(scorePanel, BorderLayout.SOUTH);
		add(controlPanel, BorderLayout.CENTER);
		
		currentAA = nextAA();
		promptLabel.setText("Type the one letter code of the amino acid: " + currentAA);
		updateTextField();
		
		startQuizButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				startQuiz();
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endQuiz();
			}
		});
		
		answerField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkAnswer();
			}
		});
		
		timer = new Timer(1000, new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						if (count > 0)
						{
							count--;
							timerLabel.setText("Time remaining: " + count);
						}
						else
						{
							timerLabel.setText("Times Up");
							endQuiz();
						}
					}
				});
		setVisible(true);
	}
	
	private void startQuiz()
	{
		count = 30;
		numCorrect = 0;
		numIncorrect = 0;
		timerLabel.setText("Time remaining: " + count);
		correctLabel.setText("Correct: " + numCorrect);
		incorrectLabel.setText("Incorrect: " + numIncorrect);
		currentAA = nextAA();
		promptLabel.setText("Type the one letter code of the amino acid: " + currentAA);
		answerField.setEnabled(true);
		cancelButton.setEnabled(true);
		startQuizButton.setEnabled(false);
		timer.start();
	}
	
	private void checkAnswer() {
		String userInput = answerField.getText().trim().toUpperCase();
		int correctIndex = java.util.Arrays.asList(FULL_NAMES).indexOf(currentAA);
		String correctAnswer = SHORT_NAMES[correctIndex];
		
		if (userInput.equals(correctAnswer)) {
			numCorrect++;
		} else {
			numIncorrect++;
		}
		
		correctLabel.setText("Correct: " + numCorrect);
		incorrectLabel.setText("Incorrect: " + numIncorrect);
		currentAA = nextAA();
		promptLabel.setText("Type the one letter code of the amino acid: " + currentAA);
		updateTextField();
	}
	
	public void endQuiz() {
		if (timer != null)
		{timer.stop();}
		timerLabel.setText("Times up!");
		answerField.setEnabled(false);
		cancelButton.setEnabled(false);
		startQuizButton.setEnabled(true);
	}
	
	public static void main(String[] args)
	{
		new AminoAcidQuiz();
	}
}
