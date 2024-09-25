import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class EventListPanel extends JPanel {
    private ArrayList<Event> events;
    private JPanel displayPanel;
    private JButton addEventButton;
    private JComboBox<String> sortDropDown;
    private JCheckBox filterComplete;
    private JCheckBox filterMeetings;
    private JCheckBox filterDeadlines;

    public EventListPanel() {
        events = new ArrayList<>();
        setLayout(new BorderLayout());

        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        add(displayPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        add(controlPanel, BorderLayout.NORTH);

        //creates a drop-down box that allows you to sort the events
        sortDropDown = new JComboBox<>(new String[]{"Sort by Name", "Sort by Date", "Sort by Reverse Name"});
        sortDropDown.addActionListener(e -> sortEvents());
        controlPanel.add(sortDropDown);

        filterComplete = new JCheckBox("Filter Complete");
        filterComplete.addItemListener(e -> updateDisplay());
        controlPanel.add(filterComplete);

        //doesn't work
        filterMeetings = new JCheckBox("Filter Meetings");
        filterMeetings.addItemListener(e -> updateDisplay());
        controlPanel.add(filterMeetings);

        //doesn't work
        filterDeadlines = new JCheckBox("Filter Deadlines");
        filterDeadlines.addItemListener(e -> updateDisplay());
        controlPanel.add(filterDeadlines);

        addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(e -> openAddEventDialog());
        controlPanel.add(addEventButton);

    }

    public void addEvent(Event event) {
        events.add(event);
        updateDisplay();
    }

    public void updateDisplay() {
        displayPanel.removeAll();
        for (Event event : events) {
            if (!(filterComplete.isSelected() && event instanceof Completable && ((Completable) event).isComplete())) {
                EventPanel eventPanel = new EventPanel(event);
                displayPanel.add(eventPanel);
            }
        }
        displayPanel.revalidate();
        displayPanel.repaint();
    }

    //sorts the data based off of what selection you make either name, reversedName, and date
    private void sortEvents() {
        int selectedIndex = sortDropDown.getSelectedIndex();

        if (selectedIndex == 2) { // Sort by Name
                events.sort(Comparator.comparing(Event::getName).reversed());}
        else if (selectedIndex == 0) {
                events.sort(Comparator.comparing(Event::getName));}
        else if (selectedIndex == 1){ // Sort by Date
            events.sort(Event::compareTo);
        }

        updateDisplay();
    }

    //displays the pop-up dialog when adding a new event
    private void openAddEventDialog() {
        AddEventModel dialog = new AddEventModel(this);
        dialog.setVisible(true);
    }
}


