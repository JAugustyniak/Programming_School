package classes;

public class Exercise {

    private int id = 0;
    private String title;
    private String description;

    public Exercise(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Exercise() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void printExerciseInfo() {
        System.out.println("Id exercise: " + this.id + ", title: " + this.title + ", description: " + this.description);
    }
}
