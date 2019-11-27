package udemy.ENUM;

public enum ColumnEnum {
    USERNAME("username"),
    PASSWORD("password"),
    USER_ID("id");

    private final String column;

    ColumnEnum(String column){
        this.column=column;
    }

    public String getColumn(){
        return this.column;
    }

}
