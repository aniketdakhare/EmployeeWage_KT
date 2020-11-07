package employeeWageComputation

data class Employee(val employeeName: String, val companyName: String, val month: String,
                    val wages: Map<String,Int> = mutableMapOf(), var day: String = "", var dailyWage: Int = 0)
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