package src.com.java.Swords;

public class Rapier extends Sword {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;

    public Rapier(String name, int dmg, int speedPenalty) {
        super(name, dmg, "rapier", speedPenalty);
        this.abilityName = "Vital Stab";
        this.abilityDescription = "Attempt to aim for vital weakpoints of the opponent, decreasing their speed and dealing significant damage.";
        this.abilityAttackDialogue = String.format("You lunge forward with your %s.", name);
    }
    
    @Override public boolean useAbility() {
        return true;
    }

    @Override protected String getAbilityDescription() {
        return String.format("Ability: %s\n Ability Description: %s\n", this.abilityName, this.abilityDescription);
    }
    
}
