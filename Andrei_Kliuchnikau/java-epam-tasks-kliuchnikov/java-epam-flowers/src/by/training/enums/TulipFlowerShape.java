package by.training.enums;

public enum TulipFlowerShape {
    CUPPED("чашевидная"),
    TERRY("махровая"),
    GOBLET("бокаловидная"),
    FRINGED("бахромчатая"),
    STAR_SHAPED("звездчатая"),
    OVAL("овальная");

    private final String cyrillicForm;

    TulipFlowerShape(String cyrillicForm) {
        this.cyrillicForm = cyrillicForm;
    }

    public String getCyrillicForm() {
        return cyrillicForm;
    }
}
