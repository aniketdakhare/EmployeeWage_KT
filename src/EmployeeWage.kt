class EmployeeWageComputation
{
    private val present = 1
    private val partTime = 2
    private val empRatePerHour = 20

    private fun getWorkingHours(): Int
    {
        return when ((0..2).random())
        {
            present -> 8
            partTime -> 4
            else -> 0
        }
    }

    fun getEmployeeWage()
    {
        val empWage = getWorkingHours() * empRatePerHour
        println("Employee Wage Per Day = $empWage")
    }
}

fun main()
{
    val empWage = EmployeeWageComputation()
    empWage.getEmployeeWage()
}