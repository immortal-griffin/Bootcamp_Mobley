package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class House extends Asset {
    private String address;
    private int condition;
    private int squareFoot;
    private int lotSize;

    public House(String description, String dateAcquired, BigDecimal originalCost, String address, int condition, int squareFoot, int lotSize) {
        super(description, dateAcquired, originalCost);
        this.address = address;
        this.condition = condition;
        this.squareFoot = squareFoot;
        this.lotSize = lotSize;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public int getSquareFoot() {
        return squareFoot;
    }

    public void setSquareFoot(int squareFoot) {
        this.squareFoot = squareFoot;
    }

    public int getLotSize() {
        return lotSize;
    }

    public void setLotSize(int lotSize) {
        this.lotSize = lotSize;
    }

    @Override
    public BigDecimal getValue() {
        BigDecimal pricePerSqFt;

        switch (condition) {
            case 1: // excellent
                pricePerSqFt = new BigDecimal("180.00");
                break;
            case 2: // good
                pricePerSqFt = new BigDecimal("130.00");
                break;
            case 3: // fair
                pricePerSqFt = new BigDecimal("90.00");
                break;
            case 4: // poor
                pricePerSqFt = new BigDecimal("80.00");
                break;
            default:
                pricePerSqFt = BigDecimal.ZERO;
                break;
        }

        // Calculate base house value: pricePerSqFt * squareFoot
        BigDecimal houseValue = pricePerSqFt.multiply(BigDecimal.valueOf(squareFoot));

        // Calculate lot value: $0.25 * lotSize
        BigDecimal lotValue = new BigDecimal("0.25").multiply(BigDecimal.valueOf(lotSize));

        // Total value rounded to 2 decimal places for standard currency formatting
        return houseValue.add(lotValue).setScale(2, RoundingMode.HALF_UP);
    }
}
