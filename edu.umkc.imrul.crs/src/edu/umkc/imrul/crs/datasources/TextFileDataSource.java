package edu.umkc.imrul.crs.datasources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TextFileDataSource implements IDataSource {
	
	private static final String studentCourseMap = "resources/StudentCourseMap";
	private static final String courseList = "resources/commonClassList";

	public TextFileDataSource() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List studentEnrolled(int studentId, String courseName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String editSyllabus(String courseName, String syllabus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addCourse(String courseName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getAllCourses() {

		List<String> allCourses = new ArrayList<String>();
		try {
			URL url = new URL("platform:/plugin/edu.umkc.imrul.crs/"
					+ courseList);
			BufferedReader br = null;
			br = new BufferedReader(new InputStreamReader(url.openConnection()
					.getInputStream()));
			
			String line;
			while (((line = br.readLine()) != null)) {
				line = line.trim();
				if (line.length() > 0) {
					allCourses.add(line);
				}
			}
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return allCourses;
	}

	
	@Override
	public List<String> getAllCoursesOfStudent(int studentId) {
		
		List<String> allCourses = new ArrayList<String>();
		try {
			URL url = new URL("platform:/plugin/edu.umkc.imrul.crs/"
					+ studentCourseMap);
			BufferedReader br = null;
			br = new BufferedReader(new InputStreamReader(url.openConnection()
					.getInputStream()));
			
			String line;
			while (((line = br.readLine()) != null)) {
				line = line.trim();
				if (line.length() > 0) {
					
					String[] studentCourseEntries = line.split(":", 2); // id:course -> 123:abc,def,xyz
			
					if (Integer.parseInt(studentCourseEntries[0]) == studentId) {
						
						String[] courses = studentCourseEntries[1].split(",");
						for (int i = 0; i < courses.length; i++) {
							allCourses.add(courses[i]);
						}
					}
				}
			}
			
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return allCourses;
	}
}
