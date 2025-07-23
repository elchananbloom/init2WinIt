import { useContext, useEffect, useState } from 'react';
import { Route, BrowserRouter as Router, Routes, useLocation, useNavigate } from 'react-router-dom';
import UserContext from './contexts/UserContext';
import SideBar from './components/SideBar';
import StatisticsPage from './pages/StatisticsPage';
import LoansPage from './pages/LoansPage';
import CategoriesPage from './pages/CategoriesPage';
import AdminAccountsPage from './pages/AdminAccountsPage';
import SignUp from './components/SignUp';
import UserPage from './pages/UserPage';
import AccountPage from './pages/AccountPage';
import LoanPage from './pages/LoanPage';
import AddLoan from './components/AddLoan';

const url = 'http://localhost:8080/api/';

const AppRoutes = () => {
    const user = useContext(UserContext);
    const location = useLocation();
    const [loans, setLoans] = useState([]);
    const navigate = useNavigate();
    const fetchLoans = () => {
        if (user) {
            fetch(`${url}/loan?userId=${user.userId}`)
                .then(res => {
                    if (res.status === 200) {
                        return res.json();
                    }
                    if (res.status === 403) {
                        navigate('/login');
                    }
                })
                .then(data => {
                    setLoans(data);
                });
        }
    }

    useEffect(() => {
        fetchLoans();
    }, [])

    const isEditUserPage = /^\/user\/[^/]+\/edit$/.test(location.pathname);

    const shouldShowSidebar = user && !isEditUserPage;
    return (
        <>
            {shouldShowSidebar && <SideBar loans={loans} fetchLoans={fetchLoans}/>}
            <Routes>
                <Route path='/' element={<></>} />
                <Route path='/login' element={<></>} />
                <Route path='/signup' element={<SignUp />} />
                <Route path='/user/:id/edit' element={<SignUp />} />
                <Route path='/user/:id' element={<UserPage />} />
                <Route path='/account/:id' element={<AccountPage />} />
                <Route path='/user/:id/loan/new' element={<AddLoan fetchLoans={fetchLoans} />} />
                <Route path='/loan/:id' element={<LoanPage />} />
                {user && user.role === 'ADMIN' &&
                    <>
                        <Route path='/admin/statistics' element={<StatisticsPage />} />
                        <Route path='/admin/loans' element={<LoansPage />} />
                        <Route path='/admin/categories' element={<CategoriesPage />} />
                        <Route path='/admin/accounts' element={<AdminAccountsPage />} />
                    </>
                }
            </Routes>
        </>
    );
}

export default AppRoutes;