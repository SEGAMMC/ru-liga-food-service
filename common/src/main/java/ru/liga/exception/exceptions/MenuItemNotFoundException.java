package ru.liga.exception.exceptions;

public class MenuItemNotFoundException extends RuntimeException {

    public MenuItemNotFoundException(long id) {
        super("Menu Item id = "+ id + " is not found in DataBase");
    }
}
