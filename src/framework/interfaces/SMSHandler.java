package framework.interfaces;

import framework.handlers.SessionHandler;
import room.RoomCommandManager;

public interface SMSHandler {
    //void process(String command, String[] args, RoomCommandManager rcm, Session session);
    void process(String command, String[] args, RoomCommandManager rcm, SessionHandler session);
}
