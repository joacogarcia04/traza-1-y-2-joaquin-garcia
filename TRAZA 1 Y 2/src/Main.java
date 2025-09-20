import Entidades.*;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {

        Empresa pepsi = Empresa.builder()
                .id(10L)
                .nombre("PepsiCo International")
                .razonSocial("PepsiCo S.R.L.")
                .cuil(30999999992L)
                .build();

        Provincia cordoba = Provincia.builder()
                .id(10L)
                .nombre("Córdoba")
                .build();

        Localidad capital = Localidad.builder()
                .id(10L)
                .nombre("Córdoba Capital")
                .provincia(cordoba)
                .build();

        Localidad villaMaria = Localidad.builder()
                .id(11L)
                .nombre("Villa María")
                .provincia(cordoba)
                .build();

        Domicilio domCentro = Domicilio.builder()
                .calle("Bv. San Juan")
                .numero(456)
                .localidad(capital)
                .build();

        Domicilio domInterior = Domicilio.builder()
                .calle("Ruta 158 Km 12")
                .numero(5)
                .localidad(villaMaria)
                .build();

        Sucursal sucursalCapital = Sucursal.builder()
                .id(10L)
                .nombre("Sucursal Capital")
                .esCasaMatriz(true)
                .horarioApertura(LocalTime.of(7, 30))
                .horarioCierre(LocalTime.of(19, 30))
                .domicilio(domCentro)
                .build();

        Sucursal sucursalInterior = Sucursal.builder()
                .id(11L)
                .nombre("Sucursal Interior")
                .esCasaMatriz(false)
                .horarioApertura(LocalTime.of(9, 0))
                .horarioCierre(LocalTime.of(17, 0))
                .domicilio(domInterior)
                .build();

        pepsi.getSucursales().add(sucursalCapital);
        pepsi.getSucursales().add(sucursalInterior);

        ArticuloInsumo jarabe = ArticuloInsumo.builder()
                .id(10L)
                .denominacion("Jarabe Base 5L")
                .precioCompra(800.0)
                .precioVenta(1600.0)
                .stockActual(80)
                .stockMaximo(400)
                .esParaElaborar(true)
                .build();

        ArticuloInsumo lata = ArticuloInsumo.builder()
                .id(11L)
                .denominacion("Lata 350ml")
                .precioCompra(450.0)
                .precioVenta(950.0)
                .stockActual(500)
                .stockMaximo(1500)
                .esParaElaborar(false)
                .build();

        ArticuloManufacturado pepsiLata = ArticuloManufacturado.builder()
                .id(12L)
                .denominacion("Pepsi 350ml")
                .precioVenta(2500.0)
                .descripcion("Gaseosa cola en lata 350ml")
                .tiempoEstimadoMinutos(3)
                .preparacion("Llenado y sellado de lata")
                .build();

        sucursalCapital.agregarArticulo(jarabe);
        sucursalCapital.agregarArticulo(lata);

        sucursalInterior.agregarArticulo(pepsiLata);

        System.out.println("===== Empresa: " + pepsi.getNombre() + " =====");
        pepsi.getSucursales().forEach(s -> {
            System.out.println("\nSucursal: " + s.getNombre()
                    + (s.isEsCasaMatriz() ? " (Casa Matriz)" : ""));
            System.out.println("Dirección: " + s.getDomicilio().getCalle() + " "
                    + s.getDomicilio().getNumero() + ", "
                    + s.getDomicilio().getLocalidad().getNombre() + ", "
                    + s.getDomicilio().getLocalidad().getProvincia().getNombre());
            System.out.println("Horario: " + s.getHorarioApertura() + " a " + s.getHorarioCierre());
            System.out.println("Artículos que ofrece:");
            s.getArticulos().forEach(a ->
                    System.out.println(" - " + a.getDenominacion() + " ($" + a.getPrecioVenta() + ")")
            );
            System.out.println("----------------------------");
        });
    }
}
