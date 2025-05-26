import java.util.Scanner;

public class Cuboid extends GeometricShape3D {
    private float length;
    private float width;
    private float height;
    private boolean isTwoDimensional;
    Scanner sc = new Scanner(System.in);
    
    
    public Cuboid(float length, float width, float height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }
    
    @Override
    public float calculateArea() {
        return 2 * (length * width + length * height + width * height);
    }
    
    @Override
    public float calculateVolume() {
        return length * width * height;
    }
    
    @Override
    public void printInfo() {
        System.out.println("Cuboid: " + length + " x " + width + " x " + height + "(Volume = " + calculateVolume() + ", Area = " + calculateArea() + ")");
    }
    
    public float getLength() {
        return length;
    }

    public float setLength(float length) {
        return this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public float setWidth(float width) {
        return this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public float setHeight(float height) {
        return this.height = height;
    }

    @Override
    public GeometricShape2D createShape(){
        System.out.print("Enter the length of the cuboid");
        length = sc.nextFloat();
        System.out.print("Enter the width of the cuboid");
        width = sc.nextFloat();
        System.out.print("Enter the height of the cuboid");
        height = sc.nextFloat();
        Cuboid cuboid = new Cuboid(length, width, height);
        cuboid.printInfo();
        return cuboid;
}
}
