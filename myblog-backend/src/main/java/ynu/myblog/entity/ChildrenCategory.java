package ynu.myblog.entity;

public class ChildrenCategory {

    private int value;
    private String label;
    private int pid;

    public ChildrenCategory(int value, String label, int pid) {
        this.value = value;
        this.label = label;
        this.pid = pid;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
