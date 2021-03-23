import java.util.ArrayList;
import java.io.*;


public class Teacher {
	private String teaName;
	private String password;
	private ArrayList<Course> courses;
	
	private Student jacky;
	private Course sad;
	private Course mis;
	
	public Teacher(String teaName, String password) throws IOException{
		this.teaName  = teaName;
		this.password = password;
		courses = new ArrayList<Course>();
		
		jacky = new Student("LeiHa", "1234");
		sad = new Course("SAD", 3, "TueD56", this);
		mis = new Course("MIS", 3, "Wed234", this);
		
		addCourse(sad);
		addCourse(mis);
		jacky.addCourse(sad);
		jacky.addCourse(mis);
		sad.addScale("HW1"  , 0.1, "11/11");
		sad.addScale("HW2"  , 0.1, "11/11");
		sad.addScale("Quiz1", 0.3, "11/11");
		sad.addScale("HW3"  , 0.1, "11/11");
		sad.addScale("HW4"  , 0.1, "11/11");
		sad.addScale("Quiz2", 0.3, "12/27");
	}
	
	public Teacher(String teaName, String password, Boolean t) throws IOException{
		this.teaName  = teaName;
		this.password = password;
		courses = new ArrayList<Course>();
		
	}
	
	public String getTeaName() {
		return this.teaName;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public ArrayList<Course> getCourses(){
		return courses;
	}
	
	public void addCourse(Course course) {
		if(!(courses.contains(course))) {
			courses.add(course);
		}
	}
	
}
