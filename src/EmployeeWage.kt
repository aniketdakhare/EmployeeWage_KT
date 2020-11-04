class EmployeeWageComputation
{
    private val present = 1
    private val partTime = 2
    private val empRatePerHour = 20
    private val workingDays = 20

    private fun getWorkingHours(): Int
    {
        var totalEmpHrs = 0
        for (day in 1..workingDays)
        {
            totalEmpHrs += when ((0..2).random())
            {
                present -> 8
                partTime -> 4
                else -> 0
            }
        }
        return totalEmpHrs
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