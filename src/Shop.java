import java.util.HashMap;
import java.util.Map;

public class Shop {
    public static class Product{
        String name;
        int serial_num;
        double price;

        public Product(String name, int serial_num, double price){
            this.name = name;
            this.serial_num = serial_num;
            this.price = price;
        }

        public String getName() {
            return name;
        }
        public double getPrice() {
            return price;
        }
    }

    public static class ProductCatolog{
        private Map<Integer, Product> catolog = new HashMap<>();

        public ProductCatolog(){
            catolog.put(1, new Product("Laptop",123,100000));
            catolog.put(2, new Product("Phone",345,30000));
            catolog.put(3, new Product("Magnum 357",678,200000));
        }

        public Product search_by_id(int searching_id){
            return catolog.get(searching_id);
        }
    }
    public static class PaymentProcessor{
        public boolean paymentProcess(double amount){
            if (amount > 0){
                System.out.println("Successfully paid!");
                return true;
            }
            else{
                System.out.println("Payment failed!");
                return false;
            }
        }
    }
    public static class InvetoryManager{
        private Map<Integer, Integer> stock ;

        public InvetoryManager(){
            stock = new HashMap<>();
            stock.put(1, 23);
            stock.put(2, 40);
            stock.put(3, 400);
        }

        public boolean check_availability(int oredered_quantity,int product_id){
            return stock.getOrDefault(product_id, 0) >= oredered_quantity;
        }

        public void reduce_stock(int product_id, int ordered_quantity){
            if(check_availability(ordered_quantity, product_id)){
                stock.put(product_id, stock.get(product_id) - ordered_quantity);
                System.out.println("Stock updated!");
            }
        }
    }
    public static class ShippingService {
        public double calculate_Shipping(int product_id){
            return product_id * 0.5;
        }
        public void shipOrder(String address){
            System.out.println("Order shipped to: " + address);
        }
    }

    public static class ShoppingFacade{
        private ShippingService shippingService;
        private PaymentProcessor paymentProcessor;
        private InvetoryManager invetoryManager;
        private ProductCatolog productCatolog;
        public ShoppingFacade(){
            shippingService = new ShippingService();
            paymentProcessor = new PaymentProcessor();
            invetoryManager = new InvetoryManager();
            productCatolog = new ProductCatolog();
        }
        public void placeOrder(int quantity, int product_id, String address){
            Product product = productCatolog.search_by_id(product_id);
            if(product == null){
                System.out.println("Product not found!");
                return;
            }
            if(!invetoryManager.check_availability(quantity, product_id)){
                System.out.println("Insufficient stock for "  + product.getName());
                return;
            }

            double totalAmount = product.getPrice() * quantity;
            if( !paymentProcessor.paymentProcess(totalAmount)){
                System.out.println("Payment failed!");
                return;
            }
            double shippingCost = shippingService.calculate_Shipping(product_id);
            System.out.println("Shipping cost: " + shippingCost);
            shippingService.shipOrder(address);
            System.out.println("Shipped to " + address);
            System.out.println("Order placed successfully for " + product.getName() + " ( " + quantity + " )");
        }
    }
}
