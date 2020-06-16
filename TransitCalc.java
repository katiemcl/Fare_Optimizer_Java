/**
 * Description: Fare calculator for New York Transit. Optimizes fares based 
 * on the rider's number of days using transit and number of rides within this time frame.
 * Test project for Visual Studio Code and GitHub 
 * 
 * Date: 6/15/2020
 */

public class TransitCalc {
    private int days; // # days using transit system
    private int rides; // number of individual rides expected
    
    // pay-per-ride, 7 day, 30 day
    private double[] PRICES = {2.75, 33, 127}; 
    // sr and disability discount
    private double[] REDUCED = {1.35, 16.50, 63.50}; 
    private double[] DAYS = {1, 7, 30};
 

    /**
     * Constructor for Transit Calculator
     * @param singleDays number days (up to 30) est. for using transit
     * @param numRides number of single rides est. over days
     * @param discount eligible for senior or disability discount
     */
    public TransitCalc(int singleDays, int numRides, boolean discount) {
        days = singleDays;
        rides = numRides;

        if(discount){
            PRICES = REDUCED;
        }
    }

    /**
     * @return overall price per ride of 7 day unlimited option
     */
    public double unlimited7Price(){
        double numWks = Math.ceil(days / DAYS[1]); // round up to full 7 day
        double totalPrice = numWks * PRICES[1];
        double pricePer = totalPrice / (double) rides;
        return pricePer;
    }

    /**
     * @return double array of the price/ride per each fare option
     */
    public double[] getRidePrices() {
        double[] pricePer = new double[PRICES.length];

        pricePer[0] = PRICES[0]; // single pay per ride
        pricePer[1] = unlimited7Price(); // per ride w/ 7 day pass
        
        double numTix = Math.ceil(days / DAYS[2]); // how many 30 day tix
        double totalPrice = numTix * PRICES[2]; // total price of all tix

        // round to nearest cent
        double priceEach = Math.round((totalPrice / (double) rides) * 100) / 100.0; 
        pricePer[2] = priceEach;
        
        return pricePer;
    }

    /**
     * @return conclusion on most price efficient ticekt option
     */
    public String getBestFare() {
        double[] prices = getRidePrices();
        double min = Math.min((Math.min(prices[0], prices[1])), prices[2]);
        String[] type = {"Pay-per-ride ", "7-day Unlimited ", "30-day Unlimited "};
        int ind = 2;
        if(min == prices[1]) {
            ind = 1;
        } if(min == prices[0]) {
            ind = 0;
        }
        return ("You should get the " + type[ind] + "option at $" + prices[ind] + " per ride.");
    }

    // test unit
    public static void main(String[] args){
        TransitCalc yoot = new TransitCalc(5, 12, true);
        System.out.println(yoot.getBestFare()); 
    }
    

}

 