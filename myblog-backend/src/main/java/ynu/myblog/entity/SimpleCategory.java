package ynu.myblog.entity;

import java.util.ArrayList;
import java.util.List;

public class SimpleCategory {


    private int value;
    private String label;
    private int pid;
    private List<ChildrenCategory> children =new ArrayList<>();

    public SimpleCategory(int value, String label, int pid) {
        this.value = value;
        this.label = label;
        this.pid = pid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
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

    public List<ChildrenCategory> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenCategory> children) {
        this.children = children;
    }
}
