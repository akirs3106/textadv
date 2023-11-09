package src.com.java.Swords;

import src.com.java.Weapon;

public class Sword extends Weapon {

    private String[] validSubtypes = {"rusty sword", "longsword", "shortsword", "rapier", "excalibur"};
    protected String subType; 

    public Sword(String name, int dmg, String subType, int speedPenalty, int abilityCooldown) {
        super(name, dmg, "sword", speedPenalty, abilityCooldown);
        for(int i = 0; i < validSubtypes.length; i++) {
            if(subType.equals(validSubtypes[i])) {
                this.subType = subType.toLowerCase();
                break;
            }
            if(i == validSubtypes.length-1) {
                throw new IllegalStateException("Invalid weapon subtype: " + subType);
            }
        }
    }
}
