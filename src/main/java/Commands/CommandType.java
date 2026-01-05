package main.java.Commands;

public enum CommandType {
    EXPENSE("expense"),
    LIST_EXPENSE("list-expense"),
    INCOME("income"),
    LIST_INCOME("list-income"),
    BUDGET("budget"),
    LIST_TOTAL("list-total"),
    EXPORT("export"),
    BYE("bye");

    private final String keyword;

    CommandType(String keyword){
        this.keyword = keyword;
    }

    public String getKeyword(){
        return keyword;
    }

    public static CommandType fromString(String input) {
        for (CommandType type : values()) {
            if (type.keyword.equalsIgnoreCase(input)) {
                return type;
            }
        }
        return null;
    }
}
