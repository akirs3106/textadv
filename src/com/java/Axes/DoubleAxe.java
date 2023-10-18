package src.com.java.Axes;

public class DoubleAxe extends Axe {

    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;

    public DoubleAxe(String name, int dmg, int speedPenalty) {
        super(name, dmg, "double axe", speedPenalty);
        this.abilityName = "Double Swing";
        this.abilityDescription = "Utilize the second axehead and hit your opponent on the backswing.";
        this.abilityAttackDialogue = String.format("You swing your %s forwards.", name);
    }

    @Override public boolean useAbility() {
        return true;
    }

    @Override protected String getAbilityDescription() {
        return String.format("Ability: %s\n Ability Description: %s\n", this.abilityName, this.abilityDescription);
    }
    
}
