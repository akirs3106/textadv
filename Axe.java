public class Axe extends Weapon {

    private String[] validSubtypes = {"rusty axe", "battleaxe", "lumberjack's axe", "halberd", "the labrys"};
    protected String subType; 

    public Axe(String name, float dmg, String type, String subType) {
        super(name, dmg, type);
        for(int i = 0; i < validSubtypes.length; i++) {
            if(subType.equals(validSubtypes[i])) {
                throw new IllegalStateException("Invalid weapon subtype.");
            }
        }
        this.subType = subType.toLowerCase();
    }
    
}
