abstract class GeometricShape2D {
    public abstract float calculateArea();
    public abstract void printInfo();
    protected boolean isTwoDimensional = true;
    public boolean isTwoDimensional() {
        return isTwoDimensional;
    }
    public abstract GeometricShape2D createShape();
}