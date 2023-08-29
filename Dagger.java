public class Dagger extends Weapon {

    protected String subType;

    public Dagger(String name, float dmg, String type, String subType) {
        super(name, dmg, type);
        this.subType = subType;
    }
    
}
