package controllers;

public interface ConsoleDesign {
    void clearScreen();
    void setColor(String color);
    void resetColor();
    void blinkText(String text, int duration);
    void printLogo();
}
