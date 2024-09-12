import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;
import service.Managers;
import service.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();

        Task task1 = new Task("Задача 1", "Описание задачи 1", Status.NEW);
        Task task2 = new Task("Задача 2", "Описание задачи 2", Status.IN_PROGRESS);
        Epic epic1 = new Epic("Эпик 1", "Описание эпика 1");
        SubTask subtask1 = new SubTask(epic1.getId(), "Подзадача 1", "Описание подзадачи 1", Status.NEW);

        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addEpic(epic1);
        taskManager.addSubtask(subtask1);

        System.out.println("Получение задач:");
        taskManager.getTask(task1.getId());
        taskManager.getEpic(epic1.getId());
        taskManager.getSubtask(subtask1.getId());

        System.out.println("История просмотров:");
        for (Task task : taskManager.getHistory()) {
            System.out.println(task);
        }

        printAllTasks(taskManager);
    }

    private static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getTasks()) {
            System.out.println(task);
        }

        System.out.println("Эпики:");
        for (Epic epic : manager.getEpics()) {
            System.out.println(epic);
            for (SubTask subtask : manager.getSubtasks()) {
                if (subtask.getEpicId() == epic.getId()) {
                    System.out.println("--> " + subtask);
                }
            }
        }

        System.out.println("Подзадачи:");
        for (SubTask subtask : manager.getSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("\nИстория просмотров:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }
}



