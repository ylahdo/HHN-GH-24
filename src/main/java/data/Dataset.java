package data;

import java.util.List;

public class Dataset {

    private List<Event> events;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "Dataset{" +
                "events=" + events +
                '}';
    }
}