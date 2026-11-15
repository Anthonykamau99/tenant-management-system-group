# Tenant Management System

Lab 8 — Architecture, Git and App Distribution

## Architecture

```
com.example.tenantmanagementsystem
├── ui                          ← UI LAYER
│   ├── auth                    LoginActivity, RegisterActivity
│   ├── main                    MainActivity (NavHost + Drawer)
│   ├── dashboard               DashboardFragment
│   ├── tenants                 AddTenantFragment, ViewTenantsFragment, TenantAdapter
│   └── viewmodel               TenantViewModel (LiveData + Room)
└── data                        ← DATA LAYER
    ├── model                   Tenant (@Entity)
    ├── local                   TenantDao, TenantDatabase
    └── repository              TenantRepository
```

**Data flow:** UI → ViewModel → Repository → Room


## Features

* Register / Login
* Dashboard with live tenant count and total rent
* Add Tenant (validated)
* View Tenants list (ordered by name)
* Call tenant
* Delete tenant
* Navigation drawer
* Data survives app close (Room)

