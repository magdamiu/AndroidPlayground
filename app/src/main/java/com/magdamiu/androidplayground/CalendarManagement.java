package com.magdamiu.androidplayground;

import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarManagement {
    public void saveEvent(Context context) {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra(CalendarContract.Events.TITLE, "Learn Android");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Home suit home");
        intent.putExtra(CalendarContract.Events.DESCRIPTION, "Download Examples");

        // Setting dates
        GregorianCalendar calDate = new GregorianCalendar(2020, 10, 02);
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                calDate.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                calDate.getTimeInMillis());

        // make it a full day event
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

        // make it a recurring Event
        intent.putExtra(CalendarContract.Events.RRULE, "FREQ=WEEKLY;COUNT=11;WKST=SU;BYDAY=TU,TH");

        // Making it private and shown as busy
        intent.putExtra(CalendarContract.Events.ACCESS_LEVEL,
                CalendarContract.Events.ACCESS_PRIVATE);
        intent.putExtra(CalendarContract.Events.AVAILABILITY,
                CalendarContract.Events.AVAILABILITY_BUSY);
        context.startActivity(intent);

        // other samples https://itnext.io/android-calendar-intent-8536232ecb38
    }
}
