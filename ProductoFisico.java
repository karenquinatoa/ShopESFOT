package ShopESFOT;

public class ProductoFisico extends Producto {

    private double pesoKG;

    public ProductoFisico(String codigo, String nombre, double precio, double pesoKG) {
        super(codigo, nombre, precio);
        this.pesoKG = pesoKG;
    }

    public ProductoFisico(String codigo, String nombre, double precio) {
        this(codigo, nombre, precio, 1.0);
    }

    public double getPesoKG() {
        return pesoKG;
    }

    @Override
    public double calcularPrecioFinal() {
        return super.calcularPrecioFinal() + 2.50 + 0.80 * pesoKG;
    }

    @Override
    public ProductoFisico crearVariante(String nuevoCodigo, double nuevoPrecio) {
        return new ProductoFisico(nuevoCodigo, getNombre(), nuevoPrecio, pesoKG);
    }

    @Override
    public String toString() {
        return String.format("[Físico] %s, %.1f kg, Precio final: $%.2f",
                super.toString(), pesoKG, calcularPrecioFinal());
    }
}