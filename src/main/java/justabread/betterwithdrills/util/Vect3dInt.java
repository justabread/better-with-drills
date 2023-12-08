package justabread.betterwithdrills.util;

public class Vect3dInt {
    private int x;
    private int y;
    private int z;

    public Vect3dInt(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public int hashCode() {
        return x+y+z;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vect3dInt other = (Vect3dInt) obj;
        return (x == other.x) && (y == other.y) && (z == other.z);
    }
}
