package modules;

import java.sql.Timestamp;

public class Submission {
    private int id;
    private int userId;
    private int problemId;
    private Timestamp submissionTime;
    private String verdict;

    public Submission(int id, int userId, int problemId, Timestamp submissionTime, String verdict) {
        this.id = id;
        this.userId = userId;
        this.problemId = problemId;
        this.submissionTime = submissionTime;
        this.verdict = verdict;
    }

    public int getId() {
        return id;
    }
    public int getUserId() {
        return userId;
    }
    public int getProblemId() {
        return problemId;
    }
    public Timestamp getSubmissionTime() {
        return submissionTime;
    }
    public String getVerdict() {
        return verdict;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }
    public void setSubmissionTime(Timestamp submissionTime) {
        this.submissionTime = submissionTime;
    }
    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    @Override
    public String toString() {
        return "Submission " +
                "[id=" + id +
                ", userId=" + userId +
                ", problemId=" + problemId +
                ", submissionTime=" + submissionTime +
                ", verdict=" + verdict + "]";
    }
}
