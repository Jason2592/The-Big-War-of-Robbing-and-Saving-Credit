import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;


public class TEACourse extends JFrame {
	private JPanel up;
	private JPanel mid;
	private JButton score;
	private JButton order;
	private JPanel panel;
	private JPanel but;
	private JRadioButton syl;
	private JRadioButton gra;
	private ButtonGroup group;
	private JTextArea area;
	private String s;
	private File course;
	private Teacher teacher;
	
	public TEACourse(Teacher teacher, String cou) throws FileNotFoundException{
		setSize(375,667);
		this.teacher = teacher;
		this.s = cou;
		this.course = new File(s);
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
	
	public void createPanel() throws FileNotFoundException{
		up = new JPanel();
		Scanner scanner = new Scanner(course);
		up.add(new JLabel(scanner.next()));
		up.add(new JLabel(scanner.next()));
		up.add(new JLabel(scanner.next()));
		scanner.close();
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
		score = new JButton("Enter Scores");
		order = new JButton("Back");
		but.add(score);
		but.add(order);
		panel = new JPanel();
		panel.add(up);
		panel.add(mid);
		panel.add(but);
		add(panel);
	}
	
	public void createAction() {
		class Enter implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				try {
					TEAScore score = new TEAScore(teacher, s);
					score.setVisible(true);
					setVisible(false);
					dispose();
				}catch(IOException ex) {}
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
						TEA tea = new TEA(teacher);
						tea.setVisible(true);
						setVisible(false);
						dispose();
					} catch(FileNotFoundException ex) {
						ex.getMessage();}
				}
			}
		}
		order.addActionListener(new Order());
		
		class Syl implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				try {
					Scanner scanner = new Scanner(course);
					while(scanner.hasNext()) {
						if("Syl".equals(scanner.next())) {
							area.setText(scanner.next().toString());break;}
						else {
							scanner.nextLine();}}
					scanner.close();
				} catch(FileNotFoundException ex) {
					System.out.println(ex.getMessage());}
			}
		}
		syl.addActionListener(new Syl());
		
		class Gra implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				try {
					Scanner scanner = new Scanner(course);
					while(scanner.hasNext()) {
						if("Gra".equals(scanner.next())) {
							area.setText(scanner.next());break;}
						else {
							scanner.nextLine();}}
					scanner.close();
				} catch(FileNotFoundException ex) {
					System.out.println(ex.getMessage());}
			}
		}
		gra.addActionListener(new Gra());
	}
}
