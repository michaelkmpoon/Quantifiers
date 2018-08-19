package spoon.quantifiers;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MyDao {

    @Insert
    void addQuestion(Question question);

    @Insert
    void addQuestions(Question... questions);

    @Query("SELECT * FROM questions")
    List<Question> getQuestions();

    @Query("SELECT * FROM questions WHERE qType = :type ORDER BY question ASC")
    List<Question> getQuestionsByType(String type);

    @Query("SELECT * FROM questions WHERE qType = :type ORDER BY RANDOM() LIMIT 1")
    Question getRandomQuestion(String type);

    @Query("SELECT id FROM questions WHERE qType = :type")
    List<Integer> getQuestionIdsByType(String type);

    @Query("SELECT * FROM questions WHERE id = :givenId")
    Question getQuestionById(int givenId);

    @Query("DELETE FROM questions")
    void deleteAll();
}
