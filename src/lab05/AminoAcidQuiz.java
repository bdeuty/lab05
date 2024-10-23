package lab05;

import java.util.Random;
import javax.swing.Timer;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private JLabel label = new JLabel("Time remaining: " + count);
	private JTextField aaText = new JTextField();
	Random random = new Random();
	
	public String nextAA()
	{
		int y = random.nextInt(20);
		currentAA = FULL_NAMES[y];
		return currentAA;
	}
	
	private void updateTextField()
	{
		aaText.setText("Type the one letter code of the amino acid: " + currentAA);
		validate();
	}
	public AminoAcidQuiz()
	{
		setTitle("Amino Acid Quiz");
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		setLayout(new BorderLayout());
		add(label, BorderLayout.SOUTH);
		add(aaText, BorderLayout.CENTER);
		aaText.setText("Type the one letter code of the amino acid: " + nextAA() + "\n");
		Timer timer = new Timer(1000, new ActionListener()
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
							((Timer)e.getSource()).stop();
							label.setText("Times Up");
						}
						updateTextField();
					}
				});
		timer.start();
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
