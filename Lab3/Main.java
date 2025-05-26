
import java.util.Scanner;

public class Main {
    private GeometricShape2D[] shapes;
    private int size;
    public Main(int size) {
        shapes = new GeometricShape2D[size];
    }
    public void printShapeMenu(){
        System.out.println("2D Shapes:");
        System.out.println("*****************************");
        System.out.println("1. Circle");
        System.out.println("2. Rectangle");
        System.out.println("3. Triangle");
        System.out.println("4. Square");
        System.out.println("5. Ellipse");
        System.out.println("6.MultiShape2D");
        System.out.println("*****************************");
        System.out.println("3D Shapes:");
        System.out.println("*****************************");
        System.out.println("7. Sphere");
        System.out.println("8. Cube");
        System.out.println("9. Cylinder");
        System.out.println("10. Pyramid");
        System.out.println("11. Cuboid");
    }
    public GeometricShape2D createShape() {
        System.out.println("Which shape you like to create?");
        printShapeMenu();
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        int index = 0;
        switch (choice) {
            case 1:
                System.out.print("Enter the radius of the circle");
                float radius = sc.nextFloat();
                Circle circle = new Circle(radius);
                circle.printInfo();
                this.printShapes();
                System.out.print("Enter the index you want to add to: ");
                index = sc.nextInt();
                this.shapes[index] = circle;
                return circle;
            case 2:
                System.out.print("Enter the length of the rectangle");
                float length = sc.nextFloat();
                System.out.print("Enter the width of the rectangle");
                float width = sc.nextFloat();
                Rectangle rectangle = new Rectangle(length, width);
                rectangle.printInfo();
                this.printShapes();
                System.out.print("Enter the index you want to add to: ");
                index = sc.nextInt();
                this.shapes[index] = rectangle;
                return rectangle;
            case 3:
                System.out.print("Enter the side of the triangle");
                float side = sc.nextFloat();
                EquilateralTriangle triangle = new EquilateralTriangle(side);
                triangle.printInfo();
                this.printShapes();
                System.out.print("Enter the index you want to add to: ");
                index = sc.nextInt();
                this.shapes[index] = triangle;
                return triangle;
            case 4:
                System.out.print("Enter the side of the square");
                float sideLength = sc.nextFloat();
                Square square = new Square(sideLength);
                square.printInfo();
                this.printShapes();
                System.out.print("Enter the index you want to add to: ");
                index = sc.nextInt();
                this.shapes[index] = square;
                return square;
            case 5:
                System.out.print("Enter the major axis of the ellipse");
                float majorAxis = sc.nextFloat();
                System.out.print("Enter the minor axis of the ellipse");
                float minorAxis = sc.nextFloat();
                Ellipse ellipse = new Ellipse(minorAxis, majorAxis);
                ellipse.printInfo();
                this.printShapes();
                System.out.print("Enter the index you want to add to: ");
                index = sc.nextInt();
                this.shapes[index] = ellipse;
                return ellipse;
            case 6:
                System.out.print("Enter the number of shapes");
                int size = sc.nextInt();
                MultiShape2D multiShape = new MultiShape2D(size);
                multiShape.printInfo();
                System.out.println();
                this.printShapes();
                System.out.print("Enter the index you want to add to: ");
                index = sc.nextInt();
                this.shapes[index] = multiShape;
                return multiShape;
            case 7:
                System.out.print("Enter the radius of the sphere");
                radius = sc.nextFloat();
                Sphere sphere = new Sphere(radius);
                sphere.printInfo();
                this.printShapes();
                System.out.print("Enter the index you want to add to: ");
                index = sc.nextInt();
                this.shapes[index] = sphere;
                return sphere;
            case 8:
                System.out.print("Enter the side of the cube");
                sideLength = sc.nextFloat();
                Cube cube = new Cube(sideLength);
                cube.printInfo();
                this.printShapes();
                System.out.print("Enter the index you want to add to: ");
                index = sc.nextInt();
                this.shapes[index] = cube;
                return cube;
            case 9:
                System.out.print("Enter the height of the cylinder");
                float height = sc.nextFloat();
                System.out.print("Enter the radius of the cylinder");
                radius = sc.nextFloat();
                Cylinder cylinder = new Cylinder(radius, height);
                cylinder.printInfo();
                this.printShapes();
                System.out.print("Enter the index you want to add to: ");
                index = sc.nextInt();
                this.shapes[index] = cylinder;
                return cylinder;
            case 10:
                System.out.print("Enter the base of the pyramid");
                float base = sc.nextFloat();
                System.out.print("Enter the height of the pyramid");
                height = sc.nextFloat();
                Pyramid pyramid = new Pyramid(base, height);
                pyramid.printInfo();
                this.printShapes();
                System.out.print("Enter the index you want to add to: ");
                index = sc.nextInt();
                this.shapes[index] = pyramid;
                return pyramid;
            case 11:
                System.out.print("Enter the length of the cuboid");
                length = sc.nextFloat();
                System.out.print("Enter the width of the cuboid");
                width = sc.nextFloat();
                System.out.print("Enter the height of the cuboid");
                height = sc.nextFloat();
                Cuboid cuboid = new Cuboid(length, width, height);
                cuboid.printInfo();
                this.printShapes();
                System.out.print("Enter the index you want to add to: ");
                index = sc.nextInt();
                this.shapes[index] = cuboid;
                return cuboid;
            default:
                System.out.println("Invalid choice");
        }
        return null;
    }

