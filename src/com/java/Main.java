package src.com.java;

import java.util.Random;
import java.util.Scanner;
class Main {

    public static final String[][] swordNames  = {
        {"Rusty Longsword", "Bronze Longsword", "Steel Longsword"},
        {"Rusty Shortsword", "Bronze Shortsword", "Steel Shortsword"},
        {"Rusty Rapier", "Bronze Rapier", "Steel Rapier"}
    };

    public static final String[][] axeNames = {
        {"Rusty Battleaxe", "Bronze Battleaxe", "Steel Battleaxe"},
        {"Rusty Felling Axe", "Bronze Felling Axe", "Steel Felling Axe"},
        {"Rusty Halberd", "Bronze Halberd", "Steel Halberd"}
    };

    public static final String[][] daggerNames = {
        {"Rusty Rondel Dagger", "Bronze Rondel Dagger", "Steel Rondel Dagger"},
        {"Rusty Stiletto Dagger", "Bronze Stiletto Dagger", "Steel Stiletto Dagger"},
        {"Rusty Castillon Dagger", "Bronze Castillon Dagger", "Steel Castillon Dagger"}
    };

    public static final String[][] legendaryNames = {
        {"Excalibur"}, 
        {"The Labrys"}, 
        {"Sacrifical Dagger"}
    };

    public static boolean choosing;
    public static Scanner scanner = new Scanner(System.in);

    public static Boss boss;
    
    public static void main(String args[]) {


        String plrClasses[] = {"Warrior" , "Barbarian", "Rogue"};

        Player plr;

        do {
            choosing = true;
            System.out.print("Classes:\n\n1. Warrior\n2. Barbarian\n3. Rogue\n\nChoose your class: ");
            
        
            int input = scanner.nextInt()-1;

            if(input >= 0 && input < plrClasses.length) {

                String plrClass = plrClasses[input];

                plr = new Player(plrClass);
                choosing = false;


            } else {
                plr = null;
                System.out.println("Please input the number next to the class you wish to choose!");
            }

        } while(choosing);

        do {
            String dungeons[] = {"Underground Ruins"};
            choosing = true;

            System.out.print("\nDungeons:\n\n1. Underground Ruins\n\nChoose a dungeon:");
            int input = scanner.nextInt()-1;

            if(input >= 0 && input < dungeons.length) {
                String dungeon = dungeons[input];

                choosing = false;
                switch(dungeon) {
                    case "Underground Ruins": 
                    Weapon bossWeapon = new Dagger("Sacrificial Dagger", 30, "sacrificial dagger", 0);
                    Move bossMove1 = new Move("Dark Pulse", "damage", 15, "Casts Dark Pulse!");
                    Move bossMove2 = new Move("Bonematter Rejuvination", "heal", 50, "Casts Bonematter Rejuvination, absorbing nearby bonemass!");
                    Move bossMove3 = new Move("Summon Undead Army", "damage", 50, "Summons an Undead Army, and you are assaulted by multiple skeletons!");
                    Move bossMove4 = new Move("Sacrificial Slash", "damage", bossWeapon.getDmg(), "Rushes you with its Sacrificial Dagger!");
                    boss = new Boss("The Necromancer", bossWeapon, "skeleton", 300, 0, 1000.00, bossMove1, bossMove2, bossMove3, bossMove4, 3);
                    break;
                }
                System.out.println("\nYou are now entering the " + dungeon + ".");
            } else {
                String dungeon = null;
                System.out.println("Please input then number next to the dungeon you wish to enter!");
            }

        } while (choosing);


            
        createRandomChest(plr, 3).interact(plr);
        // startEncounter(createRandomSkeleton(), plr);
        startBossEncounter(plr);
        

        // startBossEncounter(plr);

        // startEncounter(createRandomSkeleton(), plr);

        // Enemy enemy = new Enemy("Skeleton Footman", new Sword("Steel Longsword", 10, "longsword", 10), "skeleton", 100, 100, 100);

        // plr.inspectWeapon();
        // plr.viewStats();

        // enemy.attackPlayer(plr);

        // plr.attackEnemy(enemy, plr);
        // plr.attackEnemy(enemy, plr);
        // plr.attackEnemy(enemy, plr);
        // plr.attackEnemy(enemy, plr);
        // plr.attackEnemy(enemy, plr);

        // plr.viewStats();

    }

