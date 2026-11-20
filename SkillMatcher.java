import java.util.ArrayList;
import skilldata.SkillProvider;

// USER-DEFINED EXCEPTION
class SkillNotFoundException extends Exception {
    public SkillNotFoundException(String message) {
        super(message);
    }
}

// POLYMORPHISM AND COLLECTIONS
public class SkillMatcher {

    public ArrayList<SkillProvider> findProviders(
            ArrayList<SkillProvider> providers, String skill)
            throws SkillNotFoundException {

        ArrayList<SkillProvider> matches = new ArrayList<>();

        for (SkillProvider provider : providers) {
            if (provider.hasSkill(skill)) {
                matches.add(provider);
            }
        }

        if (matches.isEmpty()) {
            throw new SkillNotFoundException(
                    "No provider found for " + skill);
        }

        return matches;
    }

    public SkillProvider findCheapest(
            ArrayList<SkillProvider> providers, String skill)
            throws SkillNotFoundException {

        ArrayList<SkillProvider> matches =
                findProviders(providers, skill);

        SkillProvider cheapest = matches.get(0);

        for (SkillProvider provider : matches) {
            if (provider.getHourlyRate()
                    < cheapest.getHourlyRate()) {
                cheapest = provider;
            }
        }

        return cheapest;
    }
}
