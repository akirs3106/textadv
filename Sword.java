public class Sword extends Weapon {

    private String[] validSubtypes = {"rusty sword", "longsword", "shortsword", "rapier", "executioner's sword"};
    protected String subType; 

    public Sword(String name, float dmg, String type, String subType) {
        super(name, dmg, type);
        for(int i = 0; i < validSubtypes.length; i++) {
            if(subType.equals(validSubtypes[i])) {
                throw new IllegalStateException("Invalid weapon subtype.");
            }
        }
        this.subType = subType.toLowerCase();
    }
}
