package solution.handlers;

import framework.annotations.SMSAnnotation;
import framework.entity.Session;
import framework.handlers.SessionHandler;
import framework.interfaces.SMSHandler;
import framework.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import room.RoomCommandManager;

@Component
@SMSAnnotation(trigger = "UNDO")
public class UndoHandler implements SMSHandler{

    @Autowired
    public static SessionRepository rep;

    @Override
    public void process(String command, String[] args, RoomCommandManager rcm, SessionHandler sessionHandler) {
        if(!sessionHandler.undoAvailable()) {
            System.out.println("UNDO not available");
            return;
        }

        sessionHandler.restoreState();

        Session session = sessionHandler.getSession();
        System.out.println(session);
        rep.saveAndFlush(session);

        System.out.println("Previous Session state restored!");
        System.out.println("Room" + sessionHandler.getSession().getRoom());
        System.out.println("GameState:" + sessionHandler.getSession().getGameState());
    }
}
