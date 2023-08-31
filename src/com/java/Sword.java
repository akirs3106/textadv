package src.com.java;

public class Sword extends Weapon {

    private String[] validSubtypes = {"rusty sword", "longsword", "shortsword", "rapier", "executioner's sword"};
    protected String subType; 

    public Sword(String name, float dmg, String subType, int speedPenalty) {
        super(name, dmg, "sword", speedPenalty);
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
