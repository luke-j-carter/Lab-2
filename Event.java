import java.time.LocalDateTime;

public abstract class Event implements Comparable<Event> {
    protected String name;
    protected LocalDateTime dateTime;

    //constructor
    public Event(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    //gets the name of the event
    public abstract String getName();

    //gets the start time of the event
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    //sets the start time of the event
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    //sets the name of the event
    public void setName(String name) {
        this.name = name;
    }

    //compares the incoming event with the current
    public int compareTo(Event e) {
        return this.dateTime.compareTo(e.dateTime);
    }
}
