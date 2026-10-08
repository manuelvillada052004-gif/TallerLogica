package Taller;

import java.util.Scanner;

public class TallerDeLogica {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] consumos = new double[6];
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarConsumo(consumos);
                    break;
                case 2:
                    consultarFactura(consumos);
                    break;
                case 3:
                    generarReporte(consumos);
                    break;
                case 4:
                    System.out.println("Cerrando");
                    break;
                default:
                    System.out.println("Opción erronea");
                    break;
            }
        } while (opcion != 4);

        scanner.close();
    }

    public static void mostrarMenu() {
        System.out.println("\n - CONTROL DE AGUA ");
        System.out.println("1. Registrar consumo de un apartamento");
        System.out.println("2. Consultar factura de un apartamento");
        System.out.println("3. Ver reporte general del conjunto");
        System.out.println("4. Salir");
        System.out.print("Elija una opción: ");
    }

    public static void registrarConsumo(double[] consumos) {
        Scanner scanner = new Scanner(System.in);
        int apto;
        double consumo;

        System.out.print("Ingrese el número de apartamento (1 a 6): ");
        apto = scanner.nextInt();
        while (apto < 1 || apto > 6) {
            System.out.print("¡Inválido! Ingrese un apartamento entre 1 y 6: ");
            apto = scanner.nextInt();
        }

        System.out.print("Ingrese el consumo en m3: ");
        consumo = scanner.nextDouble();
        while (consumo <= 0) {
            System.out.print("¡Inválido! El consumo debe ser mayor a 0: ");
            consumo = scanner.nextDouble();
        }

        consumos[apto - 1] = consumo;
        System.out.println("¡Consumo registrado con éxito para el apartamento " + apto + "!");
    }

    public static void consultarFactura(double[] consumos) {
        Scanner scanner = new Scanner(System.in);
        int apto;

        System.out.print("Ingrese el número de apartamento a consultar (1 a 6): ");
        apto = scanner.nextInt();
        while (apto < 1 || apto > 6) {
            System.out.print("¡Inválido! Ingrese un apartamento entre 1 y 6: ");
            apto = scanner.nextInt();
        }

        if (consumos[apto - 1] == 0) {
            System.out.println("El apartamento " + apto + " no tiene consumo registrado.");
        } else {
            imprimirFactura(apto, consumos[apto - 1]);
        }
    }

    public static void imprimirFactura(int apartamento, double consumo) {
        double cargoFijo = 12000;
        double valorM3 = 0;
        double valorConsumo = 0;
        String tarifaAplicada = "";

        if (consumo <= 10) {
            tarifaAplicada = "$3.000 por m³";
            valorM3 = 3000;
        } else if (consumo <= 20) {
            tarifaAplicada = "$4.500 por m³";
            valorM3 = 4500;
        } else {
            tarifaAplicada = "$6.000 por m³";
            valorM3 = 6000;
        }

        valorConsumo = consumo * valorM3;
        double subtotal = cargoFijo + valorConsumo;
        double recargo = 0;

        if (consumo > 25) {
            recargo = subtotal * 0.10;
        }

        double totalPagar = subtotal + recargo;

        System.out.println(" FACTURA APARTAMENTO " + apartamento + " ---");
        System.out.println("Consumo: " + consumo + " m³");
        System.out.println("Tarifa aplicada: " + tarifaAplicada);
        System.out.println("Valor del consumo: $" + valorConsumo);
        System.out.println("Cargo fijo: $" + cargoFijo);
        if (recargo > 0) {
            System.out.println("Recargo (10%): $" + recargo);
        }
        System.out.println("Total a pagar: $" + totalPagar);

        if (consumo > 25) {
            System.out.println("ALERTA: consumo excesivo");
        }
        System.out.println("----------------------------------------");
    }

    public static void generarReporte(double[] consumos) {
        double consumoTotal = 0;
        int registrados = 0;
        int sinRegistro = 0;
        int excesivos = 0;
        double mayorConsumo = -1;
        int aptoMayor = 0;

        for (int i = 0; i < consumos.length; i++) {
            if (consumos[i] > 0) {
                registrados++;
                consumoTotal += consumos[i];

                if (consumos[i] > mayorConsumo) {
                    mayorConsumo = consumos[i];
                    aptoMayor = i + 1;
                }

            }
        }
    }
}