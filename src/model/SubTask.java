package model;

public class SubTask extends Task {
    private final int epicId;

    public SubTask(int epicId, String name, String description) {
        super(name, Status.NEW, description);
        this.epicId = epicId;
    }

    public SubTask(int epicId, String name, String description, Status status) {
        super(name, status, description);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                ", epicId=" + epicId +
                '}';
    }
}

