# Trail_Tranquil

An application designed in Java, version 17, and intended for Android phone systems. It is developed on API 31 for Android, utilizing the osmdroid library, which serves as a complete/free (almost) replacement for the MapView class (v1 API) for Android. The application includes a modular tile provider system with support for multiple online and offline tile sources, and overlay support with built-in overlays for laying out icons, tracking location, and drawing shapes.

## Features
- **Display Maps:** Show maps in a typical way.
- **Location Determination:** Use either Wi-Fi or GPS sensors to determine the user's location, with the ability to switch between them.
- **Tourist Area Suggestions:** Display some proposed tourist areas with an overview for the user with feature search .
- **Offline Functionality:** Operates without an internet connection, leveraging the osmdroid library to determine location.
- **Project Story Interface:** A special interface for displaying the project's story.

## Disadvantages
- **Design Issues:** Some design problems need improvement.
- **Lack of Cloud Storage:** All data shown to the user is stored locally as text.
- **No Path Drawing Support:** The application does not support the ability to draw paths for navigation.

## Planned Improvements
- **Design Enhancements:** Address the existing design issues.
- **Cloud Storage Integration:** Implement cloud storage to enhance data management.
- **Path Drawing:** Add functionality for users to draw navigation paths.
- **User Authentication:** Introduce login features and track user data.
- **Dynamic Content Updates:** Incorporate features related to dynamic content updating.

## Open Source Contributions
This project is open to contributions. It was a preliminary project, and now it is offered as open-source code for everyone to foster growth in the developer environment. The goal is to reduce control over user data by other companies and potentially become a strong competitor to other similar programs.

## Getting Started
### Prerequisites
- Java 17+
- Android Studio
- API 31 for Android
- osmdroid library

### Installation
1. Clone the repository:
    ```bash
    git clone https://github.com/Hu8MA/Trail_Tranquil.git
    ```
2. Open the project in Android Studio.
3. Build and run the project on your Android device or emulator.

### Usage
- Navigate through the app to explore maps and tourist areas.
- Switch between Wi-Fi and GPS for location determination.
- Use the offline functionality to view maps without an internet connection.

## Contributing
We welcome contributions to enhance this project. Please follow these steps to contribute:

1. Fork the repository.
2. Create a new branch:
    ```bash
    git checkout -b feature-name
    ```
3. Make your changes and commit them:
    ```bash
    git commit -m 'Add some feature'
    ```
4. Push to the branch:
    ```bash
    git push origin feature-name
    ```
5. Open a pull request.

Please ensure your code adheres to our coding standards and includes appropriate tests.

## License
This project is licensed under the MIT License  

## Acknowledgments
This project was developed by Hussein Mahdi .  the Idea from (Duha Jabbar Hussein and Israa Rafea Younis) in 2023-2024. Special thanks to all contributors and the open-source community for their support and collaboration.
 