    public static void startEncounter(Enemy enemy, Player plr) {

        System.out.println(String.format("%s approaches you!", enemy.getName()));

            //Enemy moves first if speed in greater than player's
        if(plr.getSpeed() < enemy.getSpeed()) { 
            while(plr.getHp() > 0 && enemy.getHp() > 0) {
                choosing = true;
                enemy.attackPlayer(plr);
                if(plr.getHp() <= 0) {
                    System.out.println("You died to a " + enemy.getName() + ".");
                    System.exit(0);
                }

                while(choosing) {
                    System.out.print("\n1. Attack\n2. Heal\n3. View Stats\nChoose your next move: ");

                    String in = scanner.next();
                    if(in.equals("1") || in.equals("2") || in.equals("3")) {
                        switch(Integer.parseInt(in)) {
                            case 1:
                                plr.attackEnemy(enemy, plr);
                                choosing = false;
                            break;
                            case 2: 
                                if(plr.heal()) {
                                    choosing = false;
                                } else {
                                    choosing = true;
                                }
                            break;
                            case 3:
                            plr.viewStats();
                            plr.inspectWeapon();
                            break;
                        }
                        
                    } else {
                        System.out.println("\nPut in the number next to the option you wish to choose!");
                    }


                }
                if(enemy.getHp() <= 0) {
                    System.out.println("You won the fight!");
                    plr.setUsedHeals(0);
                    break;
                }
            }
            //Player moves first if speed is greater than enemy's
        } else if (plr.getSpeed() > enemy.getSpeed()) {
            while(plr.getHp() > 0 && enemy.getHp() > 0) {
                choosing = true;
                while(choosing) {
                    System.out.print("\n1. Attack\n2. Heal\n3. View Stats\nChoose your next move: ");

                    String in = scanner.next();
                    if(in.equals("1") || in.equals("2") || in.equals("3")) {
                        switch(Integer.parseInt(in)) {
                            case 1:
                                plr.attackEnemy(enemy, plr);
                                choosing = false;
                            break;
                            case 2: 
                                if(plr.heal()) {
                                    choosing = false;
                                } else {
                                    choosing = true;
                                }
                            break;
                            case 3:
                            plr.viewStats();
                            plr.inspectWeapon();
                            break;
                        }
                        
                    } else {
                        System.out.println("\nPut in the number next to the option you wish to choose!");
                    }


                }
                

                if(enemy.getHp() <= 0) {
                    System.out.println("You won the fight!");
                    break;
                }
                enemy.attackPlayer(plr);
                if(plr.getHp() <= 0) {
                    System.out.println("You died to a " + enemy.getName() + ".");
                    System.exit(0);
                    
                }
            }
            //Player takes priority over moving first is speeds are equal
        } else {
            while(plr.getHp() > 0 && enemy.getHp() > 0) {
                choosing = true;
                while(choosing) {
                    System.out.print("\n1. Attack\n2. Heal\n3. View Stats\nChoose your next move: ");

                    String in = scanner.next();
                    if(in.equals("1") || in.equals("2") || in.equals("3")) {
                        switch(Integer.parseInt(in)) {
                            case 1:
                                plr.attackEnemy(enemy, plr);
                                choosing = false;
                            break;
                            case 2: 
                                plr.heal();
                                choosing = false;
                            break;
                            case 3:
                            plr.viewStats();
                            plr.inspectWeapon();
                            break;
                        }
                        
                    } else {
                        System.out.println("\nPut in the number next to the option you wish to choose!");
                    }


                }


                // plr.attackEnemy(enemy, plr);
                if(enemy.getHp() <= 0) {
                    System.out.println("You won the fight!");
                    break;
                }
                enemy.attackPlayer(plr);
                if(plr.getHp() <= 0) {
                    System.out.println("You died to a " + enemy.getName() + ".");
                    System.exit(0);
                    
                }
            }

        }

    }

