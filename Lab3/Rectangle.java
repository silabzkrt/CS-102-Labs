import java.util.Scanner;

public class Rectangle extends GeometricShape2D {
    private float width;
    private float height;
    Scanner sc = new Scanner(System.in);
    
    public Rectangle(float width, float height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public float calculateArea() {
        return width * height;
    }

    @Override
    public void printInfo() {
        System.out.println("Rectangle: " + width + " by " + height + "(Area = " + calculateArea() + ")");
    }
    
    public float getWidth() {
        return width;
    }
    
    public float getHeight() {
        return height;
    }
    
    public void setWidth(float width) {
        this.width = width;
    }
    
    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public GeometricShape2D createShape(){
        System.out.print("Enter the length of the rectangle");
        float length = sc.nextFloat();
        System.out.print("Enter the width of the rectangle");
        float width = sc.nextFloat();
        Rectangle rectangle = new Rectangle(length, width);
        rectangle.printInfo();
        return rectangle;
    }
}
