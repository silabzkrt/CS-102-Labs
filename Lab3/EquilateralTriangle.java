import java.util.Scanner;

public class EquilateralTriangle extends GeometricShape2D{
    private float side;
    private boolean isTwoDimensional;
    Scanner sc = new Scanner(System.in);
    
    
    public EquilateralTriangle(float side) {
        this.side = side;
    }

    @Override
    public float calculateArea() {
        return (float) (Math.sqrt(3) / 4 * side * side);
    }

    @Override
    public void printInfo() {
        System.out.println("Equilateral Triangle: " + side + " by " + side + " by " + side + "(Area = " + calculateArea() + ")");
    }
    
    public float getSide() {
        return side;
    }
    
    public void setSide(float side) {
        this.side = side;
    }

    @Override
    public GeometricShape2D createShape(){
        System.out.print("Enter the side of the triangle");
        float side = sc.nextFloat();
        EquilateralTriangle triangle = new EquilateralTriangle(side);
        triangle.printInfo();
        return triangle;
    }
}
