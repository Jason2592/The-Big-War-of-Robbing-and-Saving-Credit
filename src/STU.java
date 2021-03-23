import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class STU extends JFrame {
	private JPanel 				  up;
	private JPanel 				  mid;
	private JButton 			  confirm;
	private JPanel 				  panel;
	private Student 			  student;
	private File 				  stu;
	private Scanner 			  scanner;
	private ArrayList<JButton>    couNames;
	private ArrayList<JTextField> targets;
	private ArrayList<JCheckBox>  checks;
	
	public STU(Student student) throws IOException{		
		setSize(375, 667);
		this.student  = student;
		this.stu	  = new File("STUcourses.txt");
		this.scanner  = new Scanner(stu);
		this.couNames = new ArrayList<JButton>();
		this.targets  = new ArrayList<JTextField>();
		this.checks   = new ArrayList<JCheckBox>();
		
		createPanel();
		createAction();
		
		if(checks.get(0).isSelected()) {
			JOptionPane.showMessageDialog(null, "Your SAD score is way too low,"
					+ "\n stop spending time on your IG and games, OK?"
					, "WARNING" ,JOptionPane.WARNING_MESSAGE, new ImageIcon("img/jacky.png"));
		}
		
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
		up.setLayout(new GridLayout(2,1));
		mid = new JPanel();
		
		while(scanner.hasNext()) {
			if(student.getStuName().equals(scanner.next())) {
				for(int i = 0; i <= 6; i++) {	
					couNames.add(new JButton(scanner.next()));
					targets.add(new JTextField(10));
					checks.add(new JCheckBox());
				}
				break;
			}
			else {
				scanner.nextLine();
			}
		}
		scanner.close();
		
		mid.setLayout(new GridLayout(couNames.size()+1,3));
		mid.add(new JLabel("Courses"));
		mid.add(new JLabel("Score Limit"));
		mid.add(new JLabel("Alarm"));
		
		for(int i = 0; i <= 6; i++) {
			mid.add(couNames.get(i));
			mid.add(targets.get(i));
			mid.add(checks.get(i));
		}
		if(student.getCourses().get(0).getTarget() != 0) {
			targets.get(0).setText(""+student.getCourses().get(0).getTarget());
		}
		if(student.getCourses().get(0).getAlarm()) {
			checks.get(0).setSelected(true);
		}
		
		up.add(new JLabel("" + couNames.size()*3));
		up.add(new JLabel("Credits"));
		
		confirm = new JButton("Confirm");
		
		panel = new JPanel();
		panel.add(up);
		panel.add(mid);
		panel.add(confirm);
		add(panel);
	}
	
	public void createAction() {
		class Cou implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				try {
					JButton o = (JButton)e.getSource();
					STUCourse course = new STUCourse(student, "cou/"+o.getText()+".txt");
					course.setVisible(true);
					setVisible(false);
					dispose();
				}
				catch(FileNotFoundException ex) {
					System.out.println(ex.getMessage());
				}
			}
		}
		for(JButton cou : couNames) {
			cou.addActionListener(new Cou());
		}
		
		class Com implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(
			            null,
			            "Are you sure you want make the adjustment?",
			            "Notification",
			            JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					student.getCourses().get(0).setTarget(Integer.parseInt(targets.get(0).getText()));
					if(checks.get(0).isSelected()) {
						student.getCourses().get(0).setAlarm(true);
					}
					JOptionPane.showMessageDialog(null, "Your modifications are saved!");
				}
			}
		}
		confirm.addActionListener(new Com());
	}
}
