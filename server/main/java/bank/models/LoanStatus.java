package bank.models;

public enum LoanStatus {
    APPROVED("APPROVED"),
    REJECTED("REJECTED"),
    IN_PROGRESS("IN-PROGRESS");

    private String value;



    LoanStatus(String value){
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static LoanStatus findByValue(String value){
        for(LoanStatus loanStatus: LoanStatus.values()){
            if(loanStatus.getValue().equalsIgnoreCase(value)){
                return loanStatus;
            }
        }
        String message = String.format("No loan status with value: %s ", value);
        throw new RuntimeException(message);
    }
}
