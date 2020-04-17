public class BasicSingleton {
    private static BasicSingleton instans;

    private BasicSingleton(){}

    public static BasicSingleton getInstans() {
        if(instans == null){
           instans = new BasicSingleton();
        }
        return instans;
    }
}
