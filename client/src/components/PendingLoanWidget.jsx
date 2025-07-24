import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import TokenContext from "../contexts/TokenContext";

const url = 'http://localhost:8080/api/';


const PendingLoanWidget = ({ loan, handleAccept, handleReject }) => {
    const [user, setUser] = useState(null);
    const navigate = useNavigate();
    const {token} = useContext(TokenContext);


    useEffect(() => {
        const options = {
            method: 'GET',
            headers: {
                "Authorization": `Bearer ${token}`
            }
        }
        fetch(`${url}user/${loan.userId}`, options)
            .then(res => {
                if (res.status === 200) {
                    return res.json();
                }
                if (res.status === 403) {
                    navigate('/login');
                }
            })
            .then(data => {
                setUser(data);
            });
    }, [])

    return (
        <>
            {user && <div className="card p-2">
                <p>User: {user.firstName} {user.lastName}</p>
                <p>Due Date: {loan.dateDue}</p>
                <p>Interest: {loan.interest}%</p>
                <p>Asked Date: {loan.createdAt}</p>
                <p>Amount: {loan.initialAmount}</p>
                <p>User Balance: Not Implemented</p>
                <p>Amount + Interest: {loan.balance}</p>
                <button onClick={() => handleAccept(loan.loanId)} className="btn btn-primary mb-1">Approve</button>
                <button onClick={() => handleReject(loan.loanId)} className="btn btn-secondary">Reject</button>
            </div>}
        </>
    );
}

export default PendingLoanWidget;