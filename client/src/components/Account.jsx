import Page from "./Page";
import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";

function Account() {
  const [transactions, setTransactions] = useState([]);
  const [account, setAccount] = useState();
  const { id } = useParams();

  const urlAccount = "http://localhost:8080/api/account/";
  const urlTransactions = "http://localhost:8080/api/transaction?accountId=";

  useEffect(() => {
    fetch(urlAccount + id)
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
    fetch(urlTransactions + id)
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
        {account && <>
      <div className="container">
        <h1>Here is Account</h1>
        <div id="top-half">
          <div className="balance">
            <h2>Current Balance</h2>
            <h3>{account.balance}</h3>
          </div>

          <div id="graph" name="graph">
            {/* insert graph here if wanted */}
          </div>
        </div>
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
      </>
      }
    </Page>
  );
}

export default Account;
