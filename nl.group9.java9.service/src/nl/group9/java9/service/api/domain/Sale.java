package nl.group9.java9.service.api.domain;

import java.time.LocalTime;

/**
 * Represents a single sale.
 */
public class Sale {

    private String salesMan;
    private String consultant;
    private String customer;
    private double revenue;
    private LocalTime timeOfSale;

    public Sale(String salesMan, String consultant, String customer, double revenue, LocalTime timeOfSale) {
        this.salesMan = salesMan;
        this.consultant = consultant;
        this.customer = customer;
        this.revenue = revenue;
        this.timeOfSale = timeOfSale;
    }

    public String getSalesMan() {
        return salesMan;
    }

    public void setSalesMan(String salesMan) {
        this.salesMan = salesMan;
    }

    public String getConsultant() {
        return consultant;
    }

    public void setConsultant(String consultant) {
        this.consultant = consultant;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public LocalTime getTimeOfSale() {
        return timeOfSale;
    }

    public void setTimeOfSale(LocalTime timeOfSale) {
        this.timeOfSale = timeOfSale;
    }

    /**
     * toString returns JSON, due to lack of a proper serializer.
     *
     * @return JSON representation of the Sale.
     */
    @Override
    public String toString() {
        return "{" +
                "\"salesMan\": \"" + salesMan + '\"' +
                ", \"consultant\": \"" + consultant + '\"' +
                ", \"customer\": \"" + customer + '\"' +
                ", \"revenue\": " + revenue +
                ", \"timeOfSale\": \"" + timeOfSale.getHour() + "-" + timeOfSale.getMinute() + "-" + timeOfSale.getSecond() + "\"" +
                '}';
    }
}
