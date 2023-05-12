/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmart.entities;

/**
 *
 * @author GhassenZ
 */
public class Participation {
    private int participationlD;
    private int userID;
    private int eventlD;
    private String attendanceStatus;    

    public Participation() {
    }

    public Participation(int participationlD, int userID, int eventlD, String attendanceStatus) {
        this.participationlD = participationlD;
        this.userID = userID;
        this.eventlD = eventlD;
        this.attendanceStatus = attendanceStatus;
    }

    public Participation(int userID, int eventlD, String attendanceStatus) {
        this.userID = userID;
        this.eventlD = eventlD;
        this.attendanceStatus = attendanceStatus;
    }

    public Participation(int userID, int eventlD) {
        this.userID = userID;
        this.eventlD = eventlD;
    }

    public Participation(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public int getParticipationlD() {
        return participationlD;
    }

    public void setParticipationlD(int participationlD) {
        this.participationlD = participationlD;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getEventlD() {
        return eventlD;
    }

    public void setEventlD(int eventlD) {
        this.eventlD = eventlD;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    @Override
    public String toString() {
        return "Participation{" + "participationlD=" + participationlD + ", userID=" + userID + ", eventlD=" + eventlD + ", attendanceStatus=" + attendanceStatus + '}';
    }
    
}
