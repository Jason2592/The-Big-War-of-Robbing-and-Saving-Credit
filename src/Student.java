import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class Student {
	private String 				stuName;
	private String				password;
	private ArrayList<Course> 	courses;
	private File 			studentInfo;
	private Teacher jacky = new Teacher("Jacky", "1234", true);
	private Course sad = new Course("SAD", 3, "TueD56", jacky);
	private Course mis = new Course("MIS", 3, "Wed234", jacky);
	private Course ds  = new Course("DS" , 3, "Thu234", jacky);
	
	public Student(String id, String password) throws IOException {
		this.stuName   	 = id;
		this.password  	 = password;
		this.courses   	 = new ArrayList<Course>();
		this.studentInfo = new File("students.txt");
		
		addCourse(sad);
		addCourse(mis);
		addCourse(ds);
		jacky.addCourse(sad);
		jacky.addCourse(mis);
		jacky.addCourse(ds);
		sad.addScale("HW1"  , 0.1, "11/11");
		sad.addScale("HW2"  , 0.1, "11/11");
		sad.addScale("Quiz1", 0.3, "11/11");
		sad.addScale("HW3"  , 0.1, "11/11");
		sad.addScale("HW4"  , 0.1, "11/11");
		sad.addScale("Quiz2", 0.3, "12/27");
	}
	
	public void addCourse(Course course) {
		if(courses.contains(course) == false) {
			courses.add(course);
		}
		courses.get((courses.indexOf(course))).addStudent(this);;
	}

	public String getStuName() {
		return stuName;
	}

	public String getPassword() {
		return password;
	}
	
	public ArrayList<Course> getCourses(){
		return courses;
	}
	
}
