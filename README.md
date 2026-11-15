# Tenant Management System

Android app for **BBT 3.2 Mobile Application Development**  
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

UI may use data; data must never use UI.

## Features

* Register / Login
* Dashboard with live tenant count and total rent
* Add Tenant (validated)
* View Tenants list (ordered by name)
* Call tenant
* Delete tenant
* Navigation drawer
* Data survives app close (Room)

## How to run

1. Open in Android Studio
2. Sync Gradle
3. Run on emulator or device (API 26+)

## Building a signed APK (Stage 6)

1. **Build → Generate Signed Bundle / APK → APK**
2. Create a new keystore (never commit the `.jks` file)
3. Build release APK
4. Publish on GitHub Releases as **v1.0** with the APK attached

## 

## Tech stack

* Kotlin
* AndroidX (Navigation, Lifecycle, Room)
* ViewBinding
* KSP
* Min SDK 26, Target SDK 35

