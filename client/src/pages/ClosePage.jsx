import React, { useEffect } from 'react';

const ClosePage = () => {
  useEffect(() => {
    const timer = setTimeout(() => {
      window.close();
    }, 1000);

    return () => clearTimeout(timer);
  }, []);

  return (
    <div style={{ textAlign: 'center', marginTop: '50px' }}>
      <h2>Thank you for your payment!</h2>
      <p>This tab will close automatically.</p>
    </div>
  );
};

export default ClosePage;