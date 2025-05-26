import java.util.Scanner;

public class Pyramid extends GeometricShape3D {
    private float baseArea;
    private float height;
    Scanner sc = new Scanner(System.in);
    
    public Pyramid(float baseArea, float height) {
        this.baseArea = baseArea;
        this.height = height;
    }
    
    @Override
    public float calculateVolume() {
        return (baseArea * height) / 3;
    }
    
    @Override
    public float calculateArea() {
        return baseArea + (float) (Math.sqrt(Math.pow(baseArea / 2, 2) + Math.pow(height, 2)));
    }
    
    @Override
    public void printInfo() {
        System.out.println("Pyramid: " + baseArea + ", " + height + "(Volume = " + calculateVolume() + ")");
    }

    public float setBaseArea(float baseArea) {
        return this.baseArea = baseArea;
    }

    public float setHeight(float height) {
        return this.height = height;
    }

    @Override
    public GeometricShape2D createShape(){
        System.out.print("Enter the base of the pyramid");
        float base = sc.nextFloat();
        System.out.print("Enter the height of the pyramid");
        height = sc.nextFloat();
        Pyramid pyramid = new Pyramid(base, height);
        pyramid.printInfo();
        return pyramid;
    }
    
}
