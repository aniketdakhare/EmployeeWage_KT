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
            }
        }
        return employeeWages
    }

    private fun getCompanyName(): String
    {
        print("\nEnter your company name: ")
        return readLine().toString()
    }

    private fun getCompanyDetails(): Company
    {
        val companyName = getCompanyName()
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
        val companyDetails = getCompanyDetails()
        val employees = employeeWageBuilder(companyDetails)
        writeToCSVFile(employees)
        companyDetails.employeeDetails = employees
        companyList.add(companyDetails)
    }

    private fun sortByMonthlyWage(employeeDetails: List<Employee>?)
    {
        employeeDetails?.sortedBy { it.getMonthlyWage() }?.forEach{ employee -> println("${employee.employeeName} " +
                "working in ${employee.companyName} has ${employee.month} salary as ${employee.getMonthlyWage()}") }
    }

    private fun sortByDailyWage(employeeDetails: List<Employee>?)
    {
        val employeeList = mutableListOf<Employee>()
        employeeDetails?.forEach { employee -> employee.wages.forEach{(day, wage) -> employeeList
            .add(Employee(employee.employeeName, employee.companyName, employee.month, day = day, dailyWage = wage)) }}
        employeeList.sortedBy { it.dailyWage }.forEach{ employee -> println("${employee.employeeName} working in " +
                "${employee.companyName} has ${employee.month}'s ${employee.day} salary as ${employee.dailyWage}") }
    }

    private fun sortBy(employeeDetails: List<Employee>?)
    {
        println("\nSelect your choice. \n1: Display sorted list by monthly wages. \n2: Display sorted list by" +
                " daily wages.")
        when (Integer.valueOf(readLine()))
        {
            1 -> sortByMonthlyWage(employeeDetails)
            2 -> sortByDailyWage(employeeDetails)
            else -> println("Invalid Input")
        }
    }

    private fun displayEmployeeByItsWagePerHour()
    {
        print("\nEnter Employee rate per hour: ")
        val empRatePerHour = Integer.valueOf(readLine())
        companyList.forEach { if (it.empRatePerHour == empRatePerHour) for (emp in 1 .. it.totalNumberOfEmployee)
                println("Employee_$emp of ${it.name}") }
    }

    private fun displayDetails()
    {
        val companyName = getCompanyName()
        println("\n\n-------------------------------------------------------------------------------------------------")
        val employeeDetails = companyList.find{ it.name == companyName}
            ?.employeeDetails
        sortBy(employeeDetails)
        println("-------------------------------------------------------------------------------------------------\n")
    }

    private fun displayTotalWageOfCompany()
    {
        val companyName = getCompanyName()
        val companiesTotalWage = companyList.find { it.name == companyName }?.getTotalWageOfCompany()
        println("Total wage of $companyName is  $companiesTotalWage")
    }

    fun presentChoice()
    {
        println("\nWelcome to Employee Wage Computation")
        var flag = 1
        while (flag == 1)
        {
            println("\nSelect your choice. \n1: Calculate Employee wage for your company. \n2: Display Details." +
                    "\n3: Get total wage of company. \n4: Display Employee Details as per given wage per hour. \n5: Exit")
            when (Integer.valueOf(readLine()))
            {
                1 -> wageLoader()
                2 -> displayDetails()
                3 -> displayTotalWageOfCompany()
                4-> displayEmployeeByItsWagePerHour()
                5 -> flag = 0
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