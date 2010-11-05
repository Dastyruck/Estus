/*
 * Road Class
 */

package mainPack;

/**
 *
 * @author Garrison Price
 */
public class Road implements TickBased {

    static int carCrossTime = 7;

    int weight = 0;
    int tickValue = 0;
    int waitTime = 0;
    int cars = 5;
    
    public Road(int initialWeight, int tickValue){
        this.weight = initialWeight;
        this.tickValue = tickValue;
    }

    public void go(){
        this.weight = 0;
        this.waitTime = 0;
        //this.cars = 0;
    }

    public void tick(){
        this.waitTime += 1;
        this.weight += this.tickValue;

        if(this.waitTime % 1 == 0){
            //this.cars += 1;
        }
    }

    // Gets the current weight value
    public int getWeight(){
        return this.weight;
    }

    // Returns the number of cars currently waiting
    public int getCars(){
        return this.cars;
    }

    // Returns the amount of time since last go()
    public int getWaitTime(){
        return this.waitTime;
    }

    // Returns the cross time need for all cars to cross
    public int getCrossTime(){
        return this.cars * Road.carCrossTime;
    }

}