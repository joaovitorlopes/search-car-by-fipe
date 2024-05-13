package joaovitorlopes.com.github.searchcarbyfipe.main;

import joaovitorlopes.com.github.searchcarbyfipe.service.ConsumeAPI;

import java.util.Scanner;

public class Main {
    private Scanner reading = new Scanner(System.in);
    private ConsumeAPI consume = new ConsumeAPI();

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void showMenu() {
        var menu = """
                *** OPTIONS ***
                Car
                Motorcycle
                Truck
                
                Enter a option to search:
                """;

        System.out.println(menu);
        var option = reading.nextLine();

        String address;

        if (option.toLowerCase().contains("car")) {
            address = URL_BASE + "carros/marcas";
        } else if (option.toLowerCase().contains("mot")) {
            address = URL_BASE + "motos/marcas";
        } else {
            address = URL_BASE + "caminhoes/marcas";
        }

        var json = consume.getData(address);
        System.out.println(json);

    }
}
