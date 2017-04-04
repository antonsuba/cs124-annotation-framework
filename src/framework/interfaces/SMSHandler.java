package framework.interfaces;

import framework.entity.Session;
import framework.handlers.SessionHandler;
import room.RoomCommandManager;

public interface SMSHandler {
    void process(String command, String[] args, RoomCommandManager rcm, SessionHandler session);
}
