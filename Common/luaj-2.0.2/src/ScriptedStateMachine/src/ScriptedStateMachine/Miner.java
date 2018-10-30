/**
 * @author Petr (http://www.sallyx.org/)
 *
 * Desc:   A class defining a goldminer. The miner has a FSM defined
 *         by a Lua script.
 */
package ScriptedStateMachine;

public class Miner extends Entity {

    /**
     * the amount of nuggets a miner can carry
     */
    public static final int MaxNuggets = 3;
    /**
     * above this value a miner is sleepy
     */
    public static final int TirednessThreshold = 2;
    final public static int ComfortLevel = 5;
    private ScriptedStateMachine<Miner> m_pStateMachine;
    //how many nuggets the miner has in his pockets
    private int m_iGoldCarried;
    //the higher the value, the more tired the miner
    private int m_iFatigue;
    //above this value a miner is thirsty
    final public static int ThirstLevel = 5;
    private int m_iThirst;

    private int m_iMoneyInBank;



    public Miner(String name) {
        super(name);
        m_iGoldCarried = 0;
        m_iFatigue = 0;
        m_iThirst = 0;
        m_iMoneyInBank = 0;
        m_pStateMachine = new ScriptedStateMachine<Miner>(this);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        m_pStateMachine = null;
    }

    //this must be implemented
    @Override
    public void Update() {
        m_pStateMachine.Update();
    }

    public int GoldCarried() {
        return m_iGoldCarried;
    }

    public void SetGoldCarried(int val) {
        m_iGoldCarried = val;
    }

    public void AddToGoldCarried(int val) {
        m_iGoldCarried += val;

        if (m_iGoldCarried < 0) {
            m_iGoldCarried = 0;
        }
    }

    public boolean Fatigued() {
        if (m_iFatigue > TirednessThreshold) {
            return true;
        }

        return false;
    }

    public boolean Thirsty() {
        if (m_iThirst >= ThirstLevel) {
            return true;
        }

        return false;
    }


    public void BuyAndDrinkAWhiskey() {
        m_iThirst = 0;
        m_iMoneyInBank -= 2;
    }

    public void AddToWealth(int val) {
        m_iMoneyInBank += val;

        if (m_iMoneyInBank < 0) {
            m_iMoneyInBank = 0;
        }
    }

    public void DecreaseFatigue() {
        m_iFatigue -= 1;
    }

    public void IncreaseFatigue() {
        m_iFatigue += 1;
    }

    public ScriptedStateMachine<Miner> GetFSM() {
        return m_pStateMachine;
    }

    public int Wealth() {
        return m_iMoneyInBank;
    }
    public boolean PocketsFull() {
        return m_iGoldCarried >= MaxNuggets;
    }
    public static int x() { return 1; }
}