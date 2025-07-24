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
                <form id="form" className="form-col" onSubmit={handleSubmit}>
                    {loan && loan.loanType &&
                        <fieldset className="form-group">
                            <label htmlFor="loanType">Type</label>
                            <select
                                id="loanType"
                                name="loanType"
                                className="form-control"
                                value={loan.loanType.loanTypeName}
                                onChange={handleChange}
                            >
                                {loanTypes.map((loanType) => (

                                    <option>{loanType.loanTypeName}</option>

                                ))}
                            </select>
                        </fieldset>}
                    <fieldset className="form-group">
                        <label htmlFor="initialAmount" >Amount</label>
                        <input
                            onChange={handleChange}
                            className="form-control"
                            type="number"
                            min="1"
                            step="any"
                            name="initialAmount"
                            id="initialAmount"
                            required
                        />
                    </fieldset>
                    <fieldset className="form-group">
                        <label htmlFor="dateDue" >Due Date</label>
                        <input
                            onChange={handleChange}
                            className="form-control"
                            type="date"
                            name="dateDue"
                            id="dateDue"
                            required
                        />
                    </fieldset>
                    <fieldset>
                        <label htmlFor="description">Description</label>
                        <textarea onChange={handleChange} className="form-control" type="text" name="description" />
                    </fieldset>
                    <button className=" m-1 btn btn-primary" id="submit-form" type="submit">
                        Request a Loan
                    </button>
                    <Link
                        to={`/`}
                        className="m-1 btn btn-danger"
                        type="button"
                    >
                        Cancel
                    </Link>
                </form>
            </Page>
        </>
    );
}

export default AddLoan;