package spoon.quantifiers;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "questions")
public class Question {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String qType;

    private String question;

    private String answer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQType() {
        return qType;
    }

    public void setQType(String qType) {
        this.qType = qType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
