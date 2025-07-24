import Page from "../components/Page";
import Modal from "../components/Modal";
import { useContext, useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import TransactionFormModal from "../components/TransactionFormModal";
import Transactions from "../components/Transactions";
import TokenContext from "../contexts/TokenContext";
import UserContext from "../contexts/UserContext";
import AmountSpentByCategoryForAccount from "../components/statistics/AmountSpentByCategoryForAccount";

function AccountPage() {
  const [transactions, setTransactions] = useState([]);
  const [type, setType] = useState([]);
  const [account, setAccount] = useState();
  const { id } = useParams();
  const [showModal1, setShowModal1] = useState(false);
  const { token } = useContext(TokenContext);
  const navigate = useNavigate();
  const { appUser } = useContext(UserContext);

  const handleShowModal1 = (show, type) => {
    setType(type);
    setShowModal1(show);
  };


  const urlAccount = "http://localhost:8080/api/account/";
  const urlTransactions = "http://localhost:8080/api/transaction?accountId=";

  useEffect(() => {
    fetchAccount();
  }, [id]);

  const fetchAccount = () => {
    const options = {
      method: 'GET',
      headers: {
        "Authorization": `Bearer ${token}`
      }
    }
    fetch(urlAccount + id, options)
      .then((response) => {
        if (response.status === 200) {
          return response.json();
        } else if (response.status === 403) {
          navigate('/login')
        } else {
          return Promise.reject(`Unexpected Status Code: ${response.status}`);
        }
      })
      .then((data) => {
        if (data.accountId && data.userId === appUser.userId) {
          setAccount(data)
        } else {
          navigate('/notfound')
        }
      })
      .catch(console.log);
  }

  function fetchTransactions() {
    const options = {
      method: 'GET',
      headers: {
        "Authorization": `Bearer ${token}`
      }
    }
    fetch(urlTransactions + id, options)
      .then((response) => {
        if (response.status === 200) {
          return response.json();
        } else if (response.status === 403) {
          navigate('/login');
        } else {
          return Promise.reject(`Unexpected Status Code: ${response.status}`);
        }
      })
      .then((data) => setTransactions(data))
      .catch(console.log);

  }
  useEffect(() => {
    fetchTransactions()
  }, [id]);

  const handleFetch = () => {
    fetchAccount();
    fetchTransactions();
  }

  return (
    <Page>
      {account && <>
        <div className="container">
          <h1>Account {account.accountNumber}</h1>
          <div id="top-half">
            <div className="balance">
              <h2>Current Balance</h2>
              <h3>{account.balance}</h3>
              <AmountSpentByCategoryForAccount accountId={id}/>
            </div>

            <div id="graph" name="graph">
              {/* insert graph here if wanted */}
            </div>
          </div>
          <Transactions transactions={transactions} />
        </div>
        <div>
          <button onClick={() => handleShowModal1(true, "DEPOSIT")} className="btn btn-outline-dark mr-2">Deposit</button>
          <button onClick={() => handleShowModal1(true, "WITHDRAWAL")} className="btn btn-outline-dark mr-2">Withdrawal</button>
          <Modal
            show={showModal1}
            onClose={() => setShowModal1(false)}
            title={type}
          >
            <TransactionFormModal loanTrueAccountFalse={false} id={id} handleShowModal={handleShowModal1} transactionType={type} handleFetch={handleFetch}></TransactionFormModal>
          </Modal>

        </div>
      </>
      }
    </Page>
  );
}

export default AccountPage;
