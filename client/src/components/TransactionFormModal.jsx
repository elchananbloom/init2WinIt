import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import TokenContext from "../contexts/TokenContext";
import { loadStripe } from '@stripe/stripe-js';
const stripePromise = loadStripe("pk_test_51Rn32xR8xEV2XZugXO7DCa5bqDMB5UboeGVWYoJy2wLc1tyY9e4su2daoRsmNu42b2dp0CrMTnl3xfUlJcWfPwGM00i7C50Ini");

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
  const [selectedPrice, setSelectedPrice] = useState();

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

      const handleCheckout = async () => {
        const priceData = {
    price: selectedPrice}
  // Fetch the checkout session from your backend API
  try {
    const response = await fetch('http://localhost:8080/create-checkout-session', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      }, body: JSON.stringify(priceData),
    });

    // Check for successful response (200 OK)
    if (response.status === 200) {
      const data = await response.json();
      console.log(data);
      // Initialize Stripe with the public key and redirect to the checkout session
      window.open(data.url, "_blank");
      // const stripe = await stripePromise;
      // const { error } = await stripe.redirectToCheckout({sessionId: data.sessionId });

      // if (error) {
      //   console.error('Error redirecting to checkout:', error);
      //   alert(`Error: ${error.message}`);
      // } else {
      // }
    } else {
      const errorData = await response.json();
      console.error('Error during checkout session creation:', errorData);
      alert('Something went wrong. Please try again.');
    }
  } catch (error) {
    console.error('Error during checkout process:', error);
    alert('An unexpected error occurred. Please try again later.');
  }
};

function openTabAndFetch() {
  const newTab = window.open("about:blank", "_blank"); // Open a blank tab
  newTab.onload = () => {
    // Once the blank tab loads, execute a script within it
    newTab.document.body.innerHTML = "Loading..."; // Provide a loading message
    newTab.eval(`
      fetch("http://localhost:8080/create-checkout-session")
        .then(response => response.json())
        .then(data => {
          newTab.document.body.innerHTML = JSON.stringify(data, null, 2); // Display fetched data
        });
    `);
  };
}

  const handleSubmit = (event) => {
    event.preventDefault();
    handleCheckout();
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

    const handlePriceChange = (event) => {
    const newTransaction = {...transaction};
    setSelectedPrice(event.target.value);
    newTransaction[event.target.name] = event.target.value; // Update the selected price
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
            onChange={handlePriceChange}
            value={selectedPrice}
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