import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import TokenContext from "../contexts/TokenContext";
import TotalBalanceAcrossAccounts from "./statistics/TotalBalanceAcrossAccounts";

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

        {user && loan && (
  <div className="card p-3 shadow-sm mb-3">
    <div className="card-body">
      <h5 className="card-title mb-3">Loan Review</h5>

      <div className="row">
        <div className="col-md-6 mb-2">
          <strong>User:</strong> {user.firstName} {user.lastName}
        </div>
        <div className="col-md-6 mb-2">
          <strong>Due Date:</strong> {new Date(loan.dateDue).toLocaleDateString()}
        </div>

        <div className="col-md-6 mb-2">
          <strong>Interest:</strong> {loan.flatInterest}%
        </div>
        <div className="col-md-6 mb-2">
          <strong>Asked Date:</strong> {new Date(loan.createdAt).toLocaleDateString()}
        </div>

        <div className="col-md-6 mb-2">
          <strong>Initial Amount:</strong> ${parseFloat(loan.initialAmount).toFixed(2)}
        </div>
        <div className="col-md-6 mb-2">
          <strong>User Balance:</strong> <TotalBalanceAcrossAccounts userId={loan.userId} />
        </div>

        <div className="col-md-6 mb-3">
          <strong>Total + Interest:</strong> ${parseFloat(loan.balance).toFixed(2)}
        </div>
      </div>

      <div className="d-flex">
        <button
          onClick={() => handleAccept(loan.loanId)}
          className="btn btn-success mr-2"
        >
          Approve
        </button>
        <button
          onClick={() => handleReject(loan.loanId)}
          className="btn btn-danger"
        >
          Reject
        </button>
      </div>
    </div>
  </div>
)}


        </>
    );
}

export default PendingLoanWidget;