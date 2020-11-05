package employeeWageComputation

import java.io.File

class EmployeeWageComputation
{
    private val present = 1
    private val partTime = 2
    private val empRatePerHour = 20
    private val workingDays = 20
    private val workingHours = 100
    private var totalNumberOfEmployee: Int = 0

    private fun writeToCSVFile(employeeDetails: MutableList<Employee>)
    {
        val csvHeader = "Employee,Day,Wage"
        val file = File("EmployeeWageDetails.csv")
        file.writeText(csvHeader)
        employeeDetails.forEach { employee -> file.appendText("\n${employee.name}, ${employee.day}, ${employee.wage}") }
    }

    private fun getWorkingHours(): Map<String, Int>
    {
        val employeeDetails = mutableListOf<Employee>()
        val employeeWages = mutableMapOf<String, Int>()

        for (employee in 1..totalNumberOfEmployee)
        {
            var totalEmpHrs = 0
            var daysCount = 0

            while (daysCount < workingDays && totalEmpHrs < workingHours)
            {
                daysCount++
                val dailyHours = when ((0..2).random())
                {
                    present -> 8
                    partTime -> 4
                    else -> 0
                }
                employeeDetails.add(Employee("Employee_$employee", "Day_$daysCount", dailyHours * empRatePerHour))
                totalEmpHrs += dailyHours
            }
            employeeWages["Employee_$employee"] = totalEmpHrs * empRatePerHour
        }
        writeToCSVFile(employeeDetails)
        return employeeWages
    }

    private fun printEmployeeWage()
    {
        getWorkingHours().forEach{ (emp, wage) -> println("Monthly Wage of $emp = $wage") }
    }

    fun displayOnConsole()
    {
        print("Welcome to Employee Wage Computation\n\nEnter the number of Employee: ")
        totalNumberOfEmployee = Integer.valueOf(readLine())
        printEmployeeWage()
    }
}

fun main()
{
    val empWage = EmployeeWageComputation()
    empWage.displayOnConsole()
}