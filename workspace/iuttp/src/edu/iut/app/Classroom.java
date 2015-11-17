package edu.iut.app;

public class Classroom {
	
	public Classroom() {
		classRoomNumber="not affected";
	}
	public Classroom(String classRoomNumber) {
		/* EX2: AFFECTATION */;
		this.classRoomNumber = classRoomNumber;
	}
	
	public void setClassroomNumber(String number) {
		/* EX2: AFFECTATION */;
		this.classRoomNumber = number;
	}
	public String getClassRoomNumber() {
		return classRoomNumber;
	}
	
	protected String classRoomNumber;
}
