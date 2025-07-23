import {useEffect, useState, useContext} from 'react';
import UserContext from "../../contexts/UserContext";


function TotalBalanceAcrossAccounts(){
    const user = useContext(UserContext);
    const url = `http://localhost:8080/api/statistics/user/account_total/${user.userId}`;
    const [totalBalanceAcrossAccounts, setTotalBalanceAcrossAccounts] = useState(0.00);

    useEffect(() => {
        fetch(url).then(response => {
            if(response.status === 200){
                return response.json();
            } else{
                return Promise.reject(`Encountered unexpected status: ${response.status}`);
            }
        }).then(data => {
            console.log(data);
            setTotalBalanceAcrossAccounts(data);
        })
        .catch(console.log);
    },[]);


    return (
        <>
            <h1>
                {totalBalanceAcrossAccounts}
            </h1>
        </>
    );

} 
export default TotalBalanceAcrossAccounts;