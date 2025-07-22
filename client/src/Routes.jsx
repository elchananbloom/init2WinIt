import {Route, BrowserRouter as Router, Routes } from 'react-router-dom';

const AppRoutes = () => {
    return (
        <>
        <Router>
            <Routes>
                <Route path='/' element={<></>} />
                <Route path='/login' element={<></>} />
                <Route path='/signup' element={<></>} />
                <Route path='/new-account' element={<></>} />
                <Route path='/account/:id' element={<></>} />
                <Route path='/loan/:id' element={<></>} />
                <Route path='/admin/statistics' element={<></>} />
                <Route path='/admin/loans' element={<></>} />
                <Route path='/admin/categories' element={<></>} />
                <Route path='/admin/accounts' element={<></>} />
            </Routes>
        </Router>
        </>
    );
}

export default AppRoutes;