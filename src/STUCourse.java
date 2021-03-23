import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Scanner;

public class STUCourse extends JFrame {
	private JPanel up;
	private JPanel cre;
	private JPanel mid;
	private JPanel but;
	private JButton score;
	private JButton order;
	private JPanel panel;
	
	private JRadioButton syl;
	private JRadioButton gra;
	private ButtonGroup group;
	private JTextArea area;
	
	private Student student;
	
	private String s;
	private File stu;
	private Scanner scanner;
	
	public STUCourse(Student student, String s) throws FileNotFoundException {
		setSize(375,667);
		this.student = student;
		this.s = s;
		this.stu = new File(s);
		this.scanner = new Scanner(stu);
		createPanel();
		createAction();
		
		addWindowListener( new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		        JFrame frame = (JFrame)e.getSource();
		 
		        int result = JOptionPane.showConfirmDialog(
		            frame,
		            "Are you sure you want to exit the application?",
		            "Exit Application",
		            JOptionPane.YES_NO_OPTION);
		 
		        if (result == JOptionPane.YES_OPTION)
		            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    }
		});
	}
	
	public void createPanel() {
		up = new JPanel();
		up.add(new JLabel(scanner.next()));
		up.add(new JLabel(scanner.next()));
		up.add(new JLabel(scanner.next()));
		
		cre = new JPanel();
		cre.add(new JLabel(new ImageIcon("img/credit.png")));
		
		mid = new JPanel();
		mid.setLayout(new GridLayout(2,1));
		
		syl = new JRadioButton("Syllabus");
		syl.setSelected(true);
		gra = new JRadioButton("Grading Standard");
		group = new ButtonGroup();
		group.add(syl);
		group.add(gra);
		area = new JTextArea();
		area.setEditable(false);
		area.setText("Week1 : intro\nWeek2 : Lec1\nWeek3 : Lec2\nWeek4 : Lec3,4\nWeek5 : Lec5\nWeek6 : Quiz1");
		JPanel butt = new JPanel();
		butt.setLayout(new GridLayout(1,2));
		butt.add(syl);
		butt.add(gra);
		
		mid.add(butt);
		mid.add(area);
		
		
		but = new JPanel();
		but.setLayout(new GridLayout(1,2));
		score = new JButton("Check Your Scores");
		order = new JButton("Back");
		but.add(score);
		but.add(order);
		
		panel = new JPanel();
		panel.add(up);
		panel.add(cre);
		panel.add(mid);
		panel.add(but);
		
		add(panel);
		
	}
	
	public void createAction() {
		class Enter implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				try {
					STUScore score = new STUScore(student, s);
					score.setVisible(true);
					setVisible(false);
					dispose();
				}
				catch(FileNotFoundException ex) {
					System.out.print(ex.getMessage());
				}
			}
		}
		score.addActionListener(new Enter());
		
		class Order implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(
			            null,
			            "Go back to the previous page?",
			            "Notification",
			            JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					try {
						STU stu = new STU(student);
						stu.setVisible(true);
						setVisible(false);
						dispose();}
					catch(IOException ex) {
						ex.getMessage();
					}
				}
			}
		}
		order.addActionListener(new Order());
		
		class Syl implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				area.setText("Week1 : intro\nWeek2 : Lec1\nWeek3 : Lec2\nWeek4 : Lec3,4\nWeek5 : Lec5\nWeek6 : Quiz1");
			}
		}
		syl.addActionListener(new Syl());
		
		class Gra implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				area.setText("HW1   : 10%\nHW2   : 10%\nQuiz1 : 30%\nHW3   : 10%\nHW4   : 10%\nQuiz2 : 30%");
			}
		}
		gra.addActionListener(new Gra());
	}
}
