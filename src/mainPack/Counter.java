/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mainPack;

/**
 *
 * @author Tyler(Chacha) chacha@chacha102.com
 */
public class Counter {

    private int num = 0;
    public void increase(){
        this.num += 1;
    }
    public void increase(int num){
        this.num += num;
    }
    public int getNum(){
        return this.num;
    }

}
