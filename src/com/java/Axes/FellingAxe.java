package src.com.java.Axes;

public class FellingAxe extends Axe {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;
    
    public FellingAxe(String name, int dmg, int speedPenalty) {
        super(name, dmg, "felling axe", speedPenalty);
        this.abilityName = "Skull Splitter";
        this.abilityDescription = "When used, you have a small chance of killing an enemy immediately.";
        this.abilityAttackDialogue = String.format("You raise your %s high above your head.", name);
    }
    
    @Override public boolean useAbility() {
        return true;
    }

    @Override protected String getAbilityDescription() {
        return String.format("Ability: %s\n Ability Description: %s\n", this.abilityName, this.abilityDescription);
    }

}
