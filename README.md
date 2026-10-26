# AquaSmart

## Student Information

**Student Name:** Uwineza Delphine  
**Student ID:** 27897

## Project Name

**AquaSmart**

## Steps to Run the API

1. Make sure PostgreSQL is running.

2. Make sure the following database exists:

```text
aqua_smart_db
```

3. Open the AquaSmart project folder in a terminal.

4. Compile the project:

```powershell
.\mvnw.cmd clean compile
```

5. Run the Spring Boot application:

```powershell
.\mvnw.cmd spring-boot:run
```

6. The API runs on:

```text
http://localhost:8080
```

7. Use Postman to test the API endpoints.

---

## API Endpoints

### A2 - CRUD Operations

#### Water Sources

```text
POST   http://localhost:8080/api/water-sources/save
GET    http://localhost:8080/api/water-sources/all
GET    http://localhost:8080/api/water-sources/a6608d1f-d31d-49e2-b9c8-8daff4dbdbbd
PUT    http://localhost:8080/api/water-sources/update/{id}
DELETE http://localhost:8080/api/water-sources/delete/{id}
```

#### Channels

```text
POST   http://localhost:8080/api/channels/save
GET    http://localhost:8080/api/channels/all
GET    http://localhost:8080/api/channels/e62fbe01-6dea-49ad-9e4d-1ba5f78930a1
PUT    http://localhost:8080/api/channels/update/{id}
DELETE http://localhost:8080/api/channels/delete/{id}
```

#### Irrigation Valves

```text
POST   http://localhost:8080/api/irrigation-valves/save
GET    http://localhost:8080/api/irrigation-valves/all
GET    http://localhost:8080/api/irrigation-valves/8d0e22a1-c9e3-462c-834f-18c08089d726
PUT    http://localhost:8080/api/irrigation-valves/update/{id}
DELETE http://localhost:8080/api/irrigation-valves/delete/{id}
```

#### Farm Zones

```text
POST   http://localhost:8080/api/farm-zones/save
GET    http://localhost:8080/api/farm-zones/all
GET    http://localhost:8080/api/farm-zones/74efbd21-efab-40b5-b906-c5360a7eace4
PUT    http://localhost:8080/api/farm-zones/update/{id}
DELETE http://localhost:8080/api/farm-zones/delete/{id}
```

#### Irrigation Runs

```text
POST   http://localhost:8080/api/irrigation-runs/save
GET    http://localhost:8080/api/irrigation-runs/all
GET    http://localhost:8080/api/irrigation-runs/0a303b93-d2b2-4356-a8de-302b42b59bb9
PUT    http://localhost:8080/api/irrigation-runs/update/{id}
DELETE http://localhost:8080/api/irrigation-runs/delete/{id}
```

---

## Part B

### B1 - Farm Zones by Crop Type

```text
GET http://localhost:8080/api/farm-zones/by-crop-type?cropType=tomatoes
```

### B2 - Irrigation Runs by Status

```text
GET http://localhost:8080/api/irrigation-runs/by-status?status=QUEUED
```

### B3 - Irrigation Runs Between Dates

```text
GET http://localhost:8080/api/irrigation-runs/between?start=2026-10-01&end=2026-10-31
```

---

## Part C

### C1 - Irrigation Valves by Water Source

```text
GET http://localhost:8080/api/irrigation-valves/by-water-source?name=bore
```

### C2 - Free Channels

```text
GET http://localhost:8080/api/channels/free
```

### C3 - Unused Water Sources

```text
GET http://localhost:8080/api/water-sources/unused
```

### C4 - Farm Zones of an Irrigation Valve

```text
GET http://localhost:8080/api/farm-zones/of-irrigation-valve/8d0e22a1-c9e3-462c-834f-18c08089d726
```

---

## Part D

### D1 - Create an Irrigation Run

```text
POST http://localhost:8080/api/irrigation-runs/save
```

### D2 - Change Irrigation Run Status

```text
PATCH http://localhost:8080/api/irrigation-runs/{id}/status?status=RUNNING
```

### D3 - Add Water Source to Irrigation Valve

```text
PUT http://localhost:8080/api/irrigation-valves/{irrigationValveId}/water-sources/{waterSourceId}
```

### D4 - Change Irrigation Valve Channel

```text
PATCH http://localhost:8080/api/irrigation-valves/{irrigationValveId}/channel?channelNumber=4
```

---

## Part E

### E1 - Create Soil Reading

```text
POST http://localhost:8080/api/soil-readings/save?irrigationRunId=0a303b93-d2b2-4356-a8de-302b42b59bb9
```

### E2 - Soil Readings by Farm Zone

```text
GET http://localhost:8080/api/soil-readings/farm-zone/NF-A
```

### E3 - Delete Soil Reading

```text
DELETE http://localhost:8080/api/soil-readings/delete/{id}
```

---

## Part F

### F1 - Irrigation Run Statistics by Status

```text
GET http://localhost:8080/api/irrigation-runs/stats/by-status
```

### F2 - Cancel Irrigation Runs for a Valve on a Date

```text
PATCH http://localhost:8080/api/irrigation-runs/cancel-day?irrigationValveId=8d0e22a1-c9e3-462c-834f-18c08089d726&date=2026-10-20
```

---

## Bonus

### Bonus 1 - Dashboard Summary

```text
GET http://localhost:8080/api/dashboard/summary
```

### Bonus 2 - Pagination and Sorting

```text
GET http://localhost:8080/api/irrigation-runs/page?page=0&size=5&sort=runDate,desc
```