package mainPack;
public class OncomingCarAlgorithm {

    private int carsPerHour = Stats.defaultCarsPerHour;
    private int seconds = 0;
    private Road road;

    public Counter cars = new Counter();
    public OncomingCarAlgorithm(Road parentRoad){
        this.road = parentRoad;

        // First find out cars per minute
        int carsPerMinute = carsPerHour/60;
        
    }

    public void tick(){
        this.seconds += 1;

        // Only run the calculation every 6 seconds
        if(this.seconds % 1 == 0){
            if(oncomingCar()){
                this.road.addCar();
                this.cars.increase();
            }
        }
    }

    private boolean oncomingCar(){
        double randNum = Math.random() * 10;
        double numLimit = 9;

        if(randNum > numLimit){
            return true;
        }else{
            return false;
        }
    }

}
