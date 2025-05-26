import java.util.*;
public class MultiShape2D extends GeometricShape2D {
    GeometricShape2D[] shapes;
    int size;   
    Scanner sc = new Scanner(System.in);
    public MultiShape2D(int size) {
        this.size = size;
        shapes = new GeometricShape2D[size];
    }
    public void addShape(GeometricShape2D shape, int index) {
        shapes[index] = shape;
    }
    @Override   
    public float calculateArea() {
        float totalArea = 0;
        for (GeometricShape2D shape : this.shapes) {
            if (shape != null){
                totalArea += shape.calculateArea();
            }
        }
        return totalArea;
    }
    @Override
    public void printInfo() {
        if (checkNull()){
            System.out.print("MultiShape: ");
            return;
        }
        if (shapes.length == 1) {
            shapes[0].printInfo();
            return;
        }
        GeometricShape2D[] twoDimensional = new GeometricShape2D[gettwoDimensionals()];
        GeometricShape3D[] threeDimensional = new GeometricShape3D[getthreeDimensionals()];
        int twoDCount = 0;
        int threeDCount = 0;
        for (int i = 0; i < size; i++) {
            if (shapes[i] != null){
                if(shapes[i].isTwoDimensional()){ 
                    twoDimensional[twoDCount] = shapes[i];
                    twoDCount++;
                }
                else{
                    if (shapes[i] instanceof GeometricShape3D) {
                        threeDimensional[threeDCount] = (GeometricShape3D) shapes[i];
                    }
                    threeDCount++;
                }
            }
            
            
        }
        int shapeCount = 0;
        System.out.println("2D Shapes:");
        for (int j = 0; j < twoDimensional.length; j++) {
            System.out.print("[" + shapeCount + "]");
            twoDimensional[j].printInfo();
            shapeCount++;
        }
        System.out.println("3D Shapes:");
        for (int j = 0; j < threeDimensional.length; j++) {
            System.out.print("[" + shapeCount + "]");
            threeDimensional[j].printInfo();
            shapeCount++;
        }
        
    }
    public int gettwoDimensionals(){
        int count = 0;
        for (int i = 0; i < size; i++) {
            if(!(shapes[i] instanceof GeometricShape3D) && shapes[i] != null){
                count++;
            }
        }
        return count;
    }

    public int getthreeDimensionals(){
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (shapes[i] != null && !shapes[i].isTwoDimensional()){
                count++;
            }
        }
        return count;
    }

    public void addShape( GeometricShape2D addedShape) {
        for (int i = 0; i < size; i++) {
            if(this.shapes[i] == null){
                shapes [i] = addedShape;
                break;
            }
        }
    }

    public void mergeShapes(){
        float totalArea = calculateArea();
        int length = (int) Math.round(Math.sqrt(totalArea));
        Square square = new Square(length);
        GeometricShape2D[] merged = new GeometricShape2D[1];
        merged[0] = square;
        this.shapes = merged;
    }
    
    public void printShapes(){
        int count = 0;
        for (GeometricShape2D shape : shapes) {
            System.out.print("[" + count + "]");
            shape.printInfo();
            count++;
        }
    }

    public GeometricShape2D[] getShapes() {
        return shapes;
    }

    @Override
    public GeometricShape2D createShape(){
        System.out.print("Enter the number of shapes");
        int size = sc.nextInt();
        MultiShape2D multiShape = new MultiShape2D(size);
        multiShape.printInfo();
        return multiShape;
    }

    public boolean checkNull(){
        int length = shapes.length;
        int count = 0;
        for (int i = 0; i < shapes.length ; i++){
            if (shapes [i] == null){
                count++;
            }
        }
        if(count == length){
            return true;
        }
        return false;
    }
}