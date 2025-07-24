import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import TokenContext from "../contexts/TokenContext";

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
            {user && <div className="card p-2">
                <p>User: {user.firstName} {user.lastName}</p>
                <p>Due Date: {loan.dateDue}</p>
                <p>Interest: {loan.interest}%</p>
                <p>Asked Date: {loan.createdAt}</p>
                <p>Loan Balance: {loan.balance}</p>
                <p>User Balance: Not Implemented</p>
                <p>Status: {loan.status}</p>
            </div>}
        </>
    )
}

export default AdminLoanWidget;