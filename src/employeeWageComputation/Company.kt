package employeeWageComputation

import org.omg.PortableInterceptor.INACTIVE

data class Company(val name: String, val empRatePerHour: Int, val totalWorkingDays: Int, val totalWorkingHours: Int,
                    val totalNumberOfEmployee: Int, val numberOfMonths: Int, var totalWage: Int = 0)