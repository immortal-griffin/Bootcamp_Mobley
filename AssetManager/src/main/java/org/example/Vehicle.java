package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Year;

public class Vehicle extends Asset {
    private String makeModel;
    private int year;
    private int odometer;

    public Vehicle(String description, String dateAcquired, BigDecimal originalCost, String makeModel, int year, int odometer) {
        super(description, dateAcquired, originalCost);
        this.makeModel = makeModel;
        this.year = year;
        this.odometer = odometer;
    }

    public String getMakeModel() {
        return makeModel;
    }

    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    @Override
    public BigDecimal getValue() {
        int currentYear = Year.now().getValue();
        int age = currentYear - year;
        BigDecimal cost = getOriginalCost();
        BigDecimal value;

        if (age <= 3) {
            // 3% reduced value of cost per year
            BigDecimal reductionRate = new BigDecimal("0.03").multiply(BigDecimal.valueOf(age));
            BigDecimal reductionAmount = cost.multiply(reductionRate);
            value = cost.subtract(reductionAmount);
        } else if (age <= 6) {
            // 6% reduced value of cost per year
            BigDecimal reductionRate = new BigDecimal("0.06").multiply(BigDecimal.valueOf(age));
            BigDecimal reductionAmount = cost.multiply(reductionRate);
            value = cost.subtract(reductionAmount);
        } else if (age <= 10) {
            // 8% reduced value of cost per year
            BigDecimal reductionRate = new BigDecimal("0.08").multiply(BigDecimal.valueOf(age));
            BigDecimal reductionAmount = cost.multiply(reductionRate);
            value = cost.subtract(reductionAmount);
        } else {
            // Over 10 years old
            value = new BigDecimal("1000.00");
        }

        // Reduce final value by 25% if over 100,000 miles, unless Honda or Toyota
        if (odometer > 100000) {
            String lowerMakeModel = makeModel.toLowerCase();
            boolean isHondaOrToyota = lowerMakeModel.contains("honda") || lowerMakeModel.contains("toyota");

            if (!isHondaOrToyota) {
                value = value.multiply(new BigDecimal("0.75"));
            }
        }

        // Prevent negative value in edge cases and round to 2 decimal places
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            value = BigDecimal.ZERO;
        }

        return value.setScale(2, RoundingMode.HALF_UP);
    }
}
