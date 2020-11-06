package employeeWageComputation

data class Company(val name: String, val empRatePerHour: Int, val totalWorkingDays: Int, val totalWorkingHours: Int,
                   val totalNumberOfEmployee: Int, val numberOfMonths: Int, var employeeDetails: List<Employee> = listOf())
{
    fun getTotalWageOfCompany(): Int
    {
        var totalWage = 0
        employeeDetails.forEach{ employee -> totalWage += employee.getMonthlyWage() }
        return totalWage
    }
}