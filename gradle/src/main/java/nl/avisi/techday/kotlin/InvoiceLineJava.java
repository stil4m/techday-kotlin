package nl.avisi.techday.kotlin;

import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceLineJava {

    private final String description;
    private final BigDecimal amount;
    private final BigDecimal vatPercentage;

    public InvoiceLineJava(String description, BigDecimal amount, BigDecimal vatPercentage) {
        this.description = description;
        this.amount = amount;
        this.vatPercentage = vatPercentage;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getVatPercentage() {
        return vatPercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InvoiceLineJava that = (InvoiceLineJava) o;
        return Objects.equals(description, that.description) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(vatPercentage, that.vatPercentage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, amount, vatPercentage);
    }

    @Override
    public String toString() {
        return "InvoiceLineJava{" +
                "description='" + description + '\'' +
                ", amount=" + amount +
                ", vatPercentage=" + vatPercentage +
                '}';
    }
}
