package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a luxury car in the rental system.
 *
 * Luxury cars are high-end vehicles that may provide
 * a chauffeur option and premium features. A surcharge
 * can be added to the base rental price.
 */
public class LuxuryCar extends Car {

    // Indicates whether a chauffeur option is available.
    private boolean chauffeur = false;

    // List of premium features available in the car.
    private List<String> premiumFeatures;
    // Additional amount added to the base rental price.
    private BigDecimal surcharge = BigDecimal.ZERO;


    /**
     * Constructs a LuxuryCar with its common car information
     * and premium features.
     *
     * @param id                   unique identifier of the car
     * @param brand                brand of the car
     * @param model                model of the car
     * @param manufacturingYear    manufacturing year of the car
     * @param baseDailyRentalPrice base daily rental price
     * @param premiumFeatures      list of premium features
     */
    public LuxuryCar(String id, String brand, String model, String manufacturingYear, BigDecimal baseDailyRentalPrice, List<String> premiumFeatures) {
        super(id, brand, model, manufacturingYear, baseDailyRentalPrice);

        validatePremiumFeatures(premiumFeatures);
        this.premiumFeatures = new ArrayList<>(premiumFeatures);
    }

    /**
     * Validates the premium features list.
     *
     * @param premiumFeatures list of premium features to validate
     * @throws IllegalArgumentException if the list is null
     */
    private void validatePremiumFeatures(List<String> premiumFeatures) {
        if (premiumFeatures == null) {
            throw new IllegalArgumentException("Premium features cannot be null.");
        }
    }

    /**
     * Returns whether a chauffeur option is available.
     *
     * @return true if a chauffeur is available, otherwise false
     */
    public boolean isChauffeur() {
        return chauffeur;
    }

    /**
     * Sets whether a chauffeur option is available.
     *
     * @param chauffeur true if a chauffeur is available
     */
    public void setChauffeur(boolean chauffeur) {
        this.chauffeur = chauffeur;
    }

    /**
     * Returns the surcharge added to the rental price.
     *
     * @return surcharge amount
     */
    public BigDecimal getSurcharge() {
        return surcharge;
    }

    /**
     * Sets the surcharge amount.
     *
     * @param surcharge surcharge amount
     * @throws IllegalArgumentException if surcharge is null or negative
     */
    public void setSurcharge(BigDecimal surcharge) {
        if (surcharge == null || surcharge.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Surcharge must be zero or greater.");
        }
        this.surcharge = surcharge;
    }

    /**
     * Returns the premium features of the car.
     *
     * @return list of premium features
     */
    public List<String> getPremiumFeatures() {
        return premiumFeatures;
    }

    /**
     * Updates the premium features of the car.
     *
     * @param premiumFeatures new list of premium features
     * @throws IllegalArgumentException if the list is null
     */
    public void setPremiumFeatures(List<String> premiumFeatures) {
        validatePremiumFeatures(premiumFeatures);
        this.premiumFeatures = new ArrayList<>(premiumFeatures);
    }

    /**
     * Calculates the rental price for the specified number of days.
     *
     * @param days number of rental days
     * @return calculated rental price
     */
    @Override
    public BigDecimal calculatePrice(int days) {
        validateRentalDays(days);

        BigDecimal total = getBaseDailyRentalPrice().multiply(BigDecimal.valueOf(days));

        return total.add(getSurcharge());
    }


    /**
     * Returns a string representation of the luxury car.
     *
     * @return string containing the car's information
     */
    @Override
    public String toString() {
        return "LuxuryCar{" + super.toString() +
                "chauffeur=" + chauffeur +
                ", premiumFeatures=" + premiumFeatures +
                ", surcharge=" + surcharge + '}';
    }
}
