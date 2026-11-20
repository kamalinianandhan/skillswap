import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import skilldata.*;

// EVENT HANDLING AND GUI
public class SkillSwap extends JFrame {

    ArrayList<SkillProvider> providers = new ArrayList<>();
    SkillLearner learner;

    SkillMatcher matcher = new SkillMatcher();

    JTextField searchField;
    JTextArea output;

    public SkillSwap() {

        setTitle("SkillSwap - Skill Exchange Platform");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // SAMPLE DATA
        SkillProvider p1 =
                new SkillProvider("Arun", "arun@gmail.com", 150);

        p1.addSkill("Java");
        p1.addSkill("Python");

        SkillProvider p2 =
                new SkillProvider("Priya", "priya@gmail.com", 100);

        p2.addSkill("Java");
        p2.addSkill("HTML");

        SkillProvider p3 =
                new SkillProvider("Ravi", "ravi@gmail.com", 120);

        p3.addSkill("C");
        p3.addSkill("Python");

        providers.add(p1);
        providers.add(p2);
        providers.add(p3);

        learner = new SkillLearner(
                "Kavin", "kavin@gmail.com", "Java");

        JLabel heading = new JLabel("SKILLSWAP");
        heading.setFont(new Font("Arial", Font.BOLD, 24));

        searchField = new JTextField(15);

        JButton searchButton = new JButton("Search Skill");
        JButton scheduleButton = new JButton("Schedule Session");
        JButton exitButton = new JButton("Exit");

        output = new JTextArea(15, 42);
        output.setEditable(false);

        add(heading);
        add(new JLabel("Enter Skill:"));
        add(searchField);
        add(searchButton);
        add(scheduleButton);
        add(exitButton);
        add(new JScrollPane(output));

        // EVENT HANDLING
        searchButton.addActionListener(e -> searchSkill());

        scheduleButton.addActionListener(
                e -> scheduleSession());

        exitButton.addActionListener(
                e -> System.exit(0));

        setVisible(true);
    }

    private void searchSkill() {

        String skill = searchField.getText().trim();

        if (skill.isEmpty()) {
            output.setText("Please enter a skill.");
            return;
        }

        try {
            ArrayList<SkillProvider> matches =
                    matcher.findProviders(providers, skill);

            output.setText("Available Providers:\n\n");

            for (SkillProvider p : matches) {
                output.append(p.toString() + "\n");
            }

            SkillProvider cheapest =
                    matcher.findCheapest(providers, skill);

            output.append("\nLowest Cost Provider:\n");
            output.append(cheapest.toString());

        } catch (SkillNotFoundException e) {
            output.setText(e.getMessage());
        }
    }

    private void scheduleSession() {

        String skill = searchField.getText().trim();

        try {
            SkillProvider provider =
                    matcher.findCheapest(providers, skill);

            String input = JOptionPane.showInputDialog(
                    this, "Enter session duration in hours:");

            if (input == null) {
                return;
            }

            int hours = Integer.parseInt(input);

            if (hours <= 0) {
                output.setText("Enter a valid duration.");
                return;
            }

            LearningSession session =
                    new LearningSession(provider, skill, hours);

            output.setText(
                    "Session Scheduled!\n"
                    + "Provider: " + provider.getName()
                    + "\nSkill: " + skill
                    + "\nDuration: " + hours + " hours"
                    + "\nTotal Cost: Rs." + session.calculateCost()
            );

            // THREAD CREATION
            Thread thread = new Thread(session);
            thread.start();

        } catch (SkillNotFoundException e) {
            output.setText(e.getMessage());

        } catch (NumberFormatException e) {
            output.setText("Please enter a valid number.");
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new SkillSwap();
        });
    }
}