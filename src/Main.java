import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;
import service.TaskManager;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("Задача 1", Status.NEW, "Описание задачи 1");
        Task task2 = new Task("Задача 2", Status.NEW, "Описание задачи 2");

        taskManager.createTask(task1);
        taskManager.createTask(task2);

        System.out.println("Все задачи:");
        System.out.println(taskManager.getAllTasks());

        Epic epic1 = new Epic("Эпик 1", "Описание эпика 1");
        taskManager.createEpic(epic1);

        System.out.println("Все эпики:");
        System.out.println(taskManager.getAllEpics());

        SubTask subTask1 = new SubTask(epic1.getId(), "Подзадача 1", "Описание подзадачи 1", Status.NEW);
        SubTask subTask2 = new SubTask(epic1.getId(), "Подзадача 2", "Описание подзадачи 2", Status.NEW);

        taskManager.createSubTask(subTask1);
        taskManager.createSubTask(subTask2);

        System.out.println("Подзадачи эпика 1:");
        System.out.println(taskManager.getSubTasksOfEpic(epic1));

        subTask1.setStatus(Status.DONE);
        taskManager.updateSubTask(subTask1);

        System.out.println("Обновленные подзадачи эпика 1:");
        System.out.println(taskManager.getSubTasksOfEpic(epic1));

        System.out.println("Статус эпика 1 после обновления подзадач: " + epic1.getStatus());

        taskManager.deleteSubTaskById(subTask2.getId());

        System.out.println("Подзадачи эпика 1 после удаления подзадачи 2:");
        System.out.println(taskManager.getSubTasksOfEpic(epic1));

        taskManager.deleteAllTasks();
        System.out.println("Все задачи после удаления:");
        System.out.println(taskManager.getAllTasks());
    }
}

