import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

const SignUp = () => {
  const [user, setUser] = useState();
  const [errors, setErrors] = useState([]);

  const url = "http://localhost:8080/api/user";

  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    addUser();
  };

  const handleChange = (event) => {
    const newUser = { ...user };
    newUser[event.target.name] = event.target.value;

    setUser(newUser);
  };

  const addUser = () => {
    user.role = 'USER';
    const init = {
      method: 'POST',
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(user),
    };
    fetch(url, init)
      .then((response) => {
        if (response.status === 201 || response.status === 400) {
          return response.json();
        } else {
          return Promise.reject(`Unexpected Status Code: ${response.status}`);
        }
      })
      .then((data) => {
        if (data.userId) {
          console.log(data);
          navigate("/");
        } else {
          console.log(data);
          setErrors(data);
        }
      })
      .catch(console.log);
  };

  const validateInput = (value) => {};
//implement toasts
  return (
    <>
      <div className="container flex">
        <h2 className="mb-4">Sign Up</h2>
        {errors.length > 0 && (
          <div className="alert alert-danger">
            <p>The following errors were found:</p>
            <ul>
              {/* {errors.map((error) => (
                <li key={error}>{error}</li>
              ))} */}
            </ul>
          </div>
        )}
        <form onSubmit={handleSubmit}>
          <fieldset className="form-group">
            <label htmlFor="firstName">First Name</label>
            <input
              id="firstName"
              name="firstName"
              type="text"
              className="form-control"
              onChange={handleChange}
            />
          </fieldset>
          <fieldset className="form-group">
            <label htmlFor="lastName">Last Name</label>
            <input
              id="lastName"
              name="lastName"
              type="text"
              className="form-control"
              onChange={handleChange}
            />
          </fieldset>
          <fieldset className="form-group">
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
            <label htmlFor="phoneNumber">Phone</label>
            <input
              id="phoneNumber"
              name="phoneNumber"
              type="tel"
              className="form-control"
              onChange={handleChange}
            />
          </fieldset>
          <fieldset className="form-group">
            <label htmlFor="address">Phone</label>
            <input
              id="address"
              name="address"
              type="text"
              className="form-control"
              onChange={handleChange}
            />
          </fieldset>
          <fieldset className="form-group">
            <label htmlFor="passwordHash">Password</label>
            <input
              id="passwordHash"
              name="passwordHash"
              type="password"
              className="form-control"
              /*onblur="validate()"*/
              onChange={handleChange}
            />
          </fieldset>
          {/* <fieldset className="form-group">
                <label htmlFor="password">Retype Password</label>
                <input
                  id="retype_password"
                  name="retypepassword"
                  type="password"
                  className="form-control"
                  value={user.password}
                  onChange={handleChange}
                  onblur="validate()"
                />
              </fieldset>               */}
          <div className="mt-4">
            <button type="submit" className="btn btn-outline-success mr-4 mt-4">
              Sign Up
            </button>
            <Link
              type="button"
              to={"/"}
              className="btn btn-outline-danger mt-4"
            >
              Cancel
            </Link>
          </div>
        </form>
      </div>
    </>
  );
};

export default SignUp;
