package com.example.mad;

public class QuizList {

    private String QuizNme;

    public QuizList(String name) {
        this.QuizNme = name;
    }

    public QuizList() {
    }

    public String getQuizName() {
        return QuizNme;
    }

    public void setQuizName(String quizName) {
        QuizNme = quizName;
    }
}
