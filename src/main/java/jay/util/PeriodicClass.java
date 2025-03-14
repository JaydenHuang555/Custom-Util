package jay.util;

public abstract class PeriodicClass {

    private class PeriodicThread extends Thread {

        private PeriodicClass object;

        PeriodicThread(PeriodicClass object){
            this.object = object;
        }

        @Override
        public void run(){
            object.periodic();
        }
    }

    protected PeriodicClass(){
        new PeriodicThread(this);
    }

    public abstract void periodic();
}
