package com.example.mareu.dummylist;

import android.graphics.Color;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class DummyMeetingList {

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting("19/07/2020", "08:40:00", "4", "Choix de la nouvelle application", getRandomColor(),
                        Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com")),
            new Meeting("21/07/2020", "14:00:00", "1", "Mockup de la chartre graphique", getRandomColor(),
                    Arrays.asList("bob@email.com", "juan@email.com")),
            new Meeting("21/07/2020", "15:00:00", "1", "Finalisation du plan", getRandomColor(),
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com", "bob@email.com", "juan@email.com")),
            new Meeting("22/07/2020", "08:40:00", "4", "Choix de la nouvelle application", getRandomColor(),
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com")),
            new Meeting("22/07/2020", "14:00:00", "1", "Mockup de la chartre graphique", getRandomColor(),
                    Arrays.asList("bob@email.com", "juan@email.com")),
            new Meeting("23/07/2020", "15:00:00", "1", "Finalisation du plan", getRandomColor(),
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com", "bob@email.com", "juan@email.com")),
            new Meeting("28/07/2020", "08:40:00", "4", "Choix de la nouvelle application", getRandomColor(),
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com")),
            new Meeting("28/07/2020", "14:00:00", "1", "Mockup de la chartre graphique", getRandomColor(),
                    Arrays.asList("bob@email.com", "juan@email.com")),
            new Meeting("28/07/2020", "15:00:00", "1", "Finalisation du plan", getRandomColor(),
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com", "bob@email.com", "juan@email.com")),
            new Meeting("01/08/2020", "08:40:00", "4", "Choix de la nouvelle application", getRandomColor(),
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com")),
            new Meeting("01/08/2020", "14:00:00", "1", "Mockup de la chartre graphique", getRandomColor(),
                    Arrays.asList("bob@email.com", "juan@email.com")),
            new Meeting("16/08/2020", "15:00:00", "1", "Finalisation du plan", getRandomColor(),
                    Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com", "bob@email.com", "juan@email.com"))
    );

    public static List<Meeting> generateMeetings(){return new ArrayList<>(DUMMY_MEETINGS);}

    private static int getRandomColor()
    {
        Random r = new Random();
        return Color.HSVToColor(new float[]{ r.nextFloat(), 0.75f, 0.75f } );
    }
}
