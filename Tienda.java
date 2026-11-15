package ShopESFOT;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class Tienda {

    private HashMap<String, Producto> catalogo = new HashMap<>();
    private ArrayList<Producto> carrito = new ArrayList<>();
    private HashSet<String> cuponesValidos = new HashSet<>();
    private HashSet<String> cuponesUsados = new HashSet<>();

    public Tienda() {
        cuponesValidos.add("ESFOT10");
        cuponesValidos.add("POO10");
    }

    public Producto registrar(Producto p) {
        return catalogo.putIfAbsent(p.getCodigo(), p);
    }

    public Producto buscar(String codigo) {
        return catalogo.get(codigo);
    }

    public Collection<Producto> getCatalogo() {
        return catalogo.values();
    }

    public boolean catalogoVacio() {
        return catalogo.isEmpty();
    }

    public boolean agregarAlCarrito(String codigo) {
        return agregarAlCarrito(codigo, 1);
    }

    public boolean agregarAlCarrito(String codigo, int cantidad) {
        Producto p = catalogo.get(codigo);
        if (p == null) {
            return false;
        }
        for (int i = 0; i < cantidad; i++) {
            carrito.add(p);
        }
        return true;
    }

    public boolean carritoVacio() {
        return carrito.isEmpty();
    }

    public double totalCarrito() {
        double total = 0;
        for (Producto p : carrito) {
            total += p.calcularPrecioFinal();
        }
        return total;
    }

    public boolean cuponValido(String cupon) {
        return cuponesValidos.contains(cupon);
    }

    public boolean cuponUsado(String cupon) {
        return cuponesUsados.contains(cupon);
    }

    public double pagar() {
        double total = totalCarrito();
        carrito.clear();
        return total;
    }

    public double pagar(String cupon) {
        double total = totalCarrito() * 0.90;
        cuponesUsados.add(cupon);
        carrito.clear();
        return total;
    }
}