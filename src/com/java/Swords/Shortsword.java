package src.com.java.Swords;

public class Shortsword extends Sword {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;

    public Shortsword(String name, int dmg, int speedPenalty) {
        super(name, dmg, "shortsword", speedPenalty);
        this.abilityName = "\"Extended\" Melee Attack";
        this.abilityDescription = "Throw the weapon at your enemy, dealing increased damage and increasing your dodge chance, but making you unable to attack for one turn.";
        this.abilityAttackDialogue = String.format("You throw your %s with all your might.");
    }
    
    @Override public boolean useAbility() {
        return true;
    }

    @Override protected String getAbilityDescription() {
        return String.format("Ability: %s\n Ability Description: %s\n", this.abilityName, this.abilityDescription);
    }
    
}
