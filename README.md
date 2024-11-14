# AI Image Generator
Authors: `Lucas Lowe, Darius Long, Joshua Smith, Colson Dick`

This Project aims to take in characteristics given by the user and generates an image using an
AI image generator API.

## Info

### JavaFX warning suppressions
Warnings were suppressed for anything using the `@FXML` decorator in the view controller classes
(ex. `GeneratorViewController`). There were warnings about the relevant elements and method that
were saying that they weren't being utilized or assigned, even though they were in the background 
through JavaFX by rendering a fxml file.

### Utility warning suppressions
There are two warnings about the parameters of a utility function always being a certain value,
and prompts the user to make the values local to the function and get rid of the parameters. 
These warnings should be disregarded as this function should be used again in the future.

### Entry Points
There is one entry point to our application, and that would be the `Application` class. 
It needs to be run by the gradle menu in order for it to run properly.

## Project Structure

### View Layer
Contains classes responsible for the user interface and view management.

- `ViewUtilities`
  - The `ViewUtilities` class provides utility methods for displaying different types of alerts
  and input dialogs in a JavaFX application. It includes methods for showing error messages, 
  confirmation prompts, and retrieving user input through a text input dialog.
- `SceneController`
  - The `SceneController` class manages scene transitions, allowing for the
  switching between different views like the generator and manager views. It uses `FXMLLoader` to load 
  FXML files and pass necessary data to the controllers before displaying the new scenes.
- `ManagerViewController`
  - The `ManagerViewController` class manages the user interface for the manager view, allowing
  users to switch to the generator view and load saved image items into the view. It uses `ImageLoader` to load
  saved image data, and `ImageItemFactory` to create image item views.
- `GeneratorViewController`
  - The `GeneratorViewController` class handles the generation, display, and saving of AI-generated images based
  on user-input characteristics for a game or non-game character. It manages interactions with text fields, checkboxes,
  and buttons, and uses `ImageManager` to generate and save images, while also utilizing `SaveStatusManager` to track and
  manage file saving statuses.
- `ImageItemController`
  - The `ImageItemController` class handles interactions with image items in the user interface, allowing users to double-click
  on an image to open it in the generator view. It sets and displays the image and filename, and uses the `SceneController` to switch
  to the generator view with the associated image and characteristics.
- `ImageItemFactory`
  - The `ImageItemFactory` class creates and loads an image item component (represented by a `GridPane`) from an FXML file, setting its
  properties such as filename, filepath, and image data. It also associates the `ImageItemController` with the scene controller and provides
  the necessary image and characteristics for display.
- `SaveStatusManager`
  - The `SaveStatusManager` class manages the save state of a file, tracking whether it has been saved and handling the filename. It provides 
  methods to check the save status, prompt for a new filename, confirm unsaved changes, and update the window title based on whether the file is saved.

### Model Layer
Contains classes for data management, validation, and configuration.

- Config
  - The `Config` class handles loading application settings from a properties file 
  and provides utility methods to ensure a specified save directory exists. It includes 
  methods for accessing the save directory path and resetting properties if needed.
- FileFetcher
  - The `FileFetcher` class retrieves file paths from a specified directory, allowing access 
  to all regular files within it. It verifies that the provided path is a directory and returns 
  an ArrayList of file paths; if the path is invalid, it throws an exception.
- FileNameValidator
  - The `FileNameValidator` class checks if a file name is valid by ensuring it matches a specific
  pattern allowing letters, digits, spaces, underscores, and hyphens, and that it does not exceed 255 
  characters. The `isValidFileName` method trims the file name, verifies it is non-empty, and checks it 
  against these conditions.
- Characteristics
  - The `Characteristics` record represents various attributes of a character, 
  including physical traits and contextual details like art style and character type.
  It can be initialized directly or via a HashMap and supports serialization for data storage
  and transfer.
- CharacteristicsManager
  - The `CharacteristicsManager` class manages the creation of `Characteristics` objects by validating that
  at least one characteristic is provided. Its `createCharacteristics` method checks for empty fields in the 
  input map and constructs a `Characteristics` instance if validation passes.
- ImageWithCharacteristics
  - The `ImageWithCharacteristics` class is a wrapper of a Characteristics instance and generated image, 
  allowing for easy saving and loading.
- ImageFetcher
  - The `ImageFetcher` class retrieves images based on a prompt by creating a URL using the Pollinations API. 
  Its `fetchImage` method opens a connection to the URL and downloads the image data as a byte array.
- ImageManager
  - The `ImageManager` class manages image generation and saving by coordinating `ImageFetcher`, `CharacteristicsManager`,
  and `ImageSaver` instances. It generates an image based on character attributes, then allows saving the image and its characteristics
  to a specified file if generation was successful.
- ImageLoader
  - The `ImageLoader` class is responsible for loading serialized image data along with associated characteristics from files. Its methods
  deserialize individual image files and load multiple image items from a specified directory into a `HashMap`.
- ImageSaver
  - The `ImageSaver` class handles saving images and their associated characteristics to a file. It serializes an `ImageWithCharacteristics` 
  object and writes it to a specified file path, ensuring the file name is valid before saving.
- PromptBuilder
  - The `PromptBuilder` class constructs a detailed descriptive prompt based on a character's characteristics, such as physical traits and 
  game-related details. It dynamically assembles a string that includes various attributes like age, sex, and race, and if applicable, art style,
  character type, and game type. This string is to be fed to the Pollinations API.

