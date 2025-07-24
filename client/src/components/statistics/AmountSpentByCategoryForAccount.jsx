import { Typography } from '@mui/material';
import { PieChart } from '@mui/x-charts/PieChart';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

function AmountSpentByCategoryForAccount({ accountId, transactionCount }) {
    const navigate = useNavigate();

    const url = `http://localhost:8080/api/statistics/user/account/${accountId}`;

    const defaultData = [
        { label: 'Group A', value: 400, color: '#0088FE' },
        { label: 'Group B', value: 300, color: '#00C49F' },
        { label: 'Group C', value: 300, color: '#FFBB28' },
        { label: 'Group D', value: 200, color: '#FF8042' },
    ];

    const [data, setData] = useState(defaultData);



    useEffect(() => {
        fetch(url).then(response => {
            if (response.status === 200) {
                return response.json();
            } else if (response.status === 403) {
                navigate('/login');
            } else {
                return Promise.reject(`Encountered unexpected status: ${response.status}`);
            }
        }).then(data => {

            const res = [];
            for (const [key, value] of Object.entries(data)) {
                res.push({ label: key, value: value });
                console.log(`${key}: ${value}`);
            }
            res.map((k, v) => console.log(`${k}:${v}`));
            setData(res);

        })
            .catch(console.log);
    }, [accountId, transactionCount]);





    const settings = {
        margin: { right: 5 },
        width: 200,
        height: 200,
        hideLegend: false,
    };

    return <>
        <Typography>Account Transactions Breakdown:</Typography>
        <PieChart
            series={[{ 
                innerRadius: 50,
                outerRadius: 100, 
                data, 
                arcLabel: (item) => `$${item.value}`,
                valueFormatter: (v) => { return `$${v.value} (USD)`;}
             }]}
            {...settings}
        />
    </>
}
export default AmountSpentByCategoryForAccount;