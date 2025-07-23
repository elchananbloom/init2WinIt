import Page from "./Page";
import Modal from "./Modal";
import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import TransactionFormModal from "./TransactionFormModal";

function Account() {
  const [transactions, setTransactions] = useState([]);
  const [loans, setLoans] = useState([]);
  const [account, setAccount] = useState();
  const { id } = useParams();
  const [showModal1, setShowModal1] = useState(false);
  const [showModal2, setShowModal2] = useState(false);  const [type, setType] = useState("");

  const handleShowModal1 = (show, type) => {
      setType(type);
      setShowModal1(show);
  };

  const handleShowModal2  = (show) => {
    setShowModal2(show);
  }
  const urlAccount = "http://localhost:8080/api/account/";
  const urlTransactions = "http://localhost:8080/api/transaction?accountId=";
  const urlLoans = "http://localhost:8080/api/loan?userId="

  useEffect(() => {
    fetchAccount();
  }, []);

  const fetchAccount = () => {
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
  }

  function fetchTransactions(){
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
  
  }
  useEffect(() => {fetchTransactions()
    }, []);
  // function fetchLoans(){
  //     fetch(urlLoans + account.userId)
  //     .then((response) => {
  //       if (response.status === 200) {
  //         return response.json();
  //       } else {
  //         return Promise.reject(`Unexpected Status Code: ${response.status}`);
  //       }
  //     })
  //     .then((data) => {setLoans(data)
  //       console.log(data);
        
  //     })
  //     .catch(console.log);
  
  // }

  // useEffect(() => {fetchLoans()
  //   }, [account]);

  return (
    <Page>
        {account && <>
      <div className="container">
        <h1>Account {account.accountNumber}</h1>
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
        <button onClick={() => handleShowModal1(true, "DEPOSIT")} className="btn btn-outline-dark mr-2">Deposit</button>
        <button onClick={() => handleShowModal1(true, "WITHDRAWAL")} className="btn btn-outline-dark mr-2">Withdrawal</button>
        <button onClick={() => handleShowModal2(true)} className="btn btn-outline-dark mr-2">Request a Loan</button>
        <Modal
          show={showModal1}
          onClose={() => setShowModal1(false)}
          title={type}
        >
          <TransactionFormModal id={id} handleShowModal={handleShowModal1} transactionType={type} fetchTransactions={fetchTransactions} fetchAccount={fetchAccount}></TransactionFormModal>
        </Modal>
            <Modal
              show={showModal2}
              onClose={() => setShowModal2(false)}
              title={type}
            >

            </Modal>
      </div>
      </>
      }
    </Page>
  );
}

export default Account;
