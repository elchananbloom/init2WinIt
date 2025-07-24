import { PieChart } from '@mui/x-charts/PieChart';
import { useContext, useEffect, useState } from 'react';
import TokenContext from "../../contexts/TokenContext";
import { useNavigate } from "react-router-dom";
import { Typography } from '@mui/material';

function CategoryTransactions() {

    const { token } = useContext(TokenContext);
    const navigate = useNavigate();

    const url = "http://localhost:8080/api/statistics/bank";

    const defaultData = [
        { label: 'Group A', value: 400, color: '#0088FE' },
        { label: 'Group B', value: 300, color: '#00C49F' },
        { label: 'Group C', value: 300, color: '#FFBB28' },
        { label: 'Group D', value: 200, color: '#FF8042' },
    ];

    const [data, setData] = useState(defaultData);



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
        hideLegend: false,
    };

    return <>
        <div className="container mt-4">
            <div className="row mb-2 justify-content-center">
                <div className="col-md-8 d-flex justify-content-center">
                    <PieChart
                        series={[
                            {
                                innerRadius: 50,
                                outerRadius: 100,
                                data: data.map(((d) => ({ label: d.label, value: d.value }))),
                                arcLabel: (item) => `$${item.value}`,
                                arcLabelMinAngle: 30,
                                dataKey: 'money',
                                valueFormatter: (v) => { return `$${v.value} (USD)`; }
                            }
                        ]}
                        {...settings}
                    />
                </div>
            </div>
            <div className="row justify-content-center mb-3">
                <div className="col text-center">
                    <Typography variant="h6" gutterBottom>Total Transaction Breakdown (Fiscal year)</Typography>
                </div>
            </div>
        </div>
    </>
}
export default CategoryTransactions;