# Tenant Management System — Stage 2 (ViewModel)

**Branch:** `feature/viewmodel`  
**Waits for:** Stage 1  
**Reviewer:** Screens member (Stage 4)

## What this stage delivers

- `TenantViewModel` in `ui.viewmodel`
- LiveData: `tenants`, `tenantCountText`, `totalRentText`, `allTenantsText`
- `addTenant(...)` validation + update of LiveData
- Lifecycle logs
- ViewModel / LiveData libraries in `build.gradle.kts`

## Important

Screens still use the old in-memory repository directly.  
Connecting the Fragments to this ViewModel is **Stage 4**.

## Commit message suggestion

`Add TenantViewModel with LiveData and transformations`
