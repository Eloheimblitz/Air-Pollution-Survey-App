import { Link } from 'react-router-dom';

export default function NotFound() {
  return (
    <main className="not-found">
      <h1>Page not found</h1>
      <p className="muted">The survey page you opened is unavailable.</p>
      <Link className="button-link" to="/dashboard">Back to dashboard</Link>
    </main>
  );
}
