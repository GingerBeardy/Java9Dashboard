package nl.group9.java9.service.datastorage;

import nl.group9.java9.api.domain.Sale;

import java.util.LinkedList;
import java.util.List;

public class DataStore {

    private List<Sale> sales = new LinkedList<>();


    public void saveSale(Sale sale) {
        sales.add(sale);
    }

    public List<Sale> getSales() {
        return sales;
    }
}
