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
    private boolean craftingTable;
    public Inventory(){

        this.inv.put(1, new ItemInventory("wood", 1, 0, 64));
        this.inv.put(2, new ItemInventory("sand", 2, 0, 64));
        this.inv.put(3, new ItemInventory("stone", 3, 0, 64));
        this.inv.put(4, new ItemInventory("diamond", 4, 0, 5));
        this.inv.put(5, new ItemInventory("iron", 5, 0, 32));
        this.inv.put(6, new ItemInventory("sticks", 6, 0, 64));
        this.inv.put(7, new ItemInventory("wood_PickAxe", 7, 0, 1));
        this.inv.put(8, new ItemInventory("cTABLE", 8, 0, 1));
        this.inv.put(9, new ItemInventory("stone_Axe", 9, 0, 1));
         this.inv.put(999, new ItemInventory("devmode", 999, 0, 999));
        

        craftingTable = false;
    }

    public void showInv() {
        for (int i : this.inv.keySet()) {
            ItemInventory item = this.inv.get(i);
            if(item.getNumber() > 0){
                System.out.println(item.getItemName() + " : " + item.getNumber());
            }
        }
    }

    public void showCraftingTableStatus(){
        System.out.println("craftingTable = " + craftingTable);
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
        ItemInventory wood = this.inv.get(1);
        ItemInventory stone = this.inv.get(3);
        ItemInventory sticks = this.inv.get(6);
        switch(craftedItemIndex){
            case 6:
            
            if(wood.getNumber() >= 2) {
                wood.decrement(2);
                newItem.increment(4);
            }
            else{System.out.println("you cant craft this");}
            break;
      
            case 7:    
            if(craftingTable == false){
                System.out.println("You need a craftingTable to craft this");
            }
            else if(wood.getNumber() >= 3 && sticks.getNumber() >= 2){
                if(newItem.getNumber() < 1){
                    wood.decrement(3);
                    sticks.decrement(2);
                    newItem.increment(1);    
                }
                else{
                    System.out.println("You already got a " + newItem.getItemName());
                }
            }
            break;
            
            case 8:
            if(wood.getNumber() >= 4 && craftingTable == false){
            wood.decrement(4); 
            craftingTable = true;
            return;
        }
            else{System.out.println("you cant craft this");}
        break;
        
        case 9:    
            if(craftingTable == false){
                System.out.println("You need a craftingTable to craft this");
            }
            else if(stone.getNumber() >= 3 && sticks.getNumber() >= 2){
                if(newItem.getNumber() < 1){
                    stone.decrement(3);
                    sticks.decrement(2);
                    newItem.increment(1);    
                }
                else{
                    System.out.println("You already got a " + newItem.getItemName());
                } break;
            }
            
    } 
}

    public void use(int index){
        ItemInventory itemToUse = this.inv.get(index);
        ItemInventory stone = this.inv.get(3);
        ItemInventory wood = this.inv.get(1);
        if(itemToUse.getItemID() == 7 && itemToUse.getNumber() == 1){
            stone.increment(1);
        }
        if(itemToUse.getItemID() == 9 && itemToUse.getNumber() == 1){
            wood.increment(3);
        }
        if(itemToUse.getItemID() == 999){
          wood.increment(40);
          stone.increment(40);
          ItemInventory sticks = this.inv.get(6);
    }

}
}