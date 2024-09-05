import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;
import service.TaskManager;

import static model.Status.NEW;
import static model.Status.IN_PROGRESS;
import static model.Status.DONE;

public class Main {

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        Task task = new Task("done tasks", NEW, "done tasks");
        System.out.println("Created task" + task);
        Epic epic = new Epic("epic epic", IN_PROGRESS, "epic epic");
        System.out.println("created epic " + epic);
        SubTask subTask = new SubTask(1,"test", IN_PROGRESS, "epic epic");
    }
}
