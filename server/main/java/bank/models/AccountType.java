package bank.models;

public enum AccountType {
    CHECKING("Checking"),
    SAVINGS("Savings");

    private String value;

    AccountType(String value) {
        this.value = value;
    }
    public String getValue() {
        return this.value;
    }
    
    public static AccountType findByValue(String value){
        for(AccountType accountType: AccountType.values()){
            if(accountType.getValue().equalsIgnoreCase(value)){
                return accountType;
            }
        }
        String message = String.format("No accountType with value: %s ", value);
        throw new RuntimeException(message);
    }
    

}
