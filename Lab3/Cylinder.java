import java.util.Scanner;

public class Cylinder extends GeometricShape3D {
    private float radius;
    private float height;
    Scanner sc = new Scanner(System.in);
    
    public Cylinder(float radius, float height) {
        this.radius = radius;
        this.height = height;
    }
    
    @Override
    public float calculateVolume() {
        return (float) (Math.PI * Math.pow(radius, 2) * height);
    }
    
    @Override
    public void printInfo() {
        System.out.println("Cylinder: " + radius + " x " + height + "(Volume = " + calculateVolume() + ")");
    }
    
    @Override
    public float calculateArea() {
        return (float) (2 * Math.PI * radius * radius + 2 * Math.PI * radius * height);
    }

    public float setRadius(float radius) {
        return this.radius = radius;
    }

    public float setHeight(float height) {
        return this.height = height;
    }

    @Override
    public GeometricShape2D createShape(){
        System.out.print("Enter the height of the cylinder");
        float height = sc.nextFloat();
        System.out.print("Enter the radius of the cylinder");
        radius = sc.nextFloat();
        Cylinder cylinder = new Cylinder(radius, height);
        cylinder.printInfo();
        return cylinder;
}
}
