import { useContext, useEffect, useState } from "react";
import UserContext from "../contexts/UserContext";
import { Link, useNavigate, useNavigation } from "react-router-dom";
import AddLoan from "./AddLoan";
import Modal from "./Modal";
import TokenContext from "../contexts/TokenContext";

const url = 'http://localhost:8080/api/';

const UserSideBar = ({ loans, fetchLoans }) => {
    const [accounts, setAccounts] = useState([]);
    // const [loans, setLoans] = useState([]);
    const { appUser, setAppUser } = useContext(UserContext);
    const { token } = useContext(TokenContext);
    const navigate = useNavigate();
    const [showModal, setShowModal] = useState(false);
    const [accountType, setAccountType] = useState('CHECKING');

    const handleShowModal = (show) => {
        setShowModal(show);
    };

    const fetchAccounts = () => {
        const options = {
            method: 'GET',
            headers: {
                'Access-Control': 'Allow-Origin',
                "Authorization": `Bearer ${token}`
            }
        }
        console.log(token)
        console.log('token')
        fetch(`${url}account?userId=${appUser.userId}`, options)
            .then(res => {
                if (res.status === 200) {
                    return res.json();
                }
                if (res.status === 403) {
                    navigate('/login');
                }
            })
            .then(data => {
                setAccounts(data);
            });

    }


    useEffect(() => {
        if (token) {
            setTimeout(() => {
                fetchAccounts();
                fetchLoans();
            }, 1000);
        }
    }, [token]);

    const handleChange = (event) => {
        setAccountType(event.target.value);
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        const account = {
            accountType: accountType,
            balance: 0,
            userId: appUser.userId
        };
        const init = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify(account),
        };
        fetch(`${url}account`, init)
            .then((response) => {
                if (response.status === 201 || response.status === 400) {
                    return response.json();
                } else if (response.status === 403) {
                    navigate('/login');
                } else {
                    return Promise.reject(`Unexpected Status Code: ${response.status}`);
                }
            })
            .then((data) => {
                if (data.accountId) {
                    setShowModal(false);
                    fetchAccounts();
                } else {

                }
            })
            .catch(console.log);

    }

    const deleteLoan = (loanId) => {
        const options = {
            method: 'DELETE',
            headers: {
                "Authorization": `Bearer ${token}`
            }
        };
        fetch(`${url}loan/${loanId}`, options)
            .then(response => {
                if (response.status === 204) {
                    fetchLoans();
                } else if (response.status === 403) {
                    navigate('/login');
                } else {
                    return Promise.reject(`Unexpected Error, Status Code: ${response.status}`);
                }
            })
            .catch(console.log);
    }

    const handleDelete = (loanId) => {
        if (window.confirm(`Are you sure you want to delete Loan: ${loanId}?`)) {
            deleteLoan(loanId);
        }
    }


    return (
        <>
            <Link to={`/user/${appUser.userId}`} className="rounded-circle float-left floating-avatar">
                <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" fill="white" className="bi bi-person-circle" viewBox="0 0 16 16">
                    <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0" />
                    <path fillRule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8m8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1" />
                </svg>
            </Link>

            {accounts && accounts.length > 0 && <div className="mt-5 d-flex flex-column"> <p className="mb-3 sidebar-text">Accounts</p>
                {accounts.map(acc => {
                    return (
                        <Link to={`/account/${acc.accountId}`} className="sidebar-button p-3 align-items-center flex-grow-1"><p className="sidebar-text">{acc.accountType}: {acc.accountNumber}</p></Link>
                    )
                })}</div>}
            {loans && loans.length > 0 && <div className="mt-3 d-flex flex-column"><p className="mb-3 sidebar-text">Loans</p>
                {loans.map(loan => {
                    console.log(loan)
                    return (
                        <div className="">{loan.status !== 'REJECTED' && <div className="align-items-center mb-3 d-flex">
                            <Link to={`/loan/${loan.loanId}`}
                                className={`sidebar-button mb-1 text-left flex-grow-1 d-flex p-3 ${loan.status === 'IN_PROGRESS' ? 'disabled btn' : ''}`}
                                aria-disabled={loan.status === 'IN_PROGRESS'}
                                tabIndex={loan.status === 'IN_PROGRESS' ? -1 : 0}>
                                <p className="sidebar-text text-left">{loan.loanType.loanTypeName}</p>
                            </Link>
                            {loan.status === 'IN_PROGRESS' &&
                                <div className="d-flex align-items-center mb-1 ml-2 justify-content-center" >
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="red" onClick={() => handleDelete(loan.loanId)} className="bi bi-trash3 cursor-pointer" viewBox="0 0 16 16">
                                        <path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5M11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47M8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5" />
                                    </svg>
                                </div>}
                        </div>
                        }
                        </div>
                    )
                })}</div>}
            <div className="d-flex flex-column mt-auto">
                <Link to={`/user/${appUser.userId}/loan/new`} className="sidebar-button p-3 align-items-center flex-grow-1"><p className="sidebar-text">Add Loan</p></Link>

                <Link onClick={() => handleShowModal(true)} className="sidebar-button p-3 align-items-center flex-grow-1"><p className="sidebar-text">Add Account</p></Link>
            </div>
            {showModal && (
                <div className="card sidebar-button p-3 align-items-center flex-grow-1">
                    <h5 className="card-title text-light">Create New Account</h5>
                    <form id="form" onSubmit={handleSubmit}>
                        <div className="form-group text-light">
                            <label htmlFor="loanType">Account Type</label>
                            <select
                                id="loanType"
                                name="loanType"
                                className="form-control"
                                value={accountType}
                                onChange={handleChange}
                                required
                            >
                                <option value="CHECKING">CHECKING</option>
                                <option value="SAVINGS">SAVINGS</option>
                            </select>
                        </div>

                        <div className="d-flex justify-content-between mt-3">
                            <button
                                className="btn btn-primary"
                                id="submit-form"
                                type="submit"
                            >
                                Create Account
                            </button>
                            <button
                                onClick={() => setShowModal(false)}
                                className="btn btn-secondary"
                                type="button"
                            >
                                Cancel
                            </button>
                        </div>
                    </form>
                </div>
            )}

        </>
    );
}

export default UserSideBar;