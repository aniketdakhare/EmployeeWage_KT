package employeeWageComputation

data class Employee(val employeeName: String, val companyName: String, val month: String, val wages: Map<String, Int>)
{
    fun getMonthlyWage(): Int
    {
        var monthlyWage = 0
        for (day in wages)
        {
            monthlyWage += day.value
        }
        return monthlyWage
    }
}