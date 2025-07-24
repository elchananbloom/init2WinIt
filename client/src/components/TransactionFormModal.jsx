import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import TokenContext from "../contexts/TokenContext";

const DEFAULT_TRANSACTION = {
  transactionId: 0,
  amount: 0.0,
  description: ""
};

function TransactionFormModal({ loanTrueAccountFalse, id, handleShowModal, transactionType, handleFetch }) {
  const [transaction, setTransaction] = useState(DEFAULT_TRANSACTION);
  const [errors, setErrors] = useState([]);
  const [transactionCategories, setTransactionCategories] = useState([]);
  const { token } = useContext(TokenContext);

  const urlTransaction = "http://localhost:8080/api/transaction";
  const urlCategories = "http://localhost:8080/api/transaction/category";

  useEffect(() => {
    const options = {
      method: 'GET',
      headers: {
        "Authorization": `Bearer ${token}`
      }
    }
    fetch(urlCategories, options)
      .then((response) => {
        if (response.status === 200) {
          return response.json();
        } else {
          return Promise.reject(`Unexpected Status Code: ${response.status}`);
        }
      })
      .then((data) => {
        const newTransaction = { ...DEFAULT_TRANSACTION };
        newTransaction.transactionCategory = data[1];
        setTransaction(newTransaction);
        setTransactionCategories(data)
      })
      .catch(console.log);
  }, []);


  const handleSubmit = (event) => {
    event.preventDefault();
    handleAdd();
  };

  const handleAdd = () => {
    if (transactionType != null && transactionType == "DEPOSIT") {
      transaction.type = "DEPOSIT";
    } else {
      transaction.type = "WITHDRAWAL";
    }
    if (loanTrueAccountFalse) {
      transaction.loanId = id;

    } else {
      transaction.accountId = id;
    }
    console.log('token trans');
    console.log(token);
    const init = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${token}`
      },
      body: JSON.stringify(transaction),
    };
    fetch(urlTransaction, init)
      .then((response) => {
        if (response.status === 201 || response.status === 400) {
          return response.json();
        } else {
          return Promise.reject(`Unexpected Status Code: ${response.status}`);
        }
      })
      .then((data) => {
        if (data.transactionId) {
          handleFetch();
          handleShowModal(false);
        } else {
          setErrors(data);
          console.log(data);
        }
      })
      .catch(console.log);

  };

  const handleChange = (event) => {
    const newTransaction = { ...transaction };
    if (event.target.name === "category") {
      const tc = transactionCategories.find(tc => tc.transactionCategoryName === event.target.value);
      newTransaction.transactionCategory = tc;
      console.log(tc);
    } else {
      newTransaction[event.target.name] = event.target.value;
    }
    console.log(newTransaction);

    setTransaction(newTransaction);
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
          <label htmlFor="amount" >Amount</label>
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
        {transaction.transactionCategory &&
          <fieldset className="form-group">
            <label htmlFor="category">Category</label>
            <select
              id="category"
              name="category"
              className="form-control"
              value={transaction.transactionCategory.transactionCategoryName}
              onChange={handleChange}
            >
              {transactionCategories.map((transactionc) => (

                <option>{transactionc.transactionCategoryName}</option>

              ))}
            </select>
          </fieldset>}
        <fieldset>
          <label htmlFor="description">Description</label>
          <textarea onChange={handleChange} className="form-control" type="text" name="description" />
        </fieldset>

        <button className=" m-1 btn btn-primary" id="submit-form" type="submit">
          {transactionType}
        </button>
        <button
          onClick={() => handleShowModal(false)}
          className="m-1 btn btn-danger"
          type="button"
        >
          Cancel
        </button>
      </form>
    </>
  );
}

export default TransactionFormModal;