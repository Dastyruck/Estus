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
    private int road;
    private int average;
    public Statistic (String name){
        this.name = name;
        this.average = 0;
    }

    public void least(int newNumber, int road){
        if(newNumber < this.number){
            this.number = newNumber;
            this.road = road;
        }
        average(newNumber);
    }

    public void most(int newNumber, int road){
        if(newNumber > this.number){
            this.number = newNumber;
            this.road = road;
        }
        average(newNumber);
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

    public int getNumber(){
        return this.number;
    }

    public int getRoad(){
        return this.road;
    }

    public String getName(){
        return this.name;
    }



}
