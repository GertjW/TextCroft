
/**
 * A class that can generate challenges that the player has to accomplish
 *
 * @author (Gertjan Wiersma en Jochem Kruizinga)
 * @version (26-1-2020)
 */
public class Challenge
{
    
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

    /**
     * gets the challenge name/description
     * @return challenge name/description
     */
    public String getChallenge(){
    return this.challenges;}

    /**
     * gets the challenge ID
     * @return challengeID
     */
    public int getChallengesID(){
    return this.id;}

    /**
     * returns a boolean
     * if challenge is passed give true
     * if challenge is not passed give false
     * @return passed
     */
    public boolean challengesPassed(){
    return this.passed;}

    /**
     *
     * @param hasPassed
     * @return
     */
    public boolean setPassed(boolean hasPassed){
    this.passed = hasPassed;
    return this.passed;}
}
