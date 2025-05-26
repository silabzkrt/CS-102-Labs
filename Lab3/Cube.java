import java.util.*;
public class Cube extends Cuboid {
    Scanner sc = new Scanner(System.in);
    public Cube(float side) {
        super(side, side, side);
    }
    
    @Override
    public void printInfo() {
        System.out.println("Cube: " + getLength() + "(Volume = " + calculateVolume() + ", Area = " + calculateArea() + ")");
    }

    @Override
    public float calculateVolume() {
        return (float) Math.pow(getLength(), 3);
    }

    @Override
    public float calculateArea() {
        return (float) (6 * getLength() * getLength());
    }
    
    public float getSide() {
        return getLength();
    }

    public float setSide(float side) {
        return setLength(side);
    }

    @Override
    public GeometricShape2D createShape(){
        System.out.print("Enter the side of the cube");
        float sideLength = sc.nextFloat();
        Cube cube = new Cube(sideLength);
        cube.printInfo();
        return cube;
    }
}
