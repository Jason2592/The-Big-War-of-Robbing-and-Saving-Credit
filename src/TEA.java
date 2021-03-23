import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class TEA extends JFrame {
	private JPanel panel;
	private JButton sad;
	
	private Teacher teacher;
	private ArrayList<JButton> cour;
	private File courses;
	
	public TEA(Teacher teacher) throws FileNotFoundException{
		setSize(375,667);
		this.teacher = teacher;
		this.courses = new File("TEAcourses.txt");
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
	
	public void createPanel() throws FileNotFoundException {
		sad = new JButton(teacher.getCourses().get(0).getName());
		cour = new ArrayList<JButton>();
		Scanner scanner = new Scanner(courses);
		panel = new JPanel(new BorderLayout());
		panel.setLayout(new GridLayout(7,1));
		panel.add(new JLabel("Courses"));
		while(scanner.hasNextLine()) {
			if(teacher.getTeaName().equals(scanner.next())) {
				cour.add(new JButton(scanner.next()));
				cour.add(new JButton(scanner.next()));
				cour.add(new JButton(scanner.next()));
				cour.add(new JButton(scanner.next()));
				cour.add(new JButton(scanner.next()));
				cour.add(new JButton(scanner.next()));
				break;}
		}
		scanner.close();
		for(JButton butt : cour) {
			panel.add(butt);}
		add(panel);
	}
	public void createAction() {
		class Cou implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				try {
					JButton o = (JButton)e.getSource();
					TEACourse course = new TEACourse(teacher, "cou/"+o.getText()+".txt");
					course.setVisible(true);
					setVisible(false);
					dispose();
				}
				catch(FileNotFoundException ex) {}}
		}
		for (JButton butt : cour) {
			butt.addActionListener(new Cou());
		}
	}
}
