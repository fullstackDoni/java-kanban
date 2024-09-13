package service;

import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {
    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, SubTask> subtasks = new HashMap<>();
    private int id = 0;
    private final HistoryManager historyManager = Managers.getDefaultHistory();

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public List<SubTask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public List<SubTask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public Task getTask(int id) {
        Task task = tasks.get(id);
        if (task != null) {
            historyManager.add(task);
        }
        return task;
    }

    @Override
    public Epic getEpic(int id) {
        Epic epic = epics.get(id);
        if (epic != null) {
            historyManager.add(epic);
        }
        return epic;
    }

    @Override
    public SubTask getSubtask(int id) {
        SubTask subtask = subtasks.get(id);
        if (subtask != null) {
            historyManager.add(subtask);
        }
        return subtask;
    }

    @Override
    public void addTask(Task task) {
        task.setId(++id);
        tasks.put(task.getId(), task);
    }

    @Override
    public void addEpic(Epic epic) {
        epic.setId(++id);
        epics.put(epic.getId(), epic);
    }

    @Override
    public void addSubtask(SubTask subtask) {
        Epic epic = epics.get(subtask.getEpicId());
        if (epic != null) {
            subtask.setId(++id);
            subtasks.put(subtask.getId(), subtask);
            epic.addSubTask(subtask.getId());
        } else {
            System.out.println("Эпик с ID " + subtask.getEpicId() + " не найден.");
        }
    }

    @Override
    public void updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        } else {
            System.out.println("Задача с ID " + task.getId() + " не найдена.");
        }
    }

    @Override
    public void updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            epics.put(epic.getId(), epic);
            updateStatus(epic);
        } else {
            System.out.println("Эпик с ID " + epic.getId() + " не найден.");
        }
    }

    @Override
    public void updateSubtask(SubTask subtask) {
        if (subtasks.containsKey(subtask.getId())) {
            subtasks.put(subtask.getId(), subtask);
            Epic epic = epics.get(subtask.getEpicId());
            if (epic != null) {
                updateStatus(epic);
            }
        } else {
            System.out.println("Подзадача с ID " + subtask.getId() + " не найдена.");
        }
    }

    @Override
    public void updateStatus(Epic epic) {
        List<SubTask> subTasks = new ArrayList<>();
        for (Integer subTaskId : epic.getSubTasks()) {
            SubTask subTask = subtasks.get(subTaskId);
            if (subTask != null) {
                subTasks.add(subTask);
            }
        }

        if (subTasks.isEmpty()) {
            epic.setStatus(Status.NEW);
            return;
        }

        boolean hasInProgress = false;
        boolean hasNotDone = false;

        for (SubTask subTask : subTasks) {
            if (subTask.getStatus() == Status.IN_PROGRESS) {
                hasInProgress = true;
            } else if (subTask.getStatus() != Status.DONE) {
                hasNotDone = true;
            }
        }

        if (hasInProgress) {
            epic.setStatus(Status.IN_PROGRESS);
        } else if (!hasNotDone) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.NEW);
        }
    }

    @Override
    public void removeTask(int id) {
        tasks.remove(id);
    }

    @Override
    public void removeEpic(int id) {
        Epic epic = epics.remove(id);
        if (epic != null) {
            for (Integer subtaskId : epic.getSubTasks()) {
                subtasks.remove(subtaskId);
            }
        }
    }

    @Override
    public void removeSubtask(int id) {
        SubTask subtask = subtasks.remove(id);
        if (subtask != null) {
            Epic epic = epics.get(subtask.getEpicId());
            if (epic != null) {
                epic.removeSubTask(id);
                updateStatus(epic);
            }
        }
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }
}


