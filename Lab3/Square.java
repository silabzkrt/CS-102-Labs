import java.util.*;
public class Square extends Rectangle {
    
    Scanner sc = new Scanner (System.in);
    public Square(float side) {
        super(side, side);
    }
    
    @Override
    public void printInfo() {
        System.out.println("Square: " + "edge length" + this.getSide() + "(Area = " + calculateArea() + ")");
    }
    
    public float getSide() {
        return getWidth();
    }
    
    public void setSide(float side) {
        setWidth(side);
        setHeight(side);
    }
    
    public static void main(String[] args) {
        Square square = new Square(5);
        square.printInfo();
        square.setSide(10);
        square.printInfo();
    }
    
    @Override
    public GeometricShape2D createShape(){
        System.out.print("Enter the side of the square");
        float sideLength = sc.nextFloat();
        Square square = new Square(sideLength);
        square.printInfo();
        return square;
    }
}
