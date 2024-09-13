package service;

import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class InMemoryTaskManagerTest {

    private TaskManager taskManager;

    @Before
    public void setUp() {
        taskManager = new InMemoryTaskManager();;
    }

    @Test
    public void addTaskTest() {
        Task task = new Task("Задача 1", "Описание задачи 1", Status.NEW);
        taskManager.addTask(task);

        Task retrievedTask = taskManager.getTask(task.getId());
        Assertions.assertEquals(task, retrievedTask, "Задача должна быть получена правильно.");
    }

    @Test
    public void addEpicTest() {
        Epic epic = new Epic("Эпик 1", "Описание эпика 1");
        taskManager.addEpic(epic);

        Epic retrievedEpic = taskManager.getEpic(epic.getId());
        Assertions.assertEquals(epic, retrievedEpic, "Эпик должен быть получен правильно.");
    }

    @Test
    public void addSubTaskTest() {
        Epic epic = new Epic("Эпик 1", "Описание эпика 1");
        taskManager.addEpic(epic);

        SubTask subtask = new SubTask(epic.getId(), "Подзадача 1", "Описание подзадачи 1", Status.NEW);
        taskManager.addSubtask(subtask);

        SubTask retrievedSubtask = taskManager.getSubtask(subtask.getId());
        Assertions.assertEquals(subtask, retrievedSubtask, "Подзадача должна быть получена правильно.");
    }

    @Test
    public void testTaskUpdateTest() {
        Task task = new Task("Задача 1", "Описание задачи 1", Status.NEW);
        taskManager.addTask(task);

        Task updatedTask = new Task(task.getId(), "Обновленная задача", "Обновленное описание", Status.IN_PROGRESS);
        taskManager.updateTask(updatedTask);

        Task retrievedTask = taskManager.getTask(task.getId());
        Assertions.assertEquals(updatedTask, retrievedTask, "Обновленная задача должна быть получена правильно.");
    }

    @Test
    public void testEpicUpdate() {
        Epic epic = new Epic("Эпик 1", "Описание эпика 1");
        taskManager.addEpic(epic);

        Epic updatedEpic = new Epic(epic.getId(), "Обновленный эпик", "Обновленное описание");
        taskManager.updateEpic(updatedEpic);

        Epic retrievedEpic = taskManager.getEpic(epic.getId());
        Assertions.assertEquals(updatedEpic, retrievedEpic, "Обновленный эпик должен быть получен правильно.");
    }

    @Test
    public void testRemoveTask() {
        Task task = new Task("Задача 1", "Описание задачи 1", Status.NEW);
        taskManager.addTask(task);

        taskManager.removeTask(task.getId());

        assertNull(taskManager.getTask(task.getId()), "Задача должна быть удалена.");
    }

    @Test
    public void testRemoveEpic() {
        Epic epic = new Epic("Эпик 1", "Описание эпика 1");
        taskManager.addEpic(epic);

        SubTask subtask = new SubTask(epic.getId(), "Подзадача 1", "Описание подзадачи 1", Status.NEW);
        taskManager.addSubtask(subtask);

        taskManager.removeEpic(epic.getId());

        assertNull(taskManager.getEpic(epic.getId()), "Эпик должен быть удален.");
        assertNull(taskManager.getSubtask(subtask.getId()), "Подзадача должна быть удалена.");
    }

    @Test
    public void testRemoveSubtask() {
        Epic epic = new Epic("Эпик 1", "Описание эпика 1");
        taskManager.addEpic(epic);

        SubTask subtask = new SubTask(epic.getId(), "Подзадача 1", "Описание подзадачи 1", Status.NEW);
        taskManager.addSubtask(subtask);

        taskManager.removeSubtask(subtask.getId());

        assertNull(taskManager.getSubtask(subtask.getId()), "Подзадача должна быть удалена.");
    }

    @Test
    public void testGetHistory() {
        Task task1 = new Task("Задача 1", "Описание задачи 1", Status.NEW);
        Task task2 = new Task("Задача 2", "Описание задачи 2", Status.IN_PROGRESS);
        taskManager.addTask(task1);
        taskManager.addTask(task2);

        taskManager.getTask(task1.getId());
        taskManager.getTask(task2.getId());

        List<Task> history = taskManager.getHistory();
        assertTrue(history.contains(task1), "История должна содержать Задачу 1.");
        assertTrue(history.contains(task2), "История должна содержать Задачу 2.");
    }
}

