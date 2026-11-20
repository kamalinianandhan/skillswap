import skilldata.SkillProvider;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

// THREADING
public class LearningSession implements Runnable {

    private SkillProvider provider;
    private String skill;
    private int hours;

    private static final Logger logger =
            Logger.getLogger(LearningSession.class.getName());

    public LearningSession(SkillProvider provider,
                           String skill, int hours) {
        this.provider = provider;
        this.skill = skill;
        this.hours = hours;
    }

    public double calculateCost() {
        return provider.getHourlyRate() * hours;
    }

    @Override
    public void run() {
        logger.info("Learning session started");

        try {
            FileWriter writer =
                    new FileWriter("sessions.txt", true);

            writer.write(
                    "Provider: " + provider.getName()
                    + "\nSkill: " + skill
                    + "\nDuration: " + hours + " hours"
                    + "\nTotal Cost: Rs." + calculateCost()
                    + "\n------------------------\n"
            );

            writer.close();

            System.out.println("Session saved successfully.");

        } catch (IOException e) {
            logger.severe("Unable to save session: "
                    + e.getMessage());
        }
    }
}
