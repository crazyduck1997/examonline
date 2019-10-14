package com.qf.examonline.entity;

public class BooleanQuestions {
    private Integer booId;

    private String booDesc;

    private String booAnswer;

    public Integer getBooId() {
        return booId;
    }

    public void setBooId(Integer booId) {
        this.booId = booId;
    }

    public String getBooDesc() {
        return booDesc;
    }

    public void setBooDesc(String booDesc) {
        this.booDesc = booDesc == null ? null : booDesc.trim();
    }

    public String getBooAnswer() {
        return booAnswer;
    }

    public void setBooAnswer(String booAnswer) {
        this.booAnswer = booAnswer == null ? null : booAnswer.trim();
    }
}