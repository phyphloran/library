package models;


import lombok.Getter;


@Getter
public class File {

    private int value;

    public File() {
        this.value = 0;
    }

    public void increment() {
        value++;
    }
}