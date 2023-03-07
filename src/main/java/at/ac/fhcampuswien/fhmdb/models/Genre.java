package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Genre{
    public static final String ACTION = "ACTION";
    public static final String ADVENTURE = "ADVENTURE";
    public static final String ANIMATION = "ANIMATION";
    public static final String BIOGRAPHY = "BIOGRAPHY";
    public static final String DRAMA = "DRAMA";
    public static final String COMEDY = "COMEDY";
    public static final String CRIME = "CRIME";
    public static final String DOCUMENTARY = "DOCUMENTARY";
    public static final String FAMILY = "FAMILY";
    public static final String FANTASY = "FANTASY";
    public static final String HISTORY = "HISTORY";
    public static final String HORROR = "HORROR";
    public static final String MUSICAL = "MUSICAL";
    public static final String MYSTERY = "MYSTERY";
    public static final String ROMANCE = "ROMANCE";
    public static final String SCIENCEFICTION = "SCIENCEFICTION";
    public static final String SPORT = "SPORT";
    public static final String THRILLER = "THRILLER";
    public static final String WAR = "WAR";
    public static final String WESTERN = "WESTERN";

    public static List<String> getGenresAsList(){
        return Arrays.asList("ACTION", "ADVENTURE", "ANIMATION", "BIOGRAPHY", "COMEDY",
                "CRIME", "DRAMA", "DOCUMENTARY", "FAMILY", "FANTASY", "HISTORY", "HORROR",
                "MUSICAL", "MYSTERY", "ROMANCE", "SCIENCE FICTION", "SPORT", "THRILLER", "WAR",
                "WESTERN");
    }
}