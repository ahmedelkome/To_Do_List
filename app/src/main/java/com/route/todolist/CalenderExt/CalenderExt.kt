package com.route.todolist.CalenderExt

import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.Calendar


fun CalendarDay.timemillies(): Long {
    var calender = Calendar.getInstance()
    calender.set(this.year, this.month - 1, this.day)
    calender.clearTime()
    return calender.timeInMillis
}
fun Calendar.clearTime(){
    this.clear(Calendar.HOUR)
    this.clear(Calendar.MINUTE)
    this.clear(Calendar.SECOND)
    this.clear(Calendar.MILLISECOND)
}