    public void printShapes(){
        for (int i = 0; i < this.shapes.length; i++) {
            System.out.print("[" + i + "]");
            if(this.shapes[i] != null){
                this.shapes[i].printInfo();
            }
            System.out.println();
        }
    }

    public void addToMultiShape(MultiShape2D multishape) {
        printShapes();
        System.out.println("Remember you can only select a shape that is 2D to add to multishape2D!");
        System.out.print("Enter the index of the shape you wish to enter: ");
        Scanner sc = new Scanner(System.in);
        int index = sc.nextInt();
        while (shapes[index - 1].isTwoDimensional() == false || shapes[index - 1] instanceof MultiShape2D) {
            System.out.println("Invalid choice! Please select a multisahpe2D!");
            System.out.print("Enter the index of the shape you wish to enter: ");
            index = sc.nextInt();
        }
        multishape.printShapes();
        System.out.print("Enter the index you wish to add the shape to: ");
        int index2 = sc.nextInt();
        multishape.addShape(shapes[index - 1], index2);
    }

    public void mergeMultiShapes(MultiShape2D multi){
        int count = 0;
        for (GeometricShape2D shape : this.shapes) {
            if (shape instanceof MultiShape2D) {
                count++;
            }
        }
        if (count != 0){
            Square square = new Square((float)Math.sqrt((double)multi.calculateArea()));
            for (int i = 0; i < multi.getShapes().length; i++) {
                multi.getShapes()[i] = null;
            }
            
            multi.getShapes()[0] = square;
        }
    }

    public MultiShape2D getMultiShape(){
        Scanner sc = new Scanner(System.in);
        this.printShapes();
        System.out.print("Enter the index of the shape you wish to merge: ");
        int index = sc.nextInt();
        MultiShape2D multiShape = (MultiShape2D) shapes[index];
        multiShape.printInfo();
        return multiShape;
    }

