package sixteen;

public class TicketField {

    private String fieldName;
    private int lowSpanBottomValue;
    private int lowSpanTopValue;
    private int highSpanBottomValue;
    private int highSpanTopValue;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public int getLowSpanBottomValue() {
        return lowSpanBottomValue;
    }

    public void setLowSpanBottomValue(int lowSpanBottomValue) {
        this.lowSpanBottomValue = lowSpanBottomValue;
    }

    public int getLowSpanTopValue() {
        return lowSpanTopValue;
    }

    public void setLowSpanTopValue(int lowSpanTopValue) {
        this.lowSpanTopValue = lowSpanTopValue;
    }

    public int getHighSpanBottomValue() {
        return highSpanBottomValue;
    }

    public void setHighSpanBottomValue(int highSpanBottomValue) {
        this.highSpanBottomValue = highSpanBottomValue;
    }

    public int getHighSpanTopValue() {
        return highSpanTopValue;
    }

    public void setHighSpanTopValue(int highSpanTopValue) {
        this.highSpanTopValue = highSpanTopValue;
    }

    @Override
    public String toString() {
        return String.format("%s: %d - %d and %d - %d", fieldName, lowSpanBottomValue, lowSpanTopValue, highSpanBottomValue, highSpanTopValue);
    }
}
