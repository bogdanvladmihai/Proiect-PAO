package modules;

import java.sql.Timestamp;

public class Contest {
    private int contestId;
    private String name;
    private Timestamp startTime;
    private double duration;

    public Contest(int contestId, String name, Timestamp startTime, double duration) {
        this.contestId = contestId;
        this.name = name;
        this.startTime = startTime;
        this.duration = duration;
    }

    public int getContestId() {
        return contestId;
    }
    public String getName() {
        return name;
    }
    public Timestamp getStartTime() {
        return startTime;
    }
    public double getDuration() {
        return duration;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }
    public void setDuration(double duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Contest " +
                "[contestId=" + contestId +
                ", name=" + name +
                ", startTime=" + startTime +
                ", duration=" + duration + "]";
    }
}
