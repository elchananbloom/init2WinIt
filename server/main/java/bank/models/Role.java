package bank.models;

public enum Role {
    USER("USER"),
    ADMIN("ADMIN");



    private String value;



    Role(String value){
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Role findByValue(String value){
        for(Role role: Role.values()){
            if(role.getValue().equalsIgnoreCase(value)){
                return role;
            }
        }
        String message = String.format("No role with value: %s ", value);
        throw new RuntimeException(message);
    }
}
