/*
 * Road Class
 */

package mainPack;

/**
 *
 * @author Garrison Price
 */
public class Road implements TickBased {

    static int carCrossTime = 5;
    static int MaxCars = 20;

    int weight = 0;
    int tickValue = 0;
    int waitTime = 0;
    int cars = 1;
    
    public Road(int initialWeight, int tickValue){
        this.weight = initialWeight;
        this.tickValue = tickValue;
    }

    public void go(){
        this.weight = 0;
        this.waitTime = 0;
        this.cars = 0;
    }

    public void tick(int secondsPassed){
        this.waitTime += 1;

        // If the wait time is over than 60, the weight per second is doubled
        if(this.waitTime < 60){
            this.weight += this.tickValue * this.cars;
        }else{
            this.weight += (this.tickValue * 2) * this.cars;
        }
        

        if(secondsPassed % 5 == 0){
            addCar();
        }
    }

    private void addCar(){
        if(this.cars <= Road.MaxCars){
            this.cars += 1;
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