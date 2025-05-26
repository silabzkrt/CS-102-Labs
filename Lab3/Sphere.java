import java.util.*;
public class Sphere extends GeometricShape3D{
    private float radius;
    Scanner sc = new Scanner(System.in);

    public Sphere(float radius) {
        this.radius = radius;
    }

    @Override
    public float calculateVolume() {
        return (float) (4.0/3.0 * Math.PI * Math.pow(radius, 3));
    }

    @Override
    public void printInfo() {
        System.out.println("Sphere: " + radius + "(Volume = " + calculateVolume() + ")");
    }

    @Override
    public float calculateArea() {
        return (float) (4 * Math.PI * Math.pow(radius, 2));
    }

    public float setRadius(float radius) {
        return this.radius = radius;
    }

    @Override
    public GeometricShape2D createShape(){
        System.out.print("Enter the radius of the sphere");
        radius = sc.nextFloat();
        Sphere sphere = new Sphere(radius);
        sphere.printInfo();
        return sphere;
    }
    
}
