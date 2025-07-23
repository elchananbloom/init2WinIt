import { useParams } from "react-router-dom";
import Page from "../components/Page"
import { useEffect, useState } from "react";
import Transactions from "../components/Transactions";
import TransactionFormModal from "../components/TransactionFormModal";
import Modal from "../components/Modal";

const url = "http://localhost:8080/api";


const LoanPage = () => {
    const { id } = useParams();
    const [transactions, setTransactions] = useState([]);
    const [loan, setLoan] = useState(null);
    const [showModal, setShowModal] = useState(false);

    const handleShowModal = (show) => {
        console.log('here')
        setShowModal(show);
    };

    const fetchLoan = () => {
        fetch(`${url}/loan/${id}`)
            .then((response) => {
                if (response.status === 200) {
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected Status Code: ${response.status}`);
                }
            })
            .then((data) => {
                if (data.loanId) {
                    setLoan(data)
                }
            })
            .catch(console.log);
    }

    function fetchTransactions() {
        fetch(`${url}/transaction?loanId=${id}`)
            .then((response) => {
                if (response.status === 200) {
                    return response.json();
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
    }, [])

    const handleFetch = () => {
        fetchLoan();
        fetchTransactions();
    }
    return (
        <Page>
            {loan && <>
                <div>
                    <p>
                        {loan.dateDue}
                    </p>
                    <p>

                        ${loan.balance}
                    </p>
                </div>
                <Transactions transactions={transactions} />
                <div>
                    <button onClick={() => handleShowModal(true)} className="btn btn-outline-dark mr-2">Deposit</button>
                    <Modal
                        show={showModal}
                        onClose={() => setShowModal(false)}
                        title={'DEPOSIT'}
                    >
                        <p></p>
                        <TransactionFormModal loanTrueAccountFalse={true} id={loan.loanId} handleShowModal={handleShowModal} transactionType={'DEPOSIT'} handleFetch={handleFetch}></TransactionFormModal>
                    </Modal>


                </div>
            </>}
        </Page>
    );
}

export default LoanPage;