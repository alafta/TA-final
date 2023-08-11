package com.example.projectfrontend.classUnderstanding;

public class ItemModel {

    int icon, title;
    Type type;

    public enum Type {
        ABOUT, BAM, PARAMETER, PRINCIPLE, MATRIX
    }

    public ItemModel(int icon, int title, Type type) {
        this.icon = icon;
        this.title = title;
        this.type = type;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
