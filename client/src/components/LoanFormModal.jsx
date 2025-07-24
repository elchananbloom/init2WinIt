import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import TokenContext from "../contexts/TokenContext";

const DEFAULT_LOAN = {
  initialAmount: 0,
  loanStatus: "IN-PROGRESS",
};

function LoanFormModal({ id, handleShowModal, fetchLoans }) {
  const [loan, setLoan] = useState(DEFAULT_LOAN);
  const [loans, setLoans] = useState();
  const [loanTypes, setLoanTypes] = useState();
  const [errors, setErrors] = useState([]);
  const { token } = useContext(TokenContext);

  const urlTypes = "http://localhost:8080/api/loan/type";
  const urlLoan = "http://localhost:8080/api/loan";

  useEffect(() => {
    const options = {
      method: 'GET',
      headers: {
        "Authorization": `Bearer ${token}`
      }
    }
    fetch(urlTypes, options)
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
        const newLoan = { ...DEFAULT_LOAN };
        newLoan.loanType = data[1];
        setLoan(newLoan);
        setLoanTypes(data);
      })
      .catch(console.log);
  }, []);

  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    handleAdd();
  };

  const handleAdd = () => {
    loan.userId = id;
    const init = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${token}`
      },
      body: JSON.stringify(loan),
    };

    fetch(urlLoan, init)
      .then((response) => {
        if (response.status === 201 || response.status === 400) {
          return response.json();
        } else if (response.status === 403) {
          navigate("/login");
        } else {
          return Promise.reject(
            `Unexpected Error, Status Code: ${response.status}`
          );
        }
      })
      .then((data) => {
        if (data.loanId) {
          fetchLoans();
          handleShowModal(false);
        } else {
          setErrors(data);
        }
      })
      .catch(console.log);
  };

  const handleChange = (event) => {
    const newLoan = { ...loan };
    if (event.target.name === "type") {
      const lt = loanTypes.find((lt) => lt.loanTypeName === event.target.value);
      newLoan.loanType = lt;
      console.log(lt);
    } else {
      newLoan[event.target.name] = event.target.value;
    }
    console.log(newLoan);

    setLoan(newLoan);
  };

  return (
    <>
      {errors.length > 0 && (
        <div className="alert alert-danger">
          <p>The following errors were found:</p>
          <ul>
            {errors.map((error) => (
              <li key={error}>{error}</li>
            ))}
          </ul>
        </div>
      )}
      <form id="form" className="form-col" onSubmit={handleSubmit}>
        <fieldset className="form-group">
          <label htmlFor="initial-amount">Amount</label>
          <input
            onChange={handleChange}
            className="form-control"
            type="number"
            min="1"
            step="any"
            name="amount"
            id="amount"
            required
          />
        </fieldset>
        {loan.loanType && (
          <fieldset className="form-group">
            <label htmlFor="amount">Type</label>
            <select
              id="type"
              name="type"
              className="form-control"
              value={loan.loanType.loanTypeName}
              onChange={handleChange}
            >
              {loanTypes.map((loant) => (
                <option>{loant.loanTypeName}</option>
              ))}
            </select>
          </fieldset>
        )}

        <button className=" m-1 btn btn-primary" id="submit-form" type="submit">
          Request Loan
        </button>
        <button
          onClick={() => handleShowModal(false)}
          className="m-1 btn btn-success"
          type="button"
        >
          Cancel
        </button>
      </form>
    </>
  );
}

export default LoanFormModal;
