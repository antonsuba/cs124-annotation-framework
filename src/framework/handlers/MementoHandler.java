package framework.handlers;

import framework.entity.SessionMemento;

import java.util.Stack;

public class MementoHandler {
    private Stack<SessionMemento> sessionMementos = new Stack<>();

    public void push(SessionMemento sessionMemento){
        sessionMementos.push(sessionMemento);
    }

    public SessionMemento pop(){
        return sessionMementos.pop();
    }

    public boolean empty(){
        return sessionMementos.isEmpty();
    }
}
