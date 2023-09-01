package src.com.java;

public class Weapon {
    
    protected String name;
    protected float dmg;
    protected String type;
    protected int speedPenalty;

    public Weapon(String name, float dmg, String type, int speedPenalty) {
        this.name = name;
        this.dmg = dmg;
        this.type = type;
        this.speedPenalty = Math.abs(speedPenalty);
    }

    public String getName(){
        return this.name;
    }

    public float getDmg(){
        return this.dmg;
    }

    public String getType() {
        return this.type;
    }

    public int getSpeedPenalty() {
        return speedPenalty;
    }


}
