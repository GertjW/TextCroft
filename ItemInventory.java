
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
    * @Setter and getter for item
    * @param Set String Name
    * @param return String;
    */
  
   public ItemInventory(String itemName, int itemID, int number, int max){
       this.itemName = itemName;
       this.itemID = itemID;
       this.number = number;
       this.max = max;
    }
    
   public String getItemName(){
      return this.itemName;
    }
    
   /**
    * Setter and getter for itemID
    * @param int set Item
    * 
    * Return item ID
    * @Return int
    */ 
   public int getItemID(){
     return itemID;
    }
    public int getNumber() { return this.number;}

    public void increment(int i){
       if(this.number + i <= this.max) {
           this.number += i;
       }

    }
    public void decrement(int i){
       if(this.number - i >= 0){
           this.number -= i;
       }
    }
    
   
    /**
     * Set the location of the item based on the roomID
     * @param int set itemRoom
     * 
     * Return the roomID of the item
     * @return int
     */
    
    
       
    /**
     * Set the value of the item
     * 0 is useless, 1 is quest item
     * @param int 
     * 
     * Return the value of the item
     * @return int
     */



}
