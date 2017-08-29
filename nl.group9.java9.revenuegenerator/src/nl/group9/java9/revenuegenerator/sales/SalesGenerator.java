package nl.group9.java9.revenuegenerator.sales;

import nl.group9.java9.service.api.domain.Sale;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;

class SalesGenerator {

    private List<String> salesMen = List.of("Bas", "Rob", "Liesbeth");
    private List<String> consultants = List.of("Albert", "Alex", "Danny", "David", "Dennis",
            "Eric", "Erik", "Frans", "Henk", "Irmin", "Ivan", "Ivo", "Jelle", "Jeroen", "Joop", "Jurjen", "Marcus",
            "Mark", "Martijn A.", "Martijn v D.", "Morten", "Pascal", "Rinke", "Roel", "Ron v H.", "Ron S.", "Sjors",
            "Talip", "Tiese", "Tom", "Rick");
    private List<String> customers = List.of("Alphabet", "ASML", "Nasdaq", "Tennet",
            "APG", "Monuta", "Kadaster");
    private Random random = new Random();

    Sale generateSale() {
        return new Sale(
                this.generateSalesMan(),
                this.generateConsultant(),
                this.generateCustomer(),
                this.generateRevenue(),
                LocalTime.now()
        );
    }

    private String generateSalesMan() {
        return this.salesMen.get(random.nextInt(salesMen.size()));
    }

    private String generateConsultant() {
        return this.consultants.get(random.nextInt(consultants.size()));
    }

    private String generateCustomer() {
        return this.customers.get(random.nextInt(customers.size()));
    }

    private double generateRevenue() {
        double revenue = random.nextDouble() * 1000;
        revenue = Math.round(revenue);
        return revenue / 100;
    }

}
