package Admin.Control.Function;
import java.util.Scanner;
import static Admin.Control.Function.ManageProducts.updateAmountProduct;

public class TestAmountProduct {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        updateAmountProduct(1, 1);
        scanner.close();
    }
}
