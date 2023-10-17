package src.com.java.Daggers;

public class NecromancerDagger extends Dagger {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;
    
    public NecromancerDagger() {
        super("The Necromancer's Dagger", 30, "necromancer dagger", 15);
    }
    
    @Override public boolean useAbility() {
        return true;
    }

    @Override protected String getAbilityDescription() {
        return String.format("Ability: %s\n Ability Description: %s\n", this.abilityName, this.abilityDescription);
    }

}
