import { useContext, useEffect, useState } from "react";
import UserContext from "../contexts/UserContext";
import { Link, useNavigate, useNavigation } from "react-router-dom";

const url = 'http://localhost:8080/api/';

const UserSideBar = () => {
    const [accounts, setAccounts] = useState([]);
    const [loans, setLoans] = useState([]);
    const user = useContext(UserContext);
    const navigate = useNavigate();

    useEffect(() => {
        fetch(`${url}/account?userId=${user.userId}`)
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

        fetch(`${url}/loan?userId=${user.userId}`)
            .then(res => {
                if (res.status === 200) {
                    return res.json();
                }
                if (res.status === 403) {
                    navigate('/login');
                }
            })
            .then(data => {
                setLoans(data);
            });
    }, [])

    return (
        <>
            <Link to={`/user/${user.userId}`} className="btn btn-light mb-3 text-left">Account</Link>

            Accounts
            {accounts.map(acc => {
                return (
                    <Link to={`/account/${acc.accountId}`} className="btn btn-light mb-3 text-left">{acc.accountType}: {acc.accountNumber}</Link>
                )
            })}
            Loans
            {loans.map(loan => {
                return (
                    <Link to={`/loan/${loan.loanId}`} className="btn btn-light mb-3 text-left">{loan.loanType.loanTypeName}</Link>
                )
            })}
            <Link to={`/loan/new`} className="btn btn-light mt-5 mb-3 text-left">Add Loan</Link>
            <Link to={`/account/new`} className="btn btn-light mb-3 text-left">Add Account</Link>

        </>
    );
}

export default UserSideBar;