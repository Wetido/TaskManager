package com.example.taskmanager.Model;

import java.util.Date;
import java.util.UUID;

public class Task {

    private UUID Id;
    private String Title;
    private Date Date;
    private boolean Solved;
    private boolean IsDifficult;

    public Task() {
        this.Id = UUID.randomUUID();
        this.Date = new Date();
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public boolean isMolved() {
        return Solved;
    }

    public void setSolved(boolean Solved) {
        this.Solved = Solved;
    }

    public boolean isSolved() {
        return Solved;
    }

    public boolean isDifficult() {
        return IsDifficult;
    }

    public void setDifficult(boolean difficult) {
        IsDifficult = difficult;
    }
}
