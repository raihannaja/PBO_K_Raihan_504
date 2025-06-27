package model;

public class Task implements Verifiable {
    private String name, givenDate, deadline, description;
    private boolean done;

    public Task(String name, String givenDate, String deadline, String description, boolean done) {
        this.name = name;
        this.givenDate = givenDate;
        this.deadline = deadline;
        this.description = description;
        this.done = done;
    }

    public String getName() { return name; }
    public String getGivenDate() { return givenDate; }
    public String getDeadline() { return deadline; }
    public String getDescription() { return description; }
    public boolean isDone() { return done; }

    public void setName(String name) { this.name = name; }
    public void setGivenDate(String givenDate) { this.givenDate = givenDate; }
    public void setDeadline(String deadline) { this.deadline = deadline; }
    public void setDescription(String description) { this.description = description; }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setDone(String status) {
        this.done = status.equalsIgnoreCase("sudah");
    }


    public String getStatusText() {
        return done ? "Sudah" : "Belum";
    }

    @Override
    public boolean isValid() {
        return name != null && !name.isEmpty() &&
                givenDate != null && !givenDate.isEmpty() &&
                deadline != null && !deadline.isEmpty();
    }
}
