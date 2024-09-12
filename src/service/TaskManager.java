package service;

import model.Epic;
import model.SubTask;
import model.Task;

import java.util.List;

public interface TaskManager {
    List<Task> getTasks();
    List<Epic> getEpics();
    List<SubTask> getSubtasks();

    Task getTask(int id);
    Epic getEpic(int id);
    SubTask getSubtask(int id);

    void addTask(Task task);
    void addEpic(Epic epic);
    void addSubtask(SubTask subtask);

    void updateTask(Task task);
    void updateEpic(Epic epic);
    void updateSubtask(SubTask subtask);

    void removeTask(int id);
    void removeEpic(int id);
    void removeSubtask(int id);

    List<Task> getHistory();
}


