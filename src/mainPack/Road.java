/*
 * Road Class
 */

package mainPack;

/**
 *
 * @author Garrison Price
 */
public class Road implements TickBased {

    int weight = 0;
    int tickValue = 0;
    int waitTime = 0;
    public Road(int initialWeight, int tickValue){
        this.weight = initialWeight;
        this.tickValue = tickValue;
    }

    public void go(){
        this.weight = 0;
        this.waitTime = 0;
    }

    public void tick(){
        this.waitTime += 1;
        this.weight += this.tickValue;
    }

    public int getWeight(){
        return this.weight;
    }

    public int getWaitTime(){
        return this.waitTime;
    }

}