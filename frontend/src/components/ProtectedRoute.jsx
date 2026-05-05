import { Navigate, Outlet, useLocation } from 'react-router-dom';
import { getSession } from '../api/client';

export default function ProtectedRoute() {
  const location = useLocation();
  const session = getSession();

  if (!session) {
    return <Navigate to="/login" state={{ from: location }} replace />;
  }

  return <Outlet />;
}
