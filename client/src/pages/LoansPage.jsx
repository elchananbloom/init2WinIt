import { useContext, useEffect, useState } from "react";
import Page from "../components/Page"
import { useNavigate } from "react-router-dom";
import PendingLoanWidget from "../components/PendingLoanWidget";
import AdminLoanWidget from "../components/AdminLoanWidget";
import TokenContext from "../contexts/TokenContext";

const url = 'http://localhost:8080/api/';


const LoansPage = ({fetchLoans}) => {
    const [loans, setLoans] = useState([]);
    const navigate = useNavigate();
    const { token } = useContext(TokenContext);

    const handleAccept = (loanId) => {
        const loan = loans.find(l => l.loanId === loanId);
        loan.status = 'APPROVED';
        const options = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify(loan)
        };
        fetch(`${url}loan/${loanId}`, options)
            .then(res => {
                if (res.status === 204) {
                    return loan;
                }
                if (res.status === 400) {
                    return res.json();
                }
                if (res.status === 403) {
                    navigate('/login');
                }
            })
            .then(data => {
                if (data.loanId) {
                    fetchLoans();
                    navigate('/admin/loans');
                }
            })
    }

    const handleReject = (loanId) => {
        const loan = loans.find(l => l.loanId === loanId);
        loan.status = 'REJECTED';
        const options = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify(loan)
        };
        fetch(`${url}loan/${loanId}`, options)
            .then(res => {
                if (res.status === 204) {
                    return loan;
                }
                if (res.status === 400) {
                    return res.json();
                }
                if (res.status === 403) {
                    navigate('/login');
                }
            })
            .then(data => {
                if (data.loanId) {
                    fetchLoans();
                    navigate('/admin/loans');
                }
            })
    }

    useEffect(() => {
        const options = {
            method: 'GET',
            headers: {
                "Authorization": `Bearer ${token}`
            }
        }
        fetch(`${url}loan`, options)
            .then(res => {
                if (res.status === 200) {
                    return res.json();
                }
                if (res.status === 403) {
                    navigate('/login');
                }
            })
            .then(data => {
                console.log(data);
                setLoans(data);
            });
    }, [])
    return (<Page>
        {loans.map(loan => {
            if (loan.status === 'IN_PROGRESS') {
                return <PendingLoanWidget loan={loan} handleAccept={handleAccept} handleReject={handleReject} />
            }
            else {
                return <AdminLoanWidget loan={loan} />
            }
        })}
    </Page>);
}

export default LoansPage;