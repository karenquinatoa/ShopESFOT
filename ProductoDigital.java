package ShopESFOT;

public class ProductoDigital extends Producto {

    private int tamanioMG;

    public ProductoDigital(String codigo, String nombre, double precio, int tamanioMG) {
        super(codigo, nombre, precio);
        this.tamanioMG = tamanioMG;
    }

    @Override
    public double calcularPrecioFinal() {
        return super.calcularPrecioFinal();
    }

    @Override
    public ProductoDigital crearVariante(String nuevoCodigo, double nuevoPrecio) {
        return new ProductoDigital(nuevoCodigo, getNombre(), nuevoPrecio, tamanioMG);
    }

    @Override
    public String toString() {
        return String.format("[Digital] %s, %d MB, Precio final: $%.2f",
                super.toString(), tamanioMG, calcularPrecioFinal());
    }
}