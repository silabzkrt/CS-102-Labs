package Lab6;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Currency {
    private String name;
    private double rate;
    Random random = new Random();

    public Currency(String name) {
        this.name = name;
        this.rate = random.nextDouble(0.2 , 5.0) * 10;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public static ArrayList<String> fakeCurrencies = new ArrayList<>(Arrays.asList(
        "Zollar", "Neuro", "Lirax", "Yeno", "Pesaro", "Drambit", "Krowne", "Rublix", "Dinoro", "Manoti",
        "Franche", "Solari", "Turqa", "Beyro", "Zent", "Dirhano", "Florinx", "Kashun", "Credor", "Rupanda",
        "Escad", "Ouro", "Bitlar", "Makara", "Sterlira"
    ));
    


}
    
