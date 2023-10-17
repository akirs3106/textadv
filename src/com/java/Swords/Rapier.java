package src.com.java.Swords;

public class Rapier extends Sword {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;

    public Rapier(String name, int dmg, int speedPenalty) {
        super(name, dmg, "rapier", speedPenalty);
    }
    
    @Override public boolean useAbility() {
        return true;
    }

    @Override protected String getAbilityDescription() {
        return String.format("Ability: %s\n Ability Description: %s\n", this.abilityName, this.abilityDescription);
    }
    
}
