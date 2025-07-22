import { Link, useNavigate } from "react-router-dom"


const AdminSideBar = () => {
    return (
        <>
            <Link to={`/admin/statistics`} className="btn btn-light mt-5 mb-3 text-left">Statistics</Link>
            <Link to={`/admin/loans`} className="btn btn-light mb-3 text-left">Loans</Link>
            <Link to={`/admin/categories`} className="btn btn-light mb-3 text-left">Categories</Link>
            <Link to={`/admin/accounts`} className="btn btn-light  mb-3 text-left">Accounts</Link>
        </>
    )
}

export default AdminSideBar;