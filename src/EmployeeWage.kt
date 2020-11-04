class EmployeeWage
{
    private val present = 1

    fun getAttendance()
    {
        if ((0..2).random() == present)
            println("Employee is present.")
        else
            println("Employee is absent.")
    }
}

fun main()
{
    val empWage = EmployeeWage()
    empWage.getAttendance()
}