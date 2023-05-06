package pt.isec.pa.tinypac.ui.gui.javafx;

import javafx.event.Event;
import javafx.event.EventType;

public class CustomEvent extends Event {

    public static final EventType<CustomEvent> CUSTOM_EVENT_TYPE =
            new EventType<>(Event.ANY, "CUSTOM_EVENT");

    public CustomEvent() {
        super(CUSTOM_EVENT_TYPE);
    }
}
