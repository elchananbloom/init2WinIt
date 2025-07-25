import { Link, useNavigate } from "react-router-dom"


const AdminSideBar = () => {
    return (
        <>
            <Link to={`/admin/statistics`} className="sidebar-button p-3 align-items-center"><p className="sidebar-text">Statistics</p></Link>
            <Link to={`/admin/loans`} className="sidebar-button p-3 align-items-center"><p className="sidebar-text">Loans</p></Link>
            <Link to={`/admin/categories`} className="sidebar-button p-3 align-items-center"><p className="sidebar-text">Categories</p></Link>
            <Link to={`/admin/accounts`} className="sidebar-button p-3 align-items-center"><p className="sidebar-text">Accounts</p></Link>
        </>
    )
}

export default AdminSideBar;