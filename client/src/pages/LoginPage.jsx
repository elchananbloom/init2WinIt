import { useContext, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import UserContext from "../contexts/UserContext";
import TokenContext from "../contexts/TokenContext";

const baseUrl = "http://localhost:8080/api/";


const LoginPage = () => {
    const [user, setUser] = useState();
    const { setAppUser } = useContext(UserContext);
    const { token, setToken } = useContext(TokenContext);
    const navigate = useNavigate();

    const handleChange = (event) => {
        const newUser = { ...user };
        newUser[event.target.name] = event.target.value;

        setUser(newUser);
    };

    const getUser = (newToken) => {
        const options = {
            method: 'GET',
            headers: {
                "Authorization": `Bearer ${newToken}`
            }
        }
        fetch(`${baseUrl}user?email=${user.email}`, options)
            .then((response) => {
                console.log(response.status)
                if (response.status === 200 || response.status === 400) {
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected Status Code: ${response.status}`);
                }
            })
            .then((data) => {
                if (data.userId) {
                    setAppUser(data);
                    navigate('/');
                } else {

                }
            })
            .catch(console.log);
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        authenticate();
    }

    const authenticate = () => {
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user)
        }
        fetch(`${baseUrl}user/authenticate`, options)
            .then((response) => {
                console.log(response.status)
                if (response.status === 200 || response.status === 400) {
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected Status Code: ${response.status}`);
                }
            })
            .then((data) => {
                if (data.jwt_token) {
                    setToken(data.jwt_token);
                    getUser(data.jwt_token);
                } else {

                }
            })
            .catch(console.log);
    }

    return (
        <>
            <form onSubmit={handleSubmit}>

                {<><fieldset className="form-group">
                    <label htmlFor="email">Email</label>
                    <input
                        id="email"
                        name="email"
                        type="email"
                        className="form-control"
                        onChange={handleChange}
                    />
                </fieldset>

                    <fieldset className="form-group">
                        <label htmlFor="password">Password</label>
                        <input
                            id="password"
                            name="password"
                            type="password"
                            className="form-control"
                            /*onblur="validate()"*/
                            onChange={handleChange}
                        />
                    </fieldset>
                </>}

                <div className="mt-4">
                    <button type="submit" className="btn btn-outline-success mr-4 mt-4">
                        Login
                    </button>
                    <Link
                        type="button"
                        to={"/signup"}
                        className="btn btn-outline-danger mt-4"
                    >
                        Don't have an account yet? Sign Up
                    </Link>
                </div>
            </form>
        </>
    );
}

export default LoginPage;