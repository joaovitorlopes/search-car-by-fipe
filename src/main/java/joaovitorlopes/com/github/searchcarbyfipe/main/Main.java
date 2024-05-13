package joaovitorlopes.com.github.searchcarbyfipe.main;

import joaovitorlopes.com.github.searchcarbyfipe.model.Data;
import joaovitorlopes.com.github.searchcarbyfipe.model.Models;
import joaovitorlopes.com.github.searchcarbyfipe.model.Vehicle;
import joaovitorlopes.com.github.searchcarbyfipe.service.ConsumeAPI;
import joaovitorlopes.com.github.searchcarbyfipe.service.DataConversion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private Scanner reading = new Scanner(System.in);
    private ConsumeAPI consume = new ConsumeAPI();
    private DataConversion conversion = new DataConversion();

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
        var brands = conversion.getList(json, Data.class);
        brands.stream()
                .sorted(Comparator.comparing(Data::code))
                .forEach(System.out::println);

        System.out.println("Enter a brand code for search: ");
        var brandCode = reading.nextLine();

        address = address + "/" + brandCode + "/modelos";
        json = consume.getData(address);
        var listModel = conversion.getData(json, Models.class);

        System.out.println("\nModels of this brand:");
        listModel.models().stream()
                .sorted(Comparator.comparing(Data::code))
                .forEach(System.out::println);

        System.out.println("\nEnter part of the name of the vehicle to be searched:");
        var vehicleModel = reading.nextLine();

        List<Data> filteredModels = listModel.models().stream()
                .filter(m -> m.name().toLowerCase().contains(vehicleModel.toLowerCase()))
                        .collect(Collectors.toList());

        System.out.println("\n-----Filtered Models-----");
        filteredModels.forEach(System.out::println);

        System.out.println("Enter a model code to search the valuation values:");
        var modelCode = reading.nextLine();

        address = address + "/" + modelCode + "/anos";
        json = consume.getData(address);
        List<Data> years = conversion.getList(json, Data.class);
        List<Vehicle> vehicles = new ArrayList<>();

        for (int i = 0; i < years.size(); i++) {
            var yearsAddress = address + "/" + years.get(i).code();
            json = consume.getData(yearsAddress);
            Vehicle vehicle = conversion.getData(json, Vehicle.class);
            vehicles.add(vehicle);
        }

        System.out.println("\n-----All vehicles filtered with valuation by year-----");
        vehicles.forEach(System.out::println);

    }
}
