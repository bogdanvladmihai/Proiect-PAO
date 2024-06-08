package modules;

import java.sql.Time;
import java.sql.Timestamp;

public class Task {
    private int taskId;
    private String name;
    private String description;
    private String author;
    private String contest;
    private int difficulty;
    private Timestamp createdAt;

    public Task(int taskId, String name, String description, String author, String contest, int difficulty, Timestamp createdAt) {
        this.taskId = taskId;
        this.name = name;
        this.description = description;
        this.author = author;
        this.contest = contest;
        this.difficulty = difficulty;
        this.createdAt = createdAt;
    }

    public int getTaskId() {
        return taskId;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getAuthor() {
        return author;
    }
    public String getContest() {
        return contest;
    }
    public int getDifficulty() {
        return difficulty;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setContest(String contest) {
        this.contest = contest;
    }
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Task " +
                "[taskId=" + taskId + ", " +
                "name=" + name +
                ", description=" + description +
                ", author=" + author +
                ", contest=" + contest +
                ", difficulty=" + difficulty +
                ", createdAt=" + createdAt + "]";
    }
}
