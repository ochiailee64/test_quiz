package com.example.csaper6.test_quiz;

/**
 * Created by ANlEE on 9/15/16.
 */
public class Question
{
    private int questionId;
    private boolean isTrue;


    public Question(int questionId, boolean isTrue) {
        this.questionId = questionId;
        this.isTrue = isTrue;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }

    public boolean setAnswer(Boolean t) {
        return isTrue == t;
    }

    public boolean checkAnswer(Boolean answerGiven){
        return answerGiven == isTrue;
    }



}
