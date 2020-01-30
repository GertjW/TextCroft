
/**
 * class PowerLvl - geef hier een beschrijving van deze class
 *
 * @author (Gertjan Wiersma en Jochem Kruizinga)
 * @version (26-1-20)
 */
public class PowerLvl
{
    /**
     * fiels
     * powerlvl
     */
     private int powerLvl;

    /**
     * Constructor for creating a new powerlvl
     * @param lvl
     */
    public PowerLvl(int lvl){
         this.powerLvl = powerLvl + lvl;
        }

    /**
     * getter for powerLvl
     * @return powerLvl
     */
    public int getPowerLvl(){
         return powerLvl;
        }

    /**
     * A method to increment the powerLvl by a certain amount of power
     * @param power
     * @return powerLvl
     */
    public int increment(int power){
         this.powerLvl =  powerLvl + power;
         return powerLvl; 
        }
    /**
     * A method to decrement the powerLvl by a certain amount of power
     * @param power
     * @return powerLvl
     */
        public int decrement(int power){
         this.powerLvl =  powerLvl - power;
         return powerLvl; 
        }
}
