package employeeWageComputation

import java.io.File

class EmployeeWageComputation
{
    private val present = 1
    private val partTime = 2
    private val empRatePerHour = 20
    private val workingDays = 20
    private val workingHours = 100

    private fun writeToCSVFile(employeeDetails: MutableList<Employee>)
    {
        val csvHeader = "Employee,Month,Day,Wage"
        val file = File("EmployeeWageDetails.csv")
        file.writeText(csvHeader)
        employeeDetails.forEach { employee ->
            file.appendText("\n${employee.name},${employee.month},${employee.day},${employee.wage}") }
    }

    private fun getWorkingHours(totalNumberOfEmployee: Int, numberOfMonths: Int): List<Employee>
    {
        val employeeDetails = mutableListOf<Employee>()
        val employeeWages = mutableListOf<Employee>()

        for (employee in 1..totalNumberOfEmployee)
        {
            for (month in 1..numberOfMonths)
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
                    employeeDetails.add(Employee("Employee_$employee", "Month_$month",
                        "Day_$daysCount", dailyHours * empRatePerHour))
                    totalEmpHrs += dailyHours
                }
                employeeWages.add(Employee("Employee_$employee", "Month_$month"
                    , wage = totalEmpHrs * empRatePerHour))
            }
        }
        writeToCSVFile(employeeDetails)
        return employeeWages
    }

    fun displayOnConsole()
    {
        print("Welcome to Employee Wage Computation\n\nEnter the number of Employee: ")
        val totalNumberOfEmployee = Integer.valueOf(readLine())
        print("\nEnter number of months: ")
        val numberOfMonths = Integer.valueOf(readLine())
        getWorkingHours(totalNumberOfEmployee, numberOfMonths).forEach{ employee ->
            println("${employee.name}'s ${employee.month} salary = ${employee.wage}") }
    }
}

fun main()
{
    val empWage = EmployeeWageComputation()
    empWage.displayOnConsole()
}