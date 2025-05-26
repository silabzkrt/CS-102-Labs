import java.util.*;
public class Ellipse extends GeometricShape2D {
    private float semiMajorAxis;
    private float semiMinorAxis;
    Scanner sc = new Scanner(System.in);
    
    public Ellipse(float semiMajorAxis, float semiMinorAxis) {
        this.semiMajorAxis = semiMajorAxis;
        this.semiMinorAxis = semiMinorAxis;
    }

    @Override
    public float calculateArea() {
        return (float) (Math.PI * semiMajorAxis * semiMinorAxis);
    }

    @Override
    public void printInfo() {
        System.out.println("Ellipse: " + semiMajorAxis + " by " + semiMinorAxis + "(Area = " + calculateArea() + ")");
    }
    
    public float getSemiMajorAxis() {
        return semiMajorAxis;
    }
    
    public float getSemiMinorAxis() {
        return semiMinorAxis;
    }
    
    public void setSemiMajorAxis(float semiMajorAxis) {
        this.semiMajorAxis = semiMajorAxis;
    }
    
    public void setSemiMinorAxis(float semiMinorAxis) {
        this.semiMinorAxis = semiMinorAxis;
    }

    @Override
    public GeometricShape2D createShape(){
        System.out.print("Enter the major axis of the ellipse");
        float majorAxis = sc.nextFloat();
        System.out.print("Enter the minor axis of the ellipse");
        float minorAxis = sc.nextFloat();
        Ellipse ellipse = new Ellipse(minorAxis, majorAxis);
        ellipse.printInfo();
        return ellipse;
    }
    
}
