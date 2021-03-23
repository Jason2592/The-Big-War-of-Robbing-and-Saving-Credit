import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class SignUp extends JFrame {
	private JLabel pic;
	private JLabel namE;
	private JTextField name;
	private JLabel emaiL;
	private JTextField email;
	private JLabel passworD;
	private JTextField password;
	private JButton signUp;
	private JButton back;
	private JPanel panel;
	private File students;
	private BufferedWriter fw;
	private Scanner scanner;
	
	public SignUp() throws IOException{
		setSize(375,667);
		createPanel();
		createAction();
		students = new File("students.txt");
		
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
		pic = new JLabel(new ImageIcon("doge.jpeg"));
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3,2));
		namE = new JLabel("Name:");
		name = new JTextField(15);
		emaiL = new JLabel("Email:");
		email = new JTextField(15);
		passworD = new JLabel("Password:");
		password = new JTextField(15);
		p.add(namE);
		p.add(name);
		p.add(emaiL);
		p.add(email);
		p.add(passworD);
		p.add(password);
		
		signUp = new JButton("Sign Up");
		back = new JButton("Back");
		
		panel = new JPanel();
		panel.add(pic);
		panel.add(p);
		panel.add(signUp);
		panel.add(back);
		add(panel);
		
	}
	
	public void createAction() {
		class SignUP implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				try {
					scanner = new Scanner(students);
					while(scanner.hasNextLine()) {
						if(name.getText().equals(scanner.next())) {
							JOptionPane.showMessageDialog(null, "This name is already used!\n"
									+ "Please try an other one!");
							scanner = new Scanner(students);
							break;
						}
						else {
						
						if(password.getText().equals(null)) {							
							JOptionPane.showMessageDialog(null, "Invalid password!");
							scanner = new Scanner(students);
							break;
						}
						else {
							scanner.next();
						}
						}
						if(!(scanner.hasNext())) {
							fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(students, true),
									"UTF-8")); // 指點編碼格式，以免讀取時中文字符異常
							fw.newLine();
							fw.append(name.getText() + " " + password.getText());
							fw.flush(); // 全部寫入緩存中的內容
							JOptionPane.showMessageDialog(null, "Successfully signed up!");
							SignIn signIN = new SignIn();
							signIN.setVisible(true);
							scanner.close();
							setVisible(false);
							dispose();
							
							break;
						}
					}
				}
				catch(IOException ex) {
					System.out.println(ex.getMessage());
				}
			}
		}
		signUp.addActionListener(new SignUP());
		
		class Back implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(
			            null,
			            "Go back to the previous page?",
			            "Notification",
			            JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					try {
						SignIn s = new SignIn();
						s.setVisible(true);
						setVisible(false);
						dispose();}
					catch(IOException ex) {
						ex.getMessage();
					}
				}
			}
		}
		back.addActionListener(new Back());
	}
}
