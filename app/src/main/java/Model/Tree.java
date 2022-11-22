package Model;

import java.io.Serializable;

public class Tree implements Serializable {
    private int id=0;
    private String name;
    private  String scienceName;
    private String attribute;
    private String color;
    private String img;

    public Tree(int id, String name, String scienceName, String attribute, String color, String img) {
        this.id = id;
        this.name = name;
        this.scienceName = scienceName;
        this.attribute = attribute;
        this.color = color;
        this.img = img;
    }

    public Tree(String name, String scienceName, String attribute, String color, String img) {

        this.name = name;
        this.scienceName = scienceName;
        this.attribute = attribute;
        this.color = color;
        this.img = img;
    }

    public Tree() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScienceName() {
        return scienceName;
    }

    public void setScienceName(String scienceName) {
        this.scienceName = scienceName;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
