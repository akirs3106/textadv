package src.com.java.Axes;

public class Halberd extends Axe {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;
    
    public Halberd(String name, int dmg, int speedPenalty) {
        super(name, dmg, "halberd", speedPenalty);
    }
    
    @Override public boolean useAbility() {
        return true;
    }

    @Override protected String getAbilityDescription() {
        return String.format("Ability: %s\n Ability Description: %s\n", this.abilityName, this.abilityDescription);
    }

}
