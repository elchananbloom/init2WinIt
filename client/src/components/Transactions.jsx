

const Transactions = ({transactions}) => {
    return (
        <table className="table table-dark table-striped table-hover">
            <thead className="thead-light">
                <tr>
                    <th> Category</th>
                    <th> Date</th>
                    <th> Amount</th>
                </tr>
            </thead>
            <tbody>
                {transactions.map((transaction) => (
                    <tr>
                        <td>{transaction.transactionCategory.transactionCategoryName}</td>
                        <td>{transaction.transactionDate}</td>
                        <td>{transaction.amount}</td>
                    </tr>
                ))}
            </tbody>
        </table>
    );
}

export default Transactions;