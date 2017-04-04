package framework.interfaces;

<<<<<<< HEAD
import framework.entity.Session;
=======
>>>>>>> refs/remotes/origin/master
import framework.handlers.SessionHandler;
import room.RoomCommandManager;

public interface SMSHandler {
<<<<<<< HEAD
=======
    //void process(String command, String[] args, RoomCommandManager rcm, Session session);
>>>>>>> refs/remotes/origin/master
    void process(String command, String[] args, RoomCommandManager rcm, SessionHandler session);
}
