package ch.fhnw.mada.bonusaufgabe.helpers;

/**
 * Created by raphi on 14.05.14.
 */
public class TreeObject {

    private String text;
    private TreeObject parent;
    private TreeObject child1;
    private TreeObject child0;

    public TreeObject getParent() {
        return parent;
    }

    public TreeObject getChild1() {
        return child1;
    }

    public TreeObject getChild0() {
        return child0;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public void setParent(TreeObject parent)
    {
        this.parent = parent;
    }

    public void setChild1(TreeObject child1)
    {
        this.child1 = child1;
    }

    public void setChild0(TreeObject child0)
    {
        this.child0 = child0;
    }

    public String getText()
    {
        return text;
    }
}
