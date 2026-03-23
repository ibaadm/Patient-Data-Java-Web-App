# Patient Manager

A Java web application for viewing and managing patient data, built as part of the COMP0004 Object-Oriented Programming coursework at UCL. It runs an embedded Tomcat server and follows the MVC pattern using Java Servlets and JSPs.

## Features

- Browse a full list of patients and view all details for any individual patient
- Search across every field in the dataset by one or more keywords
- Add, edit, and delete patient records, with changes saved back to the CSV file
- Statistics page showing total patients, deceased count, oldest patient, gender breakdown, and patients per city
- Charts page with age distribution, gender breakdown, and top 10 cities visualised using Chart.js
- Export the full dataset to a JSON file via the web interface

## Tech Stack

- Java 25
- Apache Tomcat 11 (embedded)
- Jakarta Servlets and JSPs
- Apache Commons CSV
- Jackson Databind
- Maven

## Getting Started

**Prerequisites:** Java 25 and Maven 3.9+

**Run:**

```bash
mvn compile exec:java
```

Then open `http://localhost:8080` in your browser.

## Project Structure

```
src/main/java/uk/ac/ucl/
  main/        - embedded Tomcat entry point
  model/       - Column, DataFrame, DataLoader, JSONWriter, Model, ModelFactory
  servlets/    - one servlet per feature (list, search, stats, charts, CRUD, JSON export)

src/main/webapp/
  *.jsp        - views for each page
  *.html       - static pages (home, search form)
  styles.css

data/
  patients100.csv   - sample dataset (100 patients)
```

## Data

The app loads patient data from `data/patients100.csv` on startup. The CSV format follows the synthetic dataset from [UCLComputerScience/COMP0004Data](https://github.com/UCLComputerScience/COMP0004Data), with columns including ID, name, address, gender, race, birthdate, and more.