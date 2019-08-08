package classes;


import java.sql.Date;
import java.time.LocalDate;

import static java.time.LocalDate.now;

public class Solution {

    private int id = 0;
    private LocalDate created = now();
    private LocalDate updated;
    private String description;
    private int exerciseId;
    private int usersId;

    public Solution () {
    }

    public Solution(LocalDate created, LocalDate updated, String description, int exerciseId, int usersId) {
        this.created = created;
        this.updated = updated;
        this.description = description;
        this.exerciseId = exerciseId;
        this.usersId = usersId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDate updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public void setCreated(Date created) {

    }

    public void setUpdated(Date updated) {

    }

    public void printSolutionInfo () {
        System.out.println(this.getId() + " " + this.getCreated() + " " + this.getUpdated() + " " +
                this.getDescription() + " " + this.getExerciseId() + " " + this.getUsersId());
    }
}