    public static void startBossEncounter(Player plr) {
        System.out.println("\nYou have initiated a bossfight against " + boss.getName() + "!\n");
        while(plr.getHp() > 0 && boss.getHp() > 0) {
            choosing = true;
            while(choosing) {
                System.out.print("\n1. Attack\n2. Heal\n3. View Stats\nChoose your next move: ");

                    String in = scanner.next();
                    if(in.equals("1") || in.equals("2") || in.equals("3")) {
                        switch(Integer.parseInt(in)) {
                            case 1:
                                plr.attackEnemy(boss, plr);
                                choosing = false;
                            break;
                            case 2: 
                                if(plr.heal()) {
                                    choosing = false;
                                } else {
                                    choosing = true;
                                }
                            break;
                            case 3:
                            plr.viewStats();
                            plr.inspectWeapon();
                            break;
                        }
                        
                    } else {
                        System.out.println("\nPut in the number next to the option you wish to choose!");
                    }


            }
            if(boss.getHp() <= 0) {
                System.out.println("You won the fight!");
                break;
            }

            boss.chooseMove(plr);
            
            if(plr.getHp() <= 0) {
                System.out.println("You died to " + boss.getName() + ".");
                System.exit(0);
            }
        }
    }

    public static Weapon createRandomWeapon(String type) {

        Random rand = new Random();

        int randWeaponSubTypeSelector = rand.nextInt((2-0) + 1);
        int randWeaponTierSelector = rand.nextInt((2-0) + 1);

        Weapon weapon;
        String weaponName;
        String weaponSubType;
        int weaponDamage;
        int weaponSpeedPenalty;

        switch(type) {
            case "sword": 
                weaponName = swordNames[randWeaponSubTypeSelector][randWeaponTierSelector];

                switch(randWeaponSubTypeSelector) {
                    case 0: 
                        weaponSubType = "longsword";
                    break;
                    case 1:
                        weaponSubType = "shortsword";
                    break;
                    case 2: 
                        weaponSubType = "rapier";
                    break;
                    default:
                        weaponSubType = null;
                    
                }

                switch(randWeaponTierSelector) {
                    case 0: 
                        weaponDamage = rand.nextInt((15 - 10) + 1) + 10;
                        weaponSpeedPenalty = rand.nextInt((30 - 20) + 20);
                    break;
                    case 1: 
                        weaponDamage = rand.nextInt((20 - 15) + 1) + 15;
                        weaponSpeedPenalty = rand.nextInt((35 - 25) + 25);
                    break;
                    case 2:
                        weaponDamage = rand.nextInt((25 - 20) + 1) + 20;
                        weaponSpeedPenalty = rand.nextInt((40 - 30) + 30);
                    break;
                    default:
                        weaponDamage = 0;
                        weaponSpeedPenalty = 0;

                }
                weapon = new Sword(weaponName, weaponDamage, weaponSubType, weaponSpeedPenalty);
                break;
            case "axe":
                weaponName = axeNames[randWeaponSubTypeSelector][randWeaponTierSelector];

                switch(randWeaponSubTypeSelector) {
                    case 0: 
                        weaponSubType = "battleaxe";
                    break;
                    case 1:
                        weaponSubType = "felling axe";
                    break;
                    case 2: 
                        weaponSubType = "halberd";
                    break;
                    default:
                        weaponSubType = null;
                }

                switch(randWeaponTierSelector) {
                    case 0: 
                        weaponDamage = rand.nextInt((25 - 20) + 1) + 20;
                        weaponSpeedPenalty = rand.nextInt((40 - 30) + 30);
                    break;
                    case 1: 
                        weaponDamage = rand.nextInt((30 - 25) + 1) + 25;
                        weaponSpeedPenalty = rand.nextInt((45 - 35) + 35);
                    break;
                    case 2:
                        weaponDamage = rand.nextInt((35 - 30) + 1) + 30;
                        weaponSpeedPenalty = rand.nextInt((50 - 45) + 45);
                    break;
                    default:
                        weaponDamage = 0;
                        weaponSpeedPenalty = 0;
                }

                weapon = new Axe(weaponName, weaponDamage, weaponSubType, weaponSpeedPenalty);
                break;
            case "dagger": 
                weaponName = daggerNames[randWeaponSubTypeSelector][randWeaponTierSelector];

                switch(randWeaponSubTypeSelector) {
                    case 0: 
                        weaponSubType = "rondel dagger";
                    break;
                    case 1:
                        weaponSubType = "stiletto dagger";
                    break;
                    case 2: 
                        weaponSubType = "castillon dagger";
                    break;
                    default:
                        weaponSubType = null;
                }

                switch(randWeaponTierSelector) {
                    case 0: 
                        weaponDamage = rand.nextInt((13 - 8) + 1) + 8;
                        weaponSpeedPenalty = rand.nextInt((15 - 10) + 10);
                    break;
                    case 1: 
                        weaponDamage = rand.nextInt((18 - 13) + 1) + 13;
                        weaponSpeedPenalty = rand.nextInt((20 - 15) + 15);
                    break;
                    case 2:
                        weaponDamage = rand.nextInt((23 - 18) + 1) + 18;
                        weaponSpeedPenalty = rand.nextInt((25 - 20) + 20);
                    break;
                    default:
                        weaponDamage = 0;
                        weaponSpeedPenalty = 0;
                }
                weapon = new Dagger(weaponName, weaponDamage, weaponSubType, weaponSpeedPenalty);
            break;
            default:
                weapon = null;
        }
        return weapon;
    }

