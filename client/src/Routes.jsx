import { useContext, useEffect, useState } from 'react';
import { Navigate, Route, BrowserRouter as Router, Routes, useLocation, useNavigate } from 'react-router-dom';
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
import LoginPage from './pages/LoginPage';
import TokenContext from './contexts/TokenContext';
import HomePage from './pages/HomePage';
import NotFoundPage from './pages/NotFoundPage';
import ClosePage from './pages/ClosePage';

const url = 'http://localhost:8080/api/';

const AppRoutes = () => {
    const { appUser } = useContext(UserContext);
    const { token } = useContext(TokenContext);
    const location = useLocation();
    const [loans, setLoans] = useState([]);
    const navigate = useNavigate();
    const fetchLoans = () => {
        if (appUser) {
            const options = {
                method: 'GET',
                headers: {
                    "Authorization": `Bearer ${token}`
                }
            }
            fetch(`${url}loan?userId=${appUser.userId}`, options)
                .then(res => {
                    if (res.status === 200) {
                        return res.json();
                    }
                    else if (res.status === 403) {
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
    }, [token])

    const isEditUserPage = /^\/user\/[^/]+\/edit$/.test(location.pathname);

    const shouldShowSidebar = appUser && !isEditUserPage;
    return (
        <>
            {shouldShowSidebar && <SideBar loans={loans} fetchLoans={fetchLoans} />}
            <Routes>
                {appUser && <Route path='/' element={<HomePage />} />}
                {!appUser && <Route path='/' element={<Navigate to={'/login'}/>} />}
                <Route path='/login' element={<LoginPage />} />
                <Route path='/signup' element={<SignUp />} />
                <Route path='/user/:id/edit' element={<SignUp />} />
                <Route path='/user/:id' element={<UserPage />} />
                <Route path='/account/:id' element={<AccountPage />} />
                <Route path='/user/:id/loan/new' element={<AddLoan fetchLoans={fetchLoans} />} />
                <Route path='/loan/:id' element={<LoanPage />} />
                <Route path='/close' element={<ClosePage/>} />
                {appUser && appUser.role === 'ADMIN' &&
                    <>
                        <Route path='/admin/statistics' element={<StatisticsPage />} />
                        <Route path='/admin/loans' element={<LoansPage fetchLoans={fetchLoans}/>} />
                        <Route path='/admin/categories' element={<CategoriesPage />} />
                        <Route path='/admin/accounts' element={<AdminAccountsPage />} />
                    </>
                }
                <Route path="*" element={<NotFoundPage />} />
            </Routes>
        </>
    );
}

export default AppRoutes;