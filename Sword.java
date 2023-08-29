public class Sword extends Weapon {

    protected String subType;

    public Sword(String name, float dmg, String type, String subType) {
        super(name, dmg, type);
        this.subType = subType;
    }
}
