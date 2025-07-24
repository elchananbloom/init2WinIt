import { Link, useNavigate, useParams } from "react-router-dom";
import Page from "../components/Page"
import { useContext, useEffect, useState } from "react";
import TokenContext from "../contexts/TokenContext";
import UserContext from "../contexts/UserContext";

const url = 'http://localhost:8080/api/';


const UserPage = () => {
    const { id } = useParams();
    const [user, setUser] = useState();
    const navigate = useNavigate();
    const { token } = useContext(TokenContext);
    const { appUser } = useContext(UserContext);

    useEffect(() => {
        const options = {
            method: 'GET',
            headers: {
                "Authorization": `Bearer ${token}`
            }
        }
        fetch(`${url}user/${id}`, options)
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
    }, [])

    return (
        <Page>
            {user &&
                <div className="container mt-4">
                    <div className="card shadow-sm">
                        <div className="card-body">
                            <h5 className="card-title mb-3">
                                {user.firstName} {user.lastName}
                            </h5>
                            <ul className="list-group list-group-flush mb-3">
                                <li className="list-group-item">
                                    <strong>Address:</strong> {user.address}
                                </li>
                                <li className="list-group-item">
                                    <strong>Phone:</strong> {user.phoneNumber}
                                </li>
                                <li className="list-group-item">
                                    <strong>Email:</strong> {user.email}
                                </li>
                                <li className="list-group-item">
                                    <strong>Date of Birth:</strong> {user.dob}
                                </li>
                            </ul>
                            <Link
                                to={`/user/${user.userId}/edit`}
                                className="btn btn-primary btn-sm"
                            >
                                Edit
                            </Link>
                        </div>
                    </div>
                </div>}
        </Page>
    );
}

export default UserPage;