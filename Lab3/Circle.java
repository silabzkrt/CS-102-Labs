import java.util.Scanner;

public class Circle extends Ellipse {
    Scanner sc = new Scanner(System.in);
    public Circle(float radius) {
        super(radius, radius);
    }

    @Override
    public void printInfo() {
        System.out.println("Circle: " + getSemiMajorAxis() + "(Area = " + calculateArea() + ")");
    }

    @Override
    public float calculateArea() {
        return (float) (Math.PI * getRadius() * getRadius());
    }

    public float getRadius() {
        return getSemiMajorAxis();
    }

    public void setRadius(float radius) {
        setSemiMajorAxis(radius);
        setSemiMinorAxis(radius);
    }

    @Override
    public GeometricShape2D createShape() {
        System.out.print("Enter the radius of the circle");
        float radius = sc.nextFloat();
        Circle circle = new Circle(radius);
        circle.printInfo();
        return circle;
    }

    
}
