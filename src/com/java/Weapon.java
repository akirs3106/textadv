package src.com.java;

public class Weapon {
    
    protected String name;
    protected int dmg;
    protected String type;
    protected int speedPenalty;

    public Weapon(String name, int dmg, String type, int speedPenalty) {
        this.name = name;
        this.dmg = dmg;
        this.type = type;
        this.speedPenalty = Math.abs(speedPenalty);
    }

    public String getName(){
        return this.name;
    }

    public int getDmg(){
        return this.dmg;
    }

    public String getType() {
        return this.type;
    }

    public int getSpeedPenalty() {
        return speedPenalty;
    }


}
