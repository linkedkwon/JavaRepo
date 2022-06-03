package exception.custom;

//case 1 : Declaration of custom exception class
class OutOfStockException extends Exception{

    public OutOfStockException() {
    }

    public OutOfStockException(String message) {
        super(message);
    }

}

//case 2 : Exception call
class Product{
    private int stockCnt;

    public Product(int stockCnt) {
        this.stockCnt = stockCnt;
    }

    public void setStockCnt(int orderCnt) throws OutOfStockException{
        if(orderCnt > stockCnt){
            throw new OutOfStockException(
                    "Short of " + (orderCnt - stockCnt) + " in stock");
        }
        stockCnt -= orderCnt;
    }

}

public class CustomExceptionCase {

    public static void main(String[] args){
        Product order = new Product(5);
        try {
            order.setStockCnt(20);
        } catch (OutOfStockException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

}