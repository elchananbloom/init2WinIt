import Page from "../components/Page";
import TotalBalanceAcrossAccounts from "../components/statistics/TotalBalanceAcrossAccounts";
import { Link, useNavigate } from "react-router-dom";
import { useContext, useEffect } from "react";
import TokenContext from "../contexts/TokenContext";
import UserContext from "../contexts/UserContext";
const url = 'http://localhost:8080/api/';

const HomePage = () => {
  const navigate = useNavigate();
  const { token } = useContext(TokenContext);
  const { appUser } = useContext(UserContext);

  const businessHours = [
    { day: 'Monday', hours: '9AM - 6PM' },
    { day: 'Tuesday', hours: '9AM - 6PM' },
    { day: 'Wednesday', hours: '9AM - 6PM' },
    { day: 'Thursday', hours: '9AM - 6PM' },
    { day: 'Friday', hours: '9AM - 6PM' },
    { day: 'Saturday', hours: '10AM - 2PM' },
    { day: 'Sunday', hours: 'Closed' },
  ];

  useEffect(() => {
    const options = {
      method: 'GET',
      headers: {
        "Authorization": `Bearer ${token}`
      }
    };
    fetch(`${url}user/${appUser.userId}`, options)
      .then(res => {
        if (res.status === 200) return res.json();
        if (res.status === 403) navigate('/login');
      })
      .then(data => {
        if (!data.userId || data.userId !== appUser.userId) {
          navigate('/notfound');
        }
      });
  }, []);

  return (
    <Page>
      <div className="demo-wrap">
      <div className="container py-5 demo-content">
        <div className="row mb-5">
          <div className="col-md-8 offset-md-2 text-center">
            <h1 className="display-1 mb-4">Welcome to InitBank!</h1>
            <h2 className="display-4 mb-4">{appUser.firstName} {appUser.lastName}</h2>
          </div>
        </div>

        <div className="card text-white bg-teal shadow mb-5">
          <div className="card-header">Total Account Balance</div>
          <div className="card-body">
            <TotalBalanceAcrossAccounts userId={appUser.userId} />
          </div>
        </div>

        <div className="jumbotron bg-light p-4 rounded shadow-sm">
          <h1 className="display-5 fw-bold mb-3">Your Journey to Financial Confidence Begins Here</h1>
          <p className="lead">
            At <strong>InitBank</strong>, we believe banking should be personal, secure, and empowering. Whether you're managing daily finances, planning milestones, or growing your business, we help you take control of your future with clarity and ease.
          </p>
          <p className="mt-3">Explore our services, open an account, or connect with our team to start your journey today.</p>
        </div>

        <section className="mt-5">
          <h4 className="mb-3 font-weight-bold text-white">Hours of Operation</h4>
          <ul className="list-group">
            {businessHours.map((item, index) => (
              <li className="list-group-item d-flex justify-content-between align-items-center" key={index}>
                {item.day}
                <span className="badge badge-secondary">{item.hours}</span>
              </li>
            ))}
          </ul>
        </section>
      </div>
      </div>
    </Page>
  );
};

export default HomePage;