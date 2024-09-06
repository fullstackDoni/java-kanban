package model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    private final List<Integer> subTasksIds = new ArrayList<>();

    public Epic(String name, Status status, String description) {
        super(name, Status.NEW, description);
    }

    public List<Integer> getSubTasks() {
        return subTasksIds;
    }

    public void addSubTask(int id) {
        subTasksIds.add(id);
    }

    public void removeTask(int id) {
        subTasksIds.remove(Integer.valueOf(id));
    }

    public void removeAllTasks() {
        subTasksIds.clear();
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                ", subTasksIds=" + subTasksIds +
                '}';
    }
}

