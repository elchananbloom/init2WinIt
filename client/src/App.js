import React, { use, useState } from 'react';
import AppRoutes from './Routes';
import UserContext from './contexts/UserContext';
import { BrowserRouter as Router } from 'react-router-dom';
import TokenContext from './contexts/TokenContext';


function App() {
  const [token, setToken] = useState(() => {
    return localStorage.getItem('token');
  });
  const [appUser, setAppUser] = useState(() => {
    const storedUser = localStorage.getItem('appUser');
    return storedUser ? JSON.parse(storedUser) : null;
  });

  return (
    <TokenContext.Provider value={{ token, setToken }}>
      <UserContext.Provider value={{ appUser, setAppUser }}>
        <Router>

          <AppRoutes />
        </Router>
      </UserContext.Provider>
    </TokenContext.Provider>
  )
}

export default App;