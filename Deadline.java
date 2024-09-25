import java.time.LocalDateTime;

public class Deadline extends Event implements Completable {
    private boolean complete;

    //constructor
    public Deadline(String name, LocalDateTime deadline) {
        super(name, deadline);
        this.complete = false;
    }

    //gets the name of the deadline
    public String getName() {
        return name;
    }

    //sets the deadline to complete
    public void complete() {
        this.complete = true;
    }

    //gets the results of complete
    public boolean isComplete() {
        return complete;
    }
}

