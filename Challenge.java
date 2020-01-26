
/**
 * class Achievements - geef hier een beschrijving van deze class
 *
 * @author (jouw naam)
 * @version (versie nummer of datum)
 */
public class Challenge
{
    /**
     * fields
     */
    
    private String challenges;
    private int id;
    private boolean passed;
    
    /**
     * @param id from challenges 
     * @return the challenge if boolean false
     * else return boolean true
     */
    
    public Challenge(String challenges, int id, boolean passed){
    this.challenges = challenges;
    this.id = id;
    this.passed = passed;
    }
    
    public String getChallenge(){
    return this.challenges;}
    public int getChallengesID(){
    return this.id;}
    public boolean challengesPassed(){
    return this.passed;}
    public boolean setPassed(boolean hasPassed){
    this.passed = hasPassed;
    return this.passed;}
}
