import React from 'react';
import AppRoutes from './Routes';
import UserContext from './contexts/UserContext';
import { BrowserRouter as Router } from 'react-router-dom';


function App() {

  return (
    <UserContext.Provider value={{ role: 'USER', userId: 1 }}>
      <Router>

        <AppRoutes />
      </Router>
    </UserContext.Provider>
  )
}

export default App;