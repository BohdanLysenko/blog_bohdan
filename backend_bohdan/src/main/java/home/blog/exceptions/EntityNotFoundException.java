package home.blog.exceptions;

import java.util.NoSuchElementException;

public class EntityNotFoundException extends NoSuchElementException {
    public EntityNotFoundException(String s) {
        super("The {} wasn't found" + s);
    }
}
