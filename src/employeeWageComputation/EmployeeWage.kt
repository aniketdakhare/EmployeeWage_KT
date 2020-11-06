package employeeWageComputation

import java.io.File

class EmployeeWageComputation
{
    private val present = 1
    private val partTime = 2
    private var headerCheck = false
    private val companyList = mutableListOf<Company>()

    private fun writeToCSVFile(employeeDetails: List<Employee>)
    {
        val csvHeader = "Employee,Company,Month,Day,Wage"
        val file = File("EmployeeWageDetails.csv")
        if (!headerCheck)
        {
            file.writeText(csvHeader)
            headerCheck = true
        }
        employeeDetails.forEach { employee -> employee.wages.forEach{(day, wage) ->
            file.appendText("\n${employee.employeeName},${employee.companyName},${employee.month},$day,$wage") }}
    }

    private fun getWorkingHours(): Int
    {
        val employeeCheck = (0..2).random()
        return when (employeeCheck)
        {
            present -> 8
            partTime -> 4
            else -> 0
        }
    }



    private fun employeeWageBuilder(company: Company): List<Employee>
    {
        val employeeWages = mutableListOf<Employee>()

        for (employee in 1..company.totalNumberOfEmployee)
        {
            for (month in 1..company.numberOfMonths)
            {
                var totalEmpHrs = 0
                var daysCount = 0
                val dailyWages = mutableMapOf<String, Int>()

                while (daysCount < company.totalWorkingDays && totalEmpHrs < company.totalWorkingHours)
                {
                    daysCount++
                    val dailyHours = getWorkingHours()
                    dailyWages["Day_$daysCount"] = dailyHours * company.empRatePerHour
                    totalEmpHrs += dailyHours
                }
                employeeWages.add(Employee("Employee_$employee", company.name, "Month_$month"
                    , wages = dailyWages))
                company.totalWage += (totalEmpHrs * company.empRatePerHour)
                companyList.add(company)
            }
        }
        return employeeWages
    }

    private fun getCompanyDetails(): Company
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
        return Company(companyName, empRatePerHour, totalWorkingDays, totalWorkingHours, totalNumberOfEmployee,
            numberOfMonths)
    }

    private fun wageLoader()
    {
        val employees = employeeWageBuilder(getCompanyDetails())
        writeToCSVFile(employees)
        println("\n\n-------------------------------------------------------------------------------------------------")
        employees.forEach{ employee -> println("${employee.employeeName} working in " +
                "${employee.companyName} has ${employee.month} salary as ${employee.getMonthlyWage()}") }
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