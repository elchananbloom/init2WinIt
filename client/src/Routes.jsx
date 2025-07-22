import { useContext } from 'react';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import UserContext from './contexts/UserContext';
import SideBar from './components/SideBar';
import Account from './components/Account';
import StatisticsPage from './pages/StatisticsPage';
import LoansPage from './pages/LoansPage';
import CategoriesPage from './pages/CategoriesPage';
import AdminAccountsPage from './pages/AdminAccountsPage';
import SignUp from './components/SignUp';

const AppRoutes = () => {
    const user = useContext(UserContext);
    return (
        <>
            <Router>
                {user && <SideBar />}
                <Routes>
                    <Route path='/' element={<></>} />
                    <Route path='/login' element={<></>} />
                    <Route path='/signup' element={<SignUp/>} />
                    <Route path='account/new' element={<></>} />
                    <Route path='/account/:id' element={<Account />} />
                    <Route path='/loan/new' element={<></>} />
                    <Route path='/loan/:id' element={<></>} />
                    {user && user.role === 'ADMIN' &&
                        <>
                            <Route path='/admin/statistics' element={<StatisticsPage />} />
                            <Route path='/admin/loans' element={<LoansPage/>} />
                            <Route path='/admin/categories' element={<CategoriesPage/>} />
                            <Route path='/admin/accounts' element={<AdminAccountsPage/>} />
                            </>
                        }                  
                </Routes>
            </Router>
        </>
    );
}

export default AppRoutes;