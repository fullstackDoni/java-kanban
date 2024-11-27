import model.Epic;
import model.enums.Status;
import model.SubTask;
import model.Task;
import service.Managers;
import service.TaskManager;
public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();

        Task task1 = new Task("Задача по Java","Нужно создать новый Bean",Status.NEW);
        Task task2 = new Task("Задача по Spring Boot","Нужно создать новый Сервис",Status.NEW);
        Epic epic1 = new Epic("Задача по Эпик","Эпики задачи",Status.NEW);
        Epic epic2 = new Epic("Задача по Эпик два","Эпики по задачи два",Status.NEW);
        SubTask subTask = new SubTask(1,"Подзадача по Эпик","Нужно ещё добавить эпики",Status.NEW, epic1.getId());

        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addEpic(epic1);
        taskManager.addEpic(epic2);
        taskManager.addSubtask(subTask);

        System.out.println("Получение Задач:");
        printTask(taskManager,task1.getId());
        printTask(taskManager,task2.getId());
        printEpic(taskManager,epic1.getId());
        printEpic(taskManager,epic2.getId());
        printSubtask(taskManager,subTask.getId());

        System.out.println("История просмотров:");
        printHistory(taskManager);

        System.out.println("Удаление задачи 1 и эпика 1:");
        taskManager.removeTask(task1.getId());
        taskManager.removeEpic(epic1.getId());

        System.out.println("История просмотров после удаления:");
        printHistory(taskManager);
        printAllTasks(taskManager);
        printSubtask(taskManager,subTask.getId());
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
