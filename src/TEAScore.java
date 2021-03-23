import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.util.Scanner;
import java.util.ArrayList;

public class TEAScore extends JFrame {
	private JPanel mid;
	private JButton confirm;
	private JPanel panel;
	
	private JButton back;
	private Teacher teacher;
	private JComboBox<String> scale;
	private File cou;
	private String s;
	private ArrayList<JLabel> names;
	private ArrayList<JLabel> ids;
	private ArrayList<JTextField> scores;
	private Scanner scanner;
	
	public TEAScore(Teacher teacher, String s) throws IOException{
		setSize(375,667);
		this.teacher = teacher;
		this.s = s;
		this.cou = new File(s);
		this.scanner = new Scanner(cou);
		names = new ArrayList<JLabel>();
		ids = new ArrayList<JLabel>();
		scores = new ArrayList<JTextField>();
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
	
	public void createPanel() throws FileNotFoundException, NullPointerException{
		JPanel up = new JPanel();
		up.setLayout(new GridLayout(1,2));
		JLabel scaleN = new JLabel("Enter the score of : ");
		scale = new JComboBox<String>();
		scale.addItem("Choose your category");
		scale.addItem("HW1");
		scale.addItem("HW2");
		scale.addItem("Quiz1");
		scale.addItem("HW3");
		scale.addItem("HW4");
		scale.addItem("Quiz2");
		up.add(scaleN);
		up.add(scale);
		mid = new JPanel(new GridLayout(6,3));
		mid.add(new JLabel("Student"));
		mid.add(new JLabel("Number"));
		mid.add(new JLabel("Score"));	
		while(scanner.hasNext()) {
			if("Stu".equals(scanner.next())) {
				for(int i = 0; i <= 4; i++) {
					names.add(new JLabel(scanner.next()));
					ids.add(new JLabel(scanner.next()));
					scores.add(new JTextField(10)); } break;
			} else {
				scanner.nextLine();}
		}
		scanner.close();
		for(int i = 0; i <= 4; i++) {
			mid.add(names.get(i));
			mid.add(ids.get(i));
			mid.add(scores.get(i));
		}
		JPanel butt = new JPanel();
		butt.setLayout(new GridLayout(1,2));
		confirm = new JButton("Confirm");
		back = new JButton("Back");
		butt.add(confirm);
		butt.add(back);
		panel = new JPanel();
		panel.add(up);
		panel.add(mid);
		panel.add(butt);
		add(panel);
	}
	
	public void createAction() {
		class Start implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(
			            null,
			            "Are you sure you want make the adjustment?",
			            "Notification",
			            JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					
				}
			}
		}
		confirm.addActionListener(new Start());
		
		class Order implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(
			            null,
			            "Go back to the previous page?",
			            "Notification",
			            JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					try {
						TEACourse stu = new TEACourse(teacher,s);
						stu.setVisible(true);
						setVisible(false);
						dispose();
					} catch(FileNotFoundException ex) {}
				}
			}
		}
		back.addActionListener(new Order());
		
		class Scale implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				try {
					scanner = new Scanner(cou);
					scanner.nextLine();
					scanner.nextLine();
					scanner.nextLine();
					scanner.nextLine();
					while(scanner.hasNext()) {
						if(scale.getSelectedItem().equals(scanner.next())) {
							for(JTextField sc : scores) {
								sc.setText(scanner.next());
							} break;
						} else {
							scanner.nextLine();}
					}
					scanner.close();
				} catch(FileNotFoundException ex) { }
			}
		}
		scale.addActionListener(new Scale());
	
	}
}
