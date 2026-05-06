# Production Deployment Checklist

## Before Deploy

- Create a GitHub repository and push this project.
- Decide backend platform: Render or Railway.
- Decide database platform: Render PostgreSQL or Railway PostgreSQL.
- Use a strong `JWT_SECRET`, at least 64 random characters.
- Set `SEED_DEMO_USERS=false` for production after creating a real admin account strategy.
- Keep local `.env` files out of Git.

## Backend Environment

Set these on Render or Railway:

```text
PORT=8080
DATABASE_URL=postgresql://USER:PASSWORD@HOST:PORT/DB_NAME
JWT_SECRET=your_long_random_secret
APP_FRONTEND_URL=https://your-vercel-app.vercel.app
JPA_DDL_AUTO=update
SEED_DEMO_USERS=false
```

`DATABASE_URL` may be either `postgresql://...` or `jdbc:postgresql://...`. The backend normalizes hosted PostgreSQL URLs at startup.

## Frontend Environment

Set this on Vercel:

```text
VITE_API_BASE_URL=https://your-backend-url/api
```

## Render Option

- Connect the GitHub repo to Render.
- Use the root `render.yaml` blueprint, or create services manually.
- Backend root directory: `backend`
- Runtime: Docker
- Database: Render PostgreSQL
- After frontend deploys, set backend `APP_FRONTEND_URL` to the Vercel URL and redeploy.

## Railway Option

- Create a Railway project from GitHub.
- Set service root or Dockerfile path to `backend/Dockerfile`.
- Add Railway PostgreSQL.
- Set backend environment variables.
- After frontend deploys, set backend `APP_FRONTEND_URL` to the Vercel URL and redeploy.

## Vercel

- Import the GitHub repo.
- Root directory: `frontend`
- Build command: `npm run build`
- Output directory: `dist`
- Set `VITE_API_BASE_URL`.

## After Deploy

- Open the frontend URL.
- Login with the initial admin strategy you chose.
- Create real admin and surveyor accounts.
- Change default/demo passwords if demo seeding was enabled temporarily.
- Submit one test survey.
- Test one offline survey by disconnecting from the backend, saving a survey, reconnecting, and using Pending Sync.
- Confirm dashboard updates.
- Test CSV and Excel exports.
- Test surveyor role restrictions.
- Take a database backup before real field collection starts.

## Ongoing Changes

- Make code changes locally.
- Test locally.
- Push to GitHub.
- Vercel and Render/Railway redeploy from GitHub.
- Back up PostgreSQL before destructive schema changes.
