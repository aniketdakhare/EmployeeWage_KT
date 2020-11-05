package employeeWageComputation

import java.io.File

class EmployeeWageComputation
{
    private val present = 1
    private val partTime = 2
    private var headerCheck = false

    private fun writeToCSVFile(employeeDetails: MutableList<Employee>)
    {
        val csvHeader = "Employee,Month,Day,Wage"
        val file = File("EmployeeWageDetails.csv")
        if (!headerCheck)
        {
            file.writeText(csvHeader)
            headerCheck = true
        }
        employeeDetails.forEach { employee -> file.appendText("\n${employee.employeeName}," +
                "${employee.companyName},${employee.month},${employee.day},${employee.wage}") }
    }

    private fun getWorkingHours(company: Company): List<Employee>
    {
        val employeeDetails = mutableListOf<Employee>()
        val employeeWages = mutableListOf<Employee>()

        for (employee in 1..company.totalNumberOfEmployee)
        {
            for (month in 1..company.numberOfMonths)
            {
                var totalEmpHrs = 0
                var daysCount = 0

                while (daysCount < company.totalWorkingDays && totalEmpHrs < company.totalWorkingHours)
                {
                    daysCount++
                    val dailyHours = when ((0..2).random())
                    {
                        present -> 8
                        partTime -> 4
                        else -> 0
                    }
                    employeeDetails.add(Employee("Employee_$employee", company.name, "Month_$month",
                        "Day_$daysCount", dailyHours * company.empRatePerHour))
                    totalEmpHrs += dailyHours
                }
                employeeWages.add(Employee("Employee_$employee", company.name, "Month_$month"
                    , wage = totalEmpHrs * company.empRatePerHour))
            }
        }
        writeToCSVFile(employeeDetails)
        return employeeWages
    }

    private fun wageLoader()
    {

        print("\nEnter your company name: ")
        val companyName = readLine().toString()
        print("\nEnter companies employee rate per hour: ")
        val empRatePerHour = Integer.valueOf(readLine())
        print("\nEnter total number of working days in a month: ")
        val totalWorkingDays = Integer.valueOf(readLine())
        print("\nEnter total number of working hours in a month: ")
        val totalWorkingHours = Integer.valueOf(readLine())
        print("\nEnter the number of Employee: ")
        val totalNumberOfEmployee = Integer.valueOf(readLine())
        print("\nEnter number of months: ")
        val numberOfMonths = Integer.valueOf(readLine())
        println("\n\n-------------------------------------------------------------------------------------------------")
        getWorkingHours(Company(companyName, empRatePerHour, totalWorkingDays, totalWorkingHours, totalNumberOfEmployee,
            numberOfMonths)).forEach{ employee -> println("${employee.employeeName} working in " +
                "${employee.companyName} has ${employee.month} salary as ${employee.wage}") }
        println("-------------------------------------------------------------------------------------------------\n")
    }

    fun presentChoice()
    {
        println("\nWelcome to Employee Wage Computation")
        var flag = 1
        while (flag == 1)
        {
            println("\nSelect your choice. \n1: Calculate Employee wage for your company. \n2: Exit")
            when (Integer.valueOf(readLine()))
            {
                1 -> wageLoader()
                2 -> flag = 0
                else -> println("\nInvalid Input")
            }
        }
    }
}

fun main()
{
    val empWage = EmployeeWageComputation()
    empWage.presentChoice()
}