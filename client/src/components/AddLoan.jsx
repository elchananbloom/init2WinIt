import { useContext, useEffect, useState } from "react";
import Page from "./Page";
import { Link, useNavigate, useParams } from "react-router-dom";
import TokenContext from "../contexts/TokenContext";
import UserContext from "../contexts/UserContext";

const url = "http://localhost:8080/api";


const AddLoan = ({ fetchLoans }) => {
    const [loanTypes, setLoanTypes] = useState([]);
    const [loan, setLoan] = useState();
    const { id } = useParams();
    const navigate = useNavigate();
    const { token } = useContext(TokenContext);
    const {appUser} = useContext(UserContext);

    useEffect(() => {
        if(id != appUser.userId) {
            navigate('/notfound');
        }
        const options = {
            method: 'GET',
            headers: {
                "Authorization": `Bearer ${token}`
            }
        }
        fetch(`${url}/loan/type`, options)
            .then((response) => {
                if (response.status === 200) {
                    return response.json();
                } else if (response.status === 403) {
                    navigate('/login');
                } else {
                    return Promise.reject(`Unexpected Status Code: ${response.status}`);
                }
            })
            .then((data) => {
                setLoanTypes(data);
                console.log(data[0])
                setLoan({
                    loanType: data[0]
                });
            })
            .catch(console.log);
    }, []);

    const handleChange = (event) => {
        const newLoan = { ...loan };
        if (event.target.name === "loanType") {
            const lt = loanTypes.find(lt => lt.loanTypeName === event.target.value);
            newLoan.loanType = lt;
        } else {
            newLoan[event.target.name] = event.target.value;
        }

        setLoan(newLoan);
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        loan.status = 'IN_PROGRESS';
        loan.flatInterest = 7.5;
        loan.balance = loan.initialAmount * (1 + (7.5 / 100));
        loan.userId = id;

        const init = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify(loan),
        };
        fetch(`${url}/loan`, init)
            .then((response) => {
                if (response.status === 201 || response.status === 400) {
                    return response.json();
                } else if (response.status === 403) {
                    navigate('/login');
                } else {
                    return Promise.reject(`Unexpected Status Code: ${response.status}`);
                }
            })
            .then((data) => {
                if (data.loanId) {
                    fetchLoans();
                    navigate('/')
                } else {

                }
            })
            .catch(console.log);
    }

    return (
        <>
            <Page>
                <form id="form" className="col-md-6 mx-auto mt-4" onSubmit={handleSubmit}>
    <div className="card shadow-sm">
      <div className="card-body">
        <h4 className="card-title mb-4">Request a Loan</h4>

        {loan && loan.loanType && (
          <div className="form-group">
            <label htmlFor="loanType">Loan Type</label>
            <select
              id="loanType"
              name="loanType"
              className="form-control"
              value={loan.loanType.loanTypeName}
              onChange={handleChange}
              required
            >
              {loanTypes.map((loanType) => (
                <option key={loanType.loanTypeId} value={loanType.loanTypeName}>
                  {loanType.loanTypeName}
                </option>
              ))}
            </select>
          </div>
        )}

        <div className="form-group">
          <label htmlFor="initialAmount">Amount</label>
          <input
            type="number"
            className="form-control"
            id="initialAmount"
            name="initialAmount"
            min="1"
            step="any"
            required
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label htmlFor="dateDue">Due Date</label>
          <input
            type="date"
            className="form-control"
            id="dateDue"
            name="dateDue"
            required
            onChange={handleChange}
          />
        </div>
        {/* 
        <div className="form-group">
          <label htmlFor="description">Description</label>
          <textarea
            className="form-control"
            id="description"
            name="description"
            rows="3"
            onChange={handleChange}
          ></textarea>
        </div> */}

        <div className="d-flex justify-content-between">
          <button type="submit" className="btn btn-primary">
            Request Loan
          </button>
          <Link to="/" className="btn btn-danger">
            Cancel
          </Link>
        </div>
      </div>
    </div>
  </form>
            </Page>
        </>
    );
}

export default AddLoan;