    public static Weapon createRandomWeapon(String type, int rarity) {

        Random rand = new Random();

        int randWeaponSubTypeSelector = rand.nextInt((2-0) + 1);
        int weaponTier = rarity;

        Weapon weapon;
        String weaponName;
        String weaponSubType;
        int weaponDamage;
        int weaponSpeedPenalty;

        switch(type) {
            case "sword": 
                weaponName = swordNames[randWeaponSubTypeSelector][weaponTier];

                switch(randWeaponSubTypeSelector) {
                    case 0: 
                        weaponSubType = "longsword";
                    break;
                    case 1:
                        weaponSubType = "shortsword";
                    break;
                    case 2: 
                        weaponSubType = "rapier";
                    break;
                    default:
                        weaponSubType = null;
                    
                }

                switch(weaponTier) {
                    case 0: 
                        weaponDamage = rand.nextInt((15 - 10) + 1) + 10;
                        weaponSpeedPenalty = rand.nextInt((30 - 20) + 20);
                    break;
                    case 1: 
                        weaponDamage = rand.nextInt((20 - 15) + 1) + 15;
                        weaponSpeedPenalty = rand.nextInt((35 - 25) + 25);
                    break;
                    case 2:
                        weaponDamage = rand.nextInt((25 - 20) + 1) + 20;
                        weaponSpeedPenalty = rand.nextInt((40 - 30) + 30);
                    break;
                    default:
                        weaponDamage = 0;
                        weaponSpeedPenalty = 0;

                }
                weapon = new Sword(weaponName, weaponDamage, weaponSubType, weaponSpeedPenalty);
                break;
            case "axe":
                weaponName = axeNames[randWeaponSubTypeSelector][weaponTier];

                switch(randWeaponSubTypeSelector) {
                    case 0: 
                        weaponSubType = "battleaxe";
                    break;
                    case 1:
                        weaponSubType = "felling axe";
                    break;
                    case 2: 
                        weaponSubType = "halberd";
                    break;
                    default:
                        weaponSubType = null;
                }

                switch(weaponTier) {
                    case 0: 
                        weaponDamage = rand.nextInt((25 - 20) + 1) + 20;
                        weaponSpeedPenalty = rand.nextInt((40 - 30) + 30);
                    break;
                    case 1: 
                        weaponDamage = rand.nextInt((30 - 25) + 1) + 25;
                        weaponSpeedPenalty = rand.nextInt((45 - 35) + 35);
                    break;
                    case 2:
                        weaponDamage = rand.nextInt((35 - 30) + 1) + 30;
                        weaponSpeedPenalty = rand.nextInt((50 - 45) + 45);
                    break;
                    default:
                        weaponDamage = 0;
                        weaponSpeedPenalty = 0;
                }

                weapon = new Axe(weaponName, weaponDamage, weaponSubType, weaponSpeedPenalty);
                break;
            case "dagger": 
                weaponName = daggerNames[randWeaponSubTypeSelector][weaponTier];

                switch(randWeaponSubTypeSelector) {
                    case 0: 
                        weaponSubType = "rondel dagger";
                    break;
                    case 1:
                        weaponSubType = "stiletto dagger";
                    break;
                    case 2: 
                        weaponSubType = "castillon dagger";
                    break;
                    default:
                        weaponSubType = null;
                }

                switch(weaponTier) {
                    case 0: 
                        weaponDamage = rand.nextInt((13 - 8) + 1) + 8;
                        weaponSpeedPenalty = rand.nextInt((15 - 10) + 10);
                    break;
                    case 1: 
                        weaponDamage = rand.nextInt((18 - 13) + 1) + 13;
                        weaponSpeedPenalty = rand.nextInt((20 - 15) + 15);
                    break;
                    case 2:
                        weaponDamage = rand.nextInt((23 - 18) + 1) + 18;
                        weaponSpeedPenalty = rand.nextInt((25 - 20) + 20);
                    break;
                    default:
                        weaponDamage = 0;
                        weaponSpeedPenalty = 0;
                }
                weapon = new Dagger(weaponName, weaponDamage, weaponSubType, weaponSpeedPenalty);
            break;
            default:
                weapon = null;
        }
        return weapon;
    }

