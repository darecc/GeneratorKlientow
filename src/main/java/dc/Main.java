package dc;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Main {

    static int SIZE = 1000;
    static Random rnd;

    private static List<String> readNames(String fileName) throws IOException {
        List<String> lista = new ArrayList<>();
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null)
            lista.add(line);
        return lista;
    }

    private static String generatePesel() {
        String pesel = "";
        for (int i = 0; i < 11; i++) {
            pesel += rnd.nextInt(10);
        }
        return pesel;
    }

    static String[] osoby = new String[] {
            "Marcin Bosacki", "Michał Bajor", "Stefan Banach", "Hanna Banaszak", "Dariusz Ceglarek",
            "Michał Daszek", "Tomasz Iwan", "Monika Fedusio", "Michał Dworczyk", "Mariusz Fyrstenberg",
            "Paweł Janas", "Piotr Kaczkowski", "Lech Kaczyński", "Ryszard Kalisz", "Leszek Kołakowski",
            "Agnieszka Korneluk", "Paweł Kowal", "Włodzimierz Lubański", "Piotr Małachowski",
            "Robert Mateja", "Marcin Matkowski", "Mateusz Morawiecki", "Władysław Orlicz",
            "Paweł Piskorski", "Wojciech Pokora", "Grażyna Rabsztyn", "Maryla Rodowicz",
            "Kamil Semeniuk", "Anna Seniuk", "Irena Szewińska", "Ryszard Szurkowski",
            "Małgorzata Kożuchowska", "Paweł Żewłakow", "Stanisław Szozda",
            "Bartosz Kurek", "Bartosz Bednorz", "Kamil Stoch", "Adam Małysz", "Dawid Kubacki",
            "Natalia Maliszewska", "Katarzyna Piekarska", "Marian Rejewski",
            "Bronisław Komorowski", "Andrzej Duda", "Anna Dereszyńska", "Marcin Dorociński",
            "Otylia Jędrzejczak", "Szymon Kołecki", "Eryk Lipiński", "Adam Pawlikowski",
            "Mieczysław Pawlikowski", "Ewa Wiśniewska", "Zbigniew Boniek", "Sebastian Świderski",
            "Anita Włodarczyk", "Paweł Fajdek", "Piotr Żyła", "Zenon Jaskuła"
    };

    public static void main(String[] args) throws IOException {

        rnd = new Random();
        List<String> cities, streets, codes, firstNames, lastNames;
        List<Client> clients = new ArrayList<>();
        cities = readNames("cities.txt");
        codes = readNames("codes.txt");
        streets = readNames("streets.txt");
        firstNames = readNames("firstNames.txt");
        lastNames = readNames("lastNames.txt");
        for (int i = 0; i < SIZE; i++) {
            String firstName = firstNames.get(rnd.nextInt(firstNames.size()));
            String lastName = lastNames.get(rnd.nextInt(lastNames.size()));
            if (firstName.endsWith("a") && lastName.endsWith("i"))
                lastName = lastName.substring(0, lastName.length()-1) + "a";
            String code = codes.get(rnd.nextInt(codes.size()));
            String city = cities.get(rnd.nextInt(cities.size()));
            String pesel = generatePesel();
            String street = streets.get(rnd.nextInt(streets.size())) + " " + rnd.nextInt(111);
            Client client = new Client(firstName, lastName, pesel, new Address(city, code, street));
            client.setClientId(i + 1);
            clients.add(client);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String text = gson.toJson(clients);
        FileWriter fw = new FileWriter("clients.json");
        fw.write(text);
        fw.close();
        FileReader fr = new FileReader("clients.json");
        BufferedReader br = new BufferedReader(fr);
        text = "";
        String line;
        while ((line = br.readLine()) != null)
            text += line + "\n";

        Type listOfMyClassObject = new TypeToken<ArrayList<Client>>() {}.getType();

        clients = gson.fromJson(text, listOfMyClassObject);
        Client[] tablicaKlientow = gson.fromJson(text, Client[].class);
        for(Client c : clients) {
            String v = c.getFirstName() + " " + c.getLastName();
            for(String osoba : osoby)
            if (v.equals(osoba))
                System.out.println(c.toString());
        }
    }
}
