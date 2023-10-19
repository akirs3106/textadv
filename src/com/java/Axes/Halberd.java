package src.com.java.Axes;

public class Halberd extends Axe {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;
    
    public Halberd(String name, int dmg, int speedPenalty) {
        super(name, dmg, "halberd", speedPenalty);
        this.abilityName = "Defensive Stance";
        this.abilityDescription = "Protect yourself using the halberd's extended shaft, negating a large chunk of the next attack's damage.";
        this.abilityAttackDialogue = String.format("You position your %s infront of you, ready to defend yourself.", name);
    }
    
    @Override public boolean useAbility() {
        return true;
    }

    @Override protected String getAbilityDescription() {
        return String.format("Ability: %s\n Ability Description: %s\n", this.abilityName, this.abilityDescription);
    }

}
