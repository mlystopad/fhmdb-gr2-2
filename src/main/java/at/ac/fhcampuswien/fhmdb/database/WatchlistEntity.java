package at.ac.fhcampuswien.fhmdb.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

// Datenbank-Tabelle

@DatabaseTable(tableName = "watchlist")
public class WatchlistEntity {
    @DatabaseField(id = true, generatedId = true) //zählt selbst hoch, sorgt dafür: k. Duplikate
    private long id;

    //ToDo: weitere Attribute/Felder

    //ToDo: Eigene Tabelle f Objekte, Listen & Verweis (od Umwandeln d Listen in String?)

}