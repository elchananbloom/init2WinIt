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
        <div className="border-right shadow rounded-lg position-fixed h-100 bg-teal text-white sidebar nav-content-area p-3">

            <div className="d-flex flex-column h-100">
                {appUser && appUser.role === 'ADMIN' && <AdminSideBar />}
                {appUser && appUser.role === 'USER' && <UserSideBar loans={loans} fetchLoans={fetchLoans} />}
                <Link onClick={handleLogOut} className="align-self-end mt-auto mb-3 text-left btn btn-light">Log Out</Link>


            </div>
        </div>
    );
}

export default SideBar;