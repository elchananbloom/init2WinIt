import { useEffect, useState } from "react";
import Page from "../components/Page"
import { useNavigate } from "react-router-dom";

const url = 'http://localhost:8080/api/';

const AdminAccountsPage = () => {
    const [accounts, setAccounts] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetch(`${url}account`)
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
            {accounts.map(acc => {
                return (
                    <div className="card p-2 m-2">
                        <p>Account Number: {acc.accountNumber}</p>
                        <p>Type: {acc.accountType}</p>
                        <p>Balance: ${acc.balance}</p>
                        <p>Created: {acc.createdAt}</p>
                    </div>
                )
            })}

        </Page>
    );
}

export default AdminAccountsPage;