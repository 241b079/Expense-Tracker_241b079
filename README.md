# Expense Tracker (241b079)

A comprehensive Android application designed to help users track their daily expenses, manage budgets by category, and visualize their spending habits through interactive charts.

## Features

- **Dashboard Visualization**: 
  - **Pie Chart**: View your spending distribution across different categories.
  - **Line Chart**: Track your recent expense trends over time.
- **Expense Management**:
  - Add, edit, and delete expenses.
  - Specify amount, description, date, category, and payment method for each transaction.
- **Category Management**:
  - Create custom categories with specific budget limits.
  - Assign colors to categories for better visual identification.
- **Local Storage**: All data is stored locally on the device using a Room database, ensuring privacy and offline availability.

## Tech Stack

- **Language**: Java / Kotlin
- **Database**: Room Persistence Library
- **UI Components**: Material Design, ConstraintLayout, Fragment-based navigation.
- **Charts**: [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart) for interactive data visualization.
- **Architecture**: MVVM (Model-View-ViewModel) with LiveData.

## Screenshots

*(Add your screenshots here)*

## Getting Started

### Prerequisites

- Android Studio Flamingo or newer.
- Android SDK level 34 or higher.
- Java Development Kit (JDK) 11 or higher.

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/ExpenseTracker241b079.git
   ```
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Run the application on an emulator or a physical Android device.

## Dependencies

- `androidx.appcompat:appcompat`
- `com.google.android.material:material`
- `androidx.room:room-runtime`
- `com.github.PhilJay:MPAndroidChart:v3.1.0`
- `com.google.code.gson:gson`
- `com.opencsv:opencsv`

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
