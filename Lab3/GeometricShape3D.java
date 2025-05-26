abstract  class GeometricShape3D extends GeometricShape2D {
    public abstract float calculateVolume();
    protected boolean isTwoDimensional = false;
    public boolean isTwoDimensional() {
        return false;
    }
}
