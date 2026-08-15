# Insurance Policy Tracker

Microservices MVP scaffold for LIC agents and independent insurance agents.

Services (default ports):
- auth-service (8081)
- lead-service (8082)
- policy-service (8083)
- renewal-service (8084)
- claims-service (8085)
- document-service (8086)
- customer-service (8087)
- api-gateway (8080)

Run with Docker Compose:

```bash
docker-compose up --build
```

Next steps:
- Implement auth-service (JWT) and seed data
- Implement lead-service and policy-service
- Implement frontend (React + Tailwind)
