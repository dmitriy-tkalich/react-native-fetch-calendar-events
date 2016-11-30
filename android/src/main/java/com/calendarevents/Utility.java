package com.calendarevents;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Utility {
    public static WritableArray readCalendarEvent(Context context) {
        WritableArray events = Arguments.createArray();
        Cursor cursor = context.getContentResolver()
                .query(
                        Uri.parse("content://com.android.calendar/events"),
                        new String[] { "calendar_id", "title", "description",
                                "dtstart", "dtend", "eventLocation" }, null,
                        null, null);
        cursor.moveToFirst();

        // fetching calendars name
        String CNames[] = new String[cursor.getCount()];

        for (int i = 0; i < CNames.length; i++) {
            WritableMap event = Arguments.createMap();
            event.putString("title", cursor.getString(1));
            event.putString("description", cursor.getString(2));
            event.putString("startDate", cursor.getString(3));
            event.putString("endDate", cursor.getString(4));
            events.pushMap(event);
            CNames[i] = cursor.getString(1);
            cursor.moveToNext();
        }

        return events;
    }

    public static String getDate(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "dd/MM/yyyy hh:mm:ss a");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}
