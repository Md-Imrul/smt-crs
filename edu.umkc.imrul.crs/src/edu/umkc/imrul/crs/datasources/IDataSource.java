package edu.umkc.imrul.crs.datasources;

import java.util.List;

public interface IDataSource {
	
	/** Common Actions */
	public List<String> getAllCourses();
	
	/** Student Actions */
	public List studentEnrolled(int studentId, String courseName);
	public List<String> getAllCoursesOfStudent(int studentId);
	
	/** Instructor Actins */
	public String editSyllabus(String courseName, String syllabus);
	
	/** Admin Action */
	public int addCourse(String courseName);

}
