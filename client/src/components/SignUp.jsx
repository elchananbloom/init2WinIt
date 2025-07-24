import { useContext, useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import UserContext from "../contexts/UserContext";
import TokenContext from "../contexts/TokenContext";

const DEFAULT_USER = {
  userId: 0,
  firstName: '',
  lastName: '',
  address: '',
  phoneNumber: '',
  email: '',
  dob: '',
}
const SignUp = () => {
  const [user, setUser] = useState(DEFAULT_USER);
  const [errors, setErrors] = useState([]);
  const { id } = useParams();
  const [accountType, setAccountType] = useState('CHECKING');
  const {appUser, setAppUser } = useContext(UserContext);
  const { token, setToken } = useContext(TokenContext);

  const url = "http://localhost:8080/api/user";
  const baseUrl = "http://localhost:8080/api/";

  useEffect(() => {
    if (id) {
      const options = {
        method: 'GET',
        headers: {
          "Authorization": `Bearer ${token}`
        }
      }
      fetch(`${url}/${id}`, options)
        .then(res => {
          if (res.status === 200) {
            return res.json();
          }
          if (res.status === 403) {
            navigate('/login');
          }
        })
        .then(data => {
          if (data.userId && data.userId === appUser.userId) {
            setUser(data);
          } else {
            navigate('/notfound');
          }
        });
    }
  }, [])

  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    if (id) {
      updateUser();
    } else {
      addUser();
    }
  };

  const handleChange = (event) => {
    const newUser = { ...user };
    newUser[event.target.name] = event.target.value;

    setUser(newUser);
  };

  const updateUser = () => {
    console.log(user);
    const options = {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        "Authorization": `Bearer ${token}`
      },
      body: JSON.stringify(user)
    };
    fetch(`${url}/${user.userId}`, options)
      .then((response) => {
        console.log(response.status)
        if (response.status === 204) {
          return user
        } else if (response.status === 400) {
          return response.json();
        } else if (response.status === 403) {
          navigate('/login');
        } else {
          return Promise.reject(`Unexpected Status Code: ${response.status}`);
        }
      })
      .then((data) => {
        if (data.userId) {
          navigate("/");
        } else {
          console.log(data)
          setErrors(data);
        }
      })
      .catch(console.log);
  }

  const addAccount = async (userId, newToken) => {
    const account = {
      accountType: accountType,
      balance: 0,
      userId: userId
    };
    const init = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${newToken}`

      },
      body: JSON.stringify(account),
    };
    const response = await fetch(`${baseUrl}account`, init);

    if (response.status === 201 || response.status === 400) {
      const data = await response.json();
      if (data.accountId) {
        return true;
      }
    } else if (response.status === 403) {
      navigate('/login');
    }
    return false;
  }




  const authenticate = async (user1) => {
    const userToAuthenticate = {
      email: user.email,
      password: user.passwordHash
    };

    const options = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(userToAuthenticate)
    }
    const response = await fetch(`${baseUrl}user/authenticate`, options);

    if (response.status === 200 || response.status === 400) {
      const data = await response.json();
      if (data.jwt_token) {
        setToken(data.jwt_token);
        localStorage.setItem('token', data.jwt_token);
        return await addAccount(user1.userId, data.jwt_token);
      }
    } else if (response.status === 403) {
      navigate('/login');
    }
    return false;
  }



  const addUser = async () => {
    user.role = 'USER';
    const init = {
      method: 'POST',
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${token}`
      },
      body: JSON.stringify(user),
    };
    try {
      const response = await fetch(url, init);
      if (response.status === 201 || response.status === 400) {
        const data = await response.json();
        if (data.userId) {
          setAppUser(data);
          localStorage.setItem('appUser', JSON.stringify(data));
          if (await authenticate(data)) {
            console.log(token);
            navigate('/');
          }
        } else {
          console.log(data);
          setErrors(data);
        }
      } else if (response.status === 403) {
        navigate('/login');
      }
    } catch (error) {
      console.log(error);
    }
  }

  const handleAccountTypeChange = (event) => {
    setAccountType(event.target.value);
  }

  const validateInput = (value) => { };
  //implement toasts
  return (
    <>
      <div className="container col-6 mt-5 flex">
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
          <div className="form-row">
            <fieldset className="form-group col-md-4">
              <label htmlFor="firstName">First Name</label>
              <input
                id="firstName"
                name="firstName"
                type="text"
                value={user.firstName}
                className="form-control shadow-sm"
                onChange={handleChange}
              />
            </fieldset>
            <fieldset className="form-group col-md-4">
              <label htmlFor="lastName">Last Name</label>
              <input
                id="lastName"
                name="lastName"
                type="text"
                value={user.lastName}
                className="form-control shadow-sm"
                onChange={handleChange}
              />
            </fieldset>
            <fieldset className="form-group col-md-4">
              <label htmlFor="dob" >Date of Birth</label>
              <input
                onChange={handleChange}
                className="form-control shadow-sm"
                type="date"
                name="dob"
                id="dob"
                required
              />
            </fieldset>
          </div>
          <div className="row">
            <fieldset className="form-group col-md-6">
              <label htmlFor="phoneNumber">Phone</label>
              <input
                id="phoneNumber"
                name="phoneNumber"
                type="tel"
                value={user.phoneNumber}
                className="form-control shadow-sm"
                onChange={handleChange}
              />
            </fieldset>
            <fieldset className="form-group col-md-6">
              <label htmlFor="address">Address</label>
              <input
                id="address"
                name="address"
                type="text"
                value={user.address}
                className="form-control shadow-sm"
                onChange={handleChange}
              />
            </fieldset>
          </div>
          {!id && <> <div className="row">
            <fieldset className="form-group col-md-6">
              <label htmlFor="email">Email</label>
              <input
                id="email"
                name="email"
                type="email"
                className="form-control shadow-sm"
                onChange={handleChange}
              />
            </fieldset>
            <fieldset className="form-group col-md-6">
              <label htmlFor="passwordHash">Password</label>
              <input
                id="passwordHash"
                name="passwordHash"
                type="password"
                className="form-control shadow-sm"
                /*onblur="validate()"*/
                onChange={handleChange}
              />
            </fieldset>
          </div>

            <fieldset className="form-group">
              <label htmlFor="loanType">New Account Type</label>
              <select
                id="loanType"
                name="loanType"
                className="form-control shadow-sm"
                value={accountType}
                onChange={handleAccountTypeChange}
              >
                <option>CHECKING</option>
                <option>SAVINGS</option>

              </select>
            </fieldset>
          </>}
          <div className="mt-4">
            <button type="submit" className="btn teal shadow-sm mr-4 mt-4">
              {id ? 'Edit' : 'Sign Up'}
            </button>
            <Link
              type="button"
              to={"/"}
              className="btn btn-light btn-outline-dark shadow-sm mt-4"
            >
              Already Have an Account? Login
            </Link>
          </div>
        </form>
      </div>
    </>
  );
};

export default SignUp;
