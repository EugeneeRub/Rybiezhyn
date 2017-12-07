package ua.khpi.oop.Rubiezhyn13_14;

/**
 * Inner class
 * Class-Thread creating for call methods from another class
 *
 * @author Rubezhin Evgeniy
 * Data 03.12.2017
 * */
class mySpecialThread extends Thread{
    private int command;// special command that will be started in new thread
    private int timemls;// time for sleeping
    private SpecialMethods spec;// object that has methods for work
    private Thread t;// thread that was created from main thread

    /**
     * Constructor that save command
     *
     * @param command command for current thread
     * @param spec object with methods
     * @param timemls time sleep for method
     * */
    public mySpecialThread(int command,int timemls,SpecialMethods spec){
        this.command = command;
        this.timemls = timemls;
        this.spec = spec;
    }
    /**
     * Setter for current thread
     *
     * @param t current thread
     * */
    public void setT(Thread t){
        this.t = t;
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Работа потока " + getName());
        long timeCurentThread  = System.nanoTime();
        switch (command){
            case 1:
                if (!Thread.interrupted())
                    spec.findMinElement(t,timemls);
                break;
            case 2:
                if (!Thread.interrupted())
                    spec.findMaxElement(t,timemls);
                break;
            case 3:
                if (!Thread.interrupted())
                    spec.printMiddleResult(t,timemls);
                break;
            case 4:
                if (!Thread.interrupted())
                    spec.printSumOfElements(t,timemls);
                break;
            case 5:
                if (!Thread.interrupted())
                    spec.countingOfElements(t,timemls);
                break;
            case 6:
                if (!Thread.interrupted())
                    spec.printGoodElements(t,timemls);
                break;
            default:
                System.out.println("Неверна команда или нет команды");
        }
        double time =  (System.nanoTime() - timeCurentThread)/1000000000d;
        WorkClass.map.put(t.getName(),time);
    }
}
