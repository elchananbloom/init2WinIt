import { BarChart } from '@mui/x-charts/BarChart';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
function TotalWithdrawsPerQuarter() {
    const url = "http://localhost:8080/api/statistics/bank/withdrawals";
    const navigate = useNavigate();

    const defaultLabels = ['Q1', 'Q2', 'Q3', 'Q4'];
    const defaultValues = [0, 0, 0, 0];

    const [uData, setUData] = useState(defaultValues);
    const [xLabels, setXLabels] = useState(defaultLabels);

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

            console.log(Object.values(data));
            console.log(Object.keys(data));


            if (Object.keys(data).length > 0) {
                console.log("inside update view");
                if (Object.keys(data).length < 4) {
                    const appendedData = Object.values(data);
                    appendedData.push(...defaultValues);
                    setUData(appendedData);
                    setXLabels(defaultLabels);
                } else {
                    setUData(Object.values(data));
                    setXLabels(Object.keys(data));
                }

            }

        })
            .catch(console.log);
    }, []);



    return (<>
        <BarChart
            height={300}
            series={[
                { 
                    data: uData,
                    label: 'Total Amount Withdrawn (Quarterly)',
                    id: 'uvId',
                    valueFormatter: (data) => { return `$${data} (USD)`; }
                 },
            ]}
            xAxis={[{ data: xLabels }]}
            yAxis={[{ 
                width: 150,
                valueFormatter: (data) => { return `$${data} (USD)`; }
             }]}
        />
    </>
    );
}

export default TotalWithdrawsPerQuarter;