import { useNavigate, useParams } from "react-router-dom";
import Page from "../components/Page"
import { useContext, useEffect, useState } from "react";
import Transactions from "../components/Transactions";
import TransactionFormModal from "../components/TransactionFormModal";
import Modal from "../components/Modal";
import TokenContext from "../contexts/TokenContext";
import LoanBalanceOverTime from "../components/statistics/LoanBalanceOverTime";
import UserContext from "../contexts/UserContext";

const url = "http://localhost:8080/api";


const LoanPage = () => {
    const { id } = useParams();
    const [transactions, setTransactions] = useState([]);
    const [loan, setLoan] = useState(null);
    const [showModal, setShowModal] = useState(false);
    const { token } = useContext(TokenContext);
    const navigate = useNavigate();
    const { appUser } = useContext(UserContext);

    const handleShowModal = (show) => {
        console.log('here')
        setShowModal(show);
    };

    const fetchLoan = () => {
        const options = {
            method: 'GET',
            headers: {
                "Authorization": `Bearer ${token}`
            }
        }
        fetch(`${url}/loan/${id}`, options)
            .then((response) => {
                if (response.status === 200) {
                    return response.json();
                } else if (response.status === 403) {
                    navigate('/login');
                } else {
                    return Promise.reject(`Unexpected Status Code: ${response.status}`);
                }
            })
            .then((data) => {
                if (data.loanId && data.userId === appUser.userId) {
                    setLoan(data)
                } else {
                    navigate('/notfound');
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
        fetch(`${url}/transaction?loanId=${id}`, options)
            .then((response) => {
                if (response.status === 200) {
                    return response.json();
                } else if (response.status === 403) {
                    navigate('/login');
                } else {
                    return Promise.reject(`Unexpected Status Code: ${response.status}`);
                }
            })
            .then((data) => {
                setTransactions(data)
            })
            .catch(console.log);

    }

    useEffect(() => {
        handleFetch();
    }, [id])

    const handleFetch = () => {
        fetchLoan();
        fetchTransactions();
    }
    return (
        <Page>
            {loan && (
                <>
                    <div className="container">
                        <div className="row align-items-center mb-4">
                            <div className="col-md-6">
                                <h1>{loan.loanType.loanTypeName}</h1>
                                <div>
                                    <p className="mb-1"><strong>Due Date:</strong> {new Date(loan.dateDue).toLocaleDateString()}</p>
                                    <p className="mb-1"><strong>Balance:</strong> ${parseFloat(loan.balance).toFixed(2)}</p>
                                </div>
                            </div>
                            <div className="col-md-6 text-md-right mt-3 mt-md-0">
                                <LoanBalanceOverTime loanId={id} loanBalance={loan.balance} transactionsCount={transactions.length}/>
                            </div>
                        </div>

                        <Transactions transactions={transactions} />

                        <div className="floating-button shadow d-flex">
                            <button onClick={() => handleShowModal(true)} className="btn btn-outline-dark">
                                Deposit
                            </button>
                        </div>
                    </div>

                    <Modal
                        show={showModal}
                        onClose={() => setShowModal(false)}
                        title="DEPOSIT"
                    >
                        <TransactionFormModal
                            loanTrueAccountFalse={true}
                            id={loan.loanId}
                            handleShowModal={handleShowModal}
                            transactionType="DEPOSIT"
                            handleFetch={handleFetch}
                        />
                    </Modal>
                </>
            )}

        </Page>
    );
}

export default LoanPage;