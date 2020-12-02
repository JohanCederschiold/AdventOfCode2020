package second;

public class Code {

    private Integer lowFigure;
    private Integer highFigure;
    private CharSequence letter;
    private String code;

    public Integer getLowFigure() {
        return lowFigure;
    }

    public void setLowFigure(Integer lowFigure) {
        this.lowFigure = lowFigure;
    }

    public Integer getHighFigure() {
        return highFigure;
    }

    public void setHighFigure(Integer highFigure) {
        this.highFigure = highFigure;
    }

    public CharSequence getLetter() {
        return letter;
    }

    public void setLetter(CharSequence letter) {
        this.letter = letter;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Code{" +
                "minimumAmount=" + lowFigure +
                ", maximumAmount=" + highFigure +
                ", letter=" + letter +
                ", code='" + code + '\'' +
                '}';
    }
}