    public void editShape(){
        Scanner sc = new Scanner(System.in);
        this.printShapes();
        System.out.print("Enter the index of the shape you want to edit: ");
        int index = sc.nextInt();
        while (shapes[index] instanceof MultiShape2D){
            System.out.println("Choose another shape!");
            System.out.print("Enter the index of the shape you want to edit: ");
            index = sc.nextInt();
        }
        if(shapes[index] instanceof Square){
            System.out.println("Edit side length");
            int length = sc.nextInt();
            Square square = (Square) shapes[index];
            square.setSide(length);
        }
        else if(shapes[index] instanceof Circle){
            System.out.println("Edit radius");
            int radius = sc.nextInt();
            Circle circle = (Circle) shapes[index];
            circle.setRadius(radius);
        }
        else if(shapes[index] instanceof Rectangle){
            System.out.println("Edit length");
            int length = sc.nextInt();
            System.out.println("Edit width");
            int width = sc.nextInt();
            Rectangle rectangle = (Rectangle) shapes[index];
            rectangle.setHeight(length);
            rectangle.setWidth(width);
        }
        else if(shapes[index] instanceof EquilateralTriangle){
            System.out.println("Edit side length");
            int side = sc.nextInt();
            EquilateralTriangle triangle = (EquilateralTriangle) shapes[index];
            triangle.setSide(side);
        }
        else if(shapes[index] instanceof Ellipse){
            System.out.println("Edit major axis");
            int majorAxis = sc.nextInt();
            System.out.println("Edit minor axis");
            int minorAxis = sc.nextInt();
            Ellipse ellipse = (Ellipse) shapes[index];
            ellipse.setSemiMajorAxis(majorAxis);
            ellipse.setSemiMinorAxis(minorAxis);
        }
        else if(shapes[index] instanceof Sphere){
            System.out.println("Edit radius");
            int radius = sc.nextInt();
            Sphere sphere = (Sphere) shapes[index];
            sphere.setRadius(radius);
        }
        else if(shapes[index] instanceof Cube){
            System.out.println("Edit side length");
            int side = sc.nextInt();
            Cube cube = (Cube) shapes[index];
            cube.setSide(side);
        }
        else if(shapes[index] instanceof Cylinder){
            System.out.println("Edit radius");
            int radius = sc.nextInt();
            System.out.println("Edit height");
            int height = sc.nextInt();
            Cylinder cylinder = (Cylinder) shapes[index];
            cylinder.setRadius(radius);
            cylinder.setHeight(height);
        }
        else if(shapes[index] instanceof Pyramid){
            System.out.println("Edit base");
            int base = sc.nextInt();
            System.out.println("Edit height");
            int height = sc.nextInt();
            Pyramid pyramid = (Pyramid) shapes[index];
            pyramid.setBaseArea(base);
            pyramid.setHeight(height);
        }
        else if(shapes[index] instanceof Cuboid){
            System.out.println("Edit length");
            int length = sc.nextInt();
            System.out.println("Edit width");
            int width = sc.nextInt();
            System.out.println("Edit height");
            int height = sc.nextInt();
            Cuboid cuboid = (Cuboid) shapes[index];
            cuboid.setLength(length);
            cuboid.setWidth(width);
            cuboid.setHeight(height);
        }
        else{
            System.out.println("Invalid choice!");
        }
    }
    public void choiceMenu(){
        System.out.println("1- Create Shape");
        System.out.println("2- Add to MultiShape");
        System.out.println("3- Print Shapes");
        System.out.println("4- Merge MultiShapes");
        System.out.println("5- Edit Shape");
        System.out.println("Exit");
    }

    public static void main(String[] args) {
        boolean exit = false;
        Main main = new Main(5);
        Scanner sc = new Scanner(System.in);
        main.choiceMenu();
        System.out.println("Enter your choice: ");
        int choice = sc.nextInt();
        while (!exit){
            switch (choice) {
                case 1:
                    main.createShape();
                    break;
                case 2:// add to mutlti shape
                    main.printShapes();
                    System.out.println("Select multishape: ");
                    int multiindex = sc.nextInt();
                    MultiShape2D multishape = (MultiShape2D)main.shapes[multiindex]; 
                    System.out.println("Enter the index of the shape you want to add to multiShape: ");
                    int ind = sc.nextInt();
                    GeometricShape2D newShape = main.shapes[ind];
                    multishape.addShape(newShape);
                    main.shapes[ind] = null;
                    break;
                case 3:
                    main.printShapes();
                    break;
                case 4:
                    MultiShape2D multi = main.getMultiShape();
                    main.mergeMultiShapes(multi);
                    break;
                case 5:
                    main.editShape();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    throw new AssertionError();
            }
            main.choiceMenu();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
        }
    }
}
