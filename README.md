#DayAreglar
DayAreglar App helps you plan your day well.


## Features
1. Events
    - Create Event
        - Click on the add button to do so.
        - Enter Event details,
        - Select date it is scheduled on
        - Save Event with a click on the 'DONE' button
    - Created event is saved and displayed on the Event List screen
        - Click on an item of the Event list to access details
        - Edit Event information if needed
        - Press on 'Go back arrow' navigates back to the list of event with the updated info
2. Notes
    - Create Note
        - Click on the add button to do so.
        - Enter note, no need for a button to save the information.
        - Click on 'Go back arrow' navigates back to the list of Notes with the new created note displayed.
        - If Note title was not entered, the first word of Note's content displays as the Title.
    - Created Note is saved and displayed on the Notes List screen
        - Click on a note displays its information and allow user to modify if needed
    - Delete a Note with a long click on the item

## Technology
### Notes
- List of Notes inside a ListAdapter
- Custom click listeners with an interface

### Events
- Navigation Safe Args
- List of events inside a Recyclerview Adapter

### Common
- RoomDatabase for persistence
    - Model Data Class
    - Dao interface
    - Repository
    - Database
- Launch operations with CoroutineScope to avoid blocking the main thread
- Navigation to handle smooth navigation between screens.
- MVVM (Model View View-Model) architecture
    - Fragments with nested navigation graphs
    - LiveData
    - Kotlin Coroutines Flow
- View binding
- Custom Adapters
- SimpleDateFormat to manage custom display of date on the different item and screens.


Logo from [Unsplash.com](https://unsplash.com/photos/YuQEEaNOgBA)