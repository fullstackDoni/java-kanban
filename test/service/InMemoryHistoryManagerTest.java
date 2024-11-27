package service;

import model.Task;
import model.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryHistoryManagerTest {

    private HistoryManager historyManager;

    @BeforeEach
    public void setUp() {
        historyManager = new InMemoryHistoryManager();
    }

    @Test
    public void addTaskToHistoryTest() {
        Task task = new Task("Задача 1", "Описание задачи 1", Status.NEW);
        historyManager.add(task);

        List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не должна быть пустой.");
        assertEquals(1, history.size(), "История должна содержать одну задачу.");
        assertEquals(task, history.get(0), "Добавленная задача должна быть в истории.");
    }

    @Test
    public void removeTaskFromHistoryTest() {
        Task task = new Task("Задача 1", "Описание задачи 1", Status.NEW);
        historyManager.add(task);
        historyManager.remove(task.getId());

        List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не должна быть null.");
        assertTrue(history.isEmpty(), "История должна быть пустой после удаления задачи.");
    }

    @Test
    public void getEmptyHistoryTest() {
        List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не должна быть null.");
        assertTrue(history.isEmpty(), "История должна быть пустой.");
    }

    @Test
    public void addDuplicateTaskToHistoryTest() {
        Task task = new Task("Задача 1", "Описание задачи 1", Status.NEW);
        historyManager.add(task);
        historyManager.add(task);

        List<Task> history = historyManager.getHistory();
        assertEquals(1, history.size(), "История должна содержать только одну задачу, даже при повторном добавлении.");
        assertEquals(task, history.get(0), "Добавленная задача должна быть в истории.");
    }

    @Test
    public void checkOrderInHistoryTest() {
        Task task1 = new Task("Задача 1", "Описание задачи 1", Status.NEW);
        task1.setId(1);
        Task task2 = new Task("Задача 2", "Описание задачи 2", Status.NEW);
        task2.setId(2);
        Task task3 = new Task("Задача 3", "Описание задачи 3", Status.NEW);
        task3.setId(3);


        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.add(task3);

        List<Task> history = historyManager.getHistory();
        assertEquals(3, history.size(), "История должна содержать три задачи.");
        assertEquals(task1, history.get(0), "Первая задача должна быть первой в истории.");
        assertEquals(task2, history.get(1), "Вторая задача должна быть второй в истории.");
        assertEquals(task3, history.get(2), "Третья задача должна быть третьей в истории.");
    }

    @Test
    public void removeNonExistentTaskFromHistoryTest() {
        historyManager.remove(999);

        List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не должна быть null.");
        assertTrue(history.isEmpty(), "История должна быть пустой при удалении несуществующей задачи.");
    }
}
