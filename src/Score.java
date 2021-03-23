import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

public class Score{
	private String tag;
	private double scale;
	private String date;
	private double scaleScore = 0;
	
	public Score(String tag, double scale, String date) {
		this.tag   = tag;
		this.scale = scale;
		this.date  = date;
	}
	
	public String getTag() {
		return tag;
	}
	
	public double getScale() {
		return scale;
	}
	
	public String getDate() {
		return date;
	}
	
	public double getScaleScore() {
		return scaleScore;
	}
	
	public void setScaleScore(double score) {
		this.scaleScore = score * scale;
	}
	
	
}
