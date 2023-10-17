package src.com.java.Swords;

public class Longsword extends Sword {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;

    public Longsword(String name, int dmg, int speedPenalty) {
        super(name, dmg, "longsword", speedPenalty);
        this.abilityName = "Riposte";
        this.abilityDescription = "If attacked, you have a chance to block incoming damage and deal double damage to your opponent.";
        this.abilityAttackDialogue = "You ready your longsword in a defensive stance.";
        
    }
    
    @Override public boolean useAbility() {
        return true;
    }

    @Override protected String getAbilityDescription() {
        return String.format("Ability: %s\n Ability Description: %s\n", this.abilityName, this.abilityDescription);
    }
    
}
