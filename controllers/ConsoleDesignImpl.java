package controllers;

public class ConsoleDesignImpl implements ConsoleDesign {

    @Override
    public void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Error clearing screen.");
        }
    }

    @Override
    public void setColor(String color) {
        switch (color.toLowerCase()) {
            case "red":
                System.out.print("\033[31m");
                break;
            case "green":
                System.out.print("\033[32m");
                break;
            case "yellow":
                System.out.print("\033[33m");
                break;
            case "blue":
                System.out.print("\033[34m");
                break;
            case "magenta":
                System.out.print("\033[35m");
                break;
            case "cyan":
                System.out.print("\033[36m");
                break;
            default:
                System.out.print("\033[0m");
        }
    }

    @Override
    public void resetColor() {
        System.out.print("\033[0m");
    }

    @Override
    public void blinkText(String text, int duration) {
        for (int i = 0; i < duration; i++) {
            System.out.print("\033[5m" + text + "\033[0m");
            clearScreen();
        }
    }

    @Override
    public void printLogo() {
        setColor("red");
        System.out.println("██╗  ██╗ █████╗  █████╗  ██████╗  █████╗ ██████╗  █████╗ ██╗   ██╗");
        System.out.println("██║ ██╔╝██╔══██╗██╔══██╗██╔════╝ ██╔══██╗██╔══██╗██╔══██╗╚██╗ ██╔╝");
        System.out.println("█████╔╝ ███████║███████║██║  ███╗███████║██████╔╝███████║ ╚████╔╝ ");
        System.out.println("██╔═██╗ ██╔══██║██╔══██║██║   ██║██╔══██║██╔═══╝ ██╔══██║  ╚██╔╝  ");
        System.out.println("██║  ██╗██║  ██║██║  ██║╚██████╔╝██║  ██║██║     ██║  ██║   ██║   ");
        System.out.println("╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝  ╚═╝   ╚═╝   ");
        resetColor();
    }
}
