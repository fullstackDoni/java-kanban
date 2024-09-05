package model;

public class SubTask extends Task{
    private final int epicId;

    public SubTask(int id, String name, Status status,String description) {
        super(name, Status.NEW, description);
        this.epicId = id;
    }

    public SubTask(int id, String name, String description) {
        super(name, Status.NEW, description);
        this.epicId = id;
    }

    public int getEpic(){
        return epicId;
    }

    public SubTask(int id, String name, String description, Status status, int epicId) {
        super(id, name, description, status);
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + epicId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
