import java.util.HashMap;
/**
 * class inventory - geef hier een beschrijving van deze class
 *
 * @author (jouw naam)
 * @version (versie nummer of datum)
 */
public class Inventory
{
    HashMap<Integer, ItemInventory> inv = new HashMap<Integer, ItemInventory>();

    public Inventory(){

        this.inv.put(1, new ItemInventory("wood", 1, 0, 64));
        this.inv.put(2, new ItemInventory("sand", 2, 0, 64));
        this.inv.put(3, new ItemInventory("stone", 3, 0, 64));
        this.inv.put(4, new ItemInventory("diamond", 4, 0, 5));
        this.inv.put(5, new ItemInventory("iron", 5, 0, 32));
        this.inv.put(6, new ItemInventory("sticks", 6, 0, 64));
        this.inv.put(7, new ItemInventory("wood_PickAxe", 7, 0, 1));
    }

    public void showInv() {
        for (int i : this.inv.keySet()) {
            ItemInventory item = this.inv.get(i);
            if(item.getNumber() > 0){
                System.out.println(item.getItemName() + " : " + item.getNumber());
            }
        }
    }

    public void increment(int itemIndex, int number){
        ItemInventory item = this.inv.get(itemIndex);
        item.increment(number);
    }

    public void decrement(int itemIndex, int number){
        ItemInventory item = this.inv.get(itemIndex);
        item.decrement(number);
    }

    public void craft(int craftedItemIndex){
        ItemInventory newItem = this.inv.get(craftedItemIndex);
        if(craftedItemIndex == 6){
            ItemInventory wood = this.inv.get(1);
            if(wood.getNumber() >= 2) {
                
                wood.decrement(2);
                newItem.increment(4);
            }
        }
        else if(craftedItemIndex == 7){
            ItemInventory wood = this.inv.get(1);
            ItemInventory sticks = this.inv.get(6);
            if(wood.getNumber() >= 3 && sticks.getNumber() >= 2) {
                if(newItem.getNumber() >= 1){ 
                System.out.println("You already got a " + newItem.getItemName());
                }else{
                wood.decrement(3);
                sticks.decrement(2);
                newItem.increment(1);
            }
        }
    }
}

}
