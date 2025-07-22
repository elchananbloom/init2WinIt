import React from 'react';
import AppRoutes from './Routes';
import UserContext from './contexts/UserContext';

function App() {

  return (
    <UserContext.Provider value={{role: 'ADMIN'}}>
      <AppRoutes />
    </UserContext.Provider>
  )
}

export default App;