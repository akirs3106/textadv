package src.com.java.Axes;

public class Battleaxe extends Axe {

    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;

    public Battleaxe(String name, int dmg, int speedPenalty) {
        super(name, dmg, "battleaxe", speedPenalty);
    }

    @Override public boolean useAbility() {
        return true;
    }

    @Override protected String getAbilityDescription() {
        return String.format("Ability: %s\n Ability Description: %s\n", this.abilityName, this.abilityDescription);
    }
    
}
