import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import TokenContext from "../contexts/TokenContext";
import TotalBalanceAcrossAccounts from "./statistics/TotalBalanceAcrossAccounts";

const url = 'http://localhost:8080/api/';


const AdminLoanWidget = ({ loan }) => {
    const [user, setUser] = useState(null);
    const navigate = useNavigate();
    const { token } = useContext(TokenContext);

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
  <div
    className={`card p-3 shadow-sm mb-3 ${
      loan.status === 'APPROVED'
        ? 'bg-success text-white'
        : loan.status === 'REJECTED'
        ? 'bg-danger text-white'
        : ''
    }`}
  >
    <div className="card-body">
      <h5 className="card-title mb-3">Loan Details</h5>

      <div className="row">
        <div className="col-md-6 mb-2">
          <strong>User:</strong> {user.firstName} {user.lastName}
        </div>
        <div className="col-md-6 mb-2">
          <strong>Due Date:</strong> {new Date(loan.dateDue).toLocaleDateString()}
        </div>
        <div className="col-md-6 mb-2">
          <strong>Type: </strong> {loan.loanType.loanTypeName}
        </div>
        <div className="col-md-6 mb-2">
          <strong>Asked Date:</strong> {new Date(loan.createdAt).toLocaleDateString()}
        </div>
        <div className="col-md-6 mb-2"></div>

        <div className="col-md-6 mb-2">
          <strong>Loan Balance:</strong> ${parseFloat(loan.balance).toFixed(2)}
        </div>
        <div className="col-md-6 mb-2">
          <strong>User Balance:</strong> <TotalBalanceAcrossAccounts userId={loan.userId} />
        </div>
      </div>
    </div>
  </div>
)}

        </>
    )
}

export default AdminLoanWidget;