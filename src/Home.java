import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class Home extends JFrame {
	private JLabel pic;
	private JButton start;
	private JPanel panel;
	
	private Student student;
	
	public Home() {
		setSize(375,667);
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
		pic = new JLabel(new ImageIcon("img/doge.jpeg"));
		start = new JButton("Start the tour");
		panel = new JPanel();
		
		panel.add(pic);
		panel.add(start);
		add(panel);
	}
	
	public void createAction() {
		class Start implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				try {
					SignIn signIn = new SignIn();
					signIn.setVisible(true);
					setVisible(false);
					dispose();
				}
				catch(IOException ex) {
					ex.getMessage();
				}
			}
		}
		start.addActionListener(new Start());
	}
	
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public static void main(String[] args) {
		Home test = new Home();
		test.setDefaultCloseOperation(EXIT_ON_CLOSE);
		test.setVisible(true);
	}
}
