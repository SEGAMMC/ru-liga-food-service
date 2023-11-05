package ru.liga.exception;

public class MenuItemNotFoundException extends RuntimeException {

    public MenuItemNotFoundException(long id) {
        super("Menu Item id = "+ id + " is not found in DataBase");
    }
}
