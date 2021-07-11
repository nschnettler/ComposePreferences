[![Release](https://jitpack.io/v/Sh4dowSoul/ComposePreferences.svg)](https://jitpack.io/#Sh4dowSoul/ComposePreferences)

# ComposePreferences

Compose Preferences is a library which makes it easy to add preference functionality to your Compose app.

It provides an easy to use PreferenceScreen Composable, which displays a list of PreferenceItems and PreferenceGroups. The Preferences are saved in a DataStore and managed by a DataStoreManager,
which makes it easy to access the preferences on the preference screen or from anywhere else (other screens, viewModels, services, ...).

## Supported Preference Items
- Preference Group: A container for multiple PreferenceItems
- TextPreference: A basic PreferenceItem that only displays text.
- SwitchPreference: A PreferenceItem that provides a two-state toggleable option.
- ListPreference: A PreferenceItem that displays a list of entries as a dialog. Only one entry can be selected at any given time.
- MultiSelectListPreference: A PreferenceItem that displays a list of entries as a dialog. Multiple entries can be selected at the same time.
- SeekBarPreference: A PreferenceItem that displays a seekBar and the currently selected value.
- DropDownPreference: A PreferenceItem that displays a list of entries as a dropdown menu. Only one entry can be selected at any given time.

## Usage
To use ComposePreferences, create a DataStore and simply add a PreferenceScreen to your App:

``` kotlin
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

setContent {
  MaterialTheme {
    PreferenceScreen(
      items = listOf(
        // PreferenceItems and PreferenceGroups
      ),
      dataStore = this.dataStore,
      statusBarPadding = true // When going edgeToEdge
    )
  }
}
```

You can also directly supply a DataStoreManager instead of the DataStore to the PreferenceScreen Composable.

When creating the PreferenceItems you have to supply a PreferenceRequest object. A PreferenceRequest wrapps a DataStore PreferenceKey and a default value and makes it easy to querry
the selection the user made outside of the PreferenceScreen. 

``` kotlin
val waterRequest = PreferenceRequest<Boolean>(
  key = booleanPreferencesKey("pref_switch"), 
  defaultValue = true
)

// PreferenceScreen
PreferenceItem.SwitchPreference(
  request = ,
  title = "Switch Preference",
  summary = "A preference with a switch.",
  singleLineTitle = true,
  icon = {
    Icon(
      imageVector = Icons.Outlined.Delete,
      contentDescription = null,
      modifier = Modifier.padding(8.dp)
    )
  },
)

// Some service
lifecycleScope.launch {
  dataStoreManager.getPreferenceFlow(waterRequest).collect { waterEnabled ->
    // Do something when the value changes
    delay(100)
    dataStoreManager.editPreference(waterRequest.key, !waterEnabled)
  }
}
```

### Read & Edit Preferences
Reading and editing Preferences on the PreferenceScreen is managed by ComposePreferences automatically. To access Preferences outside of the PreferenceScreen, you can query the DataStore
you supplied to the PreferenceScreen Composable directly or use the DataStoreManager provided by ComposePreferences:
#### Read a preference
You can read a preference by using DataStoreManager's getPreference() and getPreferenceFlow() methods. Both methods require a PreferenceRequest parameter and either return the value for the key 
defined in the request or the default value, if the key is not present.
``` kotlin
val dataStoreManager = DataStoreManager(this.dataStore)

val waterEnabledRequest: PreferenceRequest<Boolean> = PreferenceRequest(
  key = booleanPreferencesKey("water_enabled"),
  defaultValue = true,
)

// Synchronously (suspending)
val waterEnabled: Boolean = dataStoreManager.getPreference(waterEnabledRequest)

// Asynchronously
val waterEnabledFlow: Flow<Boolean> = dataStoreManager.getPreferenceFlow(waterEnabledRequest)
```

#### Edit a preference
You can edit a preference using DataStoreManager's editPreference() method. It is suspending and takes a preferenceKey and a newValue parameter and updates the DataStore with the newValue.
``` kotlin
dataStoreManager.editPreference(waterEnabledRequest.key, false)

```

## Download

```kotlin
repositories {
     maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("com.github.Sh4dowSoul.ComposePreferences:compose-preferences:<version>")
    
    // Optional - Easy access to preferences from modules without compose dependency
    implementation("com.github.Sh4dowSoul.ComposePreferences:datastore-manager:<version>") 
}
```
