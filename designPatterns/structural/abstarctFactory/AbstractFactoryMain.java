package structural.abstarctFactory;

public class AbstractFactoryMain {
    public static void main(String[] args) {
        FurnitureFactory modernFactory=new ModernFurnitureFactory();
        FurnitureStore modernStore=new FurnitureStore(modernFactory);
        modernStore.displayFurniture();

        System.out.println("-----------------------------");

        FurnitureFactory victorianFactory=new VictorianFurnitureFactory();
        FurnitureStore victorianstore=new FurnitureStore(victorianFactory);
        victorianstore.displayFurniture();
    }
}
