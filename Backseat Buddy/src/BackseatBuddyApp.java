import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.LineBorder;

public class BackseatBuddyApp extends JFrame {

    private Map<String, Long> fastestTimes;
    private JButton checklist1Button, checklist2Button, checklist3Button, checklist4Button;
    private final Color backgroundColor = Color.BLACK;  // Background color for the GUI
    private final Color textColor = Color.ORANGE;  // Text color for the GUI
    private final Color borderColor = Color.ORANGE;  // Border color for the buttons

    public BackseatBuddyApp() {
        setTitle("Backseat Buddy | Main Menu");  // Set the title of the main window
        setSize(500, 400);  // Set the size of the main window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Close the application when the window is closed
        setLocationRelativeTo(null);  // Center the window on the screen
        fastestTimes = new HashMap<>();  // Initialize the map to store fastest times
        createStartMenu();  // Create the start menu
    }

    private void createStartMenu() {
        // Initialize buttons for each checklist
        checklist1Button = new JButton();
        checklist2Button = new JButton();
        checklist3Button = new JButton();
        checklist4Button = new JButton();

        updateButtonLabels();  // Set the initial labels for the buttons

        // Add action listeners to open the respective checklist windows
        checklist1Button.addActionListener(e -> openChecklistWindow("MiG-21bis", new String[]
            {"BRAKES ------------ SET",
            "PO.750 START Nr.1 / Nr.2 ------------ ON",
            "BATTERY HEAT ------------ ON",
            "BAT GND PWR ------------ ON",
            "DC GEN ------------ ON",
            "FIRE EXT ------------ ON",
            "RADIO ------------ ON/FREQ SET",
            "ATC ------------ CLEARED",
            "FLT REC ------------ ON",
            "Nr.1 / Nr.3 GR FUEL PUMP ------------ ON",
            "DISP TK FUEL PUMP ------------ ON",
            "ENG APU ------------ ON",
            "ENG LOCK LEVER ------------ UNLOCKED",
            "THROTTLE ------------ START/MIN",
            "STARTER ------------ ENGAGE",
            "GYRO FDS AP RDR SIGNAL ------------ ON",
            "GYRO FDS DA-200 RDR AP SIGNAL ------------ ON",
            "AC GEN-GND PWR ------------ ON",
            "ARC ------------ ON",
            "RDR ALT ------------ ON",
            "RSBN ------------ ON",
            "G1RO ------------ ON",
            "FDS ------------ ON",
            "AP ------------ ON",
            "AP PITCH ------------ ON",
            "TRIM SYSTEM ------------ ON",
            "PUMP UNIT ------------ ON",
            "CONE ------------ ON",
            "FLAPS ------------ SET T/O",
            "FDS ------------ PRESS AND HOLD UNTIL COURSE LINE IS STABLE",
            "RADAR ------------ STBY",
            "SOD IFF ------------ ON",
            "IFF SYS 'TYPE81' ------------ ON",
            "SPO-10 PWR ------------ ON",
            "RADIO COMPASS ------------ ON",
            "MASTER ARM ------------ SET GND FOR SAFE",
            "MISSILE/ROCKET HEAT ------------ ONLY IF OAT < 16*C",
            "MISSLE/ROCKET LAUNCH ------------ ON",
            "PYLONS 1-2 ------------ ON",
            "PYLONS 3-4 ------------ ON",
            "GS-23 GUN ------------ ON",
            "ASP OPTICAL GUNSIGHT ------------ ON",
            "SRZO IFF CODER/DECODER ------------ ON",
            "FIX NET ------------ ON",
            "PIPPER ------------ ON",
            "FIX NET LIGHTING (SIGHT BRIGHTNESS) ------------ ADJUST TO DESIRED",
            "CANOPY ------------ CLOSED/LOCKED/PRESSURIZED",
            "TAXI LIGHTS ------------ ON",
            "NOSEWHEEL STEERING ------------ ENGAGE",
            "***READY TO TAXI***"}));
        checklist2Button.addActionListener(e -> openChecklistWindow("F-14B", new String[]{"Sorry! the F-14B startup checklist is under construction."}));
        checklist3Button.addActionListener(e -> openChecklistWindow("F-16C", new String[]{"Sorry! the F-16C startup checklist is under construction."}));
        checklist4Button.addActionListener(e -> openChecklistWindow("A-10C II", new String[]{"Sorry! the A-10C II startup checklist is under construction."}));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));  // Set layout with 4 rows and spacing
        panel.setBackground(backgroundColor);  // Set background color

        // Add styled buttons to the panel
        panel.add(styleButton(checklist1Button));
        panel.add(styleButton(checklist2Button));
        panel.add(styleButton(checklist3Button));
        panel.add(styleButton(checklist4Button));

        add(panel);  // Add the panel to the main window
    }

    private JButton styleButton(JButton button) {
        // Style the buttons with background color, text color, and borders
        button.setBackground(backgroundColor);
        button.setForeground(textColor);
        button.setOpaque(true);
        button.setBorder(new LineBorder(borderColor, 2));
        return button;
    }

    private void updateButtonLabels() {
        // Update the labels of the buttons to show fastest times
        checklist1Button.setText(getButtonLabel("MiG-21bis"));
        checklist2Button.setText(getButtonLabel("F-14B (WIP)"));
        checklist3Button.setText(getButtonLabel("F-16C (WIP)"));
        checklist4Button.setText(getButtonLabel("A-10C II (WIP)"));
    }

    private String getButtonLabel(String title) {
        // Get the label for a button, including the fastest time (PB) if available
        long fastestTime = fastestTimes.getOrDefault(title, Long.MAX_VALUE);
        if (fastestTime == Long.MAX_VALUE) {
            return title + "    (...)";
        } else {
            return title + "    (PB: " + fastestTime / 1000 + "s)";
        }
    }

    private void openChecklistWindow(String title, String[] items) {
        JFrame checklistFrame = new JFrame(title);  // Create a new frame for the checklist
        checklistFrame.setSize(500, 600);  // Set the size of the checklist window
        checklistFrame.setLocationRelativeTo(null);  // Center the window on the screen
        checklistFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Close only this window when closing

        // Create checkbox items from the given list of items
        CheckBoxListItem[] checkBoxItems = new CheckBoxListItem[items.length];
        for (int i = 0; i < items.length; i++) {
            checkBoxItems[i] = new CheckBoxListItem(items[i]);
        }

        // Create a JList with checkbox items and set its renderer
        JList<CheckBoxListItem> checkBoxList = new JList<>(checkBoxItems);
        checkBoxList.setCellRenderer(new CheckBoxListRenderer());
        checkBoxList.setBackground(backgroundColor);  // Set background color
        checkBoxList.setForeground(textColor);  // Set text color

        long startTime = System.currentTimeMillis();  // Record the start time

        // Create and style the finish button
        JButton finishButton = new JButton("Finish");
        finishButton.setBackground(backgroundColor);
        finishButton.setForeground(textColor);
        finishButton.setOpaque(true);
        finishButton.setBorder(new LineBorder(borderColor, 2));
        finishButton.addActionListener(e -> {
            long elapsedTime = System.currentTimeMillis() - startTime;  // Calculate elapsed time
            saveFastestTime(title, elapsedTime);  // Save the fastest time if applicable
            checklistFrame.dispose();  // Close the checklist window
            JOptionPane.showMessageDialog(null, "Elapsed Time: " + elapsedTime / 1000 + " seconds\n" +
                    "Fastest Time: " + fastestTimes.get(title) / 1000 + " seconds", "Time", JOptionPane.INFORMATION_MESSAGE);
            updateButtonLabels();  // Update the button labels to reflect any new fastest time
        });

        // Add mouse listener to toggle selection of checkbox items
        checkBoxList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                int index = checkBoxList.locationToIndex(event.getPoint());
                CheckBoxListItem item = (CheckBoxListItem) checkBoxList.getModel().getElementAt(index);
                item.setSelected(!item.isSelected());
                checkBoxList.repaint(checkBoxList.getCellBounds(index, index));
            }
        });

        // Add components to the checklist window
        checklistFrame.add(new JScrollPane(checkBoxList), BorderLayout.CENTER);
        checklistFrame.add(finishButton, BorderLayout.SOUTH);
        checklistFrame.setVisible(true);  // Show the checklist window
    }

    private void saveFastestTime(String title, long elapsedTime) {
        // Save the fastest time if it's the best one so far
        if (!fastestTimes.containsKey(title) || elapsedTime < fastestTimes.get(title)) {
            fastestTimes.put(title, elapsedTime);
        }
    }

    public static void main(String[] args) {
        // Run the application
        SwingUtilities.invokeLater(() -> {
            new BackseatBuddyApp().setVisible(true);
        });
    }

    // Class representing an item with a checkbox
    static class CheckBoxListItem {
        private String label;
        private boolean isSelected;

        public CheckBoxListItem(String label) {
            this.label = label;
            this.isSelected = false;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean isSelected) {
            this.isSelected = isSelected;
        }

        public String getLabel() {
            return label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    // Renderer for displaying checkbox items in the JList
    static class CheckBoxListRenderer extends JCheckBox implements ListCellRenderer<CheckBoxListItem> {
        @Override
        public Component getListCellRendererComponent(JList<? extends CheckBoxListItem> list, CheckBoxListItem value, int index, boolean isSelected, boolean cellHasFocus) {
            setEnabled(list.isEnabled());
            setSelected(value.isSelected());
            setFont(list.getFont());
            setBackground(list.getBackground());
            setForeground(list.getForeground());
            setText(value.getLabel());
            return this;
        }
    }
}
//I wrote all of the code myself with help from the internet but I did have to use ChatGPT to help me trouble shoot the issues I had with the GUI