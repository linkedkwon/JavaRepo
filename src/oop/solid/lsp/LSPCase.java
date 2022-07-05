package oop.solid.lsp;

//case 1 : LSP not adapted
/**
class PayService{

    public void purchase(Product item){
        double price = item.getPrice();
        if(!(item instanceof LimitedProduct))
            price *= item.getDiscountRate();

        //purchase logic....
    }

}

class Product{

    private double price;
    private double discountRate;

    public Product(double price) {
        this.price = price;
    }

    public Product(double price, double discountRate) {
        this.price = price;
        this.discountRate = discountRate;
    }

    public double getPrice(){
        return price;
    }

    public double getDiscountRate() {
        return discountRate;
    }

}

class LimitedProduct extends Product{

    public LimitedProduct(double price) {
        super(price);
    }

}
*/

//case 2 : LSP adapted
class PayService{

    public void purchase(Product item){
        double price = item.getPrice();
        price *= item.isLimited() ? 1 : item.getDiscountRate();

        //purchase logic....
    }

}

class Product{

    private double price;
    private double discountRate;

    public Product(double price) {
        this.price = price;
    }

    public Product(double price, double discountRate) {
        this.price = price;
        this.discountRate = discountRate;
    }

    public boolean isLimited(){
        return false;
    }

    public double getPrice(){
        return price;
    }

    public double getDiscountRate() {
        return discountRate;
    }

}

class LimitedProduct extends Product{

    public LimitedProduct(double price) {
        super(price);
    }

    @Override
    public boolean isLimited() {
        return true;
    }

}

public class LSPCase {}
