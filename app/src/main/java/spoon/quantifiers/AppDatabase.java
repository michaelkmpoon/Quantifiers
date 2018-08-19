package spoon.quantifiers;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {Question.class}, exportSchema = false, version = 7)
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase INSTANCE;

    public abstract MyDao myDao();

    public static AppDatabase getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "questionsdb").fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }

    private static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class,
                "questions_db").build();
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
