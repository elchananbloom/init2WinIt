import { useContext } from 'react';
import { Route, BrowserRouter as Router, Routes, useLocation } from 'react-router-dom';
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

const AppRoutes = () => {
    const user = useContext(UserContext);
    const location = useLocation();

    const isEditUserPage = /^\/user\/[^/]+\/edit$/.test(location.pathname);

    const shouldShowSidebar = user && !isEditUserPage;
    return (
        <>
            {shouldShowSidebar && <SideBar />}
            <Routes>
                <Route path='/' element={<></>} />
                <Route path='/login' element={<></>} />
                <Route path='/signup' element={<SignUp />} />
                <Route path='/user/:id/edit' element={<SignUp />} />
                <Route path='/user/:id' element={<UserPage />} />
                <Route path='account/new' element={<></>} />
                <Route path='/account/:id' element={<AccountPage />} />
                <Route path='/loan/new' element={<></>} />
                <Route path='/loan/:id' element={<LoanPage/>} />
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