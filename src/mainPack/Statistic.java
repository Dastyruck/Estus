/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mainPack;

/**
 *
 * @author Tyler(Chacha) chacha@chacha102.com
 */
public class Statistic {

    private String name;
    private int number;
    private int lowest;
    private int lowestRoad;
    private int highest;
    private int highestRoad;
    private int road;
    private int average;
    public Statistic (String name){
        this.name = name;
        this.average = 0;
    }

    public void record(int newNumber, int road){
        least(newNumber, road);
        most(newNumber, road);
        average(newNumber);
    }

    public void least(int newNumber, int road){
        if(newNumber < this.lowest){
            this.lowest = newNumber;
            this.lowestRoad = road;
        }
    }

    public void most(int newNumber, int road){
        if(newNumber > this.highest){
            this.highest = newNumber;
            this.highestRoad = road;
        }
    }

    public void average(int num){
        if(this.average == 0){
            this.average = num;
        }else{
            this.average = (this.average + num) / 2;
        }
    }

    public int getAverage(){
        return this.average;
    }

    public int getLowest(){
        return this.lowest;
    }

    public int getLowestRoad(){
        return this.lowestRoad;
    }

    public int getHighest(){
        return this.highest;
    }

    public int getHighestRoad(){
        return this.highestRoad;
    }

    public int getRoad(){
        return this.road;
    }

    public String getName(){
        return this.name;
    }



}
