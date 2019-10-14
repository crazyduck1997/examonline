package com.qf.examonline.entity;

public class sketchQuestions {
    private Integer skeId;

    private String skeAnswer;

    private String skeDesc;

    public Integer getSkeId() {
        return skeId;
    }

    public void setSkeId(Integer skeId) {
        this.skeId = skeId;
    }

    public String getSkeAnswer() {
        return skeAnswer;
    }

    public void setSkeAnswer(String skeAnswer) {
        this.skeAnswer = skeAnswer == null ? null : skeAnswer.trim();
    }

    public String getSkeDesc() {
        return skeDesc;
    }

    public void setSkeDesc(String skeDesc) {
        this.skeDesc = skeDesc == null ? null : skeDesc.trim();
    }
}