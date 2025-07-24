
import { LineChart } from "@mui/x-charts/LineChart";
import Typography from '@mui/material/Typography';
import { useEffect, useState, useContext } from 'react';
import UserContext from "../../contexts/UserContext";
import { useNavigate } from "react-router-dom";

function LoanBalanceOverTime({ loanId, loanBalance }) {
    const today = new Date();
    const formattedDate = today.toISOString().substring(0, 10);
    const defaultLabels = [
        formattedDate,
    ];
    const defaultData = [loanBalance,];

    const [xLabels, setXLabels] = useState([defaultLabels]);
    const [uData, setUData] = useState([defaultData])
    const navigate = useNavigate();


    const [data, setData] = useState({});
    const user = useContext(UserContext);
    const url = `http://localhost:8080/api/statistics/user/loan/${loanId}`;


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
            if (Object.keys(data).length > 0) {
                setXLabels(Object.keys(data));
                setUData(Object.values(data));
            }


            console.log(data);
            const res = [];
            for (const [key, value] of Object.entries(data)) {
                res.push({ label: key, value: value });
                console.log(`${key}: ${value}`);
            }
            res.map((k, v) => console.log(`${k}:${v}`));
            setData(res);

        })
            .catch(console.log);
    }, []);

    const settings = {
        margin: { right: 5 },
        width: 200,
        height: 200,
        hideLegend: true,
    };

    return <>
        <Typography>Remaining Balance</Typography>
        <LineChart
            height={300}
            series={[
                { data: uData, label: 'uv', yAxisId: 'rightAxisId' },
            ]}
            xAxis={[{ scaleType: 'point', data: xLabels }]}
            yAxis={[
                { id: 'leftAxisId', width: 50 },
                { id: 'rightAxisId', position: 'right' },
            ]}
        />
    </>
}
export default LoanBalanceOverTime;