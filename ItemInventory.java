
/**
 * class Objects - geef hier een beschrijving van deze class
 *
 * @author (jouw naam)
 * @version (versie nummer of datum)
 */
public class ItemInventory
{
   /**
    * Fields
    */
    
   private int itemID;
   private String itemName;
   private int number;
   private int max;

   
   /**
    *
    * @param Set String Name
    * @param Set int itemID
    * @param Set int number(how many item do you have)
    * @param Set int max(The maximun amount of items that you can have)
    */
  
   public ItemInventory(String itemName, int itemID, int number, int max){
       this.itemName = itemName;
       this.itemID = itemID;
       this.number = number;
       this.max = max;
    }

    /**
     * Getter for Item Name;
     * @return getItemName;
     */
   public String getItemName(){
      return this.itemName;
    }
    
  /**
    * Getter for item ID
    * @Return int
    */ 
   public int getItemID(){
     return itemID;
    }

    /**
     * Getter of itemNumber
     * @return number;
     */
    public int getNumber() { return this.number;}

    /**
     * Increment the numbers of the items by i (index)
     * if i = 10, he increment the number of the items by 10
     * @param i
     */
    public void increment(int i){
       if(this.number + i <= this.max) {
           this.number += i;
       }

    }

    /**
     *decrement the numbers of the items by i (index)
     *if i = 10, he decrement the number of the items by 10
     * @param i
     */
    public void decrement(int i){
       if(this.number - i >= 0){
           this.number -= i;
       }
    }
    
   
    