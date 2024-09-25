import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class AddEventModel extends JDialog {
    private JTextField nameField;
    private JTextField dateField;
    private JTextField endField;
    private JTextField locationField;
    private JComboBox<String> eventTypeBox;
    private EventListPanel eventListPanel;

    //The pop-up panel when adding an event to the default events
    public AddEventModel(EventListPanel eventListPanel) {
        this.eventListPanel = eventListPanel;
        setTitle("Add Event");
        setModal(true);
        setLayout(new GridLayout(0, 2));

        add(new JLabel("Event Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Event Type:"));
        eventTypeBox = new JComboBox<>(new String[]{"Deadline", "Meeting"});
        eventTypeBox.addActionListener(e -> toggleFields());
        add(eventTypeBox);

        add(new JLabel("Start Time (yyyy-MM-dd'T'HH:mm):"));
        dateField = new JTextField();
        add(dateField);

        add(new JLabel("End Time (only for Meeting, yyyy-MM-dd'T'HH:mm):"));
        endField = new JTextField();
        endField.setEnabled(false);
        add(endField);

        add(new JLabel("Location (only for Meeting):"));
        locationField = new JTextField();
        locationField.setEnabled(false);
        add(locationField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addEvent());
        add(addButton);
        pack();
        setVisible(true);
    }

    //the user is able to toggle between meetings and deadlines
    private void toggleFields() {
        boolean isMeeting = eventTypeBox.getSelectedItem().equals("Meeting");
        endField.setEnabled(isMeeting);
        locationField.setEnabled(isMeeting);
    }

    //adding either a new deadline or meeting event to the calendar
    private void addEvent() {
        try {
            String name = nameField.getText();
            LocalDateTime startTime = LocalDateTime.parse(dateField.getText());
            if (eventTypeBox.getSelectedItem().equals("Deadline")) {
                eventListPanel.addEvent(new Deadline(name, startTime));
            } else {
                LocalDateTime endTime = LocalDateTime.parse(endField.getText());
                String location = locationField.getText();
                eventListPanel.addEvent(new Meeting(name, startTime, endTime, location));
            }
            dispose();
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please use yyyy-MM-dd HH:mm.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}



