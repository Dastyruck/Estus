/*
 * Car Class
 */

package mainPack;

/**
 *
 * @author Dastyruck
 */
class Car implements TickBased
{

    private int waitTime = 0;
    private String status;

    public Car(){
        this.status = "waiting";
    }

    public void tick(int secondsPassed){
        if(this.status.equals("waiting")){
            this.waitTime += 1;
        }
    }

    public void go()
    {
        this.status = "crossed";
    }

}
