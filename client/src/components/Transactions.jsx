const Transactions = ({ transactions }) => {
    if (!transactions || transactions.length === 0) {
    return (
      <div className="alert alert-secondary text-center mt-3">
        No transactions found.
      </div>
    );
  }
  return (
    <ul className="list-group mb-3">
      {transactions.map((transaction) => {
        const categoryName = transaction.type;
        const isDeposit = categoryName === "DEPOSIT";
        const isWithdrawal = categoryName === "WITHDRAWAL";

        return (
          <li
            key={transaction.transactionId}
            className="list-group-item d-flex m-1 justify-content-between align-items-center"
          >
            <div>
              <div><strong>Category:</strong> {transaction.transactionCategory.transactionCategoryName}</div>
              <div><small>{new Date(transaction.transactionDate).toLocaleDateString()}</small></div>
              {transaction.description && (
                <div className="text-muted"><small>{transaction.description}</small></div>
              )}
            </div>
            <span
              className={
                isDeposit ? "text-success font-weight-bold" :
                isWithdrawal ? "text-danger font-weight-bold" : ""
              }
            >
              ${parseFloat(transaction.amount).toFixed(2)}
            </span>
          </li>
        );
      })}
    </ul>
  );
}

export default Transactions;
