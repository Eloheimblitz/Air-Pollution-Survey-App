import { Navigate, Route, Routes } from 'react-router-dom';
import AppLayout from './layouts/AppLayout';
import Account from './pages/Account';
import ProtectedRoute from './components/ProtectedRoute';
import Dashboard from './pages/Dashboard';
import Login from './pages/Login';
import NotFound from './pages/NotFound';
import Records from './pages/Records';
import SurveyDetail from './pages/SurveyDetail';
import SurveyPage from './pages/SurveyPage';
import Users from './pages/Users';

export default function App() {
  return (
    <Routes>
      <Route path="/login" element={<Login />} />
      <Route element={<ProtectedRoute />}>
        <Route element={<AppLayout />}>
          <Route path="/" element={<Navigate to="/dashboard" replace />} />
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/surveys/new" element={<SurveyPage mode="create" />} />
          <Route path="/surveys" element={<Records />} />
          <Route path="/surveys/:id" element={<SurveyDetail />} />
          <Route path="/surveys/:id/edit" element={<SurveyPage mode="edit" />} />
          <Route path="/users" element={<Users />} />
          <Route path="/account" element={<Account />} />
        </Route>
      </Route>
      <Route path="*" element={<NotFound />} />
    </Routes>
  );
}
