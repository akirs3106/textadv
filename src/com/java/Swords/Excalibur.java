package src.com.java.Swords;

public class Excalibur extends Sword {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;

    public Excalibur() {
        super("Excalibur", 40, "excalibur", 35);
    }
    
    @Override public boolean useAbility() {
        return true;
    }

    @Override protected String getAbilityDescription() {
        return String.format("Ability: %s\n Ability Description: %s\n", this.abilityName, this.abilityDescription);
    }
    
}
