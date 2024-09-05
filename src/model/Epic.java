package model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task{

    private final List<Integer> subTasksids = new ArrayList<>();
    public Epic(String name, Status status, String description){
        super(name, Status.NEW, description);
    }
    public List<Integer> getSubTasks(){
        return subTasksids;
    }
    public void addSubTask(SubTask subTask){
        subTasksids.add(subTask.getId());
    }
    public void removeTask(SubTask subTask){
        subTasksids.remove(subTask.getId());
    }

    public void removeAllTask(SubTask subTask){
        subTasksids.clear();
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                ", subTasksids=" + subTasksids +
                '}';
    }

}
