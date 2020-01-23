
/**
 * class PowerLvl - geef hier een beschrijving van deze class
 *
 * @author (jouw naam)
 * @version (versie nummer of datum)
 */
public class PowerLvl
{
     private int powerLvl;
     
     public PowerLvl(int lvl){
         this.powerLvl = powerLvl + lvl;
        }
     public int getPowerLvl(){
         return powerLvl;
        }
     public int increment(int power){
         this.powerLvl =  powerLvl + power;
         return powerLvl; 
        }
        public int decrement(int power){
         this.powerLvl =  powerLvl - power;
         return powerLvl; 
        }
}