    public static Enemy createRandomSkeleton() {



        String skeletonNames[] = {"Skeleton Warrior", "Skeleton Axeman", "Skeleton Assassin"};
        Random random = new Random();
        //Used to determine the name of the skeleton, as well as the type of weapon they're using
        int randSelector = random.nextInt((2-0) + 1);

        Weapon skelWeapon;
        String skelName;
        int skelHp;
        int skelSpeed;
        double skelXp;

        switch(randSelector) {
            case 0:
                skelWeapon = createRandomWeapon("sword");
                skelHp = random.nextInt((120 - 90) + 1) + 90;
                skelSpeed = random.nextInt((115 - 85) + 1) + 85;
                skelName = skeletonNames[randSelector];
            break;
            case 1: 
                skelWeapon = createRandomWeapon("axe");
                skelHp = random.nextInt((150 - 115) + 1) + 115;
                skelSpeed = random.nextInt((90 - 60) + 1) + 60;
                skelName = skeletonNames[randSelector];
            break;
            case 2:
                skelWeapon = createRandomWeapon("dagger");
                skelHp = random.nextInt((100 - 50) + 1) + 50;
                skelSpeed = random.nextInt((140 - 110) + 1) + 115;
                skelName = skeletonNames[randSelector];
            break;
            default:
            skelHp = 0;
            skelSpeed = 0;
            skelWeapon = createRandomWeapon(null);
            throw new IllegalStateException("randSelector is not within required range.");

        }


        skelXp = Math.round(((skelHp + skelSpeed + skelWeapon.getDmg()) / 3) * 100) / 100;



        return new Enemy(skelName, skelWeapon, "skeleton", skelHp, skelSpeed, skelXp, "normal");

        
    }

