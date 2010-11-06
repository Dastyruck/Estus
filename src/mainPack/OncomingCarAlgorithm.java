package mainPack;
public class OncomingCarAlgorithm {

    private int carsPerHour = Stats.defaultCarsPerHour;
    private int seconds = 0;
    private Road road;
    public OncomingCarAlgorithm(Road parentRoad){
        this.road = parentRoad;

        // First find out cars per minute
        int carsPerMinute = carsPerHour/60;
        
    }

    public void tick(){
        this.seconds += 1;

        // Only run the calculation every 6 seconds
        if(this.seconds % 6 == 0){
            if(oncomingCar()){
                this.road.addCar();
            }
        }
    }

    private boolean oncomingCar(){

        double randNum = Math.random() * 1000000;
        double numLimit = 999999;

        if(randNum > numLimit){
            return true;
        }else{
            return false;
        }
    }

}
