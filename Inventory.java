import java.util.HashMap;
/**
 * class inventory - geef hier een beschrijving van deze class
 *
 * @author (Gertjan Wiersma)
 * @version (26-1-20)
 */
public class Inventory {
    HashMap<Integer, ItemInventory> inv = new HashMap<Integer, ItemInventory>();
    HashMap<Integer, Challenge> challenge = new HashMap<Integer, Challenge>();

    private boolean craftingTable;
    public boolean house;
    private PowerLvl powerLvl = new PowerLvl(0);
    private int pl;
    private Game quit;
    private int potion_quest;

    public Inventory() {
        //putting items in inv
        this.inv.put(1, new ItemInventory("wood", 1, 0, 64));
        this.inv.put(2, new ItemInventory("sand", 2, 0, 64));
        this.inv.put(3, new ItemInventory("stone", 3, 0, 64));
        this.inv.put(4, new ItemInventory("diamond", 4, 0, 32));
        this.inv.put(5, new ItemInventory("iron", 5, 0, 32));
        this.inv.put(6, new ItemInventory("sticks", 6, 0, 64));
        this.inv.put(7, new ItemInventory("wood PickAxe", 7, 0, 1));
        this.inv.put(8, new ItemInventory("cTABLE", 8, 0, 1));
        this.inv.put(9, new ItemInventory("stone_Axe", 9, 0, 1));
        this.inv.put(10, new ItemInventory("house", 10, 0, 1));
        this.inv.put(999, new ItemInventory("devmode", 999, 0, 999));
        this.inv.put(11, new ItemInventory("iron Pickaxe", 11, 0, 1));
        this.inv.put(17, new ItemInventory("iron sword", 17, 0, 1));
        this.inv.put(18, new ItemInventory("diamond PickAxe", 18, 0, 1));
        this.inv.put(12, new ItemInventory("diamond sword", 12, 0, 1));
        this.inv.put(13, new ItemInventory("diamond helmet", 13, 0, 1));
        this.inv.put(14, new ItemInventory("diamond chestplate", 14, 0, 1));
        this.inv.put(15, new ItemInventory("diamond leggings", 15, 0, 1));
        this.inv.put(16, new ItemInventory("diamond boots", 16, 0, 1));
        this.inv.put(19, new ItemInventory("strength potion", 19, 0, 1));
        this.inv.put(20, new ItemInventory("speed potion", 20, 0, 1));
        this.inv.put(21, new ItemInventory("regeneration potion", 21, 0, 1));

        //putting achievements in achiev
        this.challenge.put(1, new Challenge("Build a house", 1, false));
        this.challenge.put(5, new Challenge("Craft an iron_Pickaxe", 5, false));
        this.challenge.put(6, new Challenge("Get Diamonds", 6, false));
        this.challenge.put(2, new Challenge("kill the Zombie in the jungle temple", 2, false));
        this.challenge.put(7, new Challenge("Find the 2 potions in the nether and use them both", 7, false));
        this.challenge.put(3, new Challenge("Kill the nether_witch somewhere in the Nether", 3, false));
        this.challenge.put(4, new Challenge("Kill the EnderDragon", 4, false));

    }

    /**
     *
     */
    public void showInv() {
        for (int i : this.inv.keySet()) {
            ItemInventory item = this.inv.get(i);
            if (item.getNumber() > 0) {
                System.out.println(item.getItemName() + " : " + item.getNumber());
            }
        }
    }

    public void showChallenges(){
        for (int i : this.challenge.keySet()){
            Challenge challenges = this.challenge.get(i);
            if(challenges.challengesPassed() == false){
                System.out.println(challenges.getChallenge());
            }
        }
    }

    public void showCraftingTableStatus() {
        System.out.println("craftingTable = " + craftingTable);
    }

    public boolean getHouseInfo() {
        System.out.println(house);
        return this.house;
    }

    public boolean setHouse(boolean thing) {
        this.house = thing;
        return house;
    }

    public void increment(int itemIndex, int number) {
        ItemInventory item = this.inv.get(itemIndex);
        item.increment(number);
    }

