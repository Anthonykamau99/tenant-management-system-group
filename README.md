# Tenant Management System — Stage 1 (Lead)

**Branch:** `main` (Lead pushes directly once)

## What this stage delivers

- Shared repository on GitHub
- Code organised into **ui** and **data** packages
- Working app with Login, Register, Dashboard, Add Tenant, View Tenants
- In-memory `TenantRepository` (data is lost when the app closes)
- Navigation drawer + Navigation Component

## Package structure

```
com.example.tenantmanagementsystem
├── ui
│   ├── auth          LoginActivity, RegisterActivity
│   ├── main          MainActivity
│   ├── dashboard     DashboardFragment
│   └── tenants       AddTenantFragment, ViewTenantsFragment, TenantAdapter
└── data
    ├── model         Tenant
    └── repository    TenantRepository (in-memory)
```

## Next stages

- Stage 2 (ViewModel) and Stage 3 (Room) can start in parallel after this is merged.
