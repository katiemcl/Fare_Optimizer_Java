/**
 * Description: Fare calculator for New York Transit. Optimizes fares based 
 * on the integer inputs through command-line: number of days using transit,
 * number of rides, and if eligible for discount (1 = yes)
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

        // round to nearest cent
        double pricePer = Math.round((totalPrice / (double) rides) * 100) / 100.0;
        return pricePer;
    }

    /**
     * @return double array of the price/ride per each fare option
     */
    public double[] getRidePrices() {
        double[] pricePer = new double[PRICES.length];

        pricePer[0] = PRICES[0]; // single pay per ride
        pricePer[1] = unlimited7Price(); // per ride w/ 7 day pass
        
        double numTix = Math.ceil(days / DAYS[2]); // hround up to full 30 day 
        double totalPrice = numTix * PRICES[2]; // total price of all tix

        // round to nearest cent
        double priceEach = Math.round((totalPrice / (double) rides) * 100) / 100.0; 
        pricePer[2] = priceEach;

        printPricePer(pricePer);
        
        return pricePer;
    }

    /**
     * @return elements in array
     */
    private void printPricePer(double[] array) {
        for(int c = 0; c < array.length; c++) {
            System.out.print(array[c] + " ");
        }
        System.out.println();
    }

    /**
     * prints recommendation for ticket type and price
     * @return conclusion on most price efficient ticket option
     */
    public String getBestFare() {
        double[] costs = getRidePrices();
        double min = Math.min((Math.min(costs[0], costs[1])), costs[2]);
        String[] type = {"Pay-per-ride ", "7-day Unlimited ", "30-day Unlimited "};
        int ind = 2;
        if(min == costs[1]) {
            ind = 1;
        } if(min == costs[0]) {
            ind = 0;
        }
        return ("You should get the " + type[ind] + "option at $" + costs[ind] + " per ride.");
    }

    // test unit
    public static void main(String[] args){
        int days = Integer.parseInt(args[0]);
        int rides = Integer.parseInt(args[1]);
        int discount = Integer.parseInt(args[2]);
        
        boolean disc = (discount == 1);

        TransitCalc yoot = new TransitCalc(days, rides, disc);
        System.out.printf("Price per ride with 7-day Unlimited: $%.2f\n", yoot.unlimited7Price());
        System.out.println(yoot.getBestFare()); 
    }
    

}

 