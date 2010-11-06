/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mainPack;

/**
 *
 * @author Tyler(Chacha) chacha@chacha102.com
 */
class HighFreqRoad extends Road{

    protected int addCarInterval = Stats.addCarIntervalHigh;

    public HighFreqRoad(int initialWeight){
        super(initialWeight);
    }

    public int getFrequency(){
        return this.addCarInterval;
    }

}
