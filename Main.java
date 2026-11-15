package ShopESFOT;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Tienda tienda = new Tienda();

        while (true) {
            System.out.println("\n--- MENÚ SHOPESFOT ---");
            System.out.println("1. Registrar producto físico");
            System.out.println("2. Registrar producto digital");
            System.out.println("3. Crear variante de producto");
            System.out.println("4. Mostrar catálogo");
            System.out.println("5. Agregar al carrito");
            System.out.println("6. Pagar");
            System.out.println("7. Salir");
            System.out.print("Ingrese opción: ");
            int opcion = Integer.parseInt(sc.nextLine());

            if (opcion == 1) {
                System.out.print("Código: ");
                String codigo = sc.nextLine();
                if (tienda.buscar(codigo) != null) {
                    System.out.println("El código " + codigo + " ya existe.");
                    continue;
                }
                System.out.print("Nombre: ");
                String nombre = sc.nextLine();

                System.out.print("Precio: ");
                double precio = Double.parseDouble(sc.nextLine());
                if (precio < 0) {
                    System.out.println("El precio no puede ser negativo.");
                    continue;
                }

                System.out.print("¿Desea indicar el peso? (s/n): ");
                String respuesta = sc.nextLine();
                if (respuesta.equalsIgnoreCase("s")) {
                    System.out.print("Peso (kg): ");
                    double peso = Double.parseDouble(sc.nextLine());
                    if (peso < 0) {
                        System.out.println("El peso no puede ser negativo.");
                        continue;
                    }
                    tienda.registrar(new ProductoFisico(codigo, nombre, precio, peso));
                } else {
                    tienda.registrar(new ProductoFisico(codigo, nombre, precio));
                }
                System.out.println("Producto físico registrado exitosamente.");

            } else if (opcion == 2) {
                System.out.print("Código: ");
                String codigo = sc.nextLine();
                if (tienda.buscar(codigo) != null) {
                    System.out.println("El código " + codigo + " ya existe.");
                    continue;
                }
                System.out.print("Nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Precio: ");
                double precio = Double.parseDouble(sc.nextLine());
                if (precio < 0) {
                    System.out.println("El precio no puede ser negativo.");
                    continue;
                }
                System.out.print("Tamaño (MB): ");
                int tamano = Integer.parseInt(sc.nextLine());
                if (tamano < 0) {
                    System.out.println("El tamaño no puede ser negativo.");
                    continue;
                }

                tienda.registrar(new ProductoDigital(codigo, nombre, precio, tamano));
                System.out.println("Producto digital registrado exitosamente.");

            } else if (opcion == 3) {
                System.out.print("Código del producto original: ");
                String original = sc.nextLine();
                Producto base = tienda.buscar(original);
                if (base == null) {
                    System.out.println("El código " + original + " no existe.");
                    continue;
                }
                System.out.print("Nuevo código: ");
                String nuevoCodigo = sc.nextLine();
                if (tienda.buscar(nuevoCodigo) != null) {
                    System.out.println("El código " + nuevoCodigo + " ya existe.");
                    continue;
                }
                System.out.print("Nuevo precio: ");
                double nuevoPrecio = Double.parseDouble(sc.nextLine());
                if (nuevoPrecio < 0) {
                    System.out.println("El precio no puede ser negativo.");
                    continue;
                }

                Producto variante = base.crearVariante(nuevoCodigo, nuevoPrecio);
                tienda.registrar(variante);
                System.out.println("Variante " + nuevoCodigo + " creada a partir de " + original + ".");

            } else if (opcion == 4) {
                if (tienda.catalogoVacio()) {
                    System.out.println("No hay productos registrados.");
                    continue;
                }
                for (Producto p : tienda.getCatalogo()) {
                    System.out.println(p);
                }

            } else if (opcion == 5) {
                if (tienda.catalogoVacio()) {
                    System.out.println("No hay productos registrados.");
                    continue;
                }

                System.out.print("Código: ");
                String codigo = sc.nextLine();
                Producto p = tienda.buscar(codigo);
                if (p == null) {
                    System.out.println("El código no existe.");
                    continue;
                }

                System.out.print("¿Cuántas unidades? (Enter para 1): ");
                String texto = sc.nextLine();

                if (texto.isEmpty()) {
                    tienda.agregarAlCarrito(codigo);
                    System.out.println("Se agregó 1 unidad de " + p.getNombre() + ".");
                } else {
                    int cantidad = Integer.parseInt(texto);
                    if (cantidad <= 0) {
                        System.out.println("La cantidad debe ser mayor que 0.");
                        continue;
                    }
                    tienda.agregarAlCarrito(codigo, cantidad);

                    if (cantidad == 1) {
                        System.out.println("Se agregó 1 unidad de " + p.getNombre() + ".");
                    } else {
                        System.out.println("Se agregaron " + cantidad + " unidades de " + p.getNombre() + ".");
                    }
                }

            } else if (opcion == 6) {
                if (tienda.carritoVacio()) {
                    System.out.println("El carrito está vacío.");
                    continue;
                }

                System.out.printf("Total: $%.2f%n", tienda.totalCarrito());
                System.out.print("¿Tiene cupón? (s/n): ");
                String tieneCupon = sc.nextLine();

                if (tieneCupon.equalsIgnoreCase("s")) {
                    System.out.print("Cupón: ");
                    String cuponValido = sc.nextLine();

                    if (!tienda.cuponValido(cuponValido)) {
                        System.out.println("El cupón no es válido.");
                        continue;
                    }
                    if (tienda.cuponUsado(cuponValido)) {
                        System.out.println("El cupón " + cuponValido + " ya fue utilizado.");
                        continue;
                    }
                    System.out.println("Descuento aplicado: 10%");
                    System.out.printf("Total a pagar: $%.2f%n", tienda.pagar(cuponValido));
                    System.out.println("Gracias por su compra. El carrito quedó vacío.");

                } else {
                    System.out.printf("Total a pagar: $%.2f%n", tienda.pagar());
                    System.out.println("Gracias por su compra. El carrito quedó vacío.");
                }

            } else if (opcion == 7) {
                System.out.println("Hasta luego.");
                break;

            } else {
                System.out.println("Opción no válida.");
            }
        }
    }
}