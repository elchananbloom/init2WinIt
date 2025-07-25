
import { LineChart } from "@mui/x-charts/LineChart";
import Typography from '@mui/material/Typography';
import { useEffect, useState, useContext } from 'react';
import UserContext from "../../contexts/UserContext";
import { useNavigate } from "react-router-dom";

function LoanBalanceOverTime({ loanId, loanBalance, transactionsCount }) {
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
    const url = `http://localhost:8080/api/statistics/user/loan/`;


    useEffect(() => {
        console.log(loanId)
        fetch(`${url}${loanId}`).then(response => {
            if (response.status === 200) {
                return response.json();
            } else if (response.status === 403) {
                navigate('/login');
            } else {
                return Promise.reject(`Encountered unexpected status: ${response.status}`);
            }
        }).then(data => {
            console.log(data)
            if (Object.keys(data).length > 0) {
                console.log('here')
                setXLabels(Object.keys(data));
                setUData(Object.values(data));
            }
           

        })
            .catch(console.log);
    }, [loanId, transactionsCount]);

     return (
    <div className="container mt-4">
      <div className="row mb-2 justify-content-center">
        <div className="col-md-8 d-flex justify-content-center">
          <LineChart
            height={300}
            series={[
              {
                data: uData,
                label: 'Loan Balance',
                yAxisId: 'rightAxisId',
                valueFormatter: (data) => `$${data} (USD)`,
              },
            ]}
            xAxis={[
              {
                scaleType: 'point',
                data: xLabels,
              },
            ]}
            yAxis={[
              { id: 'leftAxisId', width: 50 },
              { id: 'rightAxisId', position: 'right' },
            ]}
          />
        </div>
      </div>
      
    </div>
  );
}
export default LoanBalanceOverTime;