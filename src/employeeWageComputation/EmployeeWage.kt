package employeeWageComputation

import java.io.File

class EmployeeWageComputation
{
    private val present = 1
    private val partTime = 2
    private val empRatePerHour = 20
    private val workingDays = 20
    private val workingHours = 100

    private fun writeToCSVFile(dailyHours: MutableMap<String, Int>)
    {
        val csvHeader = "Day,Wage"
        val file = File("EmployeeWageDetails.csv")
        file.writeText(csvHeader)
        dailyHours.forEach { (day, hours) -> file.appendText("\n$day,${hours * empRatePerHour}") }
    }

    private fun getWorkingHours(): Int
    {
        var totalEmpHrs = 0
        var daysCount = 0
        val dailyWorkingHours = mutableMapOf<String, Int>()

        while (daysCount < workingDays && totalEmpHrs < workingHours)
        {
            daysCount ++
            val dailyHours = when ((0..2).random())
            {
                present -> 8
                partTime -> 4
                else -> 0
            }
            dailyWorkingHours["Day: $daysCount"] = dailyHours
            totalEmpHrs += dailyHours
        }
        writeToCSVFile(dailyWorkingHours)
        return totalEmpHrs
    }

    fun printEmployeeWage()
    {
        val empWage = getWorkingHours() * empRatePerHour
        println("Employee Wage Per Day = $empWage")
    }
}

fun main()
{
    val empWage = EmployeeWageComputation()
    empWage.printEmployeeWage()
}