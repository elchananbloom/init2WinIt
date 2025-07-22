import { useContext } from "react";
import { Link } from "react-router-dom";
import UserContext from "../contexts/UserContext";
import UserSideBar from "./UserSideBar";
import AdminSideBar from "./AdminSideBar";


const SideBar = () => {
    const user = useContext(UserContext);
    return (
        <div className="container border-right shadow rounded-lg position-fixed h-100 bg-secondary text-white sidebar nav-content-area">

            <div className="d-flex flex-column p-3 h-100">
                <Link to={'/login'} className="btn btn-light mb-3 text-left">login</Link>
                {user && user.role === 'ADMIN' && <AdminSideBar/>}
                {user && user.role === 'USER' && <UserSideBar/>}
                
                
            </div>
        </div>
    );
}

export default SideBar;