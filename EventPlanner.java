import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class EventPlanner {
    public static void addDefaultEvents(EventListPanel eventListPanel) {
        eventListPanel.addEvent(new Deadline("Lab 2 Submission", LocalDateTime.of(2024,9,25, 15, 0)));
        eventListPanel.addEvent(new Deadline("Lab 2 Decomposition", LocalDateTime.of(2024,9,27,15,0)));
        eventListPanel.addEvent(new Meeting("Class", LocalDateTime.of(2024,9,25,15,0), LocalDateTime.of(2024,9,25,16,0), "Room 339"));
    }

    public static void main(String[] args) {

        int frameWidth = 1000;
        int frameHeight = 1000;

        // Create the main frame
        JFrame frame = new JFrame("Event Planner"); //new display window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight); // Set the size of the frame using variables

        // Create a panel for events
        EventListPanel eventListPanel = new EventListPanel();
        addDefaultEvents(eventListPanel);

        // Add the panel to the frame
        frame.getContentPane().add(eventListPanel, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }
}

