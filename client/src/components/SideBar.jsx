import { useContext } from "react";
import { Link, useNavigate } from "react-router-dom";
import UserContext from "../contexts/UserContext";
import UserSideBar from "./UserSideBar";
import AdminSideBar from "./AdminSideBar";
import TokenContext from "../contexts/TokenContext";


const SideBar = ({ loans, fetchLoans }) => {
    const { appUser, setAppUser } = useContext(UserContext);
    const { setToken } = useContext(TokenContext);
    const navigate = useNavigate();

    const handleLogOut = () => {
        setAppUser(null);
        setToken(null);
        localStorage.removeItem('token');
        localStorage.removeItem('appUser');
        navigate('/login');
    }
    return (
        <div className="container border-right shadow rounded-lg position-fixed h-100 bg-secondary text-white sidebar nav-content-area">

            <div className="d-flex flex-column p-3 h-100">
                <button onClick={handleLogOut} className="btn btn-light mb-3 text-left">Log Out</button>
                {appUser && appUser.role === 'ADMIN' && <AdminSideBar />}
                {appUser && appUser.role === 'USER' && <UserSideBar loans={loans} fetchLoans={fetchLoans} />}


            </div>
        </div>
    );
}

export default SideBar;