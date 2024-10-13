public class Main {
    public static void main(String[] args) {
        Shop.ShoppingFacade shoppingFacade = new Shop.ShoppingFacade();

        shoppingFacade.placeOrder(1, 3, "somewhere");
        shoppingFacade.placeOrder(10000, 2, "somewhere");
        shoppingFacade.placeOrder(3, 5, "somewhere");
    }
}