import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.BorderLayout;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class STUScore extends JFrame {
	private JPanel mid;
	private JButton back;
	private JPanel panel;
	private Student student;
	
	private JLabel hw1;
	private String s;
	private File stu;
	private Scanner scanner;
	private ArrayList<JLabel> couNames;
	private ArrayList<JLabel> scores;
	
	
	public STUScore(Student student, String s) throws FileNotFoundException{
		setSize(375,667);
		this.student = student;
		this.s = s;
		this.stu = new File(s);
		this.scanner = new Scanner(stu);
		this.couNames = new ArrayList<JLabel>();
		this.scores = new ArrayList<JLabel>();
		
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
		hw1 = new JLabel("50");
		
		mid = new JPanel(new GridLayout(7,2));
		mid.add(new JLabel("Quiz/Homework"));
		mid.add(new JLabel("Score"));
		scanner.next();
		scanner.next();
		scanner.next();
		for(int i = 0; i <= 5; i++) {
			couNames.add(new JLabel(scanner.next()));
		}
		while(scanner.hasNext()) {
			if(student.getStuName().equals(scanner.next())) {
				for(int i = 0; i <= 5; i++) {
					scores.add(new JLabel(scanner.next()));
				}break;
			}
			else {
				scanner.nextLine();}
		}
		scanner.close();
		for(int i = 0; i <= 5; i++) {
			mid.add(couNames.get(i));
			mid.add(scores.get(i));
		}
		JPanel butt = new JPanel();
		back = new JButton("Back");
		butt.add(back);
		panel = new JPanel(new BorderLayout());
		panel.add(mid, BorderLayout.CENTER);
		panel.add(butt, BorderLayout.SOUTH);
		add(panel);
	}
	
	public void createAction() {
		class Order implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(
			            null,
			            "Go back to the previous page?",
			            "Notification",
			            JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					try {
						STUCourse stu = new STUCourse(student, s);
						stu.setVisible(true);
						setVisible(false);
						dispose();
					}
					catch(FileNotFoundException ex) {
						System.out.println(ex.getMessage());
					}
				}
			}
		}
		back.addActionListener(new Order());
		
	}
}
