import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Course {
	private String  		   name;
	private int 			   credits;
	private String 			   time;
	private boolean			   alarm;
	private ArrayList<Score>   scales;
	private double			   score;
	private int				   target;
	private File		   courseInfo;
	private ArrayList<Student> students;
	private Teacher 		   teacher;
	
	public Course(String name, int credits, String time, Teacher teacher) throws IOException {
		this.name       = name;
		this.credits    = credits;
		this.time	    = time;
		this.alarm		= false;
		this.scales		= new ArrayList<Score>();
		this.students   = new ArrayList<Student>();
		this.teacher    = teacher;
		this.courseInfo = new File("courses.txt");
	}
	
	public void addScale(String tag, double scale, String date) {
		scales.add(new Score(tag, scale, date));
	}

	public ArrayList<Score> getScales(){
		return scales;
	}
	
	public String getName() {
		return name;
	}

	public int getCredits() {
		return credits;
	}

	public String getTime() {
		return time;
	}
	
	public int getTarget() {
		return target;
	}
	
	public boolean getAlarm() {
		return alarm;
	}
	
	public void setAlarm(boolean alarm) {
		this.alarm = alarm;
	}
	
	public void setTarget(int target) {
		this.target = target;
	}
	
	public double calculateScore() {
		for(Score a : scales) {
			score += a.getScaleScore();
		}
		
		return score;
	}
	
	public void addStudent(Student student) {
		if(!(students.contains(student))) {
			students.add(student);
		}
	}
	
	
	public void alarm() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd");
		LocalDateTime now = LocalDateTime.now();
		for(Score s : scales) {
			if(s.getDate().equals(dtf.format(now))) {
				JOptionPane.showMessageDialog(null, "Your "+name+" score is way too low,\n stop spending time on your IG and games, OK?", "WARNING" ,JOptionPane.WARNING_MESSAGE, new ImageIcon("jacky.png"));
			}
		}
	}
}
