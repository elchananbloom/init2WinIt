import { useEffect, useState, useContext } from 'react';
import UserContext from "../../contexts/UserContext";
import TokenContext from '../../contexts/TokenContext';
import { useNavigate } from 'react-router-dom';



function    TotalBalanceAcrossAccounts({ userId }) {
    const url = `http://localhost:8080/api/statistics/user/account_total/${userId}`;
    const [totalBalanceAcrossAccounts, setTotalBalanceAcrossAccounts] = useState(0.00);
    const { token } = useContext(TokenContext);
    const navigate = useNavigate();

    useEffect(() => {
        const options = {
            method: 'GET',
            headers: {
                "Authorization": `Bearer ${token}`
            }
        }
        fetch(url, options).then(response => {
            if (response.status === 200) {
                return response.json();
            } else if (response.status === 403) {
                navigate('/login');
            } else {
                return Promise.reject(`Encountered unexpected status: ${response.status}`);
            }
        }).then(data => {
            console.log(data);
            setTotalBalanceAcrossAccounts(data);
        })
            .catch(console.log);
    }, []);


    return (
        <>
            <h1>
                {`$${totalBalanceAcrossAccounts}`}
            </h1>
        </>
    );

}
export default TotalBalanceAcrossAccounts;