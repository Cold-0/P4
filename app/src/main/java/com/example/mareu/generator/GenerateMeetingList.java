package com.example.mareu.generator;

import android.graphics.Color;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class GenerateMeetingList {
    private static final Random r = new Random();

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting("01/02/2021", "08h40", "1", "Reunion1", getRandomColor(),
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com")),
            new Meeting("01/02/2021", "14h00", "2", "Reunion2", getRandomColor(),
                    Arrays.asList("bob@email.com", "juan@email.com")),
            new Meeting("01/02/2021", "15h00", "3", "Reunion3", getRandomColor(),
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com", "bob@email.com", "juan@email.com")),
            new Meeting("01/02/2021", "08h40", "4", "Reunion4", getRandomColor(),
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com")),
            new Meeting("22/07/2021", "14h00", "1", "Reunion5", getRandomColor(),
                    Arrays.asList("bob@email.com", "juan@email.com")),
            new Meeting("23/07/2021", "15h00", "1", "Reunion6", getRandomColor(),
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com", "bob@email.com", "juan@email.com")),
            new Meeting("28/07/2021", "08h40", "4", "Reunion7", getRandomColor(),
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com")),
            new Meeting("28/07/2021", "14h00", "3", "Reunion8", getRandomColor(),
                    Arrays.asList("bob@email.com", "juan@email.com")),
            new Meeting("28/07/2021", "15h00", "5", "Reunion9", getRandomColor(),
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com", "bob@email.com", "juan@email.com")),
            new Meeting("01/08/2021", "08h40", "1", "Reunion10", getRandomColor(),
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com")),
            new Meeting("01/08/2021", "14h00", "1", "Reunion11", getRandomColor(),
                    Arrays.asList("bob@email.com", "juan@email.com")),
            new Meeting("16/08/2021", "15h00", "2", "Reunion12", getRandomColor(),
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com", "bob@email.com", "juan@email.com"))
    );

    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }

    public static int getRandomColor() {
        return Color.HSVToColor(new float[]{r.nextFloat() * 360, 0.25f, 0.75f}); // use HSV to get pastel color (sat 25%, value 75%, so only varying HUE
    }
}
