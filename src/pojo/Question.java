package pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class Question {
    private String id;
    private int type;
    private String topic;
    private String answer;
    private int score;
    private String classify;
//    @JSONField(name="times")
    private String timeStr;

    private String optiona;
    private String optionb;
    private String optionc;
    private String optiond;

    private String changeTestFlag;
    public Question() {
    }

    public Question(int type, String topic, String classify, String timeStr) {
        this.type = type;
        this.topic = topic;
        this.classify = classify;
        this.timeStr = timeStr;
    }

    public Question(String id, int type, String topic, String classify, String timeStr) {
        this.id = id;
        this.type = type;
        this.topic = topic;
        this.classify = classify;
        this.timeStr = timeStr;
    }

    public String getChangeTestFlag() {
        return changeTestFlag;
    }

    public void setChangeTestFlag(String changeTestFlag) {
        this.changeTestFlag = changeTestFlag;
    }

    public Question(int type, String topic, String answer, int score, String classify, String optiona, String optionb, String optionc, String optiond) {
        this.type = type;
        this.topic = topic;
        this.answer = answer;
        this.score = score;
        this.classify = classify;
        this.optiona = optiona;
        this.optionb = optionb;
        this.optionc = optionc;
        this.optiond = optiond;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public String getOptiona() {
        return optiona;
    }

    public void setOptiona(String optiona) {
        this.optiona = optiona;
    }

    public String getOptionb() {
        return optionb;
    }

    public void setOptionb(String optionb) {
        this.optionb = optionb;
    }

    public String getOptionc() {
        return optionc;
    }

    public void setOptionc(String optionc) {
        this.optionc = optionc;
    }

    public String getOptiond() {
        return optiond;
    }

    public void setOptiond(String optiond) {
        this.optiond = optiond;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    @Override
    public String toString() {
        return "Question{" +
                "type=" + type +
                ", topic='" + topic + '\'' +
                ", answer='" + answer + '\'' +
                ", score=" + score +
                ", classify='" + classify + '\'' +
                ", timeStr='" + timeStr + '\'' +
                ", optiona='" + optiona + '\'' +
                ", optionb='" + optionb + '\'' +
                ", optionc='" + optionc + '\'' +
                ", optiond='" + optiond + '\'' +
                '}';
    }
}
