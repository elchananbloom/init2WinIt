import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

const NewAccountPage = () => {
  const [user, setUser] = useState[[]];
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
    const init = {
      method: "POST",
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
          navigate("/");
        } else {
          setErrors(data);
        }
      })
      .catch(console.log);
  };

  const validateInput = (value) => {};

  return (
    <>
      <div classname="container">
        <h2 className="mb-4">{id > 0 ? "Update Agent" : "Add Agent"}</h2>
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
        <form onSubmit={handleSubmit}>
          <fieldset className="form-group">
            <label htmlFor="firstName">First Name</label>
            <input
              id="firstName"
              name="firstName"
              type="text"
              className="form-control"
              value={user.firstName}
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
              value={user.lastName}
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
              value={user.email}
              onChange={handleChange}
            />
          </fieldset>
          <fieldset className="form-group">
            <label htmlFor="phoneNumber">Height in Inches</label>
            <input
              id="phoneNumber"
              name="phoneNumber"
              type="tel"
              className="form-control"
              value={user.phoneNumber}
              onChange={handleChange}
            />
          </fieldset>
          <fieldset className="form-group">
            <label htmlFor="password">Height in Inches</label>
            <input
              id="password"
              name="password"
              type="password"
              className="form-control"
              value={user.password}
              onblur="validate()"
              onChange={handleChange}
            />
          </fieldset>
          {/* <fieldset className="form-group">
                <label htmlFor="password">Height in Inches</label>
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
              Create Account
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

export default NewAccountForm;
