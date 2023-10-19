package src.com.java.Daggers;

public class CastillonDagger extends Dagger {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;
    
    public CastillonDagger(String name, int dmg, int speedPenalty) {
        super(name, dmg, "castillon dagger", speedPenalty);
        this.abilityName = "Relentless Slashes";
        this.abilityDescription = "Wildly swing your blade at your opponent, hitting them 2-4 times.";
        this.abilityAttackDialogue = "You begin to slash your blade around wildly.";
    }
    
    @Override public boolean useAbility() {
        return true;
    }

    @Override protected String getAbilityDescription() {
        return String.format("Ability: %s\n Ability Description: %s\n", this.abilityName, this.abilityDescription);
    }

}
