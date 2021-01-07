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
            new Meeting("19/07/2020", "08h40", "4", "Reunion1", getRandomColor(),
                        Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com")),
            new Meeting("21/07/2020", "14h00", "1", "Reunion2", getRandomColor(),
                    Arrays.asList("bob@email.com", "juan@email.com")),
            new Meeting("21/07/2020", "15h00", "1", "Reunion3", getRandomColor(),
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com", "bob@email.com", "juan@email.com")),
            new Meeting("22/07/2020", "08h40", "4", "Reunion4", getRandomColor(),
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com")),
            new Meeting("22/07/2020", "14h00", "1", "Reunion5", getRandomColor(),
                    Arrays.asList("bob@email.com", "juan@email.com")),
            new Meeting("23/07/2020", "15h00", "1", "Reunion6", getRandomColor(),
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com", "bob@email.com", "juan@email.com")),
            new Meeting("28/07/2020", "08h40", "4", "Reunion7", getRandomColor(),
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com")),
            new Meeting("28/07/2020", "14h00", "1", "Reunion8", getRandomColor(),
                    Arrays.asList("bob@email.com", "juan@email.com")),
            new Meeting("28/07/2020", "15h00", "1", "Finalisation du plan", getRandomColor(),
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com", "bob@email.com", "juan@email.com")),
            new Meeting("01/08/2020", "08h40", "4", "Choix de la nouvelle application", getRandomColor(),
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com")),
            new Meeting("01/08/2020", "14h00", "1", "Mockup de la chartre graphique", getRandomColor(),
                    Arrays.asList("bob@email.com", "juan@email.com")),
            new Meeting("16/08/2020", "15h00", "1", "Finalisation du plan", getRandomColor(),
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com", "bob@email.com", "juan@email.com"))
    );

    public static List<Meeting> generateMeetings(){return new ArrayList<>(DUMMY_MEETINGS);}

    public static int getRandomColor()
    {
        return Color.HSVToColor(new float[]{ r.nextFloat()*360, 0.25f, 0.75f } ); // use HSV to get pastel color (sat 25%, value 75%, so only varying HUE
    }
}
