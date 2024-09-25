import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class EventPanel extends JPanel {
    private Event event;
    private JButton completeButton;

    public EventPanel(Event event) {
        this.event = event;
        setLayout(new BorderLayout());

        completeButton = new JButton("Complete");
        completeButton.addActionListener(e -> {
            if (event instanceof Completable) {
                ((Completable) event).complete();
                updateUrgency();
            }
        });

        //positioning labels and data for the new window
        add(new JLabel("Event: " + event.getName()), BorderLayout.PAGE_START);
        add(new JLabel("Start: " + event.getDateTime()), BorderLayout.WEST);
        if (event instanceof Meeting) {
            Meeting meeting = (Meeting) event;
            add(new JLabel("    End: " + meeting.getEndDateTime()), BorderLayout.CENTER);
            add(new JLabel("Duration: " + meeting.getDuration().toMinutes() + " minutes"), BorderLayout.SOUTH);
            add(new JLabel("Location: " + meeting.getLocation()), BorderLayout.EAST);
        }
        add(completeButton, BorderLayout.SOUTH);
        updateUrgency();
    }

    //colors the events different colors based off of the deadline/meeting time
    public void updateUrgency() {
        LocalDateTime now = LocalDateTime.now();
        if (event.getDateTime().isBefore(now)) {
            setBackground(Color.RED);
        } else if (event.getDateTime().isBefore(now.plusHours(1))) {
            setBackground(Color.YELLOW);
        } else {
            setBackground(Color.GREEN);
        }
    }
}
