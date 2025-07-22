import React from 'react';
import AppRoutes from './Routes';
import UserContext from './contexts/UserContext';

function App() {

  return (
    <UserContext.Provider value={{role: 'USER',userId:1}}>
      <AppRoutes />
    </UserContext.Provider>
  )
}

export default App;