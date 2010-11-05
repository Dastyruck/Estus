package mainPack;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This keeps our memory from building up too much
 * @author Garrison
 */
public class GarbageCollector
{

    public Timer reset = new Timer();

    /**
     * Simple constructor to get things going
     */
    public GarbageCollector()
    {
        update();
    }

    /**
     * Calles to GC and starts a new timer
     */
    public void update()
    {
        reset.schedule(new RemindTask(), 30000);
        System.gc();
    }

    /**
     * Custom TimerTask to update our Collector
     */
    class RemindTask extends TimerTask
    {
        public void run()
        {
            update();
        }
    }
}