    public void decrement(int itemIndex, int number) {
        ItemInventory item = this.inv.get(itemIndex);
        item.decrement(number);
    }

    public void printPowerLvl() {
        this.pl = powerLvl.getPowerLvl();
        System.out.println("Your current Power lvl = " + pl);
    }

    public int getPowerLvl() {
        this.pl = powerLvl.getPowerLvl();
        return pl;
    }

    public void craft(int craftedItemIndex) {
        ItemInventory newItem = this.inv.get(craftedItemIndex);
        ItemInventory wood = this.inv.get(1);
        ItemInventory stone = this.inv.get(3);
        ItemInventory sticks = this.inv.get(6);
        ItemInventory diamond = this.inv.get(4);
        ItemInventory iron = this.inv.get(5);
        ItemInventory wood_Pickaxe = this.inv.get(7);
        ItemInventory iron_Pickaxe = this.inv.get(11);
        ItemInventory iron_Sword = this.inv.get(17);
        ItemInventory diamond_PickAxe = this.inv.get(18);
        ItemInventory diamond_Sword = this.inv.get(12);
        Challenge iron_Pickaxe_Challenge = this.challenge.get(5);

        Challenge build_a_house = this.challenge.get(1);
        switch (craftedItemIndex) {
            case 6:

            if (wood.getNumber() >= 2) {
                wood.decrement(2);
                newItem.increment(4);
                System.out.println(newItem.getItemName() + ": +4");

            } else {
                System.out.println("You need 2 wood to craft this");
            }
            break;

            case 7:
            if (craftingTable == false) {
                System.out.println("You need a craftingTable to craft this.");
            } else if (wood.getNumber() < 3 && sticks.getNumber() < 2) {
                System.out.println("You need 3 wood and 2 sticks to craft this.");
            } else if (newItem.getNumber() >= 1) {
                System.out.println("You already got a " + newItem.getItemName());
            } else {
                newItem.increment(1);
                wood.decrement(3);
                sticks.decrement(2);
                System.out.println(newItem.getItemName() + ": +1");
            }
            break;

            case 8:
            if (wood.getNumber() < 4 && craftingTable == false) {
                System.out.println("You need 4 wood to craft a workbench");
            } else if (craftingTable == true) {
                System.out.println("You already have a workbench");
            } else {
                wood.decrement(4);
                craftingTable = true;
                System.out.println("Your craftingtable is now enebled");
                return;
            }
            break;

            case 9:
            if (craftingTable == false) {
                System.out.println("You need a craftingTable to craft this.");
            } else if (stone.getNumber() < 3 && sticks.getNumber() < 2) {
                System.out.println("You need 3 stone and 2 sticks to craft this.");
            } else if (newItem.getNumber() >= 1) {
                System.out.println("You already got a " + newItem.getItemName());
            } else {
                newItem.increment(1);
                stone.decrement(3);
                sticks.decrement(2);
                System.out.println(newItem.getItemName() + ": +1");
            }
            break;
            case 10:
            if (wood.getNumber() >= 30 && stone.getNumber() >= 20) { // moet nog glas aan toegevoegd worden)
                wood.decrement(30);
                stone.decrement(20);
                setHouse(true);
                System.out.println(wood.getItemName() + ": -30");
                System.out.println(stone.getItemName() + ": -30");
                System.out.println("You have builded your own house!");
                System.out.println("You earned 3 iron for this! Use them whisely");
                iron.increment(3);
                System.out.println(iron.getItemName() + ": +3");
                System.out.println("Challenge: " + build_a_house.getChallenge() + " passed");
                build_a_house.setPassed(true);
                return;
            } else {
                System.out.println("you need more items to build a house");
                System.out.println("You need 30 wood, 30 stone to build a house.");
            }
            break;

            case 11:
            if (craftingTable == false) {
                System.out.println("You need a craftingTable to craft this.");
            } else if (iron.getNumber() < 3 && sticks.getNumber() < 2) {
                System.out.println("You need 3 iron and 2 sticks to craft this.");
            } else if (newItem.getNumber() >= 1) {
                System.out.println("You already got a " + newItem.getItemName());
            } else if(diamond_PickAxe.getNumber() == 1){
                System.out.println("Why would you go back to iron_pickaxe when you have a diamond one.");
            }
            else {
                newItem.increment(1);
                iron.decrement(3);
                sticks.decrement(3);
                wood_Pickaxe.decrement(1);
                System.out.println(newItem.getItemName() + ": +1");
                System.out.println(wood_Pickaxe.getItemName() + ": -1");
                iron_Pickaxe_Challenge.setPassed(true);
                System.out.println("Challenge: " + iron_Pickaxe_Challenge.getChallenge() + " passed");
            }
            break;

            case 12:
            if (craftingTable == false) {
                System.out.println("You need a craftingTable to craft this.");
            } else if (diamond.getNumber() < 2 && sticks.getNumber() < 1) {
                System.out.println("You need 2 diamonds and 1 stick to craft this.");
            } else if (newItem.getNumber() >= 1) {
                System.out.println("You already got a " + newItem.getItemName());
            } else {
                newItem.increment(1);
                diamond.decrement(2);
                sticks.decrement(1);
                iron_Sword.decrement(1);
                powerLvl.increment(40);
                System.out.println(newItem.getItemName() + ": +1");
                System.out.println("Iron_Sword: -1");
                System.out.println("Your PowerLvl got increment by 20!");
            }
            break;
            case 13:
            if (craftingTable == false) {
                System.out.println("You need a craftingTable to craft this.");
            } else if (diamond.getNumber() < 5) {
                System.out.println("You need 5 diamonds to craft this.");
            } else if (newItem.getNumber() >= 1) {
                System.out.println("You already got a " + newItem.getItemName());
            } else {
                newItem.increment(1);
                diamond.decrement(5);

                System.out.println(newItem.getItemName() + ": +1");
            }
            break;
            case 14:
            if (craftingTable == false) {
                System.out.println("You need a craftingTable to craft this.");
            } else if (diamond.getNumber() < 8) {
                System.out.println("You need 8 diamonds to craft this.");
            } else if (newItem.getNumber() >= 1) {
                System.out.println("You already got a " + newItem.getItemName());
            } else {
                newItem.increment(1);
                diamond.decrement(8);

                System.out.println(newItem.getItemName() + ": +1");
            }
            break;
            case 15:
            if (craftingTable == false) {
                System.out.println("You need a craftingTable to craft this.");
            } else if (diamond.getNumber() < 7) {
                System.out.println("You need 7 diamonds to craft this.");
            } else if (newItem.getNumber() >= 1) {
                System.out.println("You already got a " + newItem.getItemName());
            } else {
                newItem.increment(1);
                diamond.decrement(7);

                System.out.println(newItem.getItemName() + ": +1");
            }
            break;
            case 16:
            if (craftingTable == false) {
                System.out.println("You need a craftingTable to craft this.");
            } else if (diamond.getNumber() < 4) {
                System.out.println("You need 4 diamonds to craft this.");
            } else if (newItem.getNumber() >= 1) {
                System.out.println("You already got a " + newItem.getItemName());
            } else {
                newItem.increment(1);
                diamond.decrement(4);

                System.out.println(newItem.getItemName() + ": +1");
            }
            break;
            case 17:
            if (craftingTable == false) {
                System.out.println("You need a craftingTable to craft this.");
            } else if (iron.getNumber() < 2 && sticks.getNumber() < 1){
                System.out.println("You need 2 iron to and 1 stick to craft this.");
            } else if (newItem.getNumber() >= 1) {
                System.out.println("You already got a " + newItem.getItemName());
            }
            else if(diamond_Sword.getNumber() == 1){
                System.out.println("Why would you go back to an iron_sword when you have a diamond one.");
            }
            else {
                newItem.increment(1);
                iron.decrement(2);
                powerLvl.increment(20);
                System.out.println(newItem.getItemName() + ": +1");
                System.out.println("PowerLvl: +20");
            }
            break;
            case 18:
            if (craftingTable == false) {
                System.out.println("You need a craftingTable to craft this.");
            } else if (diamond.getNumber() < 3 && sticks.getNumber() < 2){
                System.out.println("You need 3 diamonds to and 2 sticks to craft this.");
            } else if (newItem.getNumber() >= 1) {
                System.out.println("You already got a " + newItem.getItemName());
            } else {
                newItem.increment(1);
                diamond.decrement(3);
                sticks.decrement(2);
                iron_Pickaxe.decrement(1);
                System.out.println(newItem.getItemName() + ": +1");
                System.out.println("Iron_PickAxe: -1");

            }
            break;
        }
    }

