package nl.avisi.techday.kotlin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InvoiceJava {

    private final List<InvoiceLineJava> invoiceLines;

    public InvoiceJava() {
        invoiceLines = new ArrayList<>();
    }

    public static void main(String... args) {
        System.out.println(new InvoiceJava()
                .addInvoiceLine(new InvoiceLineJava("Apple Macbook Pro", new BigDecimal(1399), new BigDecimal(21)))
                .addInvoiceLine(new InvoiceLineJava("Medium unions 1kg", new BigDecimal(1.05), new BigDecimal(6)))
                .addInvoiceLine(new InvoiceLineJava("Domain name kotlin.nl", new BigDecimal(9.90), new BigDecimal(21)))
                .addInvoiceLine(new InvoiceLineJava("Flight to Australia", new BigDecimal(699), new BigDecimal(0)))
                .printInvoice());
    }

    public InvoiceJava addInvoiceLine(InvoiceLineJava invoiceLine) {
        invoiceLines.add(invoiceLine);
        return this;
    }

    public String printInvoice() {
        String result = invoiceLines
                .stream()
                .map(invoiceLine ->
                        printInvoiceLine(
                                invoiceLine.getDescription(),
                                Optional.of(invoiceLine.getVatPercentage()),
                                Optional.of(invoiceLine.getAmount())))
                .collect(Collectors.joining("\n"));

        result += calculateTotal()
                .map(total ->
                        "\n\n" + printInvoiceLine("Total", Optional.empty(), Optional.of(total)) + "\n")
                .orElse("");

        result += calculateTotalVatAmount()
                .map(total ->
                        printInvoiceLine("Total VAT amount", Optional.empty(), Optional.of(total)) + "\n")
                .orElse("");

        return result;
    }

    private String printInvoiceLine(String description, Optional<BigDecimal> vatPercentage, Optional<BigDecimal> amount) {
        return padRight(description, 50)
                + padRight(vatPercentage.map(bd -> bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "%").orElse(""), 10)
                + padRight("€ " + amount.map(bd -> bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()).orElse(""), 10);
    }

    private Optional<BigDecimal> calculateTotalVatAmount() {
        return invoiceLines.stream()
                           .map(invoiceLine -> invoiceLine.getAmount().divide(new BigDecimal(100)).multiply(invoiceLine.getVatPercentage()))
                           .reduce(BigDecimal::add);
    }

    private Optional<BigDecimal> calculateTotal() {
        return invoiceLines.stream().map(InvoiceLineJava::getAmount).reduce(BigDecimal::add);
    }

    private static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }
}
