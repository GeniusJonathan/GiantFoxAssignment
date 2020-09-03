package com.giantfox.gemeenteAPI;

import com.giantfox.gemeenteAPI.dao.FakeGemeenteDAO;
import com.giantfox.gemeenteAPI.model.Gemeente;
import com.giantfox.gemeenteAPI.model.Provincie;
import com.giantfox.gemeenteAPI.repository.GemeenteRepository;
import com.giantfox.gemeenteAPI.repository.ProvincieRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
public class GemeenteApiApplication implements CommandLineRunner {

    @Autowired
    private GemeenteRepository gemeenteRepository;

    @Autowired
    private FakeGemeenteDAO fakeGemeenteDAO;

    @Autowired
    private ProvincieRepository provincieRepository;

    public static void main(String[] args) {
        SpringApplication.run(GemeenteApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        cleanDatabase();
        fillProvinciesInMemory();
        fillGemeentenInMemory();
//        fillProvincies();
//        fillGemeenten();
    }

    private List<Provincie> fillProvinciesInMemory() {
        List<Provincie> provincies = new ArrayList<>();

        File file = new File(GemeenteApiApplication.class.getClassLoader().getResource("data/provincies.csv").getFile());
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            line = br.readLine();
            String[] header = line.split(cvsSplitBy);

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] provincie = line.split(cvsSplitBy);
                provincies.add(new Provincie(provincie[0], provincie[1], Long.parseLong(provincie[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return provincies;
    }

    private List<Gemeente> fillGemeentenInMemory() {
        List<Provincie> provincies = fillProvinciesInMemory();
        List<Gemeente> gemeenten = new ArrayList<>();

        AtomicLong nextId = new AtomicLong();

        JSONParser parser = new JSONParser();
        File file = new File(GemeenteApiApplication.class.getClassLoader().getResource("data/gemeenten.json").getFile());

        try (Reader reader = new FileReader(file)) {

            JSONArray arr = (JSONArray) parser.parse(reader);
            Iterator<JSONObject> iterator = arr.iterator();
            while (iterator.hasNext()) {
                JSONObject obj = iterator.next();
                String naam = (String) obj.get("gemeente");
                String prov = (String) obj.get("provincie");
                Provincie provincie = provincies.stream().filter(x -> x.getNaam().equalsIgnoreCase(prov)).findFirst().orElse(null);
                long inwonders = (Long) obj.get("inwoners");

                Gemeente gemeente = new Gemeente(naam, provincie, inwonders);
                gemeente.setId(nextId.getAndIncrement());

                fakeGemeenteDAO.insertGemeente(gemeente);
                gemeenten.add(gemeente);


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return gemeenten;
    }

    private void cleanDatabase() {
        gemeenteRepository.deleteAllInBatch();
        provincieRepository.deleteAllInBatch();
    }

    private void fillGemeenten() {


        List<Gemeente> gemeenteList = fillGemeentenInMemory();
        List<Provincie> provincieList = fillProvinciesInMemory();

        for (Gemeente gemeente : gemeenteList) {
            Provincie provincie = provincieList.stream().filter(x -> x.getNaam().equalsIgnoreCase(gemeente.getProvincie().getNaam())).findFirst().orElse(null);
            gemeenteRepository.save(new Gemeente(gemeente.getNaam(), provincie, gemeente.getInwoners()));
        }


    }

    private void fillProvincies() {
        List<Provincie> provincies = fillProvinciesInMemory();

        provincies.stream().forEach(x -> {
            provincieRepository.save(new Provincie(x.getNaam(), x.getHoofdstad(), x.getOppervlaktkm2()));
        });


    }

}
