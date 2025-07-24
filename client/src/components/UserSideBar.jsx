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
        fetchAccounts();
    }, []);

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
            <Link to={`/user/${appUser.userId}`} className="rounded-circle">
                <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" fill="white" class="bi bi-person-circle" viewBox="0 0 16 16">
                    <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0" />
                    <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8m8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1" />
                </svg>
            </Link>

            Accounts
            {accounts && accounts.map(acc => {
                return (
                    <Link to={`/account/${acc.accountId}`} className="btn btn-light mb-3 text-left">{acc.accountType}: {acc.accountNumber}</Link>
                )
            })}
            Loans
            {loans && loans.map(loan => {
                console.log(loan)
                return (
                    <>{loan.status !== 'REJECTED' && <><Link to={`/loan/${loan.loanId}`}
                        className={`btn btn-light mb-3 text-left ${loan.status === 'IN_PROGRESS' ? 'disabled' : ''}`}
                        aria-disabled={loan.status === 'IN_PROGRESS'}
                        tabIndex={loan.status === 'IN_PROGRESS' ? -1 : 0}>
                        {loan.loanType.loanTypeName}
                    </Link>
                        {loan.status === 'IN_PROGRESS' && <button onClick={() => handleDelete(loan.loanId)}>Delete</button>}
                    </>
                    }
                    </>
                )
            })}
            <Link to={`/user/${appUser.userId}/loan/new`} className="btn btn-light mt-5 mb-3 text-left">Add Loan</Link>

            <button onClick={() => handleShowModal(true)} className="btn btn-light mb-3 text-left">Add Account</button>

            {showModal && <form id="form" className="form-col" onSubmit={handleSubmit}>

                <fieldset className="form-group">
                    <label htmlFor="loanType">Type</label>
                    <select
                        id="loanType"
                        name="loanType"
                        className="form-control"
                        value={accountType}
                        onChange={handleChange}
                    >
                        <option>CHECKING</option>
                        <option>SAVINGS</option>

                    </select>
                </fieldset>
                <button className=" m-1 btn btn-primary" id="submit-form" type="submit">
                    Create an Account
                </button>
                <button
                    onClick={() => setShowModal(false)}
                    className="m-1 btn btn-danger"
                    type="button"
                >
                    Cancel
                </button>
            </form>}
        </>
    );
}

export default UserSideBar;