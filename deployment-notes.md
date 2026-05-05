# Deployment Notes

Use `PRODUCTION-CHECKLIST.md` as the step-by-step deployment checklist.

## Render Backend

Recommended: use the root `render.yaml` blueprint.

Manual setup:

```text
Root directory: backend
Runtime: Docker
Health check path: /api/health
```

Environment:

```text
DATABASE_URL=postgresql://USER:PASSWORD@HOST:PORT/DB_NAME
JWT_SECRET=generate_a_long_secure_secret
APP_FRONTEND_URL=https://YOUR_VERCEL_FRONTEND_URL
JPA_DDL_AUTO=update
SEED_DEMO_USERS=false
```

The backend accepts both `postgresql://...` and `jdbc:postgresql://...` database URLs.

## Railway Backend

Deploy the `backend` folder and attach Railway PostgreSQL. The `backend/railway.json` file tells Railway to use the backend Dockerfile.

Set the same environment variables as Render.

## Vercel Frontend

Use:

```text
Root directory: frontend
Build command: npm run build
Output directory: dist
```

Environment:

```text
VITE_API_BASE_URL=https://YOUR_BACKEND_URL/api
```

The frontend includes `frontend/vercel.json` so direct page refreshes work for React Router routes.

## CORS Checklist

- Backend `APP_FRONTEND_URL` must match the Vercel origin exactly.
- Frontend `VITE_API_BASE_URL` must end with `/api`.
- Redeploy backend after CORS changes.
- Redeploy frontend after API URL changes.
