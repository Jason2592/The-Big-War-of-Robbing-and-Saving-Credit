import javax.swing.*;
import java.io.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class SignIn extends JFrame {
	private JLabel pic;
	private JRadioButton tea;
	private JRadioButton stu;
	private ButtonGroup  group;
	private JLabel 		 namE;
	private JTextField   name;
	private JLabel 		 passworD;
	private JTextField   password;
	private JButton 	 signIn;
	private JButton 	 signUp;
	private JPanel 		 panel;
	private Student 	 student;
	private Teacher 	 teacher;
	private File 		 students;
	private File 		 teachers;
	private Scanner 	 scanner;
	
	public SignIn() throws IOException{
		setSize(375,667);
		createPanel();
		createAction();
		students = new File("students.txt");
		teachers = new File("teachers.txt");
		
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
		pic = new JLabel(new ImageIcon("img/doge.jpeg"));
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3,2));
		tea = new JRadioButton("Teacher");
		stu = new JRadioButton("Student");
		group = new ButtonGroup();
		group.add(tea);
		group.add(stu);
		namE = new JLabel("Name:");
		name = new JTextField(15);
		passworD = new JLabel("Password:");
		password = new JTextField(15);
		signIn = new JButton("Sign In");
		signUp = new JButton("Sign Up");
		panel = new JPanel();
		
		panel.add(pic);
		p.add(tea);
		p.add(stu);
		p.add(namE);
		p.add(name);
		p.add(passworD);
		p.add(password);
		panel.add(p);
		panel.add(signIn);
		panel.add(signUp);
		add(panel);
	}
	
	public void createAction() {
		class Sign implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				try {
					if(tea.isSelected()) {
						scanner = new Scanner(teachers);
						while(scanner.hasNextLine()) {
							if(name.getText().equals(scanner.next())) {
								if(password.getText().equals(scanner.next())) {
									teacher = new Teacher(name.getText(), password.getText());
									TEA tea = new TEA(teacher);
									tea.setVisible(true);
									
									JOptionPane.showMessageDialog(null, "Welcome! "+name.getText()+"!", "WELCOME" ,JOptionPane.WARNING_MESSAGE, new ImageIcon("img/Cat"+(int)(Math.random()*10)+".jpg"));
									setVisible(false);
									dispose();
									scanner.close();
									break;
								}
								else {
									JOptionPane.showMessageDialog(null, "Your password is incorrected!\nPlease try again!");
									scanner = new Scanner(teachers);
									break;
								}
							}
							if(!(scanner.hasNextLine())) {
								JOptionPane.showMessageDialog(null, "You haven't signed up yet!\nPlease sign up first!");
								scanner = new Scanner(teachers);
								break;
							}
						}
					}
					else if(stu.isSelected()) {
						scanner = new Scanner(students);
						while(scanner.hasNextLine()) {
							if(name.getText().equals(scanner.next())) {
								if(password.getText().equals(scanner.next())) {
									student = new Student(name.getText(), password.getText());
									STU stu = new STU(student);
									stu.setVisible(true);
									JOptionPane.showMessageDialog(null, "Welcome! "+name.getText()+"!", "WELCOME" ,
											JOptionPane.WARNING_MESSAGE, new ImageIcon("img/Cat"+(int)(Math.random()*10)+".jpg"));
									setVisible(false);
									dispose();
									scanner.close();
									break;
								}
								else {
									JOptionPane.showMessageDialog(null, "Your password is incorrected!\nPlease try again!");
									scanner = new Scanner(students);
									break;
								}
							}
							else {
								scanner.nextLine();
							}
							if(!(scanner.hasNextLine())) {
								JOptionPane.showMessageDialog(null, "You haven't signed up yet!\nPlease sign up first!");
								break;
							}
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Please choose you identity");
					}
				}
				catch(IOException ex) {
					
				}
			}
		}
		signIn.addActionListener(new Sign());
		
		class SignUpp implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				try {
					SignUp signuP = new SignUp();
					signuP.setVisible(true);
					setVisible(false);
					dispose();
				}
				catch(IOException ex) {
					System.out.println(ex.getMessage());
				}
			}
		}
		signUp.addActionListener(new SignUpp());
	}
}
