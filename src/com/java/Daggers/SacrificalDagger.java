package src.com.java.Daggers;

public class SacrificalDagger extends Dagger {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;

    public SacrificalDagger() {
        super("Sacrifical Dagger", 30, "sacrificial dagger", 20);
        this.abilityName = "Sacrifical Strike";
        this.abilityDescription = "Offer your own blood to the dagger, increasing the power of its next attack.";
        this.abilityAttackDialogue = "You strike yourself with your own Sacrifical Dagger.";
    }
    
    @Override public boolean useAbility() {
        return true;
    }

    @Override protected String getAbilityDescription() {
        return String.format("Ability: %s\n Ability Description: %s\n", this.abilityName, this.abilityDescription);
    }
    
}
