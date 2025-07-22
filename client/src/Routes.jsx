import { useContext } from 'react';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import UserContext from './contexts/UserContext';
import SideBar from './components/SideBar';
import Account from './components/Account';
import SignUp from './components/SignUp';

const AppRoutes = () => {
    const user = useContext(UserContext);
    return (
        <>
            <Router>
                {user && <SideBar/>}
                <Routes>
                    <Route path='/' element={<></>} />
                    <Route path='/login' element={<></>} />
                    <Route path='/signup' element={<SignUp/>} />
                    <Route path='account/new' element={<></>} />
                    <Route path='/account/:id' element={<Account/>} />
                    <Route path='/loan/new' element={<></>} />
                    <Route path='/loan/:id' element={<></>} />
                    {user && user.role === 'ADMIN' &&
                        <><Route path='/admin/statistics' element={<></>} />
                            <Route path='/admin/loans' element={<></>} />
                            <Route path='/admin/categories' element={<></>} />
                            <Route path='/admin/accounts' element={<></>} />
                        </>}
                </Routes>
            </Router>
        </>
    );
}

export default AppRoutes;