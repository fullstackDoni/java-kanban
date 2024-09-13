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
        Task retrievedTask1 = taskManager.getTask(task1.getId());
        Task retrievedTask2 = taskManager.getTask(task2.getId());
        Epic retrievedEpic1 = taskManager.getEpic(epic1.getId());
        SubTask retrievedSubtask1 = taskManager.getSubtask(subtask1.getId());

        System.out.println("Задача 1: " + retrievedTask1);
        System.out.println("Задача 2: " + retrievedTask2);
        System.out.println("Эпик 1: " + retrievedEpic1);
        System.out.println("Подзадача 1: " + retrievedSubtask1);

        System.out.println("\nИстория просмотров:");
        for (Task task : taskManager.getHistory()) {
            System.out.println(task);
        }

        printAllTasks(taskManager);
    }

    private static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }

        System.out.println("\nЭпики:");
        for (Epic epic : manager.getAllEpics()) {
            System.out.println(epic);
            for (SubTask subtask : manager.getAllSubtasks()) {
                if (subtask.getEpicId() == epic.getId()) {
                    System.out.println("--> " + subtask);
                }
            }
        }

        System.out.println("\nПодзадачи:");
        for (SubTask subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("\nИстория просмотров:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }
}



