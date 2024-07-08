import java.io.IOException;
import java.util.Scanner;

public class PincipalConversion {

    public static void main(String[] args) throws IOException, InterruptedException {
        int elegirOpcion;

        TipoDeCambio consultas = new TipoDeCambio();
        TipoDeCambio miTipoDeCambio = new TipoDeCambio(consultas.consultar());


        String menu = """
                ************************************************
                Bienvenido al Conversor de Moneda:
                1- Convertir ARS a USD  = Peso Argentino  => Dolar
                2- Convertir USD a ARS  = Dolar  => Peso Argentino
                3- Convertir ARS a BRL  = Peso Argentino  => Real Brasileño
                4- Convertir BRL a ARS  = Real Brasileño  => Peso Argentino
                5- Convertir USD a BRL  = Dolar  => Real Brasileño
                6- Convertir BRL a USD  = Real Brasileño  => Dolar
                7- Elige tu propia moneda
                9- Salir
                ************************************************
                """;

        Scanner entrada = new Scanner(System.in);
        System.out.println(menu);
        System.out.println("Digite la opcion que desea convertir");

        try {
            elegirOpcion = Integer.valueOf(entrada.nextLine());
        }catch(NumberFormatException e){
            System.out.println("Solo estan permitidos numeros");
            System.out.println("Intentar  nuevamente \n" + menu);
            elegirOpcion = Integer.valueOf(entrada.nextLine());
        }
        try{
            while(elegirOpcion != 9){

                double cantidad;
                if (elegirOpcion == 1){             // De moneda [ARS] a moneda [USD]
                    System.out.println("Digite la cantidad que desea convertir de [ARS] a [USD]: ");

                    try{
                        cantidad = Double.valueOf(entrada.nextLine());
                    }catch (NumberFormatException e ){
                        System.out.println("Solo se permiten numeros,intentelo nuevamente");
                        System.out.println("Digite la cantidad que desea convertir de [ARS] a [USD]: ");
                        cantidad = Double.valueOf(entrada.nextLine());
                    }
                    System.out.println("El valor de : " + cantidad + " [ARS] " + "Corresponde al valor final de : " + miTipoDeCambio.exchangeBack(cantidad,"ARS") + " [USD] " );
                    System.out.println(menu);
                    elegirOpcion = Integer.valueOf(entrada.nextLine());

                }else if (elegirOpcion == 2){       // De moneda [USD] a moneda [ARS]

                    System.out.println("Digite la cantidad que desea convertir de [USD] a [ARS]: ");
                    cantidad = Double.valueOf(entrada.nextLine());
                    System.out.println("El valor de : " + cantidad + "[USD]" + "Corresponde al valor final de : " + miTipoDeCambio.exchangeTarget(cantidad, "ARS") + "[ARS]");
                    System.out.println(menu);
                    elegirOpcion = Integer.valueOf(entrada.nextLine());

                } else if (elegirOpcion == 3) {     // De moneda [ARS] a  moneda [BRL]

                System.out.println("Digite la cantidad que desea convertir de [ARS] a [BRL]: ");
                cantidad = Double.valueOf(entrada.nextLine());
                System.out.println("El valor de: " + cantidad + "[ARS]" + " Corresponde al valor final de : " +miTipoDeCambio.exchangeOverflow(cantidad, "ARS", "BRL") + "[BRL]");
                System.out.println(menu);
                elegirOpcion = Integer.valueOf(entrada.nextLine());

            } else if (elegirOpcion == 4) {         // De moneda [BRL] a moneda [ARS]

                System.out.println("Digite la cantidad que desea convertir de BRL a ARS: ");
                cantidad = Double.valueOf(entrada.nextLine());
                System.out.println("El valor de: " + cantidad + "[BRL]" + " Corresponde al valor final de : " + miTipoDeCambio.exchangeOverflow(cantidad, "BRL", "ARS") + "[ARS]");
                System.out.println(menu);
                elegirOpcion = Integer.valueOf(entrada.nextLine());

            } else if (elegirOpcion == 5) {         // De moneda [USD] a  moneda [BRL]

                System.out.println("Digite la cantidad que desea convertir de USD a BRL: ");
                cantidad = Double.valueOf(entrada.nextLine());
                System.out.println("El valor de: " + cantidad + "[USD]" + " Corresponde al valor final de : " +miTipoDeCambio.exchangeTarget(cantidad, "BRL") + "[BRL]");
                System.out.println(menu);
                elegirOpcion = Integer.valueOf(entrada.nextLine());

            } else if (elegirOpcion == 6) {         // De moneda [BRL] a  moneda [USD]

                System.out.println("Digite la cantidad que desea convertir de BRL a USD: ");
                cantidad = Double.valueOf(entrada.nextLine());
                System.out.println("El valor de: " + cantidad + "[BRL]" + " Corresponde al valor final de : " +miTipoDeCambio.exchangeBack(cantidad, "BRL") + "[USD]");
                System.out.println(menu);
                elegirOpcion = Integer.valueOf(entrada.nextLine());

            }else if (elegirOpcion == 7){

                    String monedaBase;
                    String monedaDestino;

                    System.out.println("Digite la Denominacion de la moneda base: ");
                    monedaBase = entrada.nextLine();
                    System.out.println("Digite la Denominacion de la moneda destino: ");
                    monedaDestino = entrada.nextLine();
                    System.out.println("Digite la cantidad que desea convertir: ");
                    cantidad = Double.valueOf(entrada.nextLine());

                    try{
                        System.out.println(miTipoDeCambio.exchangeOverflow(cantidad, monedaBase, monedaDestino));
                    }catch (NullPointerException e ){

                        System.out.println("Las monedas se escriben en su currency code, o código de moneda, " +
                                "reintentelo otra vez");
                        System.out.println("Digite la Denominacion de la moneda base: ");
                        monedaBase = entrada.nextLine();
                        System.out.println("Digite la Denominacion de la moneda destino: ");
                        monedaDestino = entrada.nextLine();
                        System.out.println("Digite la cantidad que desea convertir desde la moneda base hacia la moneda destino: ");
                        cantidad = Double.valueOf(entrada.nextLine());
                        System.out.println(miTipoDeCambio.exchangeOverflow(cantidad, monedaBase, monedaDestino));
                    }
                    System.out.println(menu);
                    elegirOpcion = Integer.valueOf(entrada.nextLine());
                }

            }
            entrada.close();
        } catch (Exception e) {

            System.out.println("Error: " + e + "\n");
            System.out.println("Ocurrio un error, reinicie la aplicación");
        }
        System.out.println("Gracias por hacer uso del Conversor de moneda");
    }
}









