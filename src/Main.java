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
        Task task3 = new Task("Задача 3", "Описание задачи 3", Status.DONE);
        Task task4 = new Task("Задача 4", "Описание задачи 4", Status.DONE);
        Epic epic1 = new Epic("Эпик 1", "Описание эпика 1");
        SubTask subtask1 = new SubTask(epic1.getId(), "Подзадача 1", "Описание подзадачи 1", Status.NEW);
        Epic epic2 = new Epic("Эпик 2", "Описание эпика 2");
        SubTask subTask2 = new SubTask(epic1.getId(), "Подзадача 1", "Описание подзадачи 1", Status.NEW);

        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);
        taskManager.addTask(task4);
        taskManager.addEpic(epic1);
        taskManager.addSubtask(subtask1);
        taskManager.addEpic(epic2);
        taskManager.addSubtask(subTask2);

        System.out.println("Получение задач:");
        printTask(taskManager, task1.getId());
        printTask(taskManager, task2.getId());
        printTask(taskManager, task3.getId());
        printTask(taskManager,task4.getId());
        printEpic(taskManager, epic1.getId());
        printSubtask(taskManager, subtask1.getId());
        printEpic(taskManager, epic2.getId());
        printSubtask(taskManager,subTask2.getId());

        System.out.println("История просмотров:");
        printHistory(taskManager);

        printAllTasks(taskManager);
    }

    private static void printTask(TaskManager manager, int id) {
        Task task = manager.getTask(id);
        if (task != null) {
            System.out.println("Задача " + id + ": " + task);
        } else {
            System.out.println("Задача " + id + " не найдена.");
        }
    }

    private static void printEpic(TaskManager manager, int id) {
        Epic epic = manager.getEpic(id);
        if (epic != null) {
            System.out.println("Эпик " + id + ": " + epic);
        } else {
            System.out.println("Эпик " + id + " не найден.");
        }
    }

    private static void printSubtask(TaskManager manager, int id) {
        SubTask subtask = manager.getSubtask(id);
        if (subtask != null) {
            System.out.println("Подзадача " + id + ": " + subtask);
        } else {
            System.out.println("Подзадача " + id + " не найдена.");
        }
    }

    private static void printHistory(TaskManager manager) {
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }

    private static void printAllTasks(TaskManager manager) {
        System.out.println("Все задачи:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }

        System.out.println("Все эпики:");
        for (Epic epic : manager.getAllEpics()) {
            System.out.println(epic);
            for (SubTask subtask : manager.getAllSubtasks()) {
                if (subtask.getEpicId() == epic.getId()) {
                    System.out.println("--> " + subtask);
                }
            }
        }

        System.out.println("Все подзадачи:");
        for (SubTask subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("История просмотров:");
        printHistory(manager);
    }
}