    public void use(int index) {
        ItemInventory itemToUse = this.inv.get(index);
        ItemInventory stone = this.inv.get(3);
        ItemInventory diamond = this.inv.get(4);
        ItemInventory iron = this.inv.get(5);
        ItemInventory wood = this.inv.get(1);
        ItemInventory sticks = this.inv.get(6);
        ItemInventory speed_pot = this.inv.get(20);
        ItemInventory regeneration_pot = this.inv.get(21);
        ItemInventory strength_pot = this.inv.get(19);

        Challenge potion_challenge = this.challenge.get(7);
        switch (index) {
            case 7:
            if (itemToUse.getNumber() == 1) {
                stone.increment(4);
                System.out.println("Stone: +4");
            } else {
                System.out.println("You need a Wood_PickAxe to do this");
            }
            break;                     
            case 9:
            if (itemToUse.getNumber() == 1) {
                wood.increment(5);
                System.out.println("Wood: +5");
            }
            case 11:
            if (itemToUse.getNumber() == 1) {
                if(iron.getNumber() >= 10){
                    System.out.println("You can't get more iron");}
                else if(iron.getNumber() < 10){
                    iron.increment(1);
                    System.out.println("Iron: +1");
                }
            } else{
                System.out.println("You need a Iron_PickAxe to do this");}
            break;
            case 18:
            if (itemToUse.getNumber() == 1) {
                if(diamond.getNumber() >= 10){
                    System.out.println("There are no more diamonds to get here, go look for another way to find some diamonds");}
                else if(diamond.getNumber() < 10){
                    diamond.increment(2);
                    System.out.println("Diamond: +2");
                }
            } else{
                System.out.println("You need a Diamond_PickAxe to do this");}
            break;
            case 19:
            if(itemToUse.getNumber() == 1){
                itemToUse.decrement(1);
                powerLvl.increment(10);
                System.out.println(itemToUse.getItemName() + ": -1");
                System.out.println("PowerLvl: +10");
            }else{
                System.out.println("You don't have this item");}
            break;
            case 20:
            if (speed_pot.getNumber() == 1 && regeneration_pot.getNumber() == 1){
                speed_pot.decrement(1);
                regeneration_pot.decrement(1);
                powerLvl.increment(20);
                System.out.println(speed_pot.getItemName() + ": -1");
                System.out.println(regeneration_pot.getItemName() + ": -1");
                System.out.println("PowerLvl: +20");
                potion_challenge.setPassed(true);
                System.out.println("You have passed your potion challenge!");
            }
            else{
                System.out.println("You first need both Speed and Regeneration potion to use them");}
            break;

            case 999:
            wood.increment(60);
            stone.increment(40);
            sticks.increment(40);
            craftingTable = true;
            powerLvl.increment(200);
            iron.increment(10);

            break;
            case 22:
            itemToUse = this.inv.get(18);
            if(itemToUse.getNumber() == 1){
                if(speed_pot.getNumber() < 1 && potion_challenge.challengesPassed() == false){
                    speed_pot.increment(1);}
                System.out.println("Speed potion: +1");
                System.out.println("If you found both potions, you can use them by typing: use potions");
            }else{
                System.out.println("You need a Diamond PickAxe to open the chest");}
            break;
            case 23:
            itemToUse = this.inv.get(18);
            if(itemToUse.getNumber() == 1){
                if(regeneration_pot.getNumber() < 1 && potion_challenge.challengesPassed() == false){
                    regeneration_pot.increment(1);}
                System.out.println("Regeneration potion: +1");
            }else{
                System.out.println("You need a Diamond PickAxe to open the chest");}
            break;
        }

    }

