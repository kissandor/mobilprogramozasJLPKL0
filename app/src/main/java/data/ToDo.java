package data;

import Interfaces.IToDo;

public class ToDo extends IToDo {

    public ToDo(boolean completed, String todo){
        this.completed = completed;
        this.todo = todo;
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    private String todo;
    private boolean completed;
    public int id;
}
