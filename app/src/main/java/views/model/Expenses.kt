package views.model

class Expenses(
    val today: Double,
    val week: Double,
    val month: Double
) {
    fun toList(): List<Double> {
        return listOf(today, week, month)
    }
}