package udemy.ENUM;

public enum  TableEnum {
    USER("users"),
    PROJECT("projects");

    private final String table;

    TableEnum(String table){
        this.table=table;
    }

    public String getTable(){
        return this.table;
    }
}
