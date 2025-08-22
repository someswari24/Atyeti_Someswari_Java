package structural.abstarctFactory;

public class FurnitureStore {
    private Chair chair;
    private Sofa sofa;

    public FurnitureStore(FurnitureFactory furnitureFactory) {
        chair=furnitureFactory.createChair();
        sofa=furnitureFactory.createSofa();
    }

    public void displayFurniture(){
        chair.create();
        sofa.create();
    }
}
