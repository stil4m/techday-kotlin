package nl.avisi.techday.kotlin

import java.math.BigDecimal

class Invoice {
    private val invoiceLines = mutableListOf<InvoiceLine>()

    fun addInvoiceLine(invoiceLine: InvoiceLine) {
        invoiceLines.add(invoiceLine)
    }

    fun printInvoice(): String {
        return invoiceLines.joinToString("") { printInvoiceLine(it.description, it.vatPercentage, it.amount) } +
                "\n" +
                calculateTotal().let { printInvoiceLine("Total", null, it) } +
                calculateTotalVatAmount().let { printInvoiceLine("Total VAT amount", null, it) }
    }

    private fun printInvoiceLine(description: String, vatPercentage: BigDecimal?, amount: BigDecimal?): String {
        return description.padRight(50) +
                vatPercentage?.let { it.twoDecimalString() + "%" }.padRight(10) +
                amount?.let { "€ " + it.twoDecimalString() }.padRight(10) +
                "\n"
    }

    private fun calculateTotalVatAmount(): BigDecimal {
        return invoiceLines
                .map { it.amount/(100.bd)*(it.vatPercentage) }
                .reduce(BigDecimal::add)
    }

    private fun calculateTotal(): BigDecimal {
        return invoiceLines.map { it.amount }
                .reduce(BigDecimal::add)
    }
}

data class InvoiceLine(
        val description: String,
        val amount: BigDecimal,
        val vatPercentage: BigDecimal
)

fun BigDecimal.twoDecimalString() = setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
fun String?.padRight(padding: Int) = String.format("%1$-${padding}s", this.orEmpty())
val Double.bd
    get() = BigDecimal(this)
val Int.bd
    get() = BigDecimal(this)

fun main(args: Array<String>) {

    Invoice().apply {
        addInvoiceLine(InvoiceLine(
                description = "Apple Macbook Pro",
                amount = 1399.bd,
                vatPercentage = 21.bd
        ))
        addInvoiceLine(InvoiceLine(
                description = "Medium unions 1kg",
                amount = 1.05.bd,
                vatPercentage = 6.bd
        ))
        addInvoiceLine(InvoiceLine(
                description = "Domain name kotlin.nl",
                amount = 9.90.bd,
                vatPercentage = 21.bd
        ))
        addInvoiceLine(InvoiceLine(
                description = "Flight to Australia",
                amount = 699.bd,
                vatPercentage = 0.bd
        ))
    }.printInvoice()
            .run(::println)
}