package skilldata;

// INHERITANCE
public class SkillLearner extends User {

    private String wantedSkill;

    public SkillLearner(String name, String email, String wantedSkill) {
        super(name, email);
        this.wantedSkill = wantedSkill;
    }

    public String getWantedSkill() {
        return wantedSkill;
    }

    public void setWantedSkill(String wantedSkill) {
        this.wantedSkill = wantedSkill;
    }
}