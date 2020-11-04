class EmployeeWage
{
    private val present = 1
    private val empRatePerHour = 20

    fun getAttendance()
    {
        var empHrs = 0
        if ((0..1).random() == present)
            empHrs = 8
        val empWage = empHrs * empRatePerHour
        println("Employee Wage Per Day = $empWage")
    }
}

fun main()
{
    val empWage = EmployeeWage()
    empWage.getAttendance()
}