    public static Chest createRandomChest(Player plr) {
        String commonChestNames[] = {"Rotted Chest", "Rusty Chest", "Scratched Chest"};
        String uncommonChestNames[] = {"Oak Chest", "Maple Chest", "Stone Chest"};
        String rareChestNames[] = {"Silver Chest", "Large Oak Chest"};
        String legendaryChestNames[] = {"Gilded Chest", "Regal Chest"};

        int rarity;
        String chestName;
        Weapon weapon;
        Random rand = new Random();
        int result = rand.nextInt(100) + 1;
        String plrClass = plr.getPlayerClass();
        
        //Common 50% chance
        //Uncommon 30% chance
        //Rare 15% chance
        //Legendary 5% chance
        if(result <= 50) {
            rarity = 0;
            chestName = commonChestNames[rand.nextInt(3)];
        } else if(result <= 80) {
            rarity = 1;
            chestName = uncommonChestNames[rand.nextInt(3)];
        } else if(result <= 95){
            rarity = 2;
            chestName = rareChestNames[rand.nextInt(2)];
        } else {
            rarity = 3;
            chestName = legendaryChestNames[rand.nextInt(2)];
        }

        if(rarity < 3) {
            switch(plrClass) {
                case "Warrior": 
                    weapon = createRandomWeapon("sword", rarity);
                break;
                case "Barbarian":
                    weapon = createRandomWeapon("axe", rarity);
                break;
                case "Rogue":
                    weapon = createRandomWeapon("dagger", rarity);
                break;
                default:
                    weapon = null;
            }
        } else {
            switch(plrClass) {
                case "Warrior":
                    weapon = new Sword("Excalibur", 40, "excalibur", 35);
                break;
                case "Barbarian":
                    weapon = new Axe("The Labrys", 55, "the labrys", 45);
                break;
                case "Rogue":
                    weapon = new Dagger("Sacrifical Dagger", 30, "sacrifical dagger", 20);
                break;
                default:
                weapon = null;
            }
        }
        
        return new Chest(weapon, chestName, rarity);


    }

    public static Chest createRandomChest(Player plr, int rarity) {
        String commonChestNames[] = {"Rotted Chest", "Rusty Chest", "Scratched Chest"};
        String uncommonChestNames[] = {"Oak Chest", "Maple Chest", "Stone Chest"};
        String rareChestNames[] = {"Silver Chest", "Large Oak Chest"};
        String legendaryChestNames[] = {"Gilded Chest", "Regal Chest"};

        String chestName;
        Weapon weapon;
        Random rand = new Random();
        String plrClass = plr.getPlayerClass();
        
        //Common 50% chance
        //Uncommon 30% chance
        //Rare 15% chance
        //Legendary 5% chance
        if(rarity == 0) {
            chestName = commonChestNames[rand.nextInt(3)];
        } else if(rarity == 1) {
            chestName = uncommonChestNames[rand.nextInt(3)];
        } else if(rarity == 2){
            chestName = rareChestNames[rand.nextInt(2)];
        } else {
            chestName = legendaryChestNames[rand.nextInt(2)];
        }

        if(rarity < 3) {
            switch(plrClass) {
                case "Warrior": 
                    weapon = createRandomWeapon("sword", rarity);
                break;
                case "Barbarian":
                    weapon = createRandomWeapon("axe", rarity);
                break;
                case "Rogue":
                    weapon = createRandomWeapon("dagger", rarity);
                break;
                default:
                    weapon = null;
            }
        } else {
            switch(plrClass) {
                case "Warrior":
                    weapon = new Sword("Excalibur", 40, "excalibur", 35);
                break;
                case "Barbarian":
                    weapon = new Axe("The Labrys", 55, "the labrys", 45);
                break;
                case "Rogue":
                    weapon = new Dagger("Sacrifical Dagger", 30, "sacrifical dagger", 20);
                break;
                default:
                weapon = null;
            }
        }
        
        return new Chest(weapon, chestName, rarity);


    }
}