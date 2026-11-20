package skilldata;

import java.util.ArrayList;

// INHERITANCE AND INTERFACE IMPLEMENTATION
public class SkillProvider extends User implements SkillExchange {

    private ArrayList<String> skills;
    private double hourlyRate;

    public SkillProvider(String name, String email, double hourlyRate) {
        super(name, email);
        this.hourlyRate = hourlyRate;
        skills = new ArrayList<>();
    }

    @Override
    public void addSkill(String skill) {
        skills.add(skill);
    }

    @Override
    public boolean hasSkill(String skill) {
        return skills.contains(skill);
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    @Override
    public String toString() {
        return getName() + " | Skills: " + skills
                + " | Rate: Rs." + hourlyRate + "/hour";
    }
}