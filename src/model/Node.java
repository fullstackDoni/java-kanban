package model;

public class Node {
    private Task Task;
    private Node prev;
    private Node next;

    public Node() {

    }

    public Node(Task Task, Node prev, Node next) {
        this.Task = Task;
        this.prev = prev;
        this.next = next;
    }

    public Task getTask() {
        return Task;
    }

    public void setTask(Task task) {
        this.Task = task;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

