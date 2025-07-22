import Page from "./Page";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function Account() {
  const [transactions, setTransactions] = useState([]);
  const [account, setAccount] = useState();
  const urlAccount = "http://localhost:8080/api/account/:id";
  const urlTransactions = "http://localhost:8080/api/transaction?accountId=";

  useEffect(() => {
    fetch(urlAccount)
      .then((response) => {
        if (response.status === 200) {
          return response.json();
        } else {
          return Promise.reject(`Unexpected Status Code: ${response.status}`);
        }
      })
      .then((data) => setAccount(data))
      .catch(console.log);
  }, []);
  
  useEffect(() => {
    fetch(urlTransactions + account.accountId)
      .then((response) => {
        if (response.status === 200) {
          return response.json();
        } else {
          return Promise.reject(`Unexpected Status Code: ${response.status}`);
        }
      })
      .then((data) => setTransactions(data))
      .catch(console.log);
  }, []);

  return (
    <Page>
      <div className="container">
        <h1>Here is Account</h1>
        <div id="top-half">
          <div className="balance">
            <h2>Current Balance</h2>
            <h3>{account.getBalance}</h3>
          </div>

          <div id="graph" name="graph">
            {/* insert graph here if wanted */}
          </div>
        </div>
        <table className="table table-dark table-striped table-hover">
          <thead className="thead-light"> Transactions</thead>
          <tbody>
            {transactions.map((transaction) => (
              <tr>
                <td>{transaction.transactionCategory}</td>
                <td>{transaction.transactionDate}</td>
                <td>{transaction.amount}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <div>
        <Link type="button" to={"/deposit"}>
          Deposit
        </Link>
        <Link type="button" to={"/withdrawal"}>
          Withdrawal
        </Link>
        <Link type="button" to={"/loan/new"}>
          Request a Loan
        </Link>
      </div>
    </Page>
  );
}

export default Account;
