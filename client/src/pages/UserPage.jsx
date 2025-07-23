import { Link, useNavigate, useParams } from "react-router-dom";
import Page from "../components/Page"
import { useEffect, useState } from "react";

const url = 'http://localhost:8080/api/';


const UserPage = () => {
    const { id } = useParams();
    const [user, setUser] = useState();
    const navigate = useNavigate();

    useEffect(() => {
        fetch(`${url}user/${id}`)
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