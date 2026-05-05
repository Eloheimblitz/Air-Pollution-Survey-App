import { NavLink, Outlet, useNavigate } from 'react-router-dom';
import { clearSession, getSession } from '../api/client';

export default function AppLayout() {
  const navigate = useNavigate();
  const session = getSession();

  function logout() {
    clearSession();
    navigate('/login');
  }

  return (
    <div className="app-shell">
      <aside className="sidebar">
        <div>
          <p className="eyebrow">APCHS</p>
          <h1>Air Pollution Health Survey</h1>
        </div>
        <nav>
          <NavLink to="/dashboard">Dashboard</NavLink>
          <NavLink to="/surveys/new">Add Survey</NavLink>
          <NavLink to="/surveys">Records</NavLink>
          {session?.role === 'ADMIN' && <NavLink to="/users">Users</NavLink>}
          <NavLink to="/account">Account</NavLink>
        </nav>
        <div className="user-card">
          <strong>{session?.username}</strong>
          <span>{session?.role}</span>
          <button className="ghost-button" onClick={logout}>Sign out</button>
        </div>
      </aside>
      <main className="main-content">
        <Outlet />
      </main>
    </div>
  );
}
