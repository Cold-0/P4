package com.example.mareu.generator;

import android.graphics.Color;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class MeetingListGenerator {
    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting("01/02/2021", "08h40", "1", "Reunion1",
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com")),
            new Meeting("01/02/2021", "14h00", "2", "Reunion2",
                    Arrays.asList("bob@email.com", "juan@email.com")),
            new Meeting("01/02/2021", "15h00", "3", "Reunion3",
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com", "bob@email.com", "juan@email.com")),
            new Meeting("01/02/2021", "08h40", "4", "Reunion4",
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com")),
            new Meeting("22/07/2021", "14h00", "1", "Reunion5",
                    Arrays.asList("bob@email.com", "juan@email.com")),
            new Meeting("23/07/2021", "15h00", "1", "Reunion6",
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com", "bob@email.com", "juan@email.com")),
            new Meeting("28/07/2021", "08h40", "4", "Reunion7",
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com")),
            new Meeting("28/07/2021", "14h00", "3", "Reunion8",
                    Arrays.asList("bob@email.com", "juan@email.com")),
            new Meeting("28/07/2021", "15h00", "5", "Reunion9",
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com", "bob@email.com", "juan@email.com")),
            new Meeting("01/08/2021", "08h40", "1", "Reunion10",
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com")),
            new Meeting("01/08/2021", "14h00", "1", "Reunion11",
                    Arrays.asList("bob@email.com", "juan@email.com")),
            new Meeting("16/08/2021", "15h00", "2", "Reunion12",
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com", "bob@email.com", "juan@email.com"))
    );

    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}
