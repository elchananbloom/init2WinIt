import { useContext, useEffect, useState } from "react";
import Page from "../components/Page"
import { useNavigate } from "react-router-dom";
import TokenContext from "../contexts/TokenContext";

const url = 'http://localhost:8080/api/';

const AdminAccountsPage = () => {
    const [accounts, setAccounts] = useState([]);
    const navigate = useNavigate();
    const { token } = useContext(TokenContext);

    useEffect(() => {
        const options = {
            method: 'GET',
            headers: {
                "Authorization": `Bearer ${token}`
            }
        }
        fetch(`${url}account`, options)
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
                setAccounts(data);
            });
    }, []);

    return (
        <Page>
           {accounts.map((acc) => (
  <div className="card p-3 m-2 shadow-sm" key={acc.accountId}>
    <div className="card-body p-2">
      <h5 className="card-title mb-2">{acc.accountType} Account</h5>
      <p className="mb-1"><strong>Account Number:</strong> {acc.accountNumber}</p>
      <p className="mb-1"><strong>Balance:</strong> ${parseFloat(acc.balance).toFixed(2)}</p>
      <p className="mb-0"><strong>Created:</strong> {new Date(acc.createdAt).toLocaleDateString()}</p>
    </div>
  </div>
))}


        </Page>
    );
}

export default AdminAccountsPage;