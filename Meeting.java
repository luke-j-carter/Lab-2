import java.time.Duration;
import java.time.LocalDateTime;

public class Meeting extends Event implements Completable {
    private LocalDateTime endDateTime;
    private String location;
    private boolean complete;

    //constructor
    public Meeting(String name, LocalDateTime start, LocalDateTime end, String location) {
        super(name, start);
        this.endDateTime = end;
        this.location = location;
        this.complete = false;
    }

    //gets the name of the meeting
    public String getName() {
        return name;
    }

    //gets the end time of the meeting
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    //returns the time between the start and end time
    public Duration getDuration() {
        return Duration.between(dateTime, endDateTime);
    }

    //returns the location of the meeting
    public String getLocation() {
        return location;
    }

    //sets the end time of the meeting
    public void setEndDateTime(LocalDateTime end) {
        this.endDateTime = end;
    }

    //sets the location of the meeting
    public void setLocation(String location) {
        this.location = location;
    }

    //sets the meeting to complete
    public void complete() {
        this.complete = true;
    }

    //gets the feedback on if meeting is complete or not
    public boolean isComplete() {
        return complete;
    }
}
