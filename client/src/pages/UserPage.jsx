import { Link, useNavigate, useParams } from "react-router-dom";
import Page from "../components/Page"
import { useContext, useEffect, useState } from "react";
import TokenContext from "../contexts/TokenContext";

const url = 'http://localhost:8080/api/';


const UserPage = () => {
    const { id } = useParams();
    const [user, setUser] = useState();
    const navigate = useNavigate();
    const { token } = useContext(TokenContext);

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
                if (data.userId) {
                    setUser(data);
                }
            });
    }, [])

    return (
        <Page>
            {user &&
                <div><p>{user.firstName}</p>
                    <p>{user.lastName}</p>
                    <p>{user.address}</p>
                    <p>{user.phoneNumber}</p>
                    <p>{user.email}</p>
                    <p>{user.dob}</p>
                    <Link to={`/user/${user.userId}/edit`}>Edit</Link>
                </div>}
        </Page>
    );
}

export default UserPage;