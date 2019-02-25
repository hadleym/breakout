package main.java;

import java.awt.event.ActionEvent;

/**
 * Created by Mark Hadley on 2/24/2019.
 */
public class EndGameEvent extends ActionEvent {
    public EndGameEvent(Object source, int id, String command) {
        super(source, id, command);
    }
}
