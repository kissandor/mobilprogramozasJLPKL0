package data;

import Interfaces.IToDo;

public class ToDo extends IToDo {

    public ToDo(Boolean completed, String todo){
        this.completed = completed;
        this.todo = todo;
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

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    private String todo;
    private Boolean completed;
}
