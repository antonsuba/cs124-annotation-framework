package framework.interfaces;

import framework.entity.Session;
import room.RoomCommandManager;

public interface SMSHandler {
    void process(String command, String[] args, RoomCommandManager rcm, Session session);
}
