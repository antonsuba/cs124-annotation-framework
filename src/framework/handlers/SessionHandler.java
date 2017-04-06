package framework.handlers;

import framework.entity.Session;

public class SessionHandler {
    private Session session;

    private boolean registered = false;
    private boolean started = false;
    private boolean inARoom = false;

    public SessionHandler(){
        session = new Session();
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isInARoom() {
        return inARoom;
    }

    public void setInARoom(boolean inARoom) {
        this.inARoom = inARoom;
    }
}