    public void equip() {
        ItemInventory diamond_helmet = this.inv.get(13);
        ItemInventory diamond_chestplate = this.inv.get(14);
        ItemInventory diamond_leggings = this.inv.get(15);
        ItemInventory diamond_boots = this.inv.get(16);
        System.out.println(diamond_boots.getNumber());
        System.out.println(diamond_chestplate.getNumber());
        if (diamond_helmet.getNumber() == 1 && diamond_chestplate.getNumber() == 1 && diamond_leggings.getNumber() == 1 && diamond_boots.getNumber() == 1) {
            System.out.println("PowerLvl : +60");
            powerLvl.increment(60);
            diamond_boots.decrement(1);
            diamond_chestplate.decrement(1);
            diamond_helmet.decrement(1);
            diamond_leggings.decrement(1);
            System.out.println(diamond_helmet.getItemName() + ": -1");
            System.out.println(diamond_chestplate.getItemName() + ": -1");
            System.out.println(diamond_leggings.getItemName() + ": -1");
            System.out.println(diamond_boots.getItemName() + ": -1");
        } else {

            System.out.println("You need all pieces of diamond gear to the equip your gear");
        }
    }

    public void fightBoss(String whatToFight) {
        Challenge kill_zombie = this.challenge.get(2);
        Challenge kill_witch = this.challenge.get(3);
        Challenge kill_dragon = this.challenge.get(4);
        ItemInventory diamond = this.inv.get(4);
        ItemInventory strength_pot = this.inv.get(19);
        String fight = whatToFight;
        if (fight.equals("end")) {
            if(kill_dragon.challengesPassed() == true){
                System.out.println("You already killed this");}
            else if(getPowerLvl() >= 130) {
                System.out.println("Challenge: " + kill_dragon.getChallenge() + " passed");
                System.out.println("You have won the game!");
                kill_dragon.setPassed(true);
            }else if(getPowerLvl() < 130){
                System.out.println("Your powerLvl is to low, the dragon killed you!!");
                System.out.println("Type quit to quit the game.");
                System.out.println("Better luck next time!");
                this.inv.clear();

            }
        }

        if(fight.equals("zombie")){
            if(kill_zombie.challengesPassed() == true){
                System.out.println("You already killed this");}
            else if(getPowerLvl() >= 20) {
                System.out.println("You have killed the zombie");
                System.out.println("Challenge: " + kill_zombie.getChallenge() + " passed");
                diamond.increment(3);
                System.out.println(diamond.getItemName() + ": +3");
                kill_zombie.setPassed(true);
            }else if(getPowerLvl() < 20){
                System.out.println("The Zombie have killed you");
                System.out.println("You lost all your stuff");
                for (int i : this.inv.keySet()) {
                    ItemInventory item = this.inv.get(i);
                    item.decrement(item.getNumber());
                }
                powerLvl.decrement(getPowerLvl());      
            }
        }
        if(fight.equals("nether_wizzard")){
            if(kill_witch.challengesPassed() == true){
                System.out.println("You already killed this");}
            if(getPowerLvl() >= 60) {
                System.out.println("You have killed the nether_witch");
                diamond.increment(20);
                strength_pot.increment(1);
                System.out.println(diamond.getItemName() + ": +3");
                System.out.println(strength_pot.getItemName() + ": +1");
                System.out.println("Take this potion in by type the words, use strength_potion");
                kill_witch.setPassed(true);
                System.out.println("Challenge: " + kill_witch.getChallenge() + " passed");
            }else if(getPowerLvl() < 60){
                System.out.println("The Nether_witch have killed you");
                System.out.println("You lost all your stuff");
                for (int i : this.inv.keySet()) {
                    ItemInventory item = this.inv.get(i);
                    item.decrement(item.getNumber());
                }
                powerLvl.decrement(getPowerLvl());
            }else{System.out.println("You already killed this");}

        }
    }
}

