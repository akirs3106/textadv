package src.com.java.Daggers;

public class StilettoDagger extends Dagger {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;

    public StilettoDagger(String name, int dmg, int speedPenalty) {
        super(name, dmg, "stiletto dagger", speedPenalty);
    }
    
    @Override public boolean useAbility() {
        return true;
    }

    @Override protected String getAbilityDescription() {
        return String.format("Ability: %s\n Ability Description: %s\n", this.abilityName, this.abilityDescription);
    }
    
}
