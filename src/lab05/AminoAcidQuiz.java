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
	private JLabel label = new JLabel("Time remaining: " + count);
	private JLabel correctLabel = new JLabel("Correct: " + numCorrect);
	private JLabel incorrectLabel = new JLabel("Inccorect: " + numIncorrect);
	private JTextField answerField = new JTextField(10);
	private JButton endQuizButton = new JButton("End Quiz");
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
		label.setText("Type the one letter code of the amino acid: " + currentAA);
		validate();
	}
	public AminoAcidQuiz()
	{
		setTitle("Amino Acid Quiz");
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		JPanel inputPanel = new JPanel();
		inputPanel.add(label);
		inputPanel.add(answerField);
		inputPanel.add(endQuizButton);
		
		JPanel scorePanel = new JPanel();
		scorePanel.add(correctLabel);
		scorePanel.add(incorrectLabel);
		
		add(inputPanel, BorderLayout.NORTH);
		add(scorePanel, BorderLayout.SOUTH);
		
		currentAA = nextAA();
		updateTextField();
		
		endQuizButton.addActionListener(new ActionListener() {
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
							label.setText("Time remaining: " + count);
						}
						else
						{
							label.setText("Times Up");
							endQuiz();
						}
						updateTextField();
					}
				});
		timer.start();
		
		setVisible(true);
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
		updateTextField();
	}
	
	public void endQuiz() {
		timer.stop();
		label.setText("Times up! Your score: "+ numCorrect);
		answerField.setEnabled(false);
		endQuizButton.setEnabled(false);
	}
	
	public static void main(String[] args)
	{
		new AminoAcidQuiz();
		
//		Random random = new Random();
//		int z = 1;
//		long startTime = System.currentTimeMillis();
//		long elapsedTime = 0L;
//		int total = 0;
//		while (z == 1 && elapsedTime < 30 * 1000) 
//		{
//			int y = random.nextInt(20);
//			System.out.println("Type the one letter code of the amino acid:");
//			System.out.println(FULL_NAMES[y]);
//			String aString = System.console().readLine().toUpperCase();
//			String aChar = ""+aString.charAt(0);
//			if (aChar.equals(SHORT_NAMES[y]))
//			{
//				System.out.println("Correct!");
//				elapsedTime = System.currentTimeMillis() - startTime;
//				System.out.println("Elapsed time: " + elapsedTime/1000 + " seconds.");
//				total++;
//			}
//			else 
//			{
//				System.out.println("Incorrect!");
//				System.out.println("Correct answer: " + SHORT_NAMES[y]);
//				System.out.println("Total score: " + total);
//				z++;
//			}
		}
